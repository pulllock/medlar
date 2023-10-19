package fun.pullock.unit.test.spock.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackages = {"fun.pullock.unit.test.spock.dao.mapper"})
public class DataSourceConfig {
}
