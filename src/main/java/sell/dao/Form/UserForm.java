package sell.dao.Form;

import java.io.Serializable;

public class UserForm implements Serializable {


    private String password;


    private String uNo;

    private String userName;

    private String email;


    private String phone;


    private Byte sex;


    private String address;


    private String identity;




    private static final long serialVersionUID = 1L;



    public String getPassword() {
        return password;
    }


    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getuNo() {
        return uNo;
    }


    public void setuNo(String uNo) {
        this.uNo = uNo == null ? null : uNo.trim();
    }


    public String getUserName() {
        return userName;
    }


    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }


    public String getEmail() {
        return email;
    }


    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }


    public Byte getSex() {
        return sex;
    }


    public void setSex(Byte sex) {
        this.sex = sex;
    }


    public String getAddress() {
        return address;
    }


    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }


    public String getIdentity() {
        return identity;
    }


    public void setIdentity(String identity) {
        this.identity = identity == null ? null : identity.trim();
    }




    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", password=").append(password);
        sb.append(", uNo=").append(uNo);
        sb.append(", userName=").append(userName);
        sb.append(", email=").append(email);
        sb.append(", phone=").append(phone);
        sb.append(", sex=").append(sex);
        sb.append(", address=").append(address);
        sb.append(", identity=").append(identity);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
