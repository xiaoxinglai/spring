package sell.dao.Form;

import java.util.List;

public class orderForm {
    private Long fightId;
    private String statrDate;
    private List<Long> uId;

    public String getStatrDate() {
        return statrDate;
    }

    public void setStatrDate(String statrDate) {
        this.statrDate = statrDate;
    }

    public orderForm(Long fightId, List<Long> uId) {
        this.fightId = fightId;
        this.uId = uId;
    }

    public orderForm() {
    }

    public Long getFightId() {
        return fightId;
    }

    public void setFightId(Long fightId) {
        this.fightId = fightId;
    }

    public List<Long> getuId() {
        return uId;
    }

    public void setuId(List<Long> uId) {
        this.uId = uId;
    }
}
