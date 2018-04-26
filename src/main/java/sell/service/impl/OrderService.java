package sell.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sell.dao.DO.*;
import sell.dao.Enum.SexEnum;
import sell.dao.Form.orderForm;
import sell.dao.VO.*;
import sell.dao.result.BizResult;
import sell.pojo.*;
import sell.service.IOrderService;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class OrderService implements IOrderService {

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderDetailMapper detailMapper;
    @Autowired
    private FightInfoMapper fightInfoMapper;
    @Autowired
    private RerationInfoMapper rerationInfoMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private CompanyInfoMapper companyInfoMapper;


    /***
     * 处理下单
     * @param orderForm
     * @param uid
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public BizResult OrderTicket(orderForm orderForm, Long uid) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");

        Date date = new Date();
        //产生1000到9999之间的随机数
        Double rad = Math.ceil(Math.random() * 8999) + 1000;
        String orderNo = "No:" + sdf.format(date) + rad.intValue();

        Order order = new Order();
        order.setuId(uid);
        order.setOrderNo(orderNo);
        FightInfo fightInfo = fightInfoMapper.selectByPrimaryKey(orderForm.getFightId());
        Long fightNum = fightInfo.getFightNum();
        if (fightNum - orderForm.getuId().size() < 0) {
            return BizResult.Create(-2, "剩余票数不足");
        }
        if (orderMapper.insert(order) > 0) {
            for (Long UserId : orderForm.getuId()) {
                OrderDetail detail = new OrderDetail();
                detail.setFightId(orderForm.getFightId());
                detail.setOrderId(order.getOrderId());
                detail.setuId(UserId);

                if (detailMapper.insert(detail) == 0) {
                    return BizResult.Create(-1, "订单详情插入失败");
                }
            }
            fightInfo.setFightNum(fightNum - orderForm.getuId().size());
            if (fightInfoMapper.updateByPrimaryKey(fightInfo) < 0) {
                return BizResult.Create(-4, "扣票失败");
            }
            ;

            return BizResult.Create();

        } else {
            return BizResult.Create(-3, "订单插入失败");
        }


    }

    /**
     * 获取对应fightId的航班信息
     *
     * @param fightId
     * @param user
     * @return
     */
    @Override
    public OrderVO getOrderVO(Long fightId, User user) {
        OrderVO orderVO = new OrderVO();
        FightInfo fightInfo = fightInfoMapper.selectByPrimaryKey(fightId);
        orderVO.setFightId(fightInfo.getFightId());
        orderVO.setFightNo(fightInfo.getFightNo());
        orderVO.setFightPay(fightInfo.getFightPay());
        orderVO.setIdentity(user.getIdentity());
        orderVO.setPhone(user.getPhone());
        orderVO.setSex(SexEnum.getMsgByCode(user.getSex().intValue()));
        orderVO.setUser(user);
        List<RerationInfo> rerationInfoList = rerationInfoMapper.selectAllByUID(user.getuId());

        if (rerationInfoList != null && rerationInfoList.size() != 0) {
            List<Long> relationUIDs = new ArrayList<>();
            rerationInfoList.forEach(x -> relationUIDs.add(x.getrUId()));
            List<User> userList = userMapper.selectAllByUIDS(relationUIDs);
            orderVO.setUsers(userList);
        }


        return orderVO;
    }

    /***
     * 获取该用户下的所有订单
     * @param user
     * @return
     */
    @Override
    public List<FightOrderVO> getFightOrder(User user) {

        //查询该用户下的所有订单
        List<Order> orders = orderMapper.selectAllByUID(user.getuId());
        if (orders == null || orders.size() == 0) {
            return null;
        }


        //查询该用户下的所有订单明细
        List<Long> orderIds = new ArrayList<>();
        orders.forEach(x -> orderIds.add(x.getOrderId()));
        List<OrderDetail> orderDetails = detailMapper.selectAllByIDs(orderIds);
        if (orderDetails == null || orderDetails.size() == 0) {
            return null;
        }


        //查询该用户下所有订单的乘机人
        List<Long> uids = new ArrayList<>();
        orderDetails.forEach(x -> uids.add(x.getuId()));
        List<User> userList = userMapper.selectAllByUIDS(uids);

        Map<Long, User> uIdAndUser = new HashMap<>();
        userList.forEach(
                x -> uIdAndUser.put(x.getuId(), x)
        );


        //查询该用户下所有订单的航班
        List<Long> fightIds = new ArrayList<>();
        orderDetails.forEach(x -> fightIds.add(x.getFightId()));
        List<FightInfo> fightInfos = fightInfoMapper.selectAllByFids(fightIds);
        Map<Long, FightInfo> IdAndfightInfo = new HashMap<>();
        fightInfos.forEach(
                x -> IdAndfightInfo.put(x.getFightId(), x)
        );


        //查询所有航空公司
        List<CompanyInfo> companyInfos = companyInfoMapper.selectAll();
        Map<Long, String> IdAndCompanyInfo = new HashMap<>();
        companyInfos.forEach(x -> IdAndCompanyInfo.put(x.getCompanyId(), x.getCompanyName()));


        //订单详情信息填充
        List<detatilVO> detatilVOS = new ArrayList<>();
        for (OrderDetail orderDetail : orderDetails) {
            detatilVO detatilVO = new detatilVO();

            FightInfo fightInfo = IdAndfightInfo.get(orderDetail.getFightId());
            if (fightInfo == null) {
                continue;
            }
            detatilVO.setDeparture(fightInfo.getDeparture());
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");

            detatilVO.setDepartureTime(sdf.format(fightInfo.getDepartureTime()));
            detatilVO.setDestination(fightInfo.getDestination());
            detatilVO.setDestinationTime(sdf.format(fightInfo.getDestinationTime()));
            detatilVO.setDetatilId(orderDetail.getDetailId());
            detatilVO.setFightCompany(IdAndCompanyInfo.get(fightInfo.getFightCompanyId()));
            detatilVO.setFightPay(fightInfo.getFightPay());
            detatilVO.setFightType(fightInfo.getFightType());
            detatilVO.setUserName(uIdAndUser.get(orderDetail.getuId()).getUserName());
            detatilVO.setOrderId(orderDetail.getOrderId());
            detatilVO.setFightNo(fightInfo.getFightNo());
            detatilVOS.add(detatilVO);
        }


        Map<Long, List<detatilVO>> orderIdAnddetatilVOList = new HashMap<>();
        //筛选分组
        detatilVOS.forEach(x -> {
            if (orderIdAnddetatilVOList.get(x.getOrderId()) == null) {
                List<detatilVO> detatilVOList = new ArrayList<>();
                detatilVOList.add(x);
                orderIdAnddetatilVOList.put(x.getOrderId(), detatilVOList);
            } else {
                orderIdAnddetatilVOList.get(x.getOrderId()).add(x);
            }

        });

        List<FightOrderVO> fightOrderVOS = new ArrayList<>();

        for (Order order : orders) {
            FightOrderVO fightOrderVO = new FightOrderVO();
            List<detatilVO> detatilVOList = orderIdAnddetatilVOList.get(order.getOrderId());
            if (detatilVOList == null) {
                continue;
            }
            String fightNo = detatilVOList.get(0).getFightNo();
            fightOrderVO.setFightNo(fightNo);
            fightOrderVO.setOrderNo(order.getOrderNo());
            fightOrderVO.setDetatilVOS(detatilVOList);
            fightOrderVOS.add(fightOrderVO);
        }

        return fightOrderVOS;
    }

    /**
     * 获取所有的订单信息
     *
     * @return
     */
    @Override
    public List<FightOrderVO> getFightOrder() {

        List<User> userList = userMapper.selectAll();
        List<FightOrderVO> fightOrderVOS = new ArrayList<>();
        for (User user : userList) {
            List<FightOrderVO> fightOrderVOList = getFightOrder(user);
            if (fightOrderVOList != null && fightOrderVOList.size() != 0) {
                fightOrderVOS.addAll(fightOrderVOList);
            }
        }

        return fightOrderVOS;
    }

    @Override
    public List<FightOrderVO> getFightOrder(String orderNo) {
        Order order = orderMapper.selectByNo(orderNo);
        if (order==null){
            return null;
        }
        User user = userMapper.selectByPrimaryKey(order.getuId());
        List<FightOrderVO> fightOrderVOList = getFightOrder(user);

        return fightOrderVOList;
    }


    /**
     * 退票
     *
     * @param detailId
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public BizResult refundTickt(Long detailId) {


        OrderDetail detatil = detailMapper.selectByPrimaryKey(detailId);

        FightInfo fightInfo = fightInfoMapper.selectByPrimaryKey(detatil.getFightId());

        Long fightNum = fightInfo.getFightNum() + 1;
        fightInfo.setFightNum(fightNum);

        if (fightInfoMapper.updateByPrimaryKey(fightInfo) == 0) {
            return BizResult.Create(-2, "更新票数失败，请重试");
        }
        ;

        if (detailMapper.deleteByPrimaryKey(detailId) > 0) {


            //查询该订单id还有没有数据订单详情表，没有的话删除掉该订单
            List<OrderDetail> orderDetails = detailMapper.selectByOrderId(detatil.getOrderId());
            if (orderDetails.size() == 0) {
                orderMapper.deleteByPrimaryKey(detatil.getOrderId());
            }

            return BizResult.Create();
        } else {
            return BizResult.Create(-1, "删除订单失败，请重试");
        }

    }


    /**
     * 查询所有起始地 目的地 公司ID
     *
     * @return
     */
    @Override
    public OrderQueryVO queryOrder() {
        OrderQueryVO orderQueryVO = new OrderQueryVO();
        List<FightInfo> fightInfos = fightInfoMapper.selectAll();
        List<CompanyInfo> companyInfos = companyInfoMapper.selectAll();

        orderQueryVO.setCompanyInfoList(companyInfos);

        orderQueryVO.setFightInfos(fightInfos);

        return orderQueryVO;
    }

    /**
     * 按照条件获取航班信息
     *
     * @param Departure
     * @param Destination
     * @param CompanyId
     * @return
     */
    @Override
    public List<FightInfoVO> queryFightInfo(String Departure, String Destination, Long CompanyId) {
        List<FightInfo> fightInfos = fightInfoMapper.queryFightInfo(Departure, Destination, CompanyId);
        List<CompanyInfo> companyInfos = companyInfoMapper.selectAll();

        Map<Long, String> IdAndName = new HashMap<>();
        companyInfos.forEach(x -> {
            IdAndName.put(x.getCompanyId(), x.getCompanyName());
        });

        List<FightInfoVO> fightInfoVOS = new ArrayList<>();
        for (FightInfo fightInfo : fightInfos) {
            FightInfoVO fightInfoVO = new FightInfoVO();
            fightInfoVO.setFightId(fightInfo.getFightId());
            fightInfoVO.setFightNo(fightInfo.getFightNo());
            fightInfoVO.setDeparture(fightInfo.getDeparture());
            fightInfoVO.setDestination(fightInfo.getDestination());
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:ss");
            fightInfoVO.setDestinationTime(simpleDateFormat.format(fightInfo.getDestinationTime()));
            fightInfoVO.setDepartureTime(simpleDateFormat.format(fightInfo.getDepartureTime()));
            fightInfoVO.setFightPay(fightInfo.getFightPay());
            fightInfoVO.setFightCompany(IdAndName.get(fightInfo.getFightCompanyId()));
            fightInfoVOS.add(fightInfoVO);
        }


        return fightInfoVOS;
    }

    /**
     * 获取改签的航班列表
     *
     * @param detailId
     * @return
     */
    @Override
    public List<FightInfoVO> changeTicket(Long detailId) {

        OrderDetail orderDetail = detailMapper.selectByPrimaryKey(detailId);
        FightInfo fightInfo = fightInfoMapper.selectByPrimaryKey(orderDetail.getFightId());
        List<FightInfoVO> fightInfoVOList = queryFightInfo(fightInfo.getDeparture(), fightInfo.getDestination(), fightInfo.getFightCompanyId());


        return fightInfoVOList;
    }

    /**
     * 进行改签操作
     *
     * @param fight
     * @return
     */
    @Override
    public BizResult dochangeOrder(Long fight, Long detailId, Long Uid) {

        OrderDetail orderDetail = detailMapper.selectByPrimaryKey(detailId);

        //先退票
        BizResult bizResult = refundTickt(detailId);
        if (bizResult.getSuccess()) {
            orderForm orderForm = new orderForm();
            orderForm.setFightId(fight);
            List<Long> uids = new ArrayList<>();
            uids.add(orderDetail.getuId());
            orderForm.setuId(uids);


            //退票成功后买新票
            BizResult bz = OrderTicket(orderForm, Uid);

            return bz;
        }
        return bizResult;
    }
}
