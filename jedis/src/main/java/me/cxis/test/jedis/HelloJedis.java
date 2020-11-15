package me.cxis.test.jedis;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by justdoit on 15-4-22.
 */
public class HelloJedis {
    private static Jedis jedis = new Jedis("127.0.0.1");


    public void testConn(){
        try {
            jedis.connect();
            jedis.ping();
            //jedis.quit();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void setTest(){
        try{
            System.out.println("dbSize1:" + jedis.dbSize());
            for(int i = 0 ; i < 100;i++){
                jedis.set("key2-" + i,"value-" + i);
            }
            System.out.println("dbSize2:" + jedis.dbSize());
        }catch (Exception e){

        }
    }

    public void getTest() {
        try {
            for (int i = 0; i < 100; i++) {
                System.out.println(jedis.get("key-" + i));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        String keyString = String.format("u_token","123123123123");
        System.out.println("keyString:" + keyString);
        System.out.println("-------");
        HelloJedis helloJedis = new HelloJedis();
        helloJedis.testConn();
        helloJedis.setTest();
        helloJedis.getTest();
        jedis.quit();

        test(args);
    }

    public static void test(String[] args) {
        String jsonVal = "{\"multistepVals\":[{\"thresholdPrice\":\"2\",\"discountPrice\":\"88\"},{\"thresholdPrice\":\"3\",\"discountPrice\":\"75\"},{\"thresholdPrice\":\"4\",\"discountPrice\":\"66\"}]}";

        List<TestModel> list = new ArrayList<>();

        TestModel model = new TestModel();
        model.setId(1L);
        model.setChannel("xxxxx");
        Date date = new Date();
        model.setEndTime(date.getTime());
        model.setStartTime(model.getEndTime());
        model.setPlatforms(Arrays.asList("XXX", "YYY"));
        model.setRulePlatform(10);
        // model.setJsonVal(JSON.toJSONString(jsonVal));
        model.setJsonVal(jsonVal);

        System.out.println(model);

        list.add(model);

        String json = JSON.toJSONString(list);
        System.out.println(json);


        //////
        try {
            jedis.connect();
            jedis.ping();
            //jedis.quit();

            System.out.println("before set");
            jedis.set("spu",json);
            System.out.println("after set");
            String spu = jedis.get("spu");
            System.out.println(spu);
            JSONArray array = JSON.parseArray(spu);
            for (int i = 0; i < array.size(); i++) {
                JSONObject jsonObject = array.getJSONObject(i);
                String s = jsonObject.getString("jsonVal");
                System.out.println(s);
                JSONObject jsonObject1 = JSON.parseObject(s);
                System.out.println(jsonObject1);

            }
            System.out.println(array);
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            jedis.quit();
        }





    }
}
