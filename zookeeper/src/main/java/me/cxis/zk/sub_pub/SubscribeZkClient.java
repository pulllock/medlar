package me.cxis.zk.sub_pub;

import org.I0Itec.zkclient.ZkClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class SubscribeZkClient {

    public static void main(String[] args) {
        List<ZkClient> clients = new ArrayList<>();
        List<WorkServer> workServers = new ArrayList<>();
        ManagerServer managerServer = null;

        try {
            ServerConfig serverConfig = new ServerConfig();
            serverConfig.setUserName("root");
            serverConfig.setPassword("123456");
            serverConfig.setUrl("jdbc:mysql://127.0.0.1:3306/mydb");

            ZkClient manageClient = new ZkClient("127.0.0.1:2181", 1000);
            managerServer = new ManagerServer(
                "/servers",
                "/command",
                "/config",
                manageClient,
                serverConfig);
            managerServer.startup();

            for (int i = 0; i < 5; i++) {
                ZkClient zkClient = new ZkClient("127.0.0.1:2181", 1000);
                clients.add(zkClient);
                ServerData serverData = new ServerData();
                serverData.setId(i);
                serverData.setName("WorkServer#" + i);
                serverData.setAddress("192.168.1." + i);

                WorkServer workServer = new WorkServer(
                    "/config",
                    "/servers",
                    serverData,
                    zkClient,
                    serverConfig
                );

                workServers.add(workServer);
                workServer.startup();
            }

            System.out.println("敲回车键退出");
            new BufferedReader(new InputStreamReader(System.in)).readLine();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            for (WorkServer workServer : workServers) {
                workServer.shutdown();
            }

            for (ZkClient client : clients) {
                client.close();
            }
        }
    }
}
