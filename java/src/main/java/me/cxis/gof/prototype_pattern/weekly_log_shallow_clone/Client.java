package me.cxis.gof.prototype_pattern.weekly_log_shallow_clone;

public class Client {

    public static void main(String[] args) throws CloneNotSupportedException {
        WeeklyLog previous = new WeeklyLog();
        Attachment attachment = new Attachment();
        previous.setAttachment(attachment);

        WeeklyLog newLog = previous.clone();

        System.out.println(previous == newLog);

        System.out.println(previous.getAttachment() == newLog.getAttachment());
    }
}
