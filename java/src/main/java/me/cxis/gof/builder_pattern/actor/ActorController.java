package me.cxis.gof.builder_pattern.actor;

public class ActorController {

    public Actor construct(ActorBuilder builder) {
        builder.buildFace();
        builder.buildSex();
        Actor actor = builder.createActor();
        return actor;
    }
}
