package me.cxis.zk.master_selection;

import org.I0Itec.zkclient.ZkClient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class LeaderSelector {

    public static void main(String[] args) {
        // 保存所有zkClient列表
        List<ZkClient> zkClients = new ArrayList<>();

        // 保存所有服务器的列表
        List<WorkServer> servers = new ArrayList<>();

        try {
            for (int i = 0; i < 10; i++) {
                ZkClient zkClient = new ZkClient("127.0.0.1:2181", 5000);
                zkClients.add(zkClient);

                RunningData runningData = new RunningData();
                runningData.setCid(Long.valueOf(i));
                runningData.setName("Client#" + i);

                WorkServer server = new WorkServer(runningData);
                server.setZkClient(zkClient);

                servers.add(server);

                server.startup();
            }

            System.out.println("回车键退出");
            new BufferedReader(new InputStreamReader(System.in)).readLine();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            for (WorkServer server : servers) {
                try {
                    server.shutdown();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            for (ZkClient zkClient : zkClients) {
                zkClient.close();
            }
        }
    }
}
