package me.cxis.zk;

import org.I0Itec.zkclient.ZkClient;

public class CreateSession {

    public static void main(String[] args) {
        ZkClient zkClient = new ZkClient("127.0.0.1:2181", 1000);
        System.out.println("connect to zookeeper!");
        zkClient.close();
    }
}
