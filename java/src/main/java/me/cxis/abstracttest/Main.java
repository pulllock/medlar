package me.cxis.abstracttest;

/**
 * Created by cheng.xi on 2017-10-27 23:01.
 */
public class Main {

    public static void main(String[] args) {
        ConvertContext context = new ConvertContext();
        context.setKey("key");
        context.setToken("token");
        SyncData syncData = new SyncData();
        syncData.convertData(context);
    }
}
