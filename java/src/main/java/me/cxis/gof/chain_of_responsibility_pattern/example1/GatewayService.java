package me.cxis.gof.chain_of_responsibility_pattern.example1;

public class GatewayService {


    public static void main(String[] args) {
        GatewayHandler flowControlHandler = new FlowControlHandler();
        GatewayHandler blacklistHandler = new BlacklistHandler();
        GatewayHandler paramCheckHandler = new ParamCheckHandler();
        GatewayHandler invokeServiceHandler = new InvokeServiceHandler();

        flowControlHandler.setNextHandler(blacklistHandler);
        blacklistHandler.setNextHandler(paramCheckHandler);
        paramCheckHandler.setNextHandler(invokeServiceHandler);

        System.out.println(flowControlHandler.process("normal_request"));
        System.out.println(flowControlHandler.process("blacklist_request_hack"));
        System.out.println(flowControlHandler.process("param_error_request"));
    }
}
