package sell.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sell.dao.DO.*;
import sell.dao.Enum.SexEnum;
import sell.dao.Form.orderForm;
import sell.dao.VO.FightOrderVO;
import sell.dao.VO.OrderVO;
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


    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean OrderTicket(orderForm orderForm, Long uid) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");

        Date date = new Date();
        //产生1000到9999之间的随机数
        Double rad = Math.ceil(Math.random() * 8999) + 1000;
        String orderNo = "No:" + sdf.format(date) + rad.intValue();

        Order order = new Order();
        order.setuId(uid);
        order.setOrderNo(orderNo);
        if (orderMapper.insert(order) > 0) {
            for (Long UserId : orderForm.getuId()) {
                OrderDetail detail = new OrderDetail();
                detail.setFightId(orderForm.getFightId());
                detail.setOrderId(order.getOrderId());
                detail.setuId(UserId);

                if (detailMapper.insert(detail) == 0) {
                    return false;
                }

            }
            return true;

        } else {
            return false;
        }


    }

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

        if (rerationInfoList!=null&&rerationInfoList.size()!=0){
            List<Long> relationUIDs = new ArrayList<>();
            rerationInfoList.forEach(x -> relationUIDs.add(x.getrUId()));
            List<User> userList=userMapper.selectAllByUIDS(relationUIDs);
            orderVO.setUsers(userList);
        }


        return orderVO;
    }

    @Override
    public List<FightOrderVO> getFightOrder(User user) {

        //查询该用户下的所有订单
        List<Order> orders=orderMapper.selectAllByUID(user.getuId());
        if (orders==null||orders.size()==0){
            return  null;
        }
        Map<Long,Order> OrderIdAndOrders=new HashMap<>();
        orders.forEach(
                x->OrderIdAndOrders.put(x.getOrderId(),x)
                );


        //查询该用户下的所有订单明细
        List<Long> orderIds=new ArrayList<>(OrderIdAndOrders.keySet());
        List<OrderDetail> orderDetails=detailMapper.selectAllByIDs(orderIds);
        if (orderDetails==null||orderDetails.size()==0){
            return  null;
         }


         //查询该用户下所有订单的乘机人
         List<Long> uids=new ArrayList<>();
         orderDetails.forEach(x->uids.add(x.getuId()));
         List<User> userList=userMapper.selectAllByUIDS(uids);

        Map<Long,User> uIdAndUser=new HashMap<>();
        userList.forEach(
                x->uIdAndUser.put(x.getuId(),x)
        );


        //查询该用户下所有订单的航班
        List<Long> fightIds=new ArrayList<>();
        orderDetails.forEach(x->fightIds.add(x.getFightId()));
        List<FightInfo> fightInfos=fightInfoMapper.selectAllByFids(uids);

        Map<Long,FightInfo> IdAndfightInfo=new HashMap<>();
        fightInfos.forEach(
                x->IdAndfightInfo.put(x.getFightId(),x)
        );

        //查询所有航空公司
       List<CompanyInfo> companyInfos= companyInfoMapper.selectAll();
        Map<Long,String> IdAndCompanyInfo=new HashMap<>();
        companyInfos.forEach(x->IdAndCompanyInfo.put(x.getCompanyId(),x.getCompanyName()));


        List<FightOrderVO> fightOrderVOS=new ArrayList<>();

        for (OrderDetail orderDetail : orderDetails) {
            FightOrderVO fightOrderVO=new FightOrderVO();

            fightOrderVO.setOrderNo(OrderIdAndOrders.get(orderDetail.getOrderId()).getOrderNo());
            fightOrderVO.setUserName(uIdAndUser.get(orderDetail.getuId()).getUserName());
            fightOrderVO.setFightId( orderDetail.getFightId());
            FightInfo fightInfo=IdAndfightInfo.get(orderDetail.getFightId());
            fightOrderVO.setDeparture(fightInfo.getDeparture());

            SimpleDateFormat sdf=new SimpleDateFormat("YYYY-MM-dd:HHMM");
            fightOrderVO.setDepartureTime(sdf.format(fightInfo.getDepartureTime()));

            fightOrderVO.setDestination(fightInfo.getDestination());

            fightOrderVO.setDestinationTime(sdf.format(fightInfo.getDestinationTime()));

            fightOrderVO.setFightCompany(IdAndCompanyInfo.get(fightInfo.getFightCompanyId()));
            fightOrderVO.setFightNo(fightInfo.getFightNo());
            fightOrderVO.setFightPay(fightInfo.getFightPay());
            fightOrderVO.setFightType(fightInfo.getFightType());
            fightOrderVOS.add(fightOrderVO);

        }


        return fightOrderVOS;
    }


}
