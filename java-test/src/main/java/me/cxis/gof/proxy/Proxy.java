package me.cxis.gof.proxy;

/**
 * Created by cheng.xi on 2017-04-12 15:40.
 * 代理，这里是专门要钱的机构
 * 为什么他要实现Subject？我现在找代理帮我要钱，张三已经不欠我了，
 * 张三欠的是代理的，代理现在是欠我钱的人了
 */
public class Proxy implements Subject{

    /**
     * 欠钱的人，代理会接受消息，都是关于欠钱人的信息
     */
    private Subject subject;

    public Proxy(Subject subject){
        this.subject = subject;
    }

    @Override
    public int giveMeMyMoney(int moneyCount) {
        System.out.println("办事之前先收取点费用");
        System.out.println("开始办事");
        //代理找张三要钱
        int money = this.subject.giveMeMyMoney(moneyCount);
        System.out.println("办完了");
        return money;
    }
}
