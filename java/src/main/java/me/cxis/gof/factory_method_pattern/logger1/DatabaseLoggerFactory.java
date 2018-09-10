package me.cxis.gof.factory_method_pattern.logger1;

public class DatabaseLoggerFactory extends LoggerFactory {

    @Override
    protected Logger createLogger() {
        Logger logger = new DatabaseLogger();
        return logger;
    }
}
