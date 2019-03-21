package me.cxis.tomcat.simple_servlet_container;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServer2 {

    /**
     * 放html之类的目录
     */
    public static final String WEB_ROOT = "webroot";

    private static final String SHUTDOWN_COMMAND = "/SHUTDOWN";

    private boolean shutdown = false;

    public static void main(String[] args) {
        HttpServer2 server = new HttpServer2();
        server.await();
    }

    public void await() {
        ServerSocket serverSocket = null;
        int port = 8080;

        try {
            serverSocket = new ServerSocket(port, 1, InetAddress.getByName("127.0.0.1"));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        while (!shutdown) {
            Socket socket;
            InputStream inputStream;
            OutputStream outputStream;

            try {
                socket = serverSocket.accept();
                inputStream = socket.getInputStream();
                outputStream = socket.getOutputStream();

                Request request = new Request(inputStream);
                request.parse();

                Response response = new Response(outputStream);

                if (request.getUri().startsWith("/servlet/")) {
                    ServletProcessor2 processor = new ServletProcessor2();
                    processor.process(request, response);
                } else {
                    StaticResourceProcessor processor = new StaticResourceProcessor();
                    processor.process(request, response);
                }

                socket.close();

                shutdown = request.getUri().equals(SHUTDOWN_COMMAND);
            } catch (IOException e) {
                e.printStackTrace();
                continue;
            }
        }
    }
}
