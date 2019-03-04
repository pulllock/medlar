package me.cxis.zk.master_selection;

import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.exception.ZkException;
import org.I0Itec.zkclient.exception.ZkInterruptedException;
import org.I0Itec.zkclient.exception.ZkNoNodeException;
import org.I0Itec.zkclient.exception.ZkNodeExistsException;
import org.apache.zookeeper.CreateMode;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 工作服务器
 * @author chengxi
 */
public class WorkServer {

    /**
     * 记录工作服务器状态
     */
    private volatile boolean running =  false;

    /**
     * master结点路径
     */
    private static final String MASTER_PATH = "/master";

    private ZkClient zkClient;

    /**
     * master结点删除的监听器
     */
    private IZkDataListener dataListener;

    /**
     * 当前结点的基本信息
     */
    private RunningData serverData;

    /**
     * 记录集群中master结点的基本信息
     */
    private RunningData masterData;

    private ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

    /**
     * 5秒
     */
    private int delayTime = 5;

    public WorkServer(RunningData data) {
        this.serverData = data;

        this.dataListener = new IZkDataListener() {
            @Override
            public void handleDataChange(String dataPath, Object data) throws Exception {

            }

            @Override
            public void handleDataDeleted(String dataPath) throws Exception {
                if (masterData != null && masterData.getName().equals(serverData.getName())) {
                    // 自己就是上一轮的master服务器，直接抢
                    takeMaster();
                } else {
                    /**
                     * 延迟5秒再抢
                     * 主要是为了应对网络抖动
                     * 给上一轮的Master服务器优先抢占master的权利
                     * 避免不必要的数据迁移开销
                     */
                    executor.schedule(
                        () -> takeMaster(),
                        delayTime,
                        TimeUnit.SECONDS
                    );
                }
            }
        };
    }

    /**
     * 启动服务器
     */
    public void startup() throws Exception {
        if (running) {
            throw new Exception("server already startup...");
        }

        running = true;

        // 订阅master结点删除事件
        zkClient.subscribeDataChanges(MASTER_PATH, dataListener);

        // 竞争master
        takeMaster();
    }

    /**
     * 停止服务器
     * @throws Exception
     */
    public void shutdown() throws Exception {
        if (!running) {
            throw new Exception("server already stopped..");
        }

        running = false;
        executor.shutdown();

        // 取消订阅master结点事件
        zkClient.unsubscribeDataChanges(MASTER_PATH, dataListener);

        // 释放master
        releaseMaster();
    }

    /**
     * 竞争master
     */
    public void takeMaster() {
        if (!running) {
            return;
        }

        try {
            // 尝试创建master临时节点
            zkClient.create(MASTER_PATH, serverData, CreateMode.EPHEMERAL);
            masterData = serverData;
            System.out.println("master: " + serverData);

            // 作为演示，让服务器没隔5秒释放一次master权利
            executor.schedule(
                () -> releaseMaster(),
                delayTime,
                TimeUnit.SECONDS
            );
        } catch (ZkNodeExistsException e) {
            // 已经被其他服务器竞争走了

            // 读取master结点信息
            RunningData runningData = zkClient.readData(MASTER_PATH, true);
            if (runningData == null) {
                // 读取的瞬间master宕机了
                takeMaster();
            } else {
                masterData = runningData;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void releaseMaster() {
        if (checkMaster()) {
            zkClient.delete(MASTER_PATH);
        }
    }

    private boolean checkMaster() {
        try {
            RunningData runningData = zkClient.readData(MASTER_PATH);
            masterData = runningData;
            if (masterData.getName().equals(serverData.getName())) {
                return true;
            }
            return false;
        } catch (ZkNoNodeException e) {
            return false;
        } catch (ZkInterruptedException e) {
            return checkMaster();
        } catch (ZkException e) {
            return false;
        }
    }

    public ZkClient getZkClient() {
        return zkClient;
    }

    public void setZkClient(ZkClient zkClient) {
        this.zkClient = zkClient;
    }
}
