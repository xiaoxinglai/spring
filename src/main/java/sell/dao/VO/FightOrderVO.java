package sell.dao.VO;

import java.util.List;

public class FightOrderVO {

    private String orderNo;
    private String fightNo;

    private List<detatilVO> detatilVOS;

    public FightOrderVO(String orderNo, String fightNo, List<detatilVO> detatilVOS) {
        this.orderNo = orderNo;
        this.fightNo = fightNo;
        this.detatilVOS = detatilVOS;
    }

    public FightOrderVO() {
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

    public List<detatilVO> getDetatilVOS() {
        return detatilVOS;
    }

    public void setDetatilVOS(List<detatilVO> detatilVOS) {
        this.detatilVOS = detatilVOS;
    }
}
