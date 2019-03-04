package me.cxis.zk;

import org.I0Itec.zkclient.ZkClient;
import org.apache.zookeeper.data.Stat;

public class GetData {

    public static void main(String[] args) {
        ZkClient zkClient = new ZkClient("127.0.0.1:2181", 1000);
        System.out.println("connect to zookeeper!");

        String path = "/MyConfig/db/url";

        Stat stat = new Stat();
        String data = zkClient.readData(path, stat);
        System.out.println(data);
        System.out.println(stat);

        zkClient.close();
    }
}
