package sell.service;

import sell.dao.Form.orderForm;
import sell.dao.VO.FightOrderVO;
import sell.dao.VO.OrderVO;
import sell.pojo.User;

import java.util.List;

public interface IOrderService {

     boolean OrderTicket(orderForm orderForm,Long uid);
     OrderVO getOrderVO(Long fightId,User user);
     List<FightOrderVO> getFightOrder(User user);
}
