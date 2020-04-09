package me.cxis.gof.chain_of_responsibility_pattern.example2;


public class GatewayHandlerFactory {

    public static GatewayHandler getGatewayHandler() {
        GatewayHandler flowControlHandler = new FlowControlHandler();
        GatewayHandler blacklistHandler = new BlacklistHandler();
        GatewayHandler paramCheckHandler = new ParamCheckHandler();
        GatewayHandler invokeServiceHandler = new InvokeServiceHandler();

        flowControlHandler.setNextHandler(blacklistHandler);
        blacklistHandler.setNextHandler(paramCheckHandler);
        paramCheckHandler.setNextHandler(invokeServiceHandler);
        return flowControlHandler;
    }
}
