package sell.dao.VO;

import sell.pojo.User;

import java.util.List;

public class OrderVO {
    //航班id
    private Long fightId;
    //航班编号
    private String fightNo;
    //机票价格
    private Long fightPay;
    //订票用户
    private User user;
    //性别
    private String sex;
    //身份证号
    private String identity;
    //手机号码
    private String phone;
    //乘机用户
    private List<User> users;

    public OrderVO(Long fightId, String fightNo, Long fightPay, User user, String sex, String identity, String phone, List<User> users) {
        this.fightId = fightId;
        this.fightNo = fightNo;
        this.fightPay = fightPay;
        this.user = user;
        this.sex = sex;
        this.identity = identity;
        this.phone = phone;
        this.users = users;
    }

    public OrderVO() {

    }

    public Long getFightId() {
        return fightId;
    }

    public void setFightId(Long fightId) {
        this.fightId = fightId;
    }

    public String getFightNo() {
        return fightNo;
    }

    public void setFightNo(String fightNo) {
        this.fightNo = fightNo;
    }

    public Long getFightPay() {
        return fightPay;
    }

    public void setFightPay(Long fightPay) {
        this.fightPay = fightPay;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
