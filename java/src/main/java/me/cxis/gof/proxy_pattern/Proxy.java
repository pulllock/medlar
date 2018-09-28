package me.cxis.gof.proxy_pattern;

public class Proxy extends Subject{

    private Subject subject = new RealSubject();

    @Override
    public void request() {
        preRequest();
        subject.request();
        postRequest();
    }

    private void preRequest() {

    }

    private void postRequest() {

    }
}
