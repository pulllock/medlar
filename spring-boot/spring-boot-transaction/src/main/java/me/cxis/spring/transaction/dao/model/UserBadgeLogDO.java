package me.cxis.spring.transaction.dao.model;

import java.time.LocalDateTime;

public class UserBadgeLogDO {
    private Long id;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private Integer version;

    private Long userId;

    private Long badgeId;

    private LocalDateTime acquireTime;

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

    public LocalDateTime getAcquireTime() {
        return acquireTime;
    }

    public void setAcquireTime(LocalDateTime acquireTime) {
        this.acquireTime = acquireTime;
    }
}