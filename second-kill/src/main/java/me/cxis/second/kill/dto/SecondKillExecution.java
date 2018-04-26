package me.cxis.second.kill.dto;

public class SecondKillExecution {

    private long secondKillId;

    private int state;

    private String stateInfo;

    private SuccessKilledDTO successKilled;

    public SecondKillExecution(long secondKillId, int state, String stateInfo, SuccessKilledDTO successKilled) {
        this.secondKillId = secondKillId;
        this.state = state;
        this.stateInfo = stateInfo;
        this.successKilled = successKilled;
    }

    public SecondKillExecution(long secondKillId, int state, String stateInfo) {
        this.secondKillId = secondKillId;
        this.state = state;
        this.stateInfo = stateInfo;
    }

    public long getSecondKillId() {
        return secondKillId;
    }

    public void setSecondKillId(long secondKillId) {
        this.secondKillId = secondKillId;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public void setStateInfo(String stateInfo) {
        this.stateInfo = stateInfo;
    }

    public SuccessKilledDTO getSuccessKilled() {
        return successKilled;
    }

    public void setSuccessKilled(SuccessKilledDTO successKilled) {
        this.successKilled = successKilled;
    }
}
