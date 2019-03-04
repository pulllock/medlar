package me.cxis.zk;

import org.I0Itec.zkclient.ZkClient;
import org.apache.zookeeper.CreateMode;

public class CreateNode {

    public static void main(String[] args) {
        ZkClient zkClient = new ZkClient("127.0.0.1:2181", 1000);
        System.out.println("connect to zookeeper!");

        String rootNode = "/MyConfig";
        String dbNode = String.format("%s/%s", rootNode, "db");
        zkClient.createPersistent(dbNode, true);

        String urlNode = String.format("%s/%s", dbNode, "url");
        String value = "jdbc:mysql://127.0.0.1:3306/xxxx";

        String pathResult = zkClient.create(urlNode, value, CreateMode.PERSISTENT);
        System.out.println("create path: " + pathResult);

        zkClient.close();
    }
}
