package me.cxis.gof.factory_method_pattern.logger;

public class DatabaseLoggerFactory implements LoggerFactory {

    @Override
    public Logger createLogger() {
        // .....
        Logger logger = new DatabaseLogger();
        // ......
        return logger;
    }
}
