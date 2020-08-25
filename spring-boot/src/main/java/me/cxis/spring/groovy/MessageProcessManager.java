package me.cxis.spring.groovy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 消息处理
 */
@Component
public class MessageProcessManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageProcessManager.class);

    @Resource
    private Processor processorFromClasspathFile;

    @Resource
    private Processor processorFromInline;

    @Resource
    private Processor processorFromDB;

    /**
     * 使用classpath中的groovy脚本
     * @param message
     * @return
     */
    public String processMessageFromClasspathFile(String message) {
        LOGGER.info("origin message: {}", message);
        String result = processorFromClasspathFile.process(message);
        LOGGER.info("process result: {}", result);
        return result;
    }

    /**
     * 使用inline的groovy脚本
     * @param message
     * @return
     */
    public String processMessageFromInline(String message) {
        LOGGER.info("origin message: {}", message);
        String result = processorFromInline.process(message);
        LOGGER.info("process result: {}", result);
        return result;
    }

    /**
     * 使用DB的groovy脚本
     * @param message
     * @return
     */
    public String processMessageFromDB(String message) {
        LOGGER.info("origin message: {}", message);
        String result = processorFromDB.process(message);
        LOGGER.info("process result: {}", result);
        return result;
    }
}
