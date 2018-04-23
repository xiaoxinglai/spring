package sell.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sell.dao.DO.MessageMapper;
import sell.dao.result.BizResult;
import sell.pojo.Message;
import sell.pojo.User;
import sell.service.IMessageService;

import java.util.Date;

@Service
public class MessageService implements IMessageService {

    @Autowired
    private MessageMapper messageMapper;

    @Override
    public BizResult insertMessage(String title,String question, User user) {
        Message message=new Message();
        message.setmTitle(title);
        message.setmQuestion(question);
        message.setqUserId(user.getuId());
        message.setqUserName(user.getUserName());
        Date date=new Date();
        message.setqTime(date);

        if (messageMapper.insert(message)>0){
            return BizResult.Create(1,"发布成功");
        }else {
            return BizResult.Create(-1,"发布失败，请重试");
        }

    }
}
