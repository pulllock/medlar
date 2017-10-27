package me.cxis.abstracttest;

/**
 * Created by cheng.xi on 2017-10-27 22:54.
 */
public abstract class AbstractDataConverter implements DataConverter{

    public abstract String convertData(ConvertContext data);

    public Object sendAndReceive(String param) {
        System.out.println("param:" + param);
        return null;
    }

    public String getAccessToken() {
        System.out.println("getAccessToken");
        this.sendAndReceive("access token...");
        return "access token";
    }
}
