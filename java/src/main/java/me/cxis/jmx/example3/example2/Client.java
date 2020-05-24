package me.cxis.jmx.example3.example2;

import javax.management.*;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
import java.io.IOException;

public class Client {

    public static void main(String[] args) throws IOException, MalformedObjectNameException, AttributeNotFoundException, MBeanException, ReflectionException, InstanceNotFoundException, InvalidAttributeValueException {
        JMXServiceURL url = new JMXServiceURL("service:jmx:rmi:///jndi/rmi://localhost:8088/jmxrmi");
        JMXConnector connector = JMXConnectorFactory.connect(url, null);

        MBeanServerConnection connection = connector.getMBeanServerConnection();
        ObjectName name = new ObjectName("jmxBean:name=user");
        String[] domains = connection.getDomains();
        for (int i = 0; i < domains.length; i++) {
            System.out.println(String.format("domain[%d]=domain[%s]", i, domains[i]));
        }

        System.out.println(String.format("MBean count=%d", connection.getMBeanCount()));

        connection.setAttribute(name, new Attribute("Name", "小明"));
        connection.setAttribute(name, new Attribute("Age", 18));

        System.out.println(String.format("name:%s,age:%s", connection.getAttribute(name, "Name"), connection.getAttribute(name, "Age")));


    }
}
