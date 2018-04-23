package me.cxis.second.kill.dao.model;

import java.util.Date;

public class SecondKillDO {

    private long secondKillId;

    private String name;

    private int mumber;

    private Date startTime;

    private Date endTime;

    private Date createTime;

    public long getSecondKillId() {
        return secondKillId;
    }

    public void setSecondKillId(long secondKillId) {
        this.secondKillId = secondKillId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMumber() {
        return mumber;
    }

    public void setMumber(int mumber) {
        this.mumber = mumber;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "SecondKillDTO{" +
                "secondKillId=" + secondKillId +
                ", name='" + name + '\'' +
                ", mumber=" + mumber +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", createTime=" + createTime +
                '}';
    }
}
