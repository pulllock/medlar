package me.cxis.gof.chain_of_responsibility_pattern.example3;


import static me.cxis.gof.chain_of_responsibility_pattern.example3.GatewayHandlerFactory.getHandler;

public class GatewayService {


    public static void main(String[] args) {
        GatewayHandler gatewayHandler = getHandler();

        System.out.println(gatewayHandler.process("normal_request"));
        System.out.println(gatewayHandler.process("blacklist_request_hack"));
        System.out.println(gatewayHandler.process("param_error_request"));
    }
}
