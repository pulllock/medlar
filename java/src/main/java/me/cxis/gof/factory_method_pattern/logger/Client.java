package me.cxis.gof.factory_method_pattern.logger;

public class Client {

    public static void main(String[] args) {
        LoggerFactory factory = (LoggerFactory) XmlUtil.getBean();
        Logger logger = factory.createLogger();
        logger.writeLog();
    }
}
