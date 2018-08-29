package me.cxis.gof.proxy;

/**
 * Created by cheng.xi on 2017-04-12 15:36.
 * 代理模式的主题类，这里代表的是欠钱的人
 */
public interface Subject {
    /**
     * 还给我的钱
     * @param moneyCount 欠钱数
     * @return 还给我的钱数
     */
    int giveMeMyMoney(int moneyCount);
}
