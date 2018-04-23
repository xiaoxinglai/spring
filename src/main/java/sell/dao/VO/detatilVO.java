package sell.dao.VO;

public class detatilVO {
    private Long orderId;
    private String departureTime;
    private String destinationTime;
    private String departure;
    private String destination;
    private String fightCompany;
    private String fightType;
    private String fightNo;
    private Long fightPay;
    private String userName;
    private Long detatilId;

    public detatilVO() {
    }


    public detatilVO(Long orderId, String departureTime, String destinationTime, String departure, String destination, String fightCompany, String fightType, String fightNo, Long fightPay, String userName, Long detatilId) {
        this.orderId = orderId;
        this.departureTime = departureTime;
        this.destinationTime = destinationTime;
        this.departure = departure;
        this.destination = destination;
        this.fightCompany = fightCompany;
        this.fightType = fightType;
        this.fightNo = fightNo;
        this.fightPay = fightPay;
        this.userName = userName;
        this.detatilId = detatilId;
    }

    public String getFightNo() {
        return fightNo;
    }

    public void setFightNo(String fightNo) {
        this.fightNo = fightNo;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
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

    public Long getDetatilId() {
        return detatilId;
    }

    public void setDetatilId(Long detatilId) {
        this.detatilId = detatilId;
    }
}
