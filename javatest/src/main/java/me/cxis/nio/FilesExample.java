package me.cxis.nio;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * Created by cheng.xi on 2017-10-13 23:04.
 */
public class FilesExample {
    public static void main(String[] args) throws IOException {
        Path path = Paths.get("xxxx");
        boolean pathExists = Files.exists(
                path,
                new LinkOption[]{LinkOption.NOFOLLOW_LINKS});

        Path newDir = Files.createDirectory(path);

        Path source = Paths.get("xsds");
        Path target = Paths.get("sdff");

        Files.copy(source, target);
        Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);

        Files.move(source, target, StandardCopyOption.REPLACE_EXISTING);

        Files.delete(path);

        Files.walkFileTree(path, new FileVisitor<Path>() {
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                return null;
            }

            @Override
            public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                return null;
            }

            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                return null;
            }
        })
    }
}
