package fun.pullock.unit.test.junit5.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackages = {"fun.pullock.unit.test.junit5.dao.mapper"})
public class DataSourceConfig {
}
