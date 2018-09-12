package me.cxis.gof.prototype_pattern.weekly_log_deep_clone;

import java.io.*;

public class WeeklyLog implements Serializable {

    private String name;

    private String date;

    private String content;

    private Attachment attachment;

    public WeeklyLog deepClone() throws IOException, ClassNotFoundException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
        objectOutputStream.writeObject(this);

        ByteArrayInputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());
        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
        return (WeeklyLog) objectInputStream.readObject();
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
