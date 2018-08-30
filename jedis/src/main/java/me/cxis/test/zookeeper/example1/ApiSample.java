package me.cxis.test.zookeeper.example1;

import org.apache.zookeeper.*;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

import static org.apache.zookeeper.CreateMode.EPHEMERAL;
import static org.apache.zookeeper.Watcher.Event.KeeperState.SyncConnected;
import static org.apache.zookeeper.ZooDefs.Ids.OPEN_ACL_UNSAFE;

public class ApiSample implements Watcher {

    private static final String HOSTS = "127.0.0.1:2181";

    private static final int TIMEOUT = 6000;

    private ZooKeeper zk = null;

    private CountDownLatch connectedSignal = new CountDownLatch(1);

    public void createConnection(String hosts, int timeout) {
        this.releaseConnection();
        try {
            zk = new ZooKeeper(hosts, timeout, this);
            connectedSignal.await();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void releaseConnection() {
        if (null != zk) {
            try {
                zk.close();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean createPath(String path, String data) {
        try {
            this.zk.create(path, data.getBytes(), OPEN_ACL_UNSAFE, EPHEMERAL);
            return true;
        } catch (KeeperException | InterruptedException e) {
            e.printStackTrace();
            return false;
        }
    }

    public String readData(String path) {
        try {
            return new String(zk.getData(path, false, null));
        } catch (KeeperException | InterruptedException e) {
            e.printStackTrace();
            return "";
        }
    }

    public boolean writeData(String path, String data) {
        try {
            this.zk.setData(path, data.getBytes(), -1);
            return true;
        } catch (KeeperException | InterruptedException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void deteleNode(String path) {
        try {
            zk.delete(path, -1);
        } catch (InterruptedException | KeeperException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        if (SyncConnected == watchedEvent.getState()) {
            connectedSignal.countDown();
        }
    }

    public static void main(String[] args) {
        ApiSample sample = new ApiSample();
        sample.createConnection(HOSTS, TIMEOUT);
        sample.deteleNode("/my_node");
        if (sample.createPath("/my_node", "初始化数据")) {
            System.out.println(sample.readData("/my_node"));
            sample.writeData("/my_node", "更新了");
            System.out.println(sample.readData("/my_node"));
            sample.deteleNode("/my_node");
        }
        sample.releaseConnection();
    }
}
