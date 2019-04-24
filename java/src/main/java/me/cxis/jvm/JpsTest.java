package me.cxis.jvm;

import java.util.concurrent.TimeUnit;

public class JpsTest {

    public static void main(String[] args) {
        while (true) {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
