package me.cxis.nacos.config.dynamic.nacos;

/**
 * 默认配置，对应dataId=项目名-default.json
 */

public class DefaultConfig {

    public static DefaultConfig EMPTY = new DefaultConfig();

    /**
     * 配置中配置的id属性
     */
    private Long id = 1L;

    /**
     * 配置中配置的name属性
     */
    private String name = "default name";

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "DefaultConfig{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
