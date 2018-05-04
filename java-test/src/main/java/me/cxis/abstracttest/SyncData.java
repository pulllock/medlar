package me.cxis.abstracttest;

/**
 * Created by cheng.xi on 2017-10-27 22:58.
 */
public class SyncData extends AbstractDataConverter{

    @Override
    public String convertData(ConvertContext context) {
        String accessToken = "";
        if (!context.getKey().equals("") && !context.getToken().equals("")) {
            accessToken = this.getAccessToken();
        }
        this.sendAndReceive("xxxx");
        return null;
    }
}
