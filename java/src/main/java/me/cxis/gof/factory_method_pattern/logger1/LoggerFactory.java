package me.cxis.gof.factory_method_pattern.logger1;

public abstract class LoggerFactory {

    public void wirteLog() {
        Logger logger = this.createLogger();
        logger.writeLog();
    }

    protected abstract Logger createLogger();
}
