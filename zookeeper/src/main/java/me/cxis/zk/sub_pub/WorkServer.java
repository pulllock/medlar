package me.cxis.zk.sub_pub;

import com.alibaba.fastjson.JSON;
import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.exception.ZkNoNodeException;

/**
 * 工作服务器
 */
public class WorkServer {

    private ZkClient zkClient;

    /**
     * 配置路径
     */
    private String configPath;

    /**
     * 服务器结点路径
     */
    private String serversPath;

    /**
     * 当前服务器基本信息
     */
    private ServerData serverData;

    /**
     * 当前服务器的配置信息
     */
    private ServerConfig serverConfig;

    private IZkDataListener dataListener;

    public WorkServer(String configPath, String serversPath, ServerData serverData, ZkClient zkClient, ServerConfig initConfig) {
        this.zkClient = zkClient;
        this.serversPath = serversPath;
        this.configPath = configPath;
        this.serverConfig = initConfig;
        this.serverData = serverData;

        this.dataListener = new IZkDataListener() {
            @Override
            public void handleDataChange(String dataPath, Object data) throws Exception {
                String json = new String((byte[])data);
                ServerConfig config = JSON.parseObject(json, ServerConfig.class);
                updateConfig(config);
                System.out.println("config update to: " + serverConfig);
            }

            @Override
            public void handleDataDeleted(String dataPath) throws Exception {
            }
        };
    }

    public void startup() {
        // 启动服务器的时候先向zookeeper注册自己
        registerMe();
        zkClient.subscribeDataChanges(configPath, dataListener);
    }

    public void shutdown() {
        zkClient.unsubscribeDataChanges(configPath, dataListener);
    }

    private void registerMe() {
        String path = serversPath.concat("/").concat(serverData.getAddress());
        try {
            zkClient.createEphemeral(path, JSON.toJSONString(serverData).getBytes());
        } catch (ZkNoNodeException e) {
            zkClient.createPersistent(serversPath, true);
            registerMe();
        }
    }

    private void updateConfig(ServerConfig serverConfig) {
        this.serverConfig = serverConfig;
    }
}
