package sell.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import sell.dao.UserMapper;
import sell.pojo.User;

import java.util.List;

@Controller
public class IndexController {
    @Autowired
    private UserMapper userMapper;

    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public String index(){
        System.out.println("hhhh");
        return "index";
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
