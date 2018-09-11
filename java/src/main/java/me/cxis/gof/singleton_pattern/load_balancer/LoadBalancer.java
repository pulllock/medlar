package me.cxis.gof.singleton_pattern.load_balancer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LoadBalancer {

    private static LoadBalancer instance = null;

    private List<String> serverList = null;

    private LoadBalancer() {
        serverList = new ArrayList<>();
    }

    public static LoadBalancer getInstance() {
        if (instance == null) {
            instance = new LoadBalancer();
        }

        return instance;
    }

    public void addServer(String server) {
        serverList.add(server);
    }

    public void removeServer(String server) {
        serverList.remove(server);
    }

    public String getServer() {
        Random random = new Random();
        int i = random.nextInt(serverList.size());
        return serverList.get(i);
    }
}
