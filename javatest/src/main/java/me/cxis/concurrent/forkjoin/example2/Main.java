package me.cxis.concurrent.forkjoin.example2;

import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;

public class Main {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        int arr[] = new int[100];
        Random random = new Random();
        int total = 0;

        for (int i = 0; i < arr.length; i++) {
            int temp = random.nextInt(100);
            total += (arr[i] = temp);
        }
        System.out.println("Total: " + total);

        ForkJoinPool forkJoinPool = new ForkJoinPool();
        Future<Integer> future = forkJoinPool.submit(new SumTask(arr, 0, arr.length));
        System.out.println(future.get());
        forkJoinPool.shutdown();
    }
}
