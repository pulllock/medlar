package me.cxis.spring.chain.commons_chain;

import org.apache.commons.chain.impl.ChainBase;

public class Chains extends ChainBase {

    public Chains() {
        addCommand(new BlacklistCommand());
        addCommand(new FlowControlCommand());
        addCommand(new ParamCheckCommand());
        addCommand(new InvokeServiceCommand());
    }
}
