package sell.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sell.dao.DO.CompanyInfoMapper;
import sell.dao.DO.FightInfoMapper;
import sell.dao.Enum.TicketTypeEnum;
import sell.dao.VO.FightInfoVO;
import sell.dao.result.BizResult;
import sell.dao.result.PageResult;
import sell.pojo.CompanyInfo;
import sell.pojo.FightInfo;
import sell.service.IFightService;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class FightServiceImpl implements IFightService {

    @Autowired
    private FightInfoMapper fightInfoMapper;
    @Autowired
    private CompanyInfoMapper companyInfoMapper;

    /**
     * 获取首页航班信息 带分页
     *
     * @param currentPage
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public PageResult<FightInfoVO> selectFightInfoListByPage(Integer currentPage) {
        List<FightInfo> fightInfos = fightInfoMapper.selectListByPage((currentPage - 1) * PageResult.pageSize, PageResult.pageSize);
        Integer totalSize = fightInfoMapper.selectCount();

        List<FightInfoVO> fightInfoVOS = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");

        List<CompanyInfo> companyInfos = companyInfoMapper.selectAll();
        Map<Long, String> IdAndName = new HashMap<>();
        companyInfos.forEach(x -> {
            IdAndName.put(x.getCompanyId(), x.getCompanyName());
        });

        if (fightInfos != null) {

            fightInfos.forEach(x -> {
                FightInfoVO fightInfoVO = new FightInfoVO();
                fightInfoVO.setDeparture(x.getDeparture());
                fightInfoVO.setDepartureTime(sdf.format(x.getDepartureTime()));
                fightInfoVO.setDestination(x.getDestination());
                fightInfoVO.setDestinationTime(sdf.format(x.getDestinationTime()));
                fightInfoVO.setFightCompany(IdAndName.get(x.getFightCompanyId()));
                fightInfoVO.setFightId(x.getFightId());
                fightInfoVO.setFightNo(x.getFightNo());
                fightInfoVO.setFightNum(x.getFightNum());
                fightInfoVO.setFightPay(x.getFightPay());
                fightInfoVO.setFightType(x.getFightType());
                fightInfoVO.setTicketType(TicketTypeEnum.getMsgByCode(x.getTicketTypeId().intValue()));


                fightInfoVOS.add(fightInfoVO);
            });
        }

        return PageResult.Create(fightInfoVOS, currentPage, totalSize / PageResult.pageSize + 1);

    }

    @Override
    public List<FightInfoVO> selectFightInfoAll() {
        List<FightInfo> fightInfos = fightInfoMapper.selectAll();
        List<FightInfoVO> fightInfoVOS = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");

        List<CompanyInfo> companyInfos = companyInfoMapper.selectAll();
        Map<Long, String> IdAndName = new HashMap<>();
        companyInfos.forEach(x -> {
            IdAndName.put(x.getCompanyId(), x.getCompanyName());
        });

        if (fightInfos != null) {

            fightInfos.forEach(x -> {
                FightInfoVO fightInfoVO = new FightInfoVO();
                fightInfoVO.setDeparture(x.getDeparture());
                fightInfoVO.setDepartureTime(sdf.format(x.getDepartureTime()));
                fightInfoVO.setDestination(x.getDestination());
                fightInfoVO.setDestinationTime(sdf.format(x.getDestinationTime()));
                fightInfoVO.setFightCompany(IdAndName.get(x.getFightCompanyId()));
                fightInfoVO.setFightId(x.getFightId());
                fightInfoVO.setFightNo(x.getFightNo());
                fightInfoVO.setFightNum(x.getFightNum());
                fightInfoVO.setFightPay(x.getFightPay());
                fightInfoVO.setFightType(x.getFightType());
                fightInfoVO.setTicketType(TicketTypeEnum.getMsgByCode(x.getTicketTypeId().intValue()));


                fightInfoVOS.add(fightInfoVO);
            });
        }

        return fightInfoVOS;
    }

    @Override
    public BizResult insertFightInfo(FightInfoVO fightInfoVO) {


        FightInfo fightInfo = new FightInfo();
        fightInfo.setFightNum(fightInfoVO.getFightNum());
        fightInfo.setDeparture(fightInfoVO.getDeparture());
        fightInfo.setDestination(fightInfoVO.getDestination());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
        try {
            fightInfo.setDepartureTime(simpleDateFormat.parse(fightInfoVO.getDepartureTime()));
            fightInfo.setDestinationTime(simpleDateFormat.parse(fightInfoVO.getDestinationTime()));
        } catch (Exception e) {
            return BizResult.Create(-1,"日期填写错误，请填入形式如04:22 的小时:分钟 形式");
        }
        fightInfo.setFightCompanyId(Long.parseLong(fightInfoVO.getFightCompany()));
        fightInfo.setCreatTime(new Date());
        fightInfo.setFightPay(fightInfoVO.getFightPay());
        fightInfo.setFightType(fightInfoVO.getFightType());
        fightInfo.setFightNo(fightInfoVO.getFightNo());
        fightInfo.setModfiyTime(new Date());
        Byte status=1;
        fightInfo.setStatus(status);
        fightInfo.setTicketTypeId(Long.parseLong(fightInfoVO.getTicketType()));
        fightInfoMapper.insert(fightInfo);

        return BizResult.Create(1,"添加成功！");
    }
}
