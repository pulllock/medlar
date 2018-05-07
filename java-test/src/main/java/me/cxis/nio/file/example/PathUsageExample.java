package me.cxis.nio.file.example;

import java.nio.file.Path;
import java.nio.file.Paths;

public class PathUsageExample {

    public static void main(String[] args) {
        Path file = Paths.get("/nio-data/txt");
        file.getFileName();
    }
}
