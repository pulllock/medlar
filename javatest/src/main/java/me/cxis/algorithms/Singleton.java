package me.cxis.algorithms;

/**
 * Created by cx on 7/20/16.
 */
public class Singleton {
    /*private Singleton(){}

    private static Singleton instance = new Singleton();

    public static Singleton getInstance(){
        return  instance;
    }*/

    private static Singleton instance = null;

    public static synchronized Singleton getInstance(){
        if(instance == null){
            instance = new Singleton();
        }
        return instance;
    }
}
