package sell.dao.VO;

import java.io.Serializable;

public class FightInfoVO implements Serializable {
    public FightInfoVO() {
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

    public String getFightCompany() {
        return fightCompany;
    }

    public void setFightCompany(String fightCompany) {
        this.fightCompany = fightCompany;
    }

    public String getTicketType() {
        return ticketType;
    }

    public void setTicketType(String ticketType) {
        this.ticketType = ticketType;
    }

    public FightInfoVO(Long fightId, String fightNo, String departure, String destination, String departureTime, String destinationTime, String fightCompany, String fightType, Long fightPay, Long fightNum, String ticketType) {
        this.fightId = fightId;
        this.fightNo = fightNo;
        this.departure = departure;
        this.destination = destination;
        this.departureTime = departureTime;
        this.destinationTime = destinationTime;
        this.fightCompany = fightCompany;
        this.fightType = fightType;
        this.fightPay = fightPay;
        this.fightNum = fightNum;
        this.ticketType = ticketType;

    }



    private Long fightId;

    private String fightNo;

    private String departure;


    private String destination;

    private String departureTime;


    private String destinationTime;

    private String fightCompany;


    private String fightType;

    private Long fightPay;

    private Long fightNum;

    private String ticketType;




    private static final long serialVersionUID = 1L;

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
        this.fightNo = fightNo == null ? null : fightNo.trim();
    }


    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure == null ? null : departure.trim();
    }


    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination == null ? null : destination.trim();
    }







    public String getFightType() {
        return fightType;
    }


    public void setFightType(String fightType) {
        this.fightType = fightType == null ? null : fightType.trim();
    }


    public Long getFightPay() {
        return fightPay;
    }


    public void setFightPay(Long fightPay) {
        this.fightPay = fightPay;
    }

    public Long getFightNum() {
        return fightNum;
    }

    public void setFightNum(Long fightNum) {
        this.fightNum = fightNum;
    }





    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", fightId=").append(fightId);
        sb.append(", fightNo=").append(fightNo);
        sb.append(", departure=").append(departure);
        sb.append(", destination=").append(destination);
        sb.append(", departureTime=").append(departureTime);
        sb.append(", destinationTime=").append(destinationTime);
        sb.append(", fightCompany=").append(fightCompany);
        sb.append(", fightType=").append(fightType);
        sb.append(", fightPay=").append(fightPay);
        sb.append(", fightNum=").append(fightNum);
        sb.append(", ticketTypeId=").append(ticketType);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}