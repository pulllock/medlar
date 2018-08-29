package me.cxis.concurrent.future.example2;

import java.util.concurrent.Callable;

public class TaskWithResult implements Callable<String> {

    private int taskId;

    public TaskWithResult(int taskId) {
        this.taskId = taskId;
    }

    @Override
    public String call() throws Exception {
        return "执行结果：任务taskId=" + taskId;
    }
}
