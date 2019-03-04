package me.cxis.zk;

import org.I0Itec.zkclient.ZkClient;

public class NodeDelete {

    public static void main(String[] args) {
        ZkClient zkClient = new ZkClient("127.0.0.1:2181", 1000);
        System.out.println("connect to zookeeper!");

        boolean result = zkClient.deleteRecursive("/MyConfig");
        System.out.println(result);

        result = zkClient.delete("/MyConfig");
        System.out.println(result);

        zkClient.close();
    }
}
