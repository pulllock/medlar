package me.cxis.spring;

import me.cxis.spring.chain.commons_chain.Chains;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommonsChainConfig {

    @Bean
    public Chains chains() {
        return new Chains();
    }
}
