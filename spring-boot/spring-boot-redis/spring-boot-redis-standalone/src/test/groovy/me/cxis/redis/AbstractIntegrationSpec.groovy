package me.cxis.redis


import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

/**
 * 集成测试需要继承此类，会启动Spring容器
 */
@SpringBootTest
abstract class AbstractIntegrationSpec extends Specification {
}
