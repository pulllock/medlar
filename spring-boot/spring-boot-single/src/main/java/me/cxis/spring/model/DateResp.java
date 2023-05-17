package me.cxis.spring.model;

import java.io.Serializable;
import java.util.Date;

public class DateResp implements Serializable {

    private Date startTime;

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    @Override
    public String toString() {
        return "DateReq{" +
                "startTime=" + startTime +
                '}';
    }

}
