package me.cxis.nio.file.example;

import java.io.IOException;
import java.nio.file.*;

public class FilesUsageExample {

    public static void main(String[] args) {
        Path path = Paths.get("nio-data.txt");

        boolean pathExists = Files.exists(path, new LinkOption[]{LinkOption.NOFOLLOW_LINKS});
        System.out.println(pathExists);

        Path dirPath = Paths.get("/data/newDir");
        try {
            Path newDir = Files.createDirectory(dirPath);
        } catch (FileAlreadyExistsException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Path sourcePath = Paths.get("source.txt");
        Path destinationPath = Paths.get("destination.txt");

        try {
            Files.copy(sourcePath, destinationPath);

            Files.copy(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);

            Files.move(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);

            Files.delete(path);
        } catch (FileAlreadyExistsException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
