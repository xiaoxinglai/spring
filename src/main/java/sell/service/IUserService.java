package sell.service;


import sell.dao.VO.RelationUser;
import sell.dao.result.BizResult;
import sell.pojo.User;

import java.util.List;

/**
 * Created by user12 on 2018/2/3.
 */
public interface IUserService {

    /**
     * 用户修改密码
     */

    BizResult userChangePwd(User user,String pwd,String newPwd,String newRePwd);

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

    /**
     * 获取用户联系人
     */
    List<RelationUser> getRelation(Long UID);

    /**
     * 根据添加联系人
     */
    BizResult addRalationUser(Long UID,String name,String identity);
}
