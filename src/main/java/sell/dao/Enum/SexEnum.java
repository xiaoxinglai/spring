package sell.dao.Enum;

public enum  SexEnum {
    MEN(1, "男"),
    WOMEN(0, "女"),;
    private Integer code;
    private String msg;

    SexEnum(Integer code, String msg) {

        this.code = code;
        this.msg = msg;
    }

    public static String getMsgByCode(Integer code){


        for (SexEnum SexEnum : SexEnum.values()) {

            if (SexEnum.getCode().equals(code)){

                return SexEnum.getMsg();
            }
        }

        return  null;

    }



    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

}
