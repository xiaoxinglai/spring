package sell.dao.VO;

public class RelationUser {
    private String name;
    private String identity;
    private Long uid;

    public RelationUser(String name, String identity, Long uid) {
        this.name = name;
        this.identity = identity;
        this.uid = uid;
    }

    public RelationUser() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }
}
