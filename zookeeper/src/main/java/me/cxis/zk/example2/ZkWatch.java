package me.cxis.zk.example2;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.List;

public class ZkWatch {

    private static final String SERVER_ADDR = "127.0.0.1:2181";

    public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
        ZooKeeper zooKeeper = new ZooKeeper(SERVER_ADDR, 5000, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                System.out.println(String.format("监听到事件WatchedEvent：%s", watchedEvent));
            }
        });

        String rootPath = "/cxis";

        Stat existsForCreate = zooKeeper.exists(rootPath, true);
        System.out.println(String.format("监听结点：%s，监听动作：创建，监听方法：exists，状态Stat：%s", rootPath, existsForCreate));

        String createResult1 = zooKeeper.create(rootPath, null, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        System.out.println(String.format("创建节点：%s，结果：%s", rootPath, createResult1));

        String childPath = "/cxis/child_1";

        List<String> children = zooKeeper.getChildren(rootPath, true);
        System.out.println(String.format("获取子节点，节点：%s，监听动作：删除，监听方法：getChildren，结果：%s", rootPath, children));

        Stat existsForChildCreate = zooKeeper.exists(childPath, true);
        System.out.println(String.format("监听结点：%s，监听动作：创建，监听方法：exists，状态Stat：%s", childPath, existsForChildCreate));

        String createResult2 = zooKeeper.create(childPath, null, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        System.out.println(String.format("创建节点：%s，结果：%s", childPath, createResult2));

        Thread.sleep(300);
    }

    public static void main3(String[] args) throws IOException, KeeperException, InterruptedException {
        ZooKeeper zooKeeper = new ZooKeeper(SERVER_ADDR, 5000, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                System.out.println(String.format("监听到事件WatchedEvent：%s", watchedEvent));
            }
        });

        String rootPath = "/cxis";

        String createResult1 = zooKeeper.create(rootPath, "cxis_value".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        System.out.println(String.format("创建节点：%s，结果：%s", rootPath, createResult1));

        Stat existsForDelete = zooKeeper.exists(rootPath, true);
        System.out.println(String.format("监听结点：%s，监听动作：变更，监听方法：exists，状态Stat：%s", rootPath, existsForDelete));

        Stat statForGetData = new Stat();
        String dataForGet = new String(zooKeeper.getData(rootPath, true, statForGetData));
        System.out.println(String.format("获取数据，节点：%s，监听动作：变更，监听方法：getData，状态Stat：%s，结果：%s", rootPath, statForGetData, dataForGet));

        Stat statForSetData = zooKeeper.setData(rootPath, "cxis_value_new".getBytes(), -1);
        System.out.println(String.format("变更数据，节点：%s，状态Stat：%s", rootPath, statForSetData));

        zooKeeper.delete(rootPath, -1);
        System.out.println(String.format("删除节点：%s", rootPath));

        Thread.sleep(300);
    }

    public static void main2(String[] args) throws IOException, KeeperException, InterruptedException {
        ZooKeeper zooKeeper = new ZooKeeper(SERVER_ADDR, 5000, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                System.out.println(String.format("监听到事件WatchedEvent：%s", watchedEvent));
            }
        });

        String rootPath = "/cxis";

        String createResult1 = zooKeeper.create(rootPath, "cxis_value".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        System.out.println(String.format("创建节点：%s，结果：%s", rootPath, createResult1));

        Stat existsForDelete = zooKeeper.exists(rootPath, true);
        System.out.println(String.format("监听结点：%s，监听动作：删除，监听方法：exists，状态Stat：%s", rootPath, existsForDelete));

        Stat statForGetData = new Stat();
        String dataForGet = new String(zooKeeper.getData(rootPath, true, statForGetData));
        System.out.println(String.format("获取数据，节点：%s，监听动作：删除，监听方法：getData，状态Stat：%s，结果：%s", rootPath, statForGetData, dataForGet));

        List<String> children = zooKeeper.getChildren(rootPath, true);
        System.out.println(String.format("获取子节点，节点：%s，监听动作：删除，监听方法：getChildren，结果：%s", rootPath, children));

        zooKeeper.delete(rootPath, -1);
        System.out.println(String.format("删除节点：%s", rootPath));

        Thread.sleep(300);
    }

    public static void main1(String[] args) throws IOException, KeeperException, InterruptedException {
        ZooKeeper zooKeeper = new ZooKeeper(SERVER_ADDR, 5000, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                System.out.println(String.format("监听到事件WatchedEvent：%s", watchedEvent));
            }
        });

        String rootPath = "/cxis";

        Stat existsForCreate = zooKeeper.exists(rootPath, true);
        System.out.println(String.format("监听结点：%s，监听动作：创建，监听方法：exists，状态Stat：%s", rootPath, existsForCreate));

        String createResult1 = zooKeeper.create(rootPath, null, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        System.out.println(String.format("创建节点：%s，结果：%s", rootPath, createResult1));

        Thread.sleep(300);
    }
}
