package fun.pullock.zookeeper.controller;

import jakarta.annotation.Resource;
import org.apache.curator.CuratorZookeeperClient;
import org.apache.curator.framework.CuratorFramework;
import org.apache.zookeeper.data.Stat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static java.nio.charset.StandardCharsets.UTF_8;

@RestController
@RequestMapping("/zookeeper")
public class ZookeeperController {

    @Resource
    private CuratorFramework curatorFramework;

    @GetMapping("/state")
    public String state() {
        return curatorFramework.getState().name();
    }

    @GetMapping("/client")
    public Map<String, Object> client() {
        CuratorZookeeperClient client = curatorFramework.getZookeeperClient();

        Map<String, Object> clientInfo = new LinkedHashMap<>();
        clientInfo.put("currentConnectionString", client.getCurrentConnectionString());
        clientInfo.put("isConnected", client.isConnected());
        clientInfo.put("connectionTimeout", client.getConnectionTimeoutMs());
        clientInfo.put("instanceIndex", client.getInstanceIndex());
        clientInfo.put("lastNegotiatedSessionTimeout", client.getLastNegotiatedSessionTimeoutMs());
        try {
            clientInfo.put("zookeeper", client.getZooKeeper().toString());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return clientInfo;
    }

    @GetMapping("/data/get")
    public String getData(@RequestParam("path") String path) throws Exception {
        byte[] data = curatorFramework.getData().forPath(path);
        return new String(data);
    }

    @GetMapping("/data/set")
    public Stat setData(@RequestParam("path") String path, @RequestParam("data") String data) throws Exception {
        return curatorFramework.setData().forPath(path, data.getBytes(UTF_8));
    }

    @GetMapping("/path/create")
    public String createPath(@RequestParam("path") String path, @RequestParam(value = "data", required = false) String data) throws Exception {
        if (data != null && !data.isEmpty()) {
            return curatorFramework.create().creatingParentsIfNeeded().forPath(path, data.getBytes(UTF_8));
        }
        return curatorFramework.create().creatingParentsIfNeeded().forPath(path);
    }

    @GetMapping("/path/stat")
    public Stat pathStat(@RequestParam("path") String path) throws Exception {
        Stat stat =new Stat();
        curatorFramework.getData().storingStatIn(stat).forPath(path);
        return stat;
    }

    @GetMapping("/path/list")
    public List<String> pathList(@RequestParam("path") String path) throws Exception {
        return curatorFramework.getChildren().forPath(path);
    }

    @GetMapping("/path/delete")
    public String pathDelete(@RequestParam("path") String path) throws Exception {
        curatorFramework.delete().guaranteed().deletingChildrenIfNeeded().inBackground().forPath(path);
        return "success";
    }
}
