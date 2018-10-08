package me.cxis.gof.interpreter_pattern.robot;

public class SentenceNode extends AbstractNode {

   private AbstractNode direction;

   private AbstractNode action;

   private AbstractNode distance;

    public SentenceNode(AbstractNode direction, AbstractNode action, AbstractNode distance) {
        this.direction = direction;
        this.action = action;
        this.distance = distance;
    }

    @Override
    public String interpert() {
        return direction.interpert() + action.interpert() + distance.interpert();
    }
}
