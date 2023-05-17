package me.cxis.unit.test.spock.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackages = {"me.cxis.unit.test.spock.dao.mapper"})
public class DataSourceConfig {
}
