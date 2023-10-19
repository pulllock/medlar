package fun.pullock.groovy.model;

import java.io.Serializable;

public class SubmitResult implements Serializable {

    private static final long serialVersionUID = 2323153327443145489L;

    /**
     * 业务字段
     */
    private String bizDesc;

    /**
     * 脚本执行后的结果，是一个Json字符串
     */
    private String scriptResult;

    public String getBizDesc() {
        return bizDesc;
    }

    public void setBizDesc(String bizDesc) {
        this.bizDesc = bizDesc;
    }

    public String getScriptResult() {
        return scriptResult;
    }

    public void setScriptResult(String scriptResult) {
        this.scriptResult = scriptResult;
    }

    @Override
    public String toString() {
        return "SubmitResult{" +
                "bizDesc='" + bizDesc + '\'' +
                ", scriptResult='" + scriptResult + '\'' +
                '}';
    }
}
