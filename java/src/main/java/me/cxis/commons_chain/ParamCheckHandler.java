package me.cxis.commons_chain;

public class ParamCheckHandler extends AbstractCommand {

    @Override
    public boolean execute(CommonContext context) {
        String request = String.valueOf(context.get("request"));
        if (request.contains("error")) {
            context.put("error", "param error!");
            return true;
        }
        return false;
    }
}
