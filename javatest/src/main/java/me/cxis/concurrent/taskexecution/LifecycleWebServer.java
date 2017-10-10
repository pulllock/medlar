package me.cxis.concurrent.taskexecution;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;

/**
 * Created by cx on 7/15/16.
 */
public class LifecycleWebServer {

    private final ExecutorService exec = Executors.newFixedThreadPool(100);

    public void start() throws IOException {
        ServerSocket socket = new ServerSocket(80);
        while(!exec.isShutdown()){
            try {
                final Socket conn = socket.accept();
                exec.execute(new Runnable() {
                    @Override
                    public void run() {
                        handleRequest(conn);
                    }
                });
            }catch (RejectedExecutionException e){
                if(!exec.isShutdown()){
                    System.out.println("task submission rejected," + e);
                }
            }
        }
    }

    public void stop(){
        exec.shutdown();
    }

    public static void handleRequest(Socket conn){

    }
}
