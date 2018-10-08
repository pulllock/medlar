package me.cxis.gof.interpreter_pattern.robot;

public class DistanceNode extends AbstractNode {

    private String distance;

    public DistanceNode(String distance) {
        this.distance = distance;
    }

    @Override
    public String interpert() {
        return distance;
    }
}
