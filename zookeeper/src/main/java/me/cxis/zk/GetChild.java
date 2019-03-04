package me.cxis.zk;

import org.I0Itec.zkclient.ZkClient;

import java.util.List;

public class GetChild {

    public static void main(String[] args) {
        ZkClient zkClient = new ZkClient("127.0.0.1:2181", 1000);
        System.out.println("connect to zookeeper!");

        List<String> children = zkClient.getChildren("/MyConfig");
        System.out.println(children.toString());

        zkClient.close();
    }
}
