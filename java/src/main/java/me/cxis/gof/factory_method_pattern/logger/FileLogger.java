package me.cxis.gof.factory_method_pattern.logger;

public class FileLogger implements Logger {

    @Override
    public void writeLog() {
        System.out.println("File logger...");
    }
}
