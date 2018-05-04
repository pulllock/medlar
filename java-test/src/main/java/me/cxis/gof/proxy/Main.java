package me.cxis.gof.proxy;

/**
 * Created by cheng.xi on 2017-04-12 15:47.
 * 这里是我，我去找代理要钱，代理帮我找张三要钱
 */
public class Main {
    public static void main(String[] args) {
        //欠钱的人，张三
        Subject zhangsan = new RealSubject();

        //代理，招代理的时候，需要提供张三的信息
        Proxy proxy = new Proxy(zhangsan);

        //代理去要钱，然后给我
        int money = proxy.giveMeMyMoney(1000);
        System.out.println("我：我的钱要回来了：" + money);

        System.out.println("我又找人去办驾照");
        //指定去哪个交通局办理
        Subject1 shanghai = new RealSubject1();
        //代理
        Proxy1 proxy1 = new Proxy1(shanghai);

        String result = proxy1.applyForDriverLicense("lisi");
        System.out.println(result);
    }
}
