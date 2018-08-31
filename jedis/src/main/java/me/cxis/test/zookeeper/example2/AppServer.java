package me.cxis.test.zookeeper.example2;

import org.apache.zookeeper.*;

import java.io.IOException;

import static org.apache.zookeeper.ZooDefs.Ids.OPEN_ACL_UNSAFE;

public class AppServer {

    private String groupNode = "/serverGroup";

    private String subNode = "/sub";

    private String address = "localhost:2181";

    public void connectZookeeper(String serverAddress) throws IOException, KeeperException, InterruptedException {
        ZooKeeper zk = new ZooKeeper(
            address,
            50000,
            System.out::println
        );

        String createdPath = zk.create(
            groupNode + subNode,
            serverAddress.getBytes(),
            OPEN_ACL_UNSAFE,
            CreateMode.EPHEMERAL_SEQUENTIAL
        );

        System.out.println("Create path: " + createdPath);
    }

    public void handle() throws InterruptedException {
        Thread.sleep(Long.MAX_VALUE);
    }

    public static void main(String[] args) throws InterruptedException, IOException, KeeperException {
        if (args.length == 0) {
            System.err.println("请填写服务器地址");
            System.exit(1);
        }

        AppServer server = new AppServer();
        server.connectZookeeper(args[0]);
        server.handle();
    }
}
