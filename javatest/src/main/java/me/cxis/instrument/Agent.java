package me.cxis.instrument;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.Instrumentation;

/**
 * Created by cheng.xi on 16/12/2016.
 */
public class Agent {
    private static Instrumentation instrumentation = null;
    public static void premain(String agentArgs,Instrumentation _instrumentation){
        System.out.println("启动代理，Agent.premain()被调用......");
        instrumentation = _instrumentation;
        ClassFileTransformer classFileTransformer = new Transformer();
        System.out.println("添加一个Transformer实例到JVM......");
        instrumentation.addTransformer(classFileTransformer);
    }
}
