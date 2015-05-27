package com.zaoqila.util;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * Created by cmcc on 15/5/27.
 */
public class HttpUtil {

    public static void get(String url){
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("http://www.baidu.com");
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpGet);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            System.out.println(response.getStatusLine().getStatusCode());
            HttpEntity entity = response.getEntity();
            System.out.println("response:" + response.getLocale());
            System.out.println("entity:" + entity.getContentEncoding());
            System.out.println("content:" + EntityUtils.toString(entity));
            // do something useful with the response body
            // and ensure it is fully consumed
            EntityUtils.consume(entity);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public static void main(String args[]){
        get("");
    }
}
