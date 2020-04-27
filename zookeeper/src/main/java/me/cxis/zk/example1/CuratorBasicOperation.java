package me.cxis.zk.example1;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.framework.api.CuratorEventType;
import org.apache.curator.framework.api.CuratorListener;
import org.apache.curator.framework.recipes.cache.*;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.framework.state.ConnectionStateListener;
import org.apache.curator.retry.RetryNTimes;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

import java.util.List;

public class CuratorBasicOperation {

    private static final String SERVER_ADDR = "127.0.0.1:2181";

    public static void main(String[] args) throws Exception {

        /**
         * 会话超时时间5s
         * 连接超时时间5s
         * 重试策略：最多重试3次，每次重试间隔1s
         */
        CuratorFramework curatorClient = CuratorFrameworkFactory
            .builder()
            .connectString(SERVER_ADDR)
            .sessionTimeoutMs(5000)
            .connectionTimeoutMs(5000)
            .retryPolicy(new RetryNTimes(3, 1000))
            .build();

        // 连接状态监听
        curatorClient
            .getConnectionStateListenable()
            .addListener(new ConnectionStateListener() {
                @Override
                public void stateChanged(CuratorFramework curatorFramework, ConnectionState connectionState) {
                    System.out.println("连接状态监听ConnectionState: " + connectionState);
                    if (connectionState == ConnectionState.RECONNECTED) {
                        System.out.println("连接状态监听reconnect, redo something");
                    }
                }
            });

        /**
         * 主要针对background通知和错误通知，
         * 对于节点的创建或修改则不会触发监听事件
         */
        curatorClient
            .getCuratorListenable()
            .addListener(new CuratorListener() {
                @Override
                public void eventReceived(CuratorFramework curatorFramework, CuratorEvent curatorEvent) throws Exception {
                    System.out.println("监听CuratorEvent: " + curatorEvent);
                    if (curatorEvent.getType() == CuratorEventType.WATCHED) {
                        System.out.println("监听watched, do something");
                    }
                }
            });

        curatorClient.start();

        // 节点事件监听
        NodeCache nodeCache = new NodeCache(curatorClient, "/my_root/cxis_2/config");
        nodeCache.start();
        nodeCache
            .getListenable()
            .addListener(new NodeCacheListener() {
                @Override
                public void nodeChanged() throws Exception {
                    String result = new String(nodeCache.getCurrentData().getData());
                    System.out.println("节点事件监听nodeChanged: " + result);
                }
            });

        // 子节点事件监听
        PathChildrenCache childrenCache = new PathChildrenCache(curatorClient, "/my_root", true);
        childrenCache.start();
        childrenCache
            .getListenable()
            .addListener(new PathChildrenCacheListener() {
                @Override
                public void childEvent(CuratorFramework curatorFramework, PathChildrenCacheEvent pathChildrenCacheEvent) throws Exception {
                    System.out.println("子节点事件监听PathChildrenCacheEvent: " + pathChildrenCacheEvent);
                }
            });

        // 创建节点
        String result1 = curatorClient
            .create()
            .creatingParentsIfNeeded()
            .withMode(CreateMode.PERSISTENT)
            .forPath("/my_root/cxis_1/config", "this is value for cxis_1".getBytes());

        System.out.println("创建节点result1: " + result1);

        String result2 = curatorClient
            .create()
            .creatingParentsIfNeeded()
            .withMode(CreateMode.PERSISTENT)
            .forPath("/my_root/cxis_2/config", "this is value for cxis_2".getBytes());

        System.out.println("创建节点result2: " + result2);

        String result3 = curatorClient
            .create()
            .creatingParentsIfNeeded()
            .withMode(CreateMode.PERSISTENT)
            .forPath("/my_root/cxis_3/config", "this is value for cxis_3".getBytes());

        System.out.println("创建节点result3: " + result3);

        // 获取子节点
        List<String> children = curatorClient.getChildren().forPath("/my_root");
        System.out.println("获取子节点children: " + children);

        // 获取节点数据
        String value3 = new String(curatorClient.getData().forPath("/my_root/cxis_3/config"));
        System.out.println("获取节点数据value3: " + value3);

        // 获取节点数据和状态
        Stat stat = new Stat();
        String value3WithStat = new String(curatorClient.getData().storingStatIn(stat).forPath("/my_root/cxis_3/config"));
        System.out.println("获取节点数据和状态value3WithStat: " + value3WithStat + ", stat: " + stat);

        // 获取节点数据
        String value1 = new String(curatorClient.getData().forPath("/my_root/cxis_1/config"));
        System.out.println("获取节点数据value1: " + value1);

        // 判断结点是否存在
        Stat stat1 = curatorClient.checkExists().forPath("/my_root/cxis_1/config");
        System.out.println("判断结点是否存在stat1: " + stat1);

        // 删除节点和子节点
        curatorClient.delete().guaranteed().deletingChildrenIfNeeded().withVersion(-1).forPath("/my_root/cxis_1");

        // 判断结点是否存在
        Stat stat2 = curatorClient.checkExists().forPath("/my_root/cxis_1/config");
        System.out.println("判断结点是否存在stat2: " + stat2);

        // 修改节点数据
        Stat stat3 = curatorClient.setData().forPath("/my_root/cxis_2/config", "this is value for cxis_2_new".getBytes());
        System.out.println("修改节点数据stat3: " + stat3);

        // 获取节点数据
        String value2AfterUpdate = new String(curatorClient.getData().forPath("/my_root/cxis_2/config"));
        System.out.println("获取节点数据value2AfterUpdate: " + value2AfterUpdate);

        Thread.sleep(1000);
    }

}
