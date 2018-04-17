package sell.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import sell.dao.DO.UserMapper;
import sell.pojo.User;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class PageSikpController {
    @Autowired
    private UserMapper userMapper;

    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public String index(HttpSession session, Model model){
       User user=(User) session.getAttribute("User");
       if (user!=null){
           model.addAttribute("name",user.getUserName());

       }
        return "index";
    }

    @RequestMapping(value = "/login ",method = RequestMethod.GET)
    public String login(){
        return "login";
    }

    @RequestMapping(value = "/admin ",method = RequestMethod.GET)
    public String admin(){
        return "admin";
    }



    @RequestMapping(value = "/sign ",method = RequestMethod.GET)
    public String sign(){
        return "sign";
    }

    @RequestMapping(value = "/test",method = RequestMethod.GET)

    public String test(){

       List<User> userList= userMapper.selectAll();
        for (User user : userList) {
            System.out.println(user.getPassword()+""+user.getuId());
        }


        return  "index";
    }
}