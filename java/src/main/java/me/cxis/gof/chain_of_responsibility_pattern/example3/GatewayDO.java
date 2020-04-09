package me.cxis.gof.chain_of_responsibility_pattern.example3;

public class GatewayDO {

    private Integer id;

    private String desc;

    private String fullName;

    private Integer preId;

    private Integer nextId;

    public GatewayDO() {
    }

    public GatewayDO(Integer id, String desc, String fullName, Integer preId, Integer nextId) {
        this.id = id;
        this.desc = desc;
        this.fullName = fullName;
        this.preId = preId;
        this.nextId = nextId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Integer getPreId() {
        return preId;
    }

    public void setPreId(Integer preId) {
        this.preId = preId;
    }

    public Integer getNextId() {
        return nextId;
    }

    public void setNextId(Integer nextId) {
        this.nextId = nextId;
    }

    @Override
    public String toString() {
        return "GatewayDO{" +
            "id=" + id +
            ", desc='" + desc + '\'' +
            ", fullName='" + fullName + '\'' +
            ", preId=" + preId +
            ", nextId=" + nextId +
            '}';
    }
}
