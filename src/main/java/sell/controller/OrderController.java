package sell.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import sell.dao.Form.orderForm;
import sell.dao.VO.OrderVO;
import sell.pojo.User;
import sell.service.IOrderService;

import javax.servlet.http.HttpSession;

@Controller
public class OrderController {

    @Autowired
    private IOrderService orderService;


    @RequestMapping(value = "/toOrder", method = RequestMethod.GET)
    public String toOrder(@RequestParam("fightId") Long fightId, HttpSession session, Model model) {
        User user = (User) session.getAttribute("User");

        if (user == null) {
            return "login";
        }

        OrderVO orderVO = orderService.getOrderVO(fightId, user);
        if (orderVO != null) {
            model.addAttribute("orderVO", orderVO);
        }

        return "Order";

    }


    @RequestMapping(value = "/order", method = RequestMethod.POST)
    public String order(orderForm orderForm, HttpSession session) {
        User user = (User) session.getAttribute("User");
        if (user == null) {
            return "redirect:/login";
        } else {
            if (orderService.OrderTicket(orderForm, user.getuId())) {
                return "redirect:/user/center";
            } else{
                return "/toOrder";
            }
        }


    }
}
