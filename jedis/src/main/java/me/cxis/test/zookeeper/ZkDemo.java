package me.cxis.test.zookeeper;
import org.apache.zookeeper.*;

import java.io.IOException;

public class ZkDemo {

    public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
        // 创建一个连接
        ZooKeeper zk = new ZooKeeper("127.0.0.1:2180", 60000, new Watcher() {
            // 监控所有被触发的事件
            @Override
            public void process(WatchedEvent watchedEvent) {
                System.out.println("WatchedEvent:" + watchedEvent.getType());
            }
        });

        //查看根节点，true表示监控子节点状态
        System.out.println("ls / => " + zk.getChildren("/", true));

        //判断某个path是否存在
        if (zk.exists("/node", true) == null) {
            // 创建一个给定的节点
            zk.create(
                "/node",
                "新添加的数据".getBytes(),
                ZooDefs.Ids.OPEN_ACL_UNSAFE,
                CreateMode.PERSISTENT
            );
            System.out.println("create /node 新添加的数据");

            // 查看数据
            System.out.println("get /node => " + new String(zk.getData("/node", false, null)));

            // 查看根节点
            System.out.println("ls / => " + zk.getChildren("/", true));

            // 创建一个子目录节点
            if (zk.exists("/node/sub1", true) == null) {
                zk.create(
                    "/node/sub1",
                    "sub1".getBytes(),
                    ZooDefs.Ids.OPEN_ACL_UNSAFE,
                    CreateMode.PERSISTENT
                );

                // 查看node节点
                System.out.println("ls /node => " + zk.getChildren("/node", true));
            }

            //修改节点数据
            if (zk.exists("/node", true) != null) {
                // version为-1，可以匹配任何版本
                zk.setData("/node", "changed".getBytes(), -1);

                // 查看数据
                System.out.println("get /node => " + new String(zk.getData("/node", false, null)));
            }

            // 删除节点
            if (zk.exists("/node/sub1", true) != null) {
                zk.delete("/node/sub1", -1);
                zk.delete("/node", -1);

                // 查看节点
                System.out.println("ls / => " + zk.getChildren("/", true));
            }

            // 关闭连接
            zk.close();
        }
    }
}
