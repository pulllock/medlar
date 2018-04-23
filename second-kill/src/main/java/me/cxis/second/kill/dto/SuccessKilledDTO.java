package me.cxis.second.kill.dto;

import java.util.Date;

public class SuccessKilledDTO {

    private long secondKillId;

    private long userPhone;

    private short state;

    private Date createTime;

    private SecondKillDTO secondKill;

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

    public SecondKillDTO getSecondKill() {
        return secondKill;
    }

    public void setSecondKill(SecondKillDTO secondKillDTO) {
        this.secondKill = secondKillDTO;
    }

    @Override
    public String toString() {
        return "SuccessKilledDTO{" +
                "secondKillId=" + secondKillId +
                ", userPhone=" + userPhone +
                ", state=" + state +
                ", createTime=" + createTime +
                ", secondKill=" + secondKill +
                '}';
    }
}
