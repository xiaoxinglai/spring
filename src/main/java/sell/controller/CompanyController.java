package sell.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import sell.dao.DO.CompanyInfoMapper;
import sell.pojo.CompanyInfo;
import sell.pojo.User;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class CompanyController {

    @Autowired
    private CompanyInfoMapper companyInfoMapper;


    /**
     * 进入公司信息列表页面
     */
    @RequestMapping(value = "/Company ", method = RequestMethod.GET)
    public String Company(HttpSession session, Model model) {
        User user = (User) session.getAttribute("User");
        if (user == null) {
         return "redirect:login";
        }
        List<CompanyInfo> companyInfos=companyInfoMapper.selectAll();
        model.addAttribute("companyInfos",companyInfos);
        model.addAttribute("admin",user);

        return "Company";

    }

    /**
     * 删除公司信息
     */
    @RequestMapping(value = "/Company/delCompany ", method = RequestMethod.GET)
    @ResponseBody
    public int DelRelation(@RequestParam("CompanyId") Long CompanyId, HttpSession session, Model model) {
        User user = (User) session.getAttribute("User");
        if (user == null) {
            return -2;
        }
        int result = companyInfoMapper.deleteByPrimaryKey(CompanyId);

        if (result > 0) {
            return 1;
        } else {
            return -1;
        }


    }


    /**
     * 添加公司
     */
    @RequestMapping(value = "/Company/addCompany ", method = RequestMethod.GET)
    @ResponseBody
    public int addRelation(@RequestParam("name") String name, HttpSession session, Model model) {
        User user = (User) session.getAttribute("User");
        if (user == null) {
            return -2;
        }

        CompanyInfo companyInfo=new CompanyInfo();
        companyInfo.setCompanyName(name);
        int result = companyInfoMapper.insert(companyInfo);

        if (result>0) {
            return 1;
        }
            return -1;
        }

}
