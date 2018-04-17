package sell.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sell.dao.DO.CompanyInfoMapper;
import sell.dao.DO.FightInfoMapper;
import sell.dao.Enum.TicketTypeEnum;
import sell.dao.VO.FightInfoVO;
import sell.dao.result.PageResult;
import sell.pojo.CompanyInfo;
import sell.pojo.FightInfo;
import sell.service.IFightService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FightServiceImpl implements IFightService {

    @Autowired
    private FightInfoMapper fightInfoMapper;
    @Autowired
    private CompanyInfoMapper companyInfoMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public PageResult<FightInfoVO> selectFightInfoListByPage(Integer currentPage) {
        List<FightInfo> fightInfos = fightInfoMapper.selectListByPage((currentPage - 1) * PageResult.pageSize, PageResult.pageSize);
        Integer totalSize=fightInfoMapper.selectCount();

        List<FightInfoVO> fightInfoVOS=new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        List<CompanyInfo> companyInfos=companyInfoMapper.selectAll();
        Map<Long,String> IdAndName=new HashMap<>();
        companyInfos.forEach(x->{
            IdAndName.put(x.getCompanyId(),x.getCompanyName());
        });

        if (fightInfos!=null){

            fightInfos.forEach(x->{
                FightInfoVO fightInfoVO=new FightInfoVO();
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

        return PageResult.Create(fightInfoVOS, currentPage, totalSize/PageResult.pageSize+1);

    }
}
