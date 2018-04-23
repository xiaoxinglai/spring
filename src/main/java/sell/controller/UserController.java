package sell.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sell.dao.DO.RerationInfoMapper;
import sell.dao.Enum.UserEnum;
import sell.dao.Form.UserForm;
import sell.dao.VO.FightOrderVO;
import sell.dao.VO.RelationUser;
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
    @Autowired
    private RerationInfoMapper rerationInfoMapper;

    /**
     * 处理注册请求
     *
     * @param userForm
     * @param session
     * @return
     */
    @RequestMapping(value = "/doSign", method = RequestMethod.POST)
    @ResponseBody
    public int doSign(@RequestBody UserForm userForm, HttpSession session) {
        User user = new User(userForm.getuNo(), userForm.getAddress(), userForm.getEmail(), userForm.getIdentity(), userForm.getPassword(), userForm.getPhone(), userForm.getSex(), userForm.getUserName());

        BizResult bizResult = userService.doSign(user);
        if (bizResult.getSuccess()) {

            session.setAttribute("User", user);
            return 1;
        } else {
            return -1;
        }
    }

    @RequestMapping(value = "/doValiNo", method = RequestMethod.GET)
    @ResponseBody
    public int doValiNo(@RequestParam("uno") String uno) {

        if (StringUtils.isNotBlank(uno)) {
            BizResult bizResult = userService.valiNo(uno);
            if (bizResult.getSuccess()) {
                return 1;
            } else {
                return -1;
            }
        } else {
            return 0;
        }

    }

    /**
     * 处理登陆请求
     *
     * @param userForm
     * @param session
     * @return
     */
    @RequestMapping(value = "/doLogin", method = RequestMethod.POST)
    public String doLogin(UserForm userForm, HttpSession session) {

        User user = new User();
        user.setuNo(userForm.getuNo());
        user.setPassword(userForm.getPassword());
        BizResult<User> bizResult = userService.doLogin(user);
        if (bizResult.getSuccess()) {
            session.setAttribute("User", bizResult.getDate());
            User resultDate = bizResult.getDate();
            if (resultDate.getPower().equals(UserEnum.ADMIN.getCode())) {

                return "redirect:/admin";
            } else if (resultDate.getPower().equals(UserEnum.USER.getCode())) {
                return "redirect:/index";
            } else {
                return "redirect:/admin";
            }

        } else {
            String msg = bizResult.getMsg();
            System.out.println("校验失败:  " + msg);

            return "/index";
        }

    }


    /**
     * 处理进入个人中心请求
     *
     * @param session
     * @param model
     * @return
     */
    @RequestMapping(value = "/center ", method = RequestMethod.GET)
    public String center(HttpSession session, Model model) {
        User user = (User) session.getAttribute("User");
        if (user == null) {
            return "redirect:/login";
        }

        List<FightOrderVO> fightOrderVOs = orderService.getFightOrder(user);

        model.addAttribute("fightOrderVO", fightOrderVOs);
        return "userAdmin";
    }


    /**
     * 进入联系人列表
     */
    @RequestMapping(value = "/relation ", method = RequestMethod.GET)
    public String relation(HttpSession session, Model model) {
        User user = (User) session.getAttribute("User");
        if (user == null) {
            return "redirect:/login";
        }


        List<RelationUser> relationUsers = userService.getRelation(user.getuId());
        model.addAttribute("relationUsers", relationUsers);
        return "userRalation";

    }

    /**
     * 删除联系人
     */
    @RequestMapping(value = "/DelRelation ", method = RequestMethod.GET)
    @ResponseBody
    public int DelRelation(@RequestParam("RUID") Long RUID, HttpSession session, Model model) {
        User user = (User) session.getAttribute("User");
        if (user == null) {
            return -2;
        }
        int result = rerationInfoMapper.deleteByPrimaryKey(user.getuId(), RUID);

        if (result > 0) {
            return 1;
        } else {
            return -1;
        }


    }


    /**
     * 添加联系人
     */
    @RequestMapping(value = "/addRelation ", method = RequestMethod.GET)
    @ResponseBody
    public int addRelation(@RequestParam("name") String name, @RequestParam("Identity") String Identity, HttpSession session, Model model) {
        User user = (User) session.getAttribute("User");
        if (user == null) {
            return -2;
        }

        BizResult bizResult = userService.addRalationUser(user.getuId(), name, Identity);

        if (bizResult.getSuccess()) {
            return 1;
        } else {
            if (bizResult.getCode().equals(-3)) {
                return -3;
            }

            if (bizResult.getCode().equals(-5)) {
                return -5;
            }


            return -1;
        }


    }


    /**
     * 进入修改用户密码页面
     */
    @RequestMapping(value = "/userPwd", method = RequestMethod.GET)
    public String modifyPwd(HttpSession session) {

        User user = (User) session.getAttribute("User");
        if (user == null) {
            return "/login";
        }

        return "userPwd";
    }

    /**
     * 处理修改密码的请求
     */
    @RequestMapping(value = "/userChangePwd", method = RequestMethod.POST)
    public String userChangePwd(String pwd, String newPwd, String newRePwd, HttpSession session,Model model) {
        User user = (User) session.getAttribute("User");
        if (user == null) {
            return "/login";
        }

        BizResult bizResult = userService.userChangePwd(user, pwd, newPwd, newRePwd);
        model.addAttribute("msg",bizResult.getMsg());
        return "result";

    }


}
