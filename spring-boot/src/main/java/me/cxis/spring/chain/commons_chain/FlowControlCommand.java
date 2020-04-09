package me.cxis.spring.chain.commons_chain;

import org.springframework.stereotype.Component;

@Component
public class FlowControlCommand extends AbstractCommand {

    @Override
    public boolean execute(CommonContext context) {
        System.out.println("FlowControlCommand...");
        return false;
    }
}
