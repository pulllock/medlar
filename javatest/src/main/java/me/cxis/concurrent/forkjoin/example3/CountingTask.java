package me.cxis.concurrent.forkjoin.example3;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

public class CountingTask extends RecursiveTask<Integer> {

    private Path dir;

    public CountingTask(Path dir) {
        this.dir = dir;
    }

    @Override
    protected Integer compute() {
        int count = 0;
        List<CountingTask> subTasks = new ArrayList<>();

        try(DirectoryStream<Path> ds = Files.newDirectoryStream(dir)) {
            for (Path subPath : ds) {
                if (Files.isDirectory(subPath, LinkOption.NOFOLLOW_LINKS)) {
                    subTasks.add(new CountingTask(subPath));
                } else {
                    count++;
                }
            }

            if (!subTasks.isEmpty()) {
                for (CountingTask subTask : invokeAll(subTasks)) {
                    count += subTask.join();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return count;
    }
}
