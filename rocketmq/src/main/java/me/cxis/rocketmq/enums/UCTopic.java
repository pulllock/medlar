package me.cxis.rocketmq.enums;

public enum UCTopic {

    /**
     * 用户注册
     */
    USER_REGISTERED("USER_CENTER", "USER_REGISTERED", "用户注册");

    private String topic;

    private String tag;

    private String desc;

    UCTopic(String topic, String tag, String desc) {
        this.topic = topic;
        this.tag = tag;
        this.desc = desc;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
