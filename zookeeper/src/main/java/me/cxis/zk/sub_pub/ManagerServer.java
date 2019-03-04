package me.cxis.zk.sub_pub;

import com.alibaba.fastjson.JSON;
import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.exception.ZkNoNodeException;
import org.I0Itec.zkclient.exception.ZkNodeExistsException;

import java.util.List;

public class ManagerServer {

    /**
     * servers结点路径
     */
    private String serversPath;

    /**
     * command结点路径
     */
    private String commandPath;

    /**
     * config结点路径
     */
    private String configPath;

    private ZkClient zkClient;

    private ServerConfig config;

    /**
     * 监听servers结点的子结点列表的变化
     */
    private IZkChildListener childListener;

    /**
     * 监听command结点数据内容变化
     */
    private IZkDataListener dataListener;

    /**
     * 工作服务器列表
     */
    private List<String> workServerList;

    public ManagerServer(String serversPath, String commandPath, String configPath, ZkClient zkClient, ServerConfig serverConfig) {
        this.serversPath = serversPath;
        this.commandPath = commandPath;
        this.configPath = configPath;
        this.zkClient = zkClient;
        this.config = serverConfig;

        this.childListener = new IZkChildListener() {
            @Override
            public void handleChildChange(String parentPath, List<String> currentChilds) throws Exception {
                workServerList = currentChilds;
                System.out.println("work server list changed to:" + workServerList);
            }
        };

        this.dataListener = new IZkDataListener() {
            @Override
            public void handleDataChange(String dataPath, Object data) throws Exception {
                System.out.println("xxxxxxx");
                System.out.println(dataPath + ":" + data);
                String cmd = data.toString();
                System.out.println("cmd:" + cmd);
                execCmd(cmd);
            }

            @Override
            public void handleDataDeleted(String dataPath) throws Exception {
                System.out.println(dataPath);
            }
        };
    }

    public void startup() {
        zkClient.subscribeDataChanges(commandPath, dataListener);
        zkClient.subscribeChildChanges(serversPath, childListener);
    }


    private void execCmd(String cmd) {
        if ("list".equals(cmd)) {
            System.out.println(workServerList);
        } else if ("create".equals(cmd)) {
            execCreate();
        } else if ("modify".equals(cmd)) {
            execModify();
        }
    }

    private void execCreate() {
        if (!zkClient.exists(configPath)) {
            try {
                zkClient.createPersistent(configPath, JSON.toJSONString(config).getBytes());
            } catch (ZkNodeExistsException e) {
                zkClient.writeData(configPath, JSON.toJSONString(config).getBytes());
            } catch (ZkNoNodeException e) {
                String parentDir = configPath.substring(0, configPath.lastIndexOf("/"));
                zkClient.createPersistent(parentDir, true);
                execCreate();
            }
        }
    }

    private void execModify() {
        config.setUserName(config.getUserName() + "_modify");
        try {
            zkClient.writeData(configPath, JSON.toJSONString(config).getBytes());
        } catch (ZkNoNodeException e) {
            execCreate();
        }
    }

    public void stop() {
        zkClient.unsubscribeChildChanges(serversPath, childListener);
        zkClient.unsubscribeDataChanges(commandPath, dataListener);
    }
}
