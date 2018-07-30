package me.cxis.spring.service;

import com.taobao.tair.impl.DefaultTairManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class TairManagerConfig {

    @Bean(initMethod = "init", destroyMethod = "close")
    public DefaultTairManager tairManager(
        @Value("${tair.master}") String masterServer,
        @Value("${tair.slave}") String slaveServer,
        @Value("${tair.groupName}") String groupName
    ) {
        DefaultTairManager tairManager = new DefaultTairManager();
        tairManager.setConfigServerList(
            Arrays.asList(
                masterServer,
                slaveServer
            )
        );
        tairManager.setGroupName(groupName);
        return tairManager;
    }
}
