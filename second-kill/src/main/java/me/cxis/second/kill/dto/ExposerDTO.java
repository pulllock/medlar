package me.cxis.second.kill.dto;

public class ExposerDTO {

    private boolean exposed;

    private String md5;

    private long secondKillId;

    private long now;

    private long start;

    private long end;

    public ExposerDTO(boolean exposed, String md5, long secondKillId) {
        this.exposed = exposed;
        this.md5 = md5;
        this.secondKillId = secondKillId;
    }

    public ExposerDTO(boolean exposed, long now, long start, long end) {
        this.exposed = exposed;
        this.now = now;
        this.start = start;
        this.end = end;
    }

    public ExposerDTO(boolean exposed, long secondKillId) {
        this.exposed = exposed;
        this.secondKillId = secondKillId;
    }

    public boolean isExposed() {
        return exposed;
    }

    public void setExposed(boolean exposed) {
        this.exposed = exposed;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public long getSecondKillId() {
        return secondKillId;
    }

    public void setSecondKillId(long secondKillId) {
        this.secondKillId = secondKillId;
    }

    public long getNow() {
        return now;
    }

    public void setNow(long now) {
        this.now = now;
    }

    public long getStart() {
        return start;
    }

    public void setStart(long start) {
        this.start = start;
    }

    public long getEnd() {
        return end;
    }

    public void setEnd(long end) {
        this.end = end;
    }
}
