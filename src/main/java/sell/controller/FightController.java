package sell.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import sell.dao.DO.CompanyInfoMapper;
import sell.dao.VO.FightInfoVO;
import sell.dao.result.BizResult;
import sell.dao.result.PageResult;
import sell.pojo.CompanyInfo;
import sell.pojo.User;
import sell.service.IFightService;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/fight")
public class FightController {

    @Autowired
    private IFightService fightService;
    @Autowired
    private CompanyInfoMapper companyInfoMapper;


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

    /**
     *  跳转到航班管理界面fightAdmin
     */
    @RequestMapping(value ="/fightAdmin",method = RequestMethod.GET)
    public String fightAdmin(HttpSession session, Model model){
        User user = (User) session.getAttribute("User");
        if (user == null) {
            return "redirect:login";
        }
        List<FightInfoVO> fightInfoVOList=fightService.selectFightInfoAll();
        model.addAttribute("fightInfoVOList",fightInfoVOList);
        model.addAttribute("admin",user);
        return "/fightAdmin";
    }


    /**
     * 添加航班
     */

    @RequestMapping(value ="/addFight",method = RequestMethod.POST)
    public String addFight(FightInfoVO fightInfoVO,HttpSession session, Model model){
        User user = (User) session.getAttribute("User");
        if (user == null) {
            return "redirect:login";
        }

        BizResult bizResult=fightService.insertFightInfo(fightInfoVO);
        model.addAttribute("msg",bizResult.getMsg());

        return "/result";
    }

    /**
     * 进入添加航班页面
     */

    @RequestMapping(value ="/ToAddFight",method = RequestMethod.GET)
    public String ToAddFight(HttpSession session, Model model){
        User user = (User) session.getAttribute("User");
        if (user == null) {
            return "redirect:login";
        }

        List<CompanyInfo> companyInfos= companyInfoMapper.selectAll();

        model.addAttribute("companyInfos",companyInfos);
        model.addAttribute("admin",user);

        return "/addFight";
    }

}
