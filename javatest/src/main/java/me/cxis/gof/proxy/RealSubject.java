package me.cxis.gof.proxy;

/**
 * Created by cheng.xi on 2017-04-12 15:38.
 * 真实主题，这里代表的是欠我钱的人
 */
public class RealSubject implements Subject {
    @Override
    public int giveMeMyMoney(int moneyCount) {
        return moneyCount;
    }
}
