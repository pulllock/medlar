package me.cxis.concurrent.thread;

import java.util.Random;
import java.util.concurrent.Callable;

/**
 * Created by cheng.xi on 30/11/2016.
 */
public class CallableDemo implements Callable<Integer>{
    @Override
    public Integer call() throws Exception {
        return new Random().nextInt(1000);
    }
}
