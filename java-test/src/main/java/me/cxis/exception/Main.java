package me.cxis.exception;

/**
 * Created by cheng.xi on 2017-03-15 09:21.
 */
public class Main {
    public static void main(String[] args) {
        for(int i = 0; i < 20; i++){
            Thread thread = new Thread(new LogThread(i));
            thread.start();
        }
    }
}
