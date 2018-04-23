package me.cxis.second.kill.dao.model;

import java.util.Date;

public class SuccessKilledDO {

    private long secondKillId;

    private long userPhone;

    private short state;

    private Date createTime;


    public long getSecondKillId() {
        return secondKillId;
    }

    public void setSecondKillId(long secondKillId) {
        this.secondKillId = secondKillId;
    }

    public long getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(long userPhone) {
        this.userPhone = userPhone;
    }

    public short getState() {
        return state;
    }

    public void setState(short state) {
        this.state = state;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }


    @Override
    public String toString() {
        return "SuccessKilledDTO{" +
                "secondKillId=" + secondKillId +
                ", userPhone=" + userPhone +
                ", state=" + state +
                ", createTime=" + createTime +
                '}';
    }
}
