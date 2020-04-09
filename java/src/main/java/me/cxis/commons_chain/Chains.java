package me.cxis.commons_chain;

import org.apache.commons.chain.impl.ChainBase;

public class Chains extends ChainBase {

    public Chains() {
        addCommand(new BlacklistHandler());
        addCommand(new FlowControlHandler());
        addCommand(new ParamCheckHandler());
        addCommand(new InvokeServiceHandler());
    }
}
