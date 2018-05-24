package sell.controller;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import sell.dao.Enum.UserEnum;
import sell.dao.Form.orderForm;
import sell.dao.VO.FightInfoVO;
import sell.dao.VO.FightOrderVO;
import sell.dao.VO.OrderQueryVO;
import sell.dao.VO.OrderVO;
import sell.dao.result.BizResult;
import sell.pojo.User;
import sell.service.IOrderService;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class OrderController {

    @Autowired
    private IOrderService orderService;


    /**
     * 点击订票后 获取对应的航班信息 并跳转到订票页面
     *
     * @param fightId
     * @param session
     * @param model
     * @return
     */
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


    /**
     * 处理订票请求 订票成功后跳转到个人中心页面
     *
     * @param orderForm
     * @param session
     * @return
     */
    @RequestMapping(value = "/order", method = RequestMethod.POST)
    public String order(orderForm orderForm, HttpSession session, Model model) {
        User user = (User) session.getAttribute("User");
        if (user == null) {
            return "redirect:/login";
        } else {
            BizResult bizResult = orderService.OrderTicket(orderForm, user.getuId());
            if (bizResult.getSuccess()) {

                if (user.getPower().equals(UserEnum.ADMIN.getCode())){
                    return "redirect:/user/adminOrder";
                }
                return "redirect:/user/center";


            } else {
                model.addAttribute("msg", bizResult.getMsg());
                return "result";
            }
        }


    }


    /**
     * 处理退票
     *
     * @param detailId
     * @param session
     * @param model
     * @return
     */
    @RequestMapping(value = "/refund", method = RequestMethod.GET)
    public String refund(@RequestParam("detailId") Long detailId, HttpSession session, Model model) {
        User user = (User) session.getAttribute("User");
        if (user == null) {
            return "redirect:login"; // 表示未登陆
        }
        BizResult bizResult = orderService.refundTickt(detailId);
        if (bizResult.getSuccess()) {
            if (user.getPower().equals(UserEnum.ADMIN.getCode())){
                return "redirect:/user/adminOrder";
            }

            return "redirect:/user/center"; //表示成功
        } else {
            model.addAttribute("msg", bizResult.getMsg());
            return "result"; //表示失败
        }

    }


    /**
     * 进入航班查询页面
     */
    @RequestMapping(value = "/QueryOrder", method = RequestMethod.GET)
    public String QueryOrder(HttpSession session, Model model) {
        User user = (User) session.getAttribute("User");
        if (user == null) {
            return "redirect:login"; // 表示未登陆
        }
        OrderQueryVO orderQueryVO = orderService.queryOrder();
        model.addAttribute("orderQueryVO", orderQueryVO);

        return "OrderQuery";

    }

    /**
     * 进入航班查询列表页面
     */
    @RequestMapping(value = "/QueryOrderList", method = RequestMethod.POST)
    public String QueryOrderList(String Departure, String Destination, Long CompanyId, HttpSession session, Model model) {
        User user = (User) session.getAttribute("User");
        if (user == null) {
            return "redirect:login"; // 表示未登陆
        }
        List<FightInfoVO> fightInfoVOS = orderService.queryFightInfo(Departure, Destination, CompanyId);

        model.addAttribute("fightInfos", fightInfoVOS);

        return "OrderQueryList";

    }


    /**
     * 处理改签请求  并进入改签航班列表
     */
    @RequestMapping(value = "/toChangeTicket", method = RequestMethod.GET)
    public String toChangeTicket(@RequestParam("detailId") Long detailId, HttpSession session, Model model) {
        User user = (User) session.getAttribute("User");
        if (user == null) {
            return "redirect:login"; // 表示未登陆
        }

        List<FightInfoVO> fightInfoVOS = orderService.changeTicket(detailId);
        model.addAttribute("fightInfos", fightInfoVOS);
        model.addAttribute("detailId", detailId);
        return "ChangeOrdeList";
    }


    /**
     * 进行改签操作
     *
     * @param fightId
     * @param session
     * @param model
     * @return
     */
    @RequestMapping(value = "/dochangeOrder", method = RequestMethod.GET)
    @ResponseBody
    public String dochangeOrder(@RequestParam("fightId") Long fightId, @RequestParam("detailId") Long detailId, @Param("date") String date, HttpSession session, Model model) {
        User user = (User) session.getAttribute("User");
        if (user == null) {
            return "redirect:login";
        }
        BizResult bizResult=orderService.dochangeOrder(fightId,detailId,user.getuId(),date);
        if (bizResult.getSuccess()){
            if (user.getPower().equals(UserEnum.ADMIN.getCode())){
                return "redirect:/user/adminOrder";
            }
            return "redirect:/user/center"; //表示成功
        }else{

            model.addAttribute("msg",bizResult.getMsg());
            return "result";
        }

    }


    /**
     * 进入根据单号查询订单页面
     */
    @RequestMapping(value = "/OrderSerch ", method = RequestMethod.GET)
    public String OrderSerch(HttpSession session, Model model) {
        User user = (User) session.getAttribute("User");
        if (user != null&&user.getPower().equals(UserEnum.ADMIN.getCode())) {

            model.addAttribute("admin",user);

            return "OrderSerch";
        }

        return "redirect:/login";


    }


    /**
     *
     *根据订单号查询航班
     * @param session
     * @param model
     * @return
     */
    @RequestMapping(value = "/adminOrder ", method = RequestMethod.POST)
    public String adminOrder(@RequestParam("orderNo") String orderNo,HttpSession session, Model model) {
        User user = (User) session.getAttribute("User");
        if (user != null&&user.getPower().equals(UserEnum.ADMIN.getCode())) {
            List<FightOrderVO> fightOrderVOs = orderService.getFightOrder(orderNo);

            if (fightOrderVOs!=null&&fightOrderVOs.size()!=0){
                for (FightOrderVO fightOrderVO : fightOrderVOs) {

                   if (fightOrderVO.getOrderNo().equals(orderNo)){
                       model.addAttribute("fightOrderVO", fightOrderVO);
                   }
                }
            }

            model.addAttribute("admin",user);
            return "OrderSerch";
        }

        return "redirect:/login";


    }

}
