package me.cxis.netty.example.time;

import java.util.Date;

public class UnixTime {

    private long value;

    public UnixTime() {
        this(System.currentTimeMillis() / 1000L + 2208988800L);
    }

    public UnixTime(long value) {
        this.value = value;
    }

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return new Date(getValue() - 2208988800L * 1000L).toString();
    }
}
