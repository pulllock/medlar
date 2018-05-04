package me.cxis.concurrent.future.example3;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class QueryThread implements Callable<List<String>> {

    private int pageIndex;

    public QueryThread(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    @Override
    public List<String> call() throws Exception {
        if (pageIndex % 2 == 0) {
            Thread.sleep(5000);
        } else {
            Thread.sleep(2000);
        }

        System.out.println("查询第：" + pageIndex + "页");

        List<String> list = new ArrayList<>();
        list.add("xxxx" + pageIndex);
        list.add("xxyy" + pageIndex);
        list.add("xxxy" + pageIndex);
        return list;
    }
}
