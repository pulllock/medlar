package me.cxis.gof.proxy.dynamicproxy;

/**
 * Created by cheng.xi on 2017-04-12 19:58.
 * 真实主题，这里代表的是欠我钱的人
 */
public class RealSubject implements Subject {
    @Override
    public int giveMeMyMoney(int moneyCount) {
        return moneyCount;
    }
}
