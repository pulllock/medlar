package me.cxis.spring.chain.commons_chain;

public class BlacklistCommand extends AbstractCommand {

    @Override
    public boolean execute(CommonContext context) {
        System.out.println("BlacklistCommand...");
        String request = String.valueOf(context.get("request"));
        if (request.contains("hack")) {
            context.put("error", "ip locked");
            return true;
        }
        return false;
    }
}
