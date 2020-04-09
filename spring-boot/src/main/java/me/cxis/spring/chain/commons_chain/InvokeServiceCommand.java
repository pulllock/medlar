package me.cxis.spring.chain.commons_chain;

import org.springframework.stereotype.Component;

@Component
public class InvokeServiceCommand extends AbstractCommand {

    @Override
    public boolean execute(CommonContext context) {
        System.out.println("InvokeServiceCommand...");
        String request = String.valueOf(context.get("request"));

        String result = "result for request: " + request;
        context.put("result", result);
        return false;
    }
}
