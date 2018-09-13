package me.cxis.gof.builder_pattern.actor;

public class HeroBuilder extends ActorBuilder {

    @Override
    protected void buildType() {
        actor.setType("xxx");
    }

    @Override
    protected void buildSex() {

    }

    @Override
    protected void buildFace() {

    }
}
