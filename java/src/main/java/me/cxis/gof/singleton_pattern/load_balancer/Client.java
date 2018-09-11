package me.cxis.gof.singleton_pattern.load_balancer;

public class Client {

    public static void main(String[] args) {
        LoadBalancer balancer1 = LoadBalancer.getInstance();
        LoadBalancer balancer2 = LoadBalancer.getInstance();
        LoadBalancer balancer3 = LoadBalancer.getInstance();
        LoadBalancer balancer4 = LoadBalancer.getInstance();

        if (balancer1 == balancer2 && balancer2 == balancer3 && balancer3 == balancer4) {
            System.out.println("同一个负载实例");
        }

        balancer1.addServer("11");
        balancer1.addServer("22");
        balancer1.addServer("33");
        balancer1.addServer("44");

        for (int i = 0; i < 10; i++) {
            String server = balancer1.getServer();
            System.out.println(server);
        }
    }
}
