package me.cxis.instrument;

import java.lang.instrument.Instrumentation;

/**
 * Created by cheng.xi on 16/12/2016.
 */
public class LoadedAgent {
    public static void agentmain(String args, Instrumentation instrumentation){
        System.out.println("LoadedAgent......");
        Class[] classes = instrumentation.getAllLoadedClasses();
        for(Class clazz : classes){
            System.out.println(clazz.getName());
        }
    }
}
