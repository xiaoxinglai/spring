package sell.dao.Enum;

/**
 * Created by user12 on 2018/2/11.
 */
public enum TicketTypeEnum {
    PUTONG(1, "普通"),
    TEJIA(0, "特价"),;
    private Integer code;
    private String msg;

    TicketTypeEnum(Integer code, String msg) {

        this.code = code;
        this.msg = msg;
    }

    public static String getMsgByCode(Integer code){


        for (TicketTypeEnum ticketTypeEnum : TicketTypeEnum.values()) {

            if (ticketTypeEnum.getCode().equals(code)){

                return ticketTypeEnum.getMsg();
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
