package sell.service;

import sell.dao.VO.FightInfoVO;
import sell.dao.result.PageResult;

public interface IFightService {

    /**
     * 根据页数查找航班列表
     */
    PageResult<FightInfoVO> selectFightInfoListByPage(Integer currentPage);

}
