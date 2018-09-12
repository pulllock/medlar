package me.cxis.gof.prototype_pattern.weekly_log;

public class Client {

    public static void main(String[] args) throws CloneNotSupportedException {
        WeeklyLog previous = new WeeklyLog();
        previous.setName("xxx");
        previous.setDate("xsdf");
        previous.setContent("内容");

        System.out.println(previous);

        WeeklyLog newLog = previous.clone();
        newLog.setName("xxwww");
        System.out.println(newLog.getDate() == previous.getDate());
    }
}
