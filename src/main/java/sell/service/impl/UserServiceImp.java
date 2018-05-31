package sell.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sell.dao.DO.RerationInfoMapper;
import sell.dao.DO.UserMapper;
import sell.dao.Enum.ParamEnum;
import sell.dao.Enum.ResultEnum;
import sell.dao.Enum.UserEnum;
import sell.dao.VO.RelationUser;
import sell.dao.result.BizResult;
import sell.pojo.RerationInfo;
import sell.pojo.User;
import sell.service.IUserService;

import java.util.*;


/**
 * Created by user12 on 2018/2/3.
 */
@Service
public class UserServiceImp implements IUserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RerationInfoMapper rerationInfoMapper;

    @Override
    public BizResult userChangePwd(User user, String pwd, String newPwd, String newRePwd) {

        if (!user.getPassword().equals(pwd)){
            return BizResult.Create(-1,"原密码不对，请重试!");
        }else {
            if(!newPwd.equals(newRePwd)){
                return BizResult.Create(-2,"两次新密码输入不对，请注意有没有混入空格，重试!");
            }
            user.setPassword(newPwd);
            if (userMapper.updateByPrimaryKey(user)>0){
                return BizResult.Create(1,"密码修改成功!");
            }else {
                return BizResult.Create(-3,"更新密码失败，请重试!");
            }


        }

    }

    /**
     * 处理注册
     *
     * @param user
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public BizResult<Boolean> doSign(User user) {
        if (user == null) {
            return BizResult.Create(ParamEnum.PARAM_IS_NULL.getCode(), ParamEnum.PARAM_IS_NULL.getMsg());
        }

        User result = userMapper.selectByNo(user.getuNo());


        if (result == null) {
            user.setPower(UserEnum.USER.getCode());
            int i = userMapper.insert(user);
            if (i > 0) {
                return BizResult.Create();
            } else {
                return BizResult.Create(ResultEnum.SQL_EXCEPTION.getCode(), ResultEnum.SQL_EXCEPTION.getMsg());
            }

        } else {
            return BizResult.Create(ParamEnum.NO_IS_REPEAT.getCode(), ParamEnum.NO_IS_REPEAT.getMsg());
        }

    }

    /**
     * 验证账号是否被注册
     *
     * @param no
     * @return
     */
    @Override
    public BizResult<Boolean> valiNo(String no) {

        User result = userMapper.selectByNo(no);


        if (result != null) {
            return BizResult.Create(ParamEnum.NO_IS_REPEAT.getCode(), ParamEnum.NO_IS_REPEAT.getMsg());


        } else {
            return BizResult.Create();
        }
    }

    /**
     * 处理登陆
     *
     * @param user
     * @return
     */
    @Override
    public BizResult<User> doLogin(User user) {

        User result = userMapper.selectByNo(user.getuNo());
        if (result == null) {
            return BizResult.Create(ParamEnum.NO_USER.getCode(), ParamEnum.NO_USER.getMsg());
        }


        if (result.getPassword().equals(user.getPassword())) {

            return BizResult.Create(result);
        } else {
            return BizResult.Create(ParamEnum.PASS_ERR.getCode(), ParamEnum.PASS_ERR.getMsg());
        }

    }

    @Override
    public List<RelationUser> getRelation(Long UID) {

        List<RerationInfo> rerationInfos = rerationInfoMapper.selectAllByUID(UID);
        List<User> userList = userMapper.selectAll();
        Map<Long, User> IdUserMap = new HashMap<>();
        for (User user : userList) {
            IdUserMap.put(user.getuId(), user);
        }


        List<RelationUser> relationUsers = new ArrayList<>();
        for (RerationInfo rerationInfo : rerationInfos) {
            RelationUser relationUser = new RelationUser();
            User user = IdUserMap.get(rerationInfo.getrUId());
            relationUser.setName(user.getUserName());
            relationUser.setIdentity(user.getIdentity());
            relationUser.setUid(rerationInfo.getrUId());

            relationUsers.add(relationUser);
        }
        return relationUsers;
    }

    @Override
    public BizResult addRalationUser(Long UID, String name, String identity) {

        User user = userMapper.selectbyNameAndidentity(name, identity);
        if (user == null) {
            return BizResult.Create(-3, "没有找到该人信息");
        } else {
            //判断这个联系人是否是已经添加过 以防止重复添加
          RerationInfo info= rerationInfoMapper.selectByPrimaryKey(UID,user.getuId());
          if (info!=null){
              return BizResult.Create(-5,"该人已经是你的联系人，不能重复添加");

          }

            RerationInfo rerationInfo = new RerationInfo();
            rerationInfo.setrUId(user.getuId());
            rerationInfo.setuId(UID);
            Byte status = 1;
            rerationInfo.setStatus(status);
            Date date=new Date();
            rerationInfo.setCreatTime(date);
            rerationInfo.setModifyTime(date);
            if (rerationInfoMapper.insert(rerationInfo) == 0) {

                return BizResult.Create(-1, "插入失败");
            }
        }

        return BizResult.Create();
    }
}
