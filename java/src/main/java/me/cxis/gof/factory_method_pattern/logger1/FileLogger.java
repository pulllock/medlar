package me.cxis.gof.factory_method_pattern.logger1;

public class FileLogger implements Logger{

    @Override
    public void writeLog() {
        System.out.println("file logger...");
    }
}
