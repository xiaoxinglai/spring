package sell.dao.Enum;

/**
 * Created by user12 on 2018/2/11.
 */
public enum UserEnum {
    USER(1, "普通用户"),
    ADMIN(2, "系统管理员");
    private Integer code;
    private String msg;

    UserEnum(Integer code, String msg) {

        this.code = code;
        this.msg = msg;
    }



    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

}
