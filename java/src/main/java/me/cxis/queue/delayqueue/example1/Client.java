package me.cxis.queue.delayqueue.example1;

public class Client {

    public static void main(String[] args) throws InterruptedException {
        SmsSender smsSender = new SmsSender();

        long createTime = System.currentTimeMillis();
        smsSender.sendDelaySms("短信1", createTime, 20 * 1000);

        smsSender.sendDelaySms("短信2", createTime, 30 * 1000);

        smsSender.sendDelaySms("短信3", createTime, 10 * 1000);

    }
}
