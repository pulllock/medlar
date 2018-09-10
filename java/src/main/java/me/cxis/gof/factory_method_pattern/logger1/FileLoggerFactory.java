package me.cxis.gof.factory_method_pattern.logger1;

public class FileLoggerFactory extends LoggerFactory {

    @Override
    protected Logger createLogger() {
        Logger logger = new FileLogger();
        return logger;
    }
}
