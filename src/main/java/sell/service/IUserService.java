package sell.service;


import sell.dao.result.BizResult;
import sell.pojo.User;

/**
 * Created by user12 on 2018/2/3.
 */
public interface IUserService {

    /**
     * 用户注册方法
     * @param user
     * @return
     */
    BizResult<Boolean> doSign(User user);

    /**
     * 验证是否账号已经注册
     * @param no
     * @return
     */
    BizResult<Boolean> valiNo(String no);

    /**
     * 用户登陆的方法
     */
    BizResult<User> doLogin(User user);
}
