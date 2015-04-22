package cheng.xi.test.jedis;

import redis.clients.jedis.Jedis;

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
                jedis.set("key-" + i,"value-" + i);
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
        System.out.println("-------");
        HelloJedis helloJedis = new HelloJedis();
        helloJedis.testConn();
        helloJedis.setTest();
        helloJedis.getTest();
        jedis.quit();
    }
}
