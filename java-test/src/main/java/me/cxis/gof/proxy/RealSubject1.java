package me.cxis.gof.proxy;

/**
 * Created by cheng.xi on 2017-04-12 15:38.
 * 真实主题，这里是真正办驾照的人
 */
public class RealSubject1 implements Subject1 {

    @Override
    public String applyForDriverLicense(String name) {
        return name + "的驾照";
    }
}
