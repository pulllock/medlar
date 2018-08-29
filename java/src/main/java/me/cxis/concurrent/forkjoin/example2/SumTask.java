package me.cxis.concurrent.forkjoin.example2;

import java.util.concurrent.RecursiveTask;

public class SumTask extends RecursiveTask<Integer> {

    private static final int MAX = 20;

    private int arr[];

    private int start;

    private int end;

    public SumTask(int[] arr, int start, int end) {
        this.arr = arr;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        int sum = 0;
        if ((end - start) < MAX) {
            for (int i = start; i < end; i++) {
                sum += arr[i];
            }
            return sum;
        } else {
            int middle = (start + end) / 2;

            SumTask left = new SumTask(arr, start, middle);
            SumTask right = new SumTask(arr, middle, end);

            left.fork();
            right.fork();

            return left.join() + right.join();
        }
    }
}
