package me.cxis.gof.factory_method_pattern.logger;

public class FileLoggerFactory implements LoggerFactory {

    @Override
    public Logger createLogger() {
        // ......
        Logger logger = new FileLogger();
        // ......
        return logger;
    }
}
