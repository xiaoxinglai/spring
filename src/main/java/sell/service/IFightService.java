package sell.service;

import sell.dao.VO.FightInfoVO;
import sell.dao.result.BizResult;
import sell.dao.result.PageResult;

import java.util.List;

public interface IFightService {

    /**
     * 根据页数查找航班列表
     */
    PageResult<FightInfoVO> selectFightInfoListByPage(Integer currentPage);


    /**
     * 查看所有的航班
     * @return
     */
    List<FightInfoVO> selectFightInfoAll();

    /**
     * 增加新的航班
     */
    BizResult insertFightInfo(FightInfoVO fightInfoVO);

}
