package me.cxis.gof.proxy;

/**
 * Created by cheng.xi on 2017-04-12 15:40.
 * 代理，这里是专门替人办驾照的机构
 *
 */
public class Proxy1 implements Subject1{

    /**
     * 办驾照的交通局
     */
    private Subject1 subject;

    public Proxy1(Subject1 subject){
        this.subject = subject;
    }

    @Override
    public String applyForDriverLicense(String name) {
        System.out.println("办事之前先收取点费用");
        System.out.println("开始办事");
        String result = this.subject.applyForDriverLicense(name);
        System.out.println("办完了");
        return result;
    }
}
