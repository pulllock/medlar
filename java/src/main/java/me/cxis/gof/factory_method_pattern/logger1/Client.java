package me.cxis.gof.factory_method_pattern.logger1;

public class Client {

    public static void main(String[] args) {
        LoggerFactory factory = (LoggerFactory) XmlUtil.getBean();
        factory.wirteLog();
    }
}
