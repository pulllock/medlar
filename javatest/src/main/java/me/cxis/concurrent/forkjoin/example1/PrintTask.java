package me.cxis.concurrent.forkjoin.example1;

import java.util.concurrent.RecursiveAction;

public class PrintTask extends RecursiveAction {

    private static final int MAX = 50;

    private int start;
    private int end;

    public PrintTask(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected void compute() {
        if ((end - start) < MAX) {
            for (int i = start; i < end; i++) {
                System.out.println(Thread.currentThread().getName() + ": " + i);
            }
        } else {
            int middle = (start + end) / 2;
            PrintTask left = new PrintTask(start, middle);
            PrintTask right = new PrintTask(middle, end);

            left.fork();
            right.fork();
        }
    }
}
