package me.cxis.nio.file;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by cheng.xi on 21/12/2016.
 */
public class TestFileChannel {
    private static String FILE = "/xxx/深入理解Java虚拟机 JVM高级特性与最佳实践.pdf";
    private static String OUT_FILE = "/xxx/out.pdf";

    private static int READ_BYTES = 1024;

    public static void readByChannel() throws IOException {
        long start = System.currentTimeMillis();
        Path file = Paths.get(FILE);
        Path outFile = Paths.get(OUT_FILE);

        FileInputStream fileInputStream = new FileInputStream(file.toFile());
        FileOutputStream fileOutputStream = new FileOutputStream(outFile.toFile());

        FileChannel fileChannel = fileInputStream.getChannel();
        FileChannel outFileChannel = fileOutputStream.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate(READ_BYTES);
        int length = -1;
        while((length = fileChannel.read(byteBuffer)) != -1){
            byteBuffer.flip();

            int outLength = 0;
            while((outLength = outFileChannel.write(byteBuffer)) != 0){
            }

            byteBuffer.clear();
        }

        fileChannel.close();
        fileInputStream.close();

        long end = System.currentTimeMillis();
        System.out.println("耗时：" + (end - start));
    }

    public static void readByChannel2() throws IOException {
        long start = System.currentTimeMillis();

        Path file = Paths.get(FILE);
        Path outFile = Paths.get(OUT_FILE);

        FileInputStream fileInputStream = new FileInputStream(file.toFile());

        FileChannel fileChannel = fileInputStream.getChannel();

        long fileSize = fileChannel.size();
        MappedByteBuffer mappedByteBuffer = fileChannel.map(FileChannel.MapMode.READ_ONLY,0,fileSize);

        byte[] bytes = new byte[READ_BYTES];
        long times = fileSize / READ_BYTES;
        int remainds = (int) fileSize % READ_BYTES;
        for(int i = 0 ; i < times; i++){
            mappedByteBuffer.get(bytes);
        }
        if(remainds > 0){
            bytes = new byte[remainds];
            mappedByteBuffer.get(bytes);
        }

        fileChannel.close();
        fileInputStream.close();

        long end = System.currentTimeMillis();
        System.out.println("耗时：" + (end - start));

    }

    public static void readByStream() throws IOException {
        long start = System.currentTimeMillis();
        Path file = Paths.get(FILE);
        BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file.toFile()));
        long size = bufferedInputStream.available();
        int length = 0;
        byte[] bytes = new byte[READ_BYTES];
        while((length = bufferedInputStream.read(bytes)) != -1){

        }
        bufferedInputStream.close();
        long end = System.currentTimeMillis();
        System.out.println("耗时：" + (end - start));
    }

    public static void main(String[] args) {
        try {
            //readByChannel();
            //readByChannel2();
            readByStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
