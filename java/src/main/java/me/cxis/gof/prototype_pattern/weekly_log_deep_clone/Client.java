package me.cxis.gof.prototype_pattern.weekly_log_deep_clone;

import java.io.IOException;

public class Client {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        WeeklyLog previous = new WeeklyLog();
        Attachment attachment = new Attachment();
        previous.setAttachment(attachment);

        WeeklyLog newLog = previous.deepClone();

        System.out.println(previous == newLog);

        System.out.println(previous.getAttachment() == newLog.getAttachment());
    }
}
