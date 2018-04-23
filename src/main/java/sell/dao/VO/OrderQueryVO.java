package sell.dao.VO;

import sell.pojo.CompanyInfo;
import sell.pojo.FightInfo;

import java.util.List;

public class OrderQueryVO {
    List<FightInfo> fightInfos;
    List<CompanyInfo> companyInfoList;

    public OrderQueryVO(List<FightInfo> fightInfos, List<CompanyInfo> companyInfoList) {
        this.fightInfos = fightInfos;
        this.companyInfoList = companyInfoList;
    }

    public OrderQueryVO() {
    }

    public List<FightInfo> getFightInfos() {
        return fightInfos;
    }

    public void setFightInfos(List<FightInfo> fightInfos) {
        this.fightInfos = fightInfos;
    }

    public List<CompanyInfo> getCompanyInfoList() {
        return companyInfoList;
    }

    public void setCompanyInfoList(List<CompanyInfo> companyInfoList) {
        this.companyInfoList = companyInfoList;
    }
}
