package me.cxis.nio.file;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.AccessController;
import java.security.PrivilegedAction;

/**
 * Created by cheng.xi on 22/12/2016.
 */
public class BigFileTest {

    private static final int DATA_CHUNK = 128 * 1024 * 1024;
    private static final long LENGTH = 2L * 1024 * 1024 * 1024L;

    public static void writeWithFileChannel() throws IOException {
        Path file = Paths.get("/xxx/writeWithFileChannel.txt");
        RandomAccessFile randomAccessFile = new RandomAccessFile(file.toFile(),"rw");
        FileChannel fileChannel = randomAccessFile.getChannel();

        byte[] data;
        long length = LENGTH;
        ByteBuffer byteBuffer = ByteBuffer.allocate(DATA_CHUNK);

        while(length >= DATA_CHUNK){
            System.out.println("写文件：" + DATA_CHUNK / (1024 * 1024) + "M");
            byteBuffer.clear();
            data = new byte[DATA_CHUNK];
            for(int i = 0; i < DATA_CHUNK; i++){
                byteBuffer.put(data[i]);
            }

            data = null;

            byteBuffer.flip();
            fileChannel.write(byteBuffer);
            fileChannel.force(true);
            length -= DATA_CHUNK;
        }
        if(length > 0){
            System.out.println("剩余数据：" + length + "B");
            byteBuffer = ByteBuffer.allocate((int)length);
            data = new byte[(int)length];
            for(int i = 0; i <length; i++){
                byteBuffer.put(data[i]);
            }
            byteBuffer.flip();
            fileChannel.write(byteBuffer);
            fileChannel.force(true);

            data = null;
        }

        fileChannel.close();
        randomAccessFile.close();
    }

    public static void writeWithMappedByteBuffer() throws IOException {
        Path path = Paths.get("/xxx/writeWithMappedByteBuffer.txt");
        RandomAccessFile randomAccessFile = new RandomAccessFile(path.toFile(),"rw");

        FileChannel fileChannel = randomAccessFile.getChannel();
        int position = 0;
        MappedByteBuffer mappedByteBuffer = null;
        byte[] data = null;
        long length = LENGTH;
        while(length >= DATA_CHUNK){
            System.out.println("写文件：" + DATA_CHUNK / (1024 * 1024) + "M");
            mappedByteBuffer = fileChannel.map(FileChannel.MapMode.READ_WRITE,position,DATA_CHUNK);
            data = new byte[DATA_CHUNK];
            mappedByteBuffer.put(data);

            data = null;

            length -= DATA_CHUNK;
            position += DATA_CHUNK;
        }
        if(length > 0){
            System.out.println("写剩余的文件：" + length + "B");
            mappedByteBuffer = fileChannel.map(FileChannel.MapMode.READ_WRITE,position,length);
            data = new byte[(int)length];
            mappedByteBuffer.put(data);
        }

        data = null;
        unmap(mappedByteBuffer);
        fileChannel.close();
    }

    public static void writeWithTransferTo() throws IOException {
        Path path = Paths.get("/xxx/writeWithTransferTo.txt");
        RandomAccessFile randomAccessFile = new RandomAccessFile(path.toFile(),"rw");
        FileChannel toFileChannel = randomAccessFile.getChannel();

        long length = LENGTH;
        byte[] data = null;
        ByteArrayInputStream byteArrayInputStream = null;
        ReadableByteChannel fromByteChannel = null;
        long position = 0;
        while(length >= DATA_CHUNK){
            System.out.println("写文件：" + DATA_CHUNK / (1024 * 1024) + "M");
            data = new byte[DATA_CHUNK];
            byteArrayInputStream = new ByteArrayInputStream(data);
            fromByteChannel = Channels.newChannel(byteArrayInputStream);

            long count = DATA_CHUNK;
            toFileChannel.transferFrom(fromByteChannel,position,count);
            data = null;
            position += DATA_CHUNK;
            length -= DATA_CHUNK;
        }
        if(length > 0){
            System.out.println("写剩余的文件：" + length + "B");
            data = new byte[(int)length];
            byteArrayInputStream = new ByteArrayInputStream(data);
            fromByteChannel = Channels.newChannel(byteArrayInputStream);
            long count = length;
            toFileChannel.transferFrom(fromByteChannel,position,count);
        }
        data = null;
        toFileChannel.close();
        fromByteChannel.close();

    }


    public static void unmap(final MappedByteBuffer mappedByteBuffer){
        try {
            if(mappedByteBuffer == null){
                return;
            }
            mappedByteBuffer.force();
            AccessController.doPrivileged(new PrivilegedAction<Object>() {
                @Override
                public Object run() {
                    try {
                        Method getCleanerMethod = mappedByteBuffer.getClass()
                                .getMethod("cleaner", new Class[0]);
                        getCleanerMethod.setAccessible(true);
                        sun.misc.Cleaner cleaner =
                                (sun.misc.Cleaner) getCleanerMethod
                                        .invoke(mappedByteBuffer, new Object[0]);
                        cleaner.clean();
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                    System.out.println("clean MappedByteBuffer completed");
                    return null;
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        long start = System.currentTimeMillis();
        writeWithFileChannel();
        long end = System.currentTimeMillis();
        System.out.println("writeWithFileChannel time:" + (end - start));
        start = System.currentTimeMillis();
        writeWithMappedByteBuffer();
        end = System.currentTimeMillis();
        System.out.println("writeWithMappedByteBuffer time:" + (end - start));

        start = System.currentTimeMillis();
        writeWithTransferTo();
        end = System.currentTimeMillis();
        System.out.println("writeWithTransferTo time:" + (end - start));

    }

}
