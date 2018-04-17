package sell.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sell.dao.DO.UserMapper;
import sell.dao.Enum.ParamEnum;
import sell.dao.Enum.ResultEnum;
import sell.dao.result.BizResult;
import sell.pojo.User;
import sell.service.IUserService;



/**
 * Created by user12 on 2018/2/3.
 */
@Service
public class UserServiceImp implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public BizResult<Boolean> doSign(User user) {
        if (user==null) {
            return BizResult.Create(ParamEnum.PARAM_IS_NULL.getCode(), ParamEnum.PARAM_IS_NULL.getMsg());
        }

        User result = userMapper.selectByNo(user.getuNo());


        if (result==null) {
            int i = userMapper.insert(user);
            if (i > 0) {
                return BizResult.Create();
            }else {
                return  BizResult.Create(ResultEnum.SQL_EXCEPTION.getCode(),ResultEnum.SQL_EXCEPTION.getMsg());
            }

        } else {
            return BizResult.Create(ParamEnum.NO_IS_REPEAT.getCode(),ParamEnum.NO_IS_REPEAT.getMsg());
        }

    }

    @Override
    public BizResult<Boolean> valiNo(String no) {

        User result = userMapper.selectByNo(no);


        if (result!=null) {
            return BizResult.Create(ParamEnum.NO_IS_REPEAT.getCode(),ParamEnum.NO_IS_REPEAT.getMsg());


        } else {
            return BizResult.Create();
        }
    }

    @Override
    public BizResult<User> doLogin(User user) {

        User result = userMapper.selectByNo(user.getuNo());
        if (result==null) {
        return BizResult.Create(ParamEnum.NO_USER.getCode(), ParamEnum.NO_USER.getMsg());
        }

        if (result.getPassword().equals(user.getPassword())){

            return  BizResult.Create(result);
        }else {
            return BizResult.Create(ParamEnum.PASS_ERR.getCode(), ParamEnum.PASS_ERR.getMsg());
        }

    }
}
