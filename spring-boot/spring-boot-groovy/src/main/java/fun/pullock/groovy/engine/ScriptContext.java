package fun.pullock.groovy.engine;

public class ScriptContext {

    private Long bizResultId;

    private String param;

    public Long getBizResultId() {
        return bizResultId;
    }

    public void setBizResultId(Long bizResultId) {
        this.bizResultId = bizResultId;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    @Override
    public String toString() {
        return "ScriptContext{" +
                "bizResultId=" + bizResultId +
                ", param='" + param + '\'' +
                '}';
    }
}
