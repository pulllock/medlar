package me.cxis.gof.chain_of_responsibility_pattern.example3;

public enum GatewayEnum {
    FLOW_CONTROL(new GatewayDO(1, "流控", "me.cxis.gof.chain_of_responsibility_pattern.example3.FlowControlHandler", null, 2)),
    BLACKLIST(new GatewayDO(2, "黑名单", "me.cxis.gof.chain_of_responsibility_pattern.example3.BlacklistHandler", 1, 3)),
    PARAM_CHECK(new GatewayDO(3, "参数检查", "me.cxis.gof.chain_of_responsibility_pattern.example3.ParamCheckHandler", 2, 4)),
    INVOKE_SERVICE(new GatewayDO(4, "调用服务", "me.cxis.gof.chain_of_responsibility_pattern.example3.InvokeServiceHandler", 3, null))
    ;

    GatewayEnum(GatewayDO gatewayDO) {
        this.gatewayDO = gatewayDO;
    }

    private GatewayDO gatewayDO;

    public GatewayDO getGatewayDO() {
        return gatewayDO;
    }
}
