package sell.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sell.dao.Enum.UserEnum;
import sell.dao.Form.UserForm;
import sell.dao.VO.FightOrderVO;
import sell.dao.result.BizResult;
import sell.pojo.User;
import sell.service.IOrderService;
import sell.service.IUserService;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {


    @Autowired
    private IUserService userService;
    @Autowired
    private IOrderService orderService;

    @RequestMapping(value = "/doSign",method = RequestMethod.POST)
    @ResponseBody
    public int doSign(@RequestBody  UserForm userForm, HttpSession session) {
        User user = new User(userForm.getuNo(), userForm.getAddress(), userForm.getEmail(), userForm.getIdentity(), userForm.getPassword(), userForm.getPhone(), userForm.getSex(), userForm.getUserName());

        BizResult bizResult = userService.doSign(user);
        if (bizResult.getSuccess()) {

            session.setAttribute("User",user);
            return 1;
        }else {
            return  -1;
        }
    }

    @RequestMapping(value = "/doValiNo",method = RequestMethod.GET)
    @ResponseBody
    public int doValiNo(@RequestParam("uno") String uno){

        if (StringUtils.isNotBlank(uno)){
            BizResult bizResult = userService.valiNo(uno);
            if (bizResult.getSuccess())
            {
                return 1;
            }else{
                return -1;
            }
        }else {
            return 0;
        }

        }


    @RequestMapping(value = "/doLogin",method = RequestMethod.POST)
    public String doLogin(UserForm userForm, HttpSession session){

        User user=new User();
        user.setuNo(userForm.getuNo());
        user.setPassword(userForm.getPassword());
            BizResult<User> bizResult=userService.doLogin(user);
            if (bizResult.getSuccess()){
                session.setAttribute("User",bizResult.getDate());
                User resultDate=bizResult.getDate();
                if (resultDate.getPower().equals(UserEnum.ADMIN.getCode())){

                    return "redirect:/admin";
                }else if (resultDate.getPower().equals(UserEnum.USER.getCode())){
                    return "redirect:/index";
                }else {
                    return "redirect:/admin";
                }

            }else {
                String msg=bizResult.getMsg();
                System.out.println("校验失败:  "+msg);

                return "/index";
            }

        }



    @RequestMapping(value = "/center ",method = RequestMethod.GET)
    public String center(HttpSession session,Model model){
        User user=(User)session.getAttribute("User");
        if (user==null){
            return "redirect:/login";
        }

        List<FightOrderVO> fightOrderVOs=orderService.getFightOrder(user);

        model.addAttribute("fightOrderVO",fightOrderVOs);
        return "userAdmin";
    }

}
