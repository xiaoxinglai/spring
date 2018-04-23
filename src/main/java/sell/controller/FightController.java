package sell.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import sell.dao.result.PageResult;
import sell.service.IFightService;

@Controller
@RequestMapping("/fight")
public class FightController {

    @Autowired
    private IFightService fightService;


    /**
     *获取航班信息 带分页
     * @param currPage
     * @return
     */
    @RequestMapping(value = "/ajaxList",method = RequestMethod.GET)
    @ResponseBody
    public Object ajaxlist(@RequestParam(value = "currPage", required=false) Integer currPage) {


        if (ObjectUtils.isEmpty(currPage)){
            currPage=1;
        }

        PageResult pageResult=fightService.selectFightInfoListByPage(currPage);
        return pageResult;

    }



}
