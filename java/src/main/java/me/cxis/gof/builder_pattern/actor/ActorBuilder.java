package me.cxis.gof.builder_pattern.actor;

public abstract class ActorBuilder {

    Actor actor = new Actor();

    protected abstract void buildType();
    protected abstract void buildSex();
    protected abstract void buildFace();

    public Actor createActor() {
        return actor;
    }
}
