package me.cxis.gof.builder_pattern.actor;

public class Client {

    public static void main(String[] args) {
        ActorBuilder builder = (ActorBuilder) XmlUtil.getBean();

        ActorController controller = new ActorController();
        Actor actor = controller.construct(builder);
        System.out.println(actor.getSex());
    }
}
