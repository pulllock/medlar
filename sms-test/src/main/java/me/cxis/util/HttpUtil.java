package me.cxis.util;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by cmcc on 15/5/27.
 */
public class HttpUtil {

    private static final String URL = "http://112.124.17.46:7001/send?username=user&password=888888&mobile=13900000000&content=短信内容【千橙科技】";

    public static void get(String url){
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
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

    public static void post(String url) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        //nvps.add(new BasicNameValuePair("username", "vip"));
        //nvps.add(new BasicNameValuePair("password", "secret"));
        httpPost.setEntity(new UrlEncodedFormEntity(nvps));

        CloseableHttpResponse response = httpClient.execute(httpPost);

        try {
            System.out.println(response.getStatusLine());
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
            response.close();
        }
    }

    public static void main(String args[]){
        //get(URL);
        try {
            post(URL);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
