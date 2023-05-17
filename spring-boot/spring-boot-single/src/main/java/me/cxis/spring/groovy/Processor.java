package me.cxis.spring.groovy;

/**
 * 处理器接口，提供给groovy脚本来实现
 */
public interface Processor {

    /**
     * 处理消息，不同的处理方式，可使用不同的groovy脚本实现
     * @param message
     * @return
     */
    String process(String message);
}
