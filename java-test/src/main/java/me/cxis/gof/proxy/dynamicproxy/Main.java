package me.cxis.gof.proxy.dynamicproxy;

/**
 * Created by cheng.xi on 2017-04-12 20:06.
 */
public class Main {

    public static void main(String[] args) {
        Subject zhangsan = new RealSubject();
        Subject proxy = new DynamicProxy(zhangsan).getProxy();
        int money = proxy.giveMeMyMoney(1000);
        System.out.println(money);

        //办驾照

        Subject1 shanghai = new RealSubject1();
        Subject1 proxy1 = new DynamicProxy(shanghai).getProxy();
        String result = proxy1.applyForDriverLicense("lisi");
        System.out.println(result);
    }
}
