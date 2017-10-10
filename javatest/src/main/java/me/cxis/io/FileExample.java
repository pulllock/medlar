package me.cxis.io;

import java.io.File;

/**
 * Created by cheng.xi on 2017-10-10 23:10.
 */
public class FileExample {
    public static void main(String[] args) {
        File file = new File("xxxx");
        boolean exists = file.exists();

        long length = file.length();

        boolean success = file.renameTo(new File("yyyy"));

        success = file.delete();

        boolean isDirectory = file.isDirectory();

        String[] fileNames = file.list();

        File[] files = file.listFiles();
    }
}
