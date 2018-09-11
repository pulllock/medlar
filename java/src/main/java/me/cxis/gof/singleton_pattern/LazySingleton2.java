package me.cxis.gof.singleton_pattern;

public class LazySingleton2 {

    private volatile static LazySingleton2 instance = null;

    private LazySingleton2() {

    }

    public static LazySingleton2 getInstance() {
        if (instance == null) {
            synchronized (LazySingleton2.class) {
                if (instance == null) {
                    instance = new LazySingleton2();
                }
            }
        }

        return instance;
    }
}
