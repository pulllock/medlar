package me.cxis.zk;

import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.ZkClient;

import java.util.List;

public class SubscribeChildNodeChanges {

    public static void main(String[] args) throws InterruptedException {
        ZkClient zkClient = new ZkClient("127.0.0.1:2181", 1000);
        System.out.println("connect to zookeeper!");

        zkClient.subscribeChildChanges("/MyConfig", new ZkChildListener());
        Thread.sleep(60000);
        zkClient.close();
    }

    private static class ZkChildListener implements IZkChildListener {

        @Override
        public void handleChildChange(String parentPath, List<String> currentChilds) throws Exception {
            System.out.println(parentPath);
            System.out.println(currentChilds);
        }
    }
}
