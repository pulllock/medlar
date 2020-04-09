package me.cxis.commons_chain;

public class Client {

    public static void main(String[] args) throws Exception {
        Chains chains = new Chains();
        CommonContext commonContext = new CommonContext();
        commonContext.put("request", "this hack");
        System.out.println(chains.execute(commonContext));
        // System.out.println(JSON.tojsonString(commonContext));

        commonContext.put("request", "this error");
        System.out.println(chains.execute(commonContext));

        commonContext.put("request", "this is normal request");
        System.out.println(chains.execute(commonContext));
    }
}
