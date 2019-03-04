package me.cxis.zk;

import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;

import java.util.concurrent.TimeUnit;

public class SubscribeDataChanges {

    public static void main(String[] args) throws InterruptedException {
        ZkClient zkClient = new ZkClient("127.0.0.1:2181", 1000);
        System.out.println("connect to zookeeper!");

        zkClient.subscribeDataChanges("/MyConfig/db/url", new ZkDataListener());
        TimeUnit.MINUTES.sleep(1);
        zkClient.close();
    }

    private static class ZkDataListener implements IZkDataListener {

        @Override
        public void handleDataChange(String dataPath, Object data) throws Exception {
            System.out.println(dataPath);
            System.out.println(data);
        }

        @Override
        public void handleDataDeleted(String dataPath) throws Exception {
            System.out.println(dataPath);
        }
    }
}
