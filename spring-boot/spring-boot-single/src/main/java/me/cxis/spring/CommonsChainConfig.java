package me.cxis.spring;

import me.cxis.spring.chain.commons_chain.BlacklistCommand;
import me.cxis.spring.chain.commons_chain.FlowControlCommand;
import me.cxis.spring.chain.commons_chain.InvokeServiceCommand;
import me.cxis.spring.chain.commons_chain.ParamCheckCommand;
import org.apache.commons.chain.Command;
import org.apache.commons.chain.impl.ChainBase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommonsChainConfig {

    @Bean
    public Command blackListCommand() {
        return new BlacklistCommand();
    }

    @Bean
    public Command flowControlCommand() {
        return new FlowControlCommand();
    }

    @Bean
    public Command paramCheckCommand() {
        return new ParamCheckCommand();
    }

    @Bean
    public Command invokeServiceCommand() {
        return new InvokeServiceCommand();
    }

    @Bean
    public ChainBase chains() {
        ChainBase chainBase = new ChainBase();
        chainBase.addCommand(blackListCommand());
        chainBase.addCommand(flowControlCommand());
        chainBase.addCommand(paramCheckCommand());
        chainBase.addCommand(invokeServiceCommand());
        return chainBase;
    }
}
