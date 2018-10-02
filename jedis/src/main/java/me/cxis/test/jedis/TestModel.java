package me.cxis.test.jedis;

import java.util.List;

public class TestModel {

    private String channel;

    private Long endTime;

    private Long id;

    private List<String> platforms;

    private int rulePlatform;

    private Long startTime;

    private String jsonVal;

    @Override
    public String toString() {
        return "TestModel{" +
            "channel='" + channel + '\'' +
            ", endTime=" + endTime +
            ", id=" + id +
            ", platforms=" + platforms +
            ", rulePlatform=" + rulePlatform +
            ", startTime=" + startTime +
            ", jsonVal='" + jsonVal + '\'' +
            '}';
    }

    public String getJsonVal() {
        return jsonVal;
    }

    public void setJsonVal(String jsonVal) {
        this.jsonVal = jsonVal;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<String> getPlatforms() {
        return platforms;
    }

    public void setPlatforms(List<String> platforms) {
        this.platforms = platforms;
    }

    public int getRulePlatform() {
        return rulePlatform;
    }

    public void setRulePlatform(int rulePlatform) {
        this.rulePlatform = rulePlatform;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

}
