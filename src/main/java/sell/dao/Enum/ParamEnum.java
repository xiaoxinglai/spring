package sell.dao.Enum;

/**
 * Created by user12 on 2018/2/3.
 */
public enum ParamEnum {
    PARAM_IS_NULL(10,"参数为空"),
    NO_IS_REPEAT(11,"账号重复"),
    NO_USER(12,"用户不存在"),
    PASS_ERR(13,"密码错误")
    ;
    private Integer code;
    private String msg;

    ParamEnum(Integer code, String msg){
        this.code=code;
        this.msg=msg;

    }


    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

}
