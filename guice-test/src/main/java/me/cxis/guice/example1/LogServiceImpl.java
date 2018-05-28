package me.cxis.guice.example1;

public class LogServiceImpl implements LogService {

    @Override
    public void log(String msg) {
        System.out.printf("Log: %s%n", msg);
    }
}
