package me.cxis.test.zookeeper.example1;

import org.apache.zookeeper.*;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

public class ZKConnection {

    /**
     * server列表，逗号分隔
     */
    protected String hosts = "localhost:2181";

    /**
     * 连接超时时间，毫秒
     */
    private static final int TIME_OUT = 6000;

    private CountDownLatch connectedSignal = new CountDownLatch(1);

    protected ZooKeeper zk;

    /**
     * 连接
     */
    public void connect() throws InterruptedException, IOException {
        zk = new ZooKeeper(hosts, TIME_OUT, new ConnWatcher());
        // 等待连接完成
        connectedSignal.await();
    }

    private class ConnWatcher implements Watcher {
        @Override
        public void process(WatchedEvent watchedEvent) {
            if (watchedEvent.getState() == Event.KeeperState.SyncConnected) {
                connectedSignal.countDown();
            }
        }
    }

    /**
     * 创建临时节点
     * @param path znode的路径
     * @param data 数据
     * @throws KeeperException
     * @throws InterruptedException
     */
    public void create(String path, byte[] data) throws KeeperException, InterruptedException {
        zk.create(path, data, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
    }
}
