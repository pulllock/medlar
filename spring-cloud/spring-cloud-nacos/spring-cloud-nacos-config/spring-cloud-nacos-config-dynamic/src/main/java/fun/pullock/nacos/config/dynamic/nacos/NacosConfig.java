package fun.pullock.nacos.config.dynamic.nacos;

import com.alibaba.cloud.nacos.NacosConfigManager;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.exception.NacosException;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import fun.pullock.nacos.config.dynamic.json.Json;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;

/**
 * 从Nacos中获取配置，并且会在本地进行缓存，使用项目配置文件中默认的命名空间，
 * 默认使用DEFAULT_GROUP，默认的配置格式使用JSON，默认的配置名字为：项目名-default.json
 */
@Component
public class NacosConfig {

    private final static Logger LOGGER = LoggerFactory.getLogger(NacosConfig.class);

    private static String appName;

    private static String defaultDataId;

    private static String DEFAULT_GROUP = "DEFAULT_GROUP";

    private static NacosConfigManager nacosConfigManager;

    private final static Map<String, String> CONFIG_CACHE = new ConcurrentHashMap<>();

    @Resource
    public void setDefaultDataId(@Value("${spring.application.name}") String appName) {
        NacosConfig.appName = appName;
        NacosConfig.defaultDataId = String.format("%s-default.json", appName);
    }

    @Resource
    public void setNacosConfigManager(NacosConfigManager nacosConfigManager) {
        NacosConfig.nacosConfigManager = nacosConfigManager;
    }

    public static String getConfig(String dataId) {
        return CONFIG_CACHE.get(dataId);
    }

    @PostConstruct
    public void init() throws NacosException {
        initListener(defaultDataId, DEFAULT_GROUP);
    }

    public static DefaultConfig defaultConfig() {
        String config = getConfig(defaultDataId);
        if (config == null || config.length() == 0) {
            return DefaultConfig.EMPTY;
        }
        return Json.parseObject(config, DefaultConfig.class);
    }

    public static ConfigService getConfigService() {
        return nacosConfigManager.getConfigService();
    }

    private static void initListener(String dataId, String group) throws NacosException {
        LOGGER.info("add config listener to nacos, dataId: {}, group: {}", dataId, group);
        getConfigService().addListener(dataId, group, new Listener() {
            @Override
            public Executor getExecutor() {
                return null;
            }

            @Override
            public void receiveConfigInfo(String configInfo) {
                LOGGER.info("receive config info from nacos, dataId: {}, group: {}, configInfo: {}", dataId, group, configInfo);
                CONFIG_CACHE.put(dataId, configInfo);
            }
        });
    }
}
