package me.cxis.spring.chain.commons_chain;

import org.springframework.stereotype.Component;

@Component
public class ParamCheckCommand extends AbstractCommand {

    @Override
    public boolean execute(CommonContext context) {
        System.out.println("ParamCheckCommand...");
        String request = String.valueOf(context.get("request"));
        if (request.contains("error")) {
            context.put("error", "param error!");
            return true;
        }
        return false;
    }
}
