package me.cxis.zk;

import org.I0Itec.zkclient.ZkClient;

public class WriteData {

    public static void main(String[] args) {
        ZkClient zkClient = new ZkClient("127.0.0.1:2181", 1000);
        System.out.println("connect to zookeeper!");

        String path = "/MyConfig/db/url";
        zkClient.writeData(path, "jdbc:mysql://192.168.1.1:3306/xxx-1");

        zkClient.close();
    }
}
