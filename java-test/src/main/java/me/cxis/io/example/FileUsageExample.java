package me.cxis.io.example;

import java.io.File;

public class FileUsageExample {

    public static void main(String[] args) {
        File file = new File("io-data.txt");

        boolean fileExists = file.exists();

        File dir = new File("/xxx/yyy/newDir");

        boolean dirCreated = dir.mkdir();

        boolean dirsCreated = dir.mkdirs();

        long length = file.length();

        boolean success = file.renameTo(new File("xxx.txt"));

        success = file.delete();

        boolean isDirectory = file.isDirectory();

        String[] fileName = file.list();

        File[] files = file.listFiles();
    }
}
