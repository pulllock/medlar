package fun.pullock.spring.transaction.dao.model;

import java.time.LocalDateTime;

public class UserBadgeDO {
    private Long id;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private Integer version;

    private Long userId;

    private Long badgeId;

    private Integer count;

    private LocalDateTime latestAcquireTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getBadgeId() {
        return badgeId;
    }

    public void setBadgeId(Long badgeId) {
        this.badgeId = badgeId;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public LocalDateTime getLatestAcquireTime() {
        return latestAcquireTime;
    }

    public void setLatestAcquireTime(LocalDateTime latestAcquireTime) {
        this.latestAcquireTime = latestAcquireTime;
    }
}