package me.cxis.gof.factory_method_pattern.logger;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class XmlUtil {

    public static Object getBean() {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new File("config.xml"));
            NodeList nodeList = document.getElementsByTagName("className");
            Node node = nodeList.item(0).getFirstChild();
            String className = node.getNodeValue();

            Class clazz = Class.forName(className);
            Object object = clazz.getDeclaredConstructor().newInstance();
            return object;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
