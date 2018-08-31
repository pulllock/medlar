package me.cxis.test.zookeeper.example2;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class AppClient {

    private String groupNode = "/serverGroup";

    private ZooKeeper zk;

    private Stat stat = new Stat();

    private volatile List<String> serverList;

    private String address = "localhost:2181";

    public void connectZookeeper() throws IOException, KeeperException, InterruptedException {
        zk = new ZooKeeper(
            address,
            50000,
            watchedEvent -> {
                // server_group节点下的子节点变化事件，更新server列表，并重新注册监听
                if (
                    watchedEvent.getType() == Watcher.Event.EventType.NodeChildrenChanged &&
                        groupNode.equals(watchedEvent.getPath())
                ) {
                    try {
                        updateServerList();
                    } catch (KeeperException | InterruptedException | UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }
            }
        );

        updateServerList();
    }

    private void updateServerList() throws KeeperException, InterruptedException, UnsupportedEncodingException {
        List<String> newServerList = new ArrayList<>();

        // 获取并监听groupNode的子节点变化
        // 每次需要重新注册监听，监听事件是一次性事件
        List<String> subList = zk.getChildren(groupNode, true);
        for (String subNode : subList) {
            byte[] data = zk.getData(groupNode + subNode, false, stat);
            newServerList.add(new String(data, "utf-8"));
        }

        //替换server列表
        serverList = newServerList;
        System.out.println("Server list updated: " + serverList);
    }

    public void handle() throws InterruptedException {
        Thread.sleep(Long.MAX_VALUE);
    }

    public static void main(String[] args) throws InterruptedException, IOException, KeeperException {
        AppClient appClient = new AppClient();
        appClient.connectZookeeper();
        appClient.handle();
    }
}
