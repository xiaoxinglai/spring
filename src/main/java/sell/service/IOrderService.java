package sell.service;

import sell.dao.Form.orderForm;
import sell.dao.VO.FightInfoVO;
import sell.dao.VO.FightOrderVO;
import sell.dao.VO.OrderQueryVO;
import sell.dao.VO.OrderVO;
import sell.dao.result.BizResult;
import sell.pojo.User;

import java.util.List;

public interface IOrderService {

     BizResult OrderTicket(orderForm orderForm, Long uid);
     OrderVO getOrderVO(Long fightId,User user);
     List<FightOrderVO> getFightOrder(User user);
     List<FightOrderVO> getFightOrder();
     List<FightOrderVO> getFightOrder(String orderNo);
     BizResult refundTickt(Long detailId);
     OrderQueryVO queryOrder();
     List<FightInfoVO> queryFightInfo(String Departure, String Destination, Long CompanyId);
     List<FightInfoVO> changeTicket(Long detailId);

     BizResult dochangeOrder(Long fight,Long detailId,Long UID);
}
