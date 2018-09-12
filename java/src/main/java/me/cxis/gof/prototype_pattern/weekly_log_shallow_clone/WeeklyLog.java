package me.cxis.gof.prototype_pattern.weekly_log_shallow_clone;

public class WeeklyLog implements Cloneable {

    private String name;

    private String date;

    private String content;

    private Attachment attachment;

    @Override
    protected WeeklyLog clone() throws CloneNotSupportedException {
        Object object = super.clone();
        return (WeeklyLog) object;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Attachment getAttachment() {
        return attachment;
    }

    public void setAttachment(Attachment attachment) {
        this.attachment = attachment;
    }

    @Override
    public String toString() {
        return "WeeklyLog{" +
            "name='" + name + '\'' +
            ", date='" + date + '\'' +
            ", content='" + content + '\'' +
            ", attachment=" + attachment +
            '}';
    }
}
