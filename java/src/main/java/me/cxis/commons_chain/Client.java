package me.cxis.commons_chain;

import com.alibaba.fastjson.JSON;

public class Client {

    public static void main(String[] args) throws Exception {
        Chains chains = new Chains();
        CommonContext commonContext = new CommonContext();
        commonContext.put("request", "this hack");
        System.out.println(chains.execute(commonContext));
        System.out.println(JSON.toJSONString(commonContext));

        commonContext = new CommonContext();
        commonContext.put("request", "this error");
        System.out.println(chains.execute(commonContext));
        System.out.println(JSON.toJSONString(commonContext));

        commonContext = new CommonContext();
        commonContext.put("request", "this is normal request");
        System.out.println(chains.execute(commonContext));
        System.out.println(JSON.toJSONString(commonContext));
    }
}
