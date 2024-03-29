package fun.pullock.mybatis.model;

import java.io.Serializable;
import java.util.Date;

public class ApiParam implements Serializable {

    private static final long serialVersionUID = 1374543999809129956L;

    private Long id;

    private Date createdTime;

    private Date modifiedTime;

    private Integer version;

    private Long apiId;

    private String name;

    private String type;

    private Integer sequence;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(Date modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Long getApiId() {
        return apiId;
    }

    public void setApiId(Long apiId) {
        this.apiId = apiId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    @Override
    public String toString() {
        return "ApiParam{" +
                "id=" + id +
                ", createdTime=" + createdTime +
                ", modifiedTime=" + modifiedTime +
                ", version=" + version +
                ", apiId=" + apiId +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", sequence=" + sequence +
                '}';
    }
}
