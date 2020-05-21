package me.cxis.queue.delayqueue.example1;

import java.util.Date;
import java.util.concurrent.DelayQueue;

public class SmsSender {

    private DelayQueue<DelayedSms> queue = new DelayQueue<>();

    public SmsSender() {
        new Thread(new SmsSendTask()).start();
    }

    /**
     * 发送延时短信
     * @param sms 短信内容
     * @param createTime 短信创建时间
     * @param delay 要延长的时间，毫秒
     */
    public void sendDelaySms(String sms, long createTime, long delay) {
        DelayedSms delayedSms = new DelayedSms();
        delayedSms.setSms(sms);
        delayedSms.setCreateTime(createTime);
        delayedSms.setSendTime(createTime + delay);

        queue.offer(delayedSms);
    }

    private class SmsSendTask implements Runnable {

        @Override
        public void run() {
            while (true) {
                try {
                    DelayedSms sms = queue.take();
                    sendSms(sms);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void sendSms(DelayedSms sms) {
        System.out.println("发送短信：" + sms.getSms() + "，createTime：" + new Date(sms.getCreateTime()) + "，sendTime: " + new Date(sms.getSendTime()) + "，发送时间：" + new Date(System.currentTimeMillis()));
    }
}
