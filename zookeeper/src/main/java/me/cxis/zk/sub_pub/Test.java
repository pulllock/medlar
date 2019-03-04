package me.cxis.zk.sub_pub;

import org.I0Itec.zkclient.ZkClient;
import org.apache.zookeeper.CreateMode;

public class Test {

    public static void main(String[] args) {
        ZkClient client = new ZkClient("127.0.0.1:2181", 1000);
        String path = client.create("/command", "list", CreateMode.PERSISTENT);
        System.out.println(path);
    }
}
