package me.cxis.zk.master_selection;

import java.io.Serializable;

/**
 * 工作服务器信息
 *
 * @author chengxi
 */
public class RunningData implements Serializable {

    private static final long serialVersionUID = -3481410444515814536L;

    private Long cid;

    private String name;

    public Long getCid() {
        return cid;
    }

    public void setCid(Long cid) {
        this.cid = cid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "RunningData{" +
            "cid=" + cid +
            ", name='" + name + '\'' +
            '}';
    }
}
