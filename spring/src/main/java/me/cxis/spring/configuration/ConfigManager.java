package me.cxis.spring.configuration;

public class ConfigManager {

    private ConfigDao configDao;

    public ConfigManager(ConfigDao configDao) {
        this.configDao = configDao;
    }

    public String getConfig() {
        return configDao.getConfig();
    }
}
