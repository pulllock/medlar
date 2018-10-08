package me.cxis.gof.interpreter_pattern.robot;

public class ActionNode extends AbstractNode {

    private String action;

    public ActionNode(String action) {
        this.action = action;
    }

    @Override
    public String interpert() {
        if (action.equalsIgnoreCase("move")) {
            return "移动";
        } else if (action.equalsIgnoreCase("run")) {
            return "快速移动";
        } else {
            return "无效指令";
        }
    }
}
