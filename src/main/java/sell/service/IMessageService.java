package sell.service;

import sell.dao.result.BizResult;
import sell.pojo.User;

public interface IMessageService {

    /**
     * 发布留言
     */
    BizResult insertMessage(String title,String question,User user);


}
