package me.cxis.jmx.example3.example2;

import javax.management.*;
import javax.management.remote.JMXConnectorServer;
import javax.management.remote.JMXConnectorServerFactory;
import javax.management.remote.JMXServiceURL;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.rmi.registry.LocateRegistry;

public class UserAgent {

    public static void main(String[] args) throws NotCompliantMBeanException, InstanceAlreadyExistsException, MBeanRegistrationException, MalformedObjectNameException, IOException {
        // 获取MBeanServer，作为MBean的容器
        MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
        // 域名:name=MBean名称，域名和MBean名可以任意
        ObjectName name = new ObjectName("jmxBean:name=user");
        // 注册到MBeanServer中
        mBeanServer.registerMBean(new User(), name);

        // 注册端口，绑定url后用于客户端通过rmi方式连接JMXConnectorServer
        LocateRegistry.createRegistry(8088);
        // URL结尾可以随意指定，如果需要JConsole连接，需要使用jmxrmi结尾
        JMXServiceURL url = new JMXServiceURL("service:jmx:rmi:///jndi/rmi://localhost:8088/jmxrmi");
        JMXConnectorServer connectorServer = JMXConnectorServerFactory.newJMXConnectorServer(url, null, mBeanServer);
        connectorServer.start();

    }
}
