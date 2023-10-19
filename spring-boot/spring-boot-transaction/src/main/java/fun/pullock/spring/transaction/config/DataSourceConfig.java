package fun.pullock.spring.transaction.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@MapperScan(basePackages = {"fun.pullock.spring.transaction.dao.mapper"})
@EnableTransactionManagement
public class DataSourceConfig {
}
