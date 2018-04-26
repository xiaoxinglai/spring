package sell.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import sell.dao.DO.MessageMapper;
import sell.dao.result.BizResult;
import sell.pojo.Message;
import sell.pojo.User;
import sell.service.IMessageService;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller
public class MessageController {

    @Autowired
    private IMessageService messageService;
    @Autowired
    private MessageMapper messageMapper;

    /**
     * 进入发布留言列表
     */
    @RequestMapping(value = "/toAddMessage",method = RequestMethod.GET)
    public String toAddMessage(HttpSession session){
        User user = (User) session.getAttribute("User");
        if (user == null) {
            return "redirect:login";
        }

        return "AddMessage";
    }


    /**
     * 发布留言
     */
    @RequestMapping(value = "/doAddMessage",method = RequestMethod.POST)
    public String doAddMessage(String title, String question, HttpSession session, Model model){
        User user = (User) session.getAttribute("User");
        if (user == null) {
            return "redirect:login";
        }
        BizResult bizResult=messageService.insertMessage(title,question,user);
        model.addAttribute("msg",bizResult.getMsg());
        return "result";
    }

    /**
     * 进入留言查看列表
     */
    @RequestMapping(value = "/toMessageList",method = RequestMethod.GET)
    public String toMessageList(HttpSession session, Model model){
        User user = (User) session.getAttribute("User");
        if (user == null) {
            return "redirect:login";
        }

       List<Message> messageList= messageMapper.selectAllByUID(user.getuId());
        model.addAttribute("messageList",messageList);
        return "userAdminMessage";
    }

    /**
     * 进入管理员留言查看列表
     */
    @RequestMapping(value = "/toAdminMessageList",method = RequestMethod.GET)
    public String toAdminMessageList(HttpSession session, Model model){
        User user = (User) session.getAttribute("User");
        if (user == null) {
            return "redirect:login";
        }

        List<Message> messageList= messageMapper.selectAll();
        model.addAttribute("messageList",messageList);
        model.addAttribute("admin",user);
        return "AdminMessage";
    }

    /**
     * 删除留言
     */
    @RequestMapping(value = "/delMessage",method = RequestMethod.GET)
    public String delMessage(@RequestParam("mId") Long mId){
        messageMapper.deleteByPrimaryKey(mId);
        return "redirect:toMessageList";
    }



    /**
     * 管理员删除留言
     */
    @RequestMapping(value = "/AdmindelMessage",method = RequestMethod.GET)
    public String AdmindelMessage(@RequestParam("mId") Long mId){
        messageMapper.deleteByPrimaryKey(mId);
        return "redirect:toAdminMessageList";
    }

    /**
     * 进入留言回复列表
     */
    @RequestMapping(value = "/toUpdateMessage",method = RequestMethod.GET)
    public String toUpdateMessage(@RequestParam("mId") Long mId,Model model){
        Message message=messageMapper.selectByPrimaryKey(mId);
        model.addAttribute("message",message);

        return "UpdateMessage";
    }



    /**
     *  留言回复
     */

    @RequestMapping(value = "/DoUpdateMessage",method = RequestMethod.POST)
    public String DoUpdateMessage(HttpSession session,String mAnswer,Long mId,Model model){
        User user=(User)session.getAttribute("User");
        if (user==null){
            return "redirect:/login";
        }

        Message message=messageMapper.selectByPrimaryKey(mId);
        message.setmAnswer(mAnswer);
        message.setaTime(new Date());
        message.setaUserId(user.getuId());
        message.setaUserName(user.getUserName());

        messageMapper.updateByPrimaryKey(message);


       model.addAttribute("msg","回复成功！");

        return "result";
    }
}
