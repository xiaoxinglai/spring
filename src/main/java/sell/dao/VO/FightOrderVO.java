package sell.dao.VO;

public class FightOrderVO {


    private Long fightId;
    private String orderNo;
    private String fightNo;

    private String departureTime;
    private String destinationTime;
    private String departure;
    private String destination;
    private String fightCompany;
    private String fightType;
    private Long fightPay;
    private String userName;


    public FightOrderVO(Long fightId, String orderNo, String fightNo, String departureTime, String destinationTime, String departure, String destination, String fightCompany, String fightType, Long fightPay, String userName) {
        this.fightId = fightId;
        this.orderNo = orderNo;
        this.fightNo = fightNo;
        this.departureTime = departureTime;
        this.destinationTime = destinationTime;
        this.departure = departure;
        this.destination = destination;
        this.fightCompany = fightCompany;
        this.fightType = fightType;
        this.fightPay = fightPay;
        this.userName = userName;
    }

    public FightOrderVO() {
    }

    public Long getFightId() {
        return fightId;
    }

    public void setFightId(Long fightId) {
        this.fightId = fightId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getFightNo() {
        return fightNo;
    }

    public void setFightNo(String fightNo) {
        this.fightNo = fightNo;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getDestinationTime() {
        return destinationTime;
    }

    public void setDestinationTime(String destinationTime) {
        this.destinationTime = destinationTime;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getFightCompany() {
        return fightCompany;
    }

    public void setFightCompany(String fightCompany) {
        this.fightCompany = fightCompany;
    }

    public String getFightType() {
        return fightType;
    }

    public void setFightType(String fightType) {
        this.fightType = fightType;
    }

    public Long getFightPay() {
        return fightPay;
    }

    public void setFightPay(Long fightPay) {
        this.fightPay = fightPay;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
