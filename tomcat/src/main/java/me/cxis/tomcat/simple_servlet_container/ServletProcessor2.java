package me.cxis.tomcat.simple_servlet_container;


import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLStreamHandler;

public class ServletProcessor2 {

    public void process(Request request, Response response) {
        String uri = request.getUri();
        String serletName = uri.substring(uri.lastIndexOf("/") + 1);

        URLClassLoader classLoader = null;
        try {
            URL[] urls = new URL[1];
            URLStreamHandler streamHandler = null;
            URL url = this.getClass().getClassLoader().getResource("");
            File classPath = new File(url.getFile());

            String repository = (new URL("file", null, classPath.getCanonicalPath() + File.separator)).toString();
            urls[0] = new URL(null, repository, streamHandler);
            classLoader = new URLClassLoader(urls);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Class myClass = null;
        try {
            myClass = classLoader.loadClass(serletName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Servlet servlet;
        RequestFacade requestFacade = new RequestFacade(request);
        ResponseFacade responseFacade = new ResponseFacade(response);
        try {
            servlet = (Servlet) myClass.newInstance();
            servlet.service(requestFacade, responseFacade);
        } catch (IllegalAccessException | IOException | ServletException | InstantiationException e) {
            e.printStackTrace();
        }
    }
}
