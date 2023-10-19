package fun.pullock.mvc.model;

public class DefaultMessage {

    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "DefaultMessage{" +
                "msg='" + msg + '\'' +
                '}';
    }
}
