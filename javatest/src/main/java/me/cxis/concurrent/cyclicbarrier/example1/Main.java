package me.cxis.concurrent.cyclicbarrier.example1;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(5, () -> {
            System.out.println("恭喜所有玩家都通过第一关！！！！");
        });

        ExecutorService executor = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 5; i++) {
            executor.execute(new Player(i + 1, cyclicBarrier));
        }

        executor.shutdown();
    }
}
