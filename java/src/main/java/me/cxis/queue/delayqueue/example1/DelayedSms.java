package me.cxis.queue.delayqueue.example1;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * 延迟短信
 */
public class DelayedSms implements Delayed {

    /**
     * 短信内容
     */
    private String sms;

    /**
     * 短信创建时间
     */
    private long createTime;

    /**
     * 短信发送时间
     */
    private long sendTime;

    /**
     * 返回剩余的时间
     * @param unit
     * @return
     */
    @Override
    public long getDelay(TimeUnit unit) {
        long remaining = unit.convert(sendTime - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
        System.out.println("剩余时间：" + remaining + "ms");
        return remaining;
    }

    /**
     * 延时队列内部比较排序
     * @param other
     * @return
     */
    @Override
    public int compareTo(Delayed other) {
        return Long.compare(sendTime, ((DelayedSms) other).getSendTime());
    }

    public String getSms() {
        return sms;
    }

    public void setSms(String sms) {
        this.sms = sms;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public long getSendTime() {
        return sendTime;
    }

    public void setSendTime(long sendTime) {
        this.sendTime = sendTime;
    }

    @Override
    public String toString() {
        return "DelayedSms{" +
                "sms='" + sms + '\'' +
                ", createTime=" + createTime +
                ", sendTime=" + sendTime +
                '}';
    }
}
