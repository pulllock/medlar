package me.cxis.nio.file;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by cheng.xi on 22/12/2016.
 */
public class FileOperate {

    private static final String FILE_PATH = "/xxx/xyj.txt";

    /**
     * 以字节为单位来操作文件
     */
    public static void operateFileByBytes() {
        Path path = Paths.get(FILE_PATH);
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            inputStream = new FileInputStream(path.toFile());
            outputStream = new FileOutputStream("/xxx/xyj_byBytes.txt");

            int length;
            while((length = inputStream.read()) != -1){
                outputStream.write(length);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(outputStream != null){
                try{
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if(inputStream != null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }

    public static void operateFileByCharacter(){
        Path path = Paths.get(FILE_PATH);
        FileReader fileReader = null;
        FileWriter fileWriter = null;

        try {
            fileReader = new FileReader(path.toFile());
            fileWriter = new FileWriter("/xxx/xyj_ByCharacter.txt");

            int length;
            while((length = fileReader.read()) != -1){
                fileWriter.write((char)length);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(fileWriter != null){
                try {
                    fileWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(fileReader != null){
                try {
                    fileReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

    }

    public static void operateFileByLine(){
        Path path = Paths.get(FILE_PATH);
        BufferedReader reader = null;
        BufferedWriter writer = null;
        try{
            reader = new BufferedReader(new FileReader(path.toFile()));
            writer = new BufferedWriter(new FileWriter("/xxx/xyj_line.txt"));
            String temp = null;
            while((temp = reader.readLine()) != null){
                writer.write(temp + "\n");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(writer != null){
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if(reader != null){
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void operateFileByByteBuffer(){
        FileInputStream fileInputStream = null;
        FileOutputStream fileOutputStream = null;
        try {
            fileInputStream = new FileInputStream(FILE_PATH);
            fileOutputStream = new FileOutputStream("/xxx/xyj_byteBuffer.txt");

            FileChannel inChannel = fileInputStream.getChannel();
            FileChannel outChannel = fileOutputStream.getChannel();

            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            int length;
            while((length = inChannel.read(byteBuffer)) != -1){
                byteBuffer.flip();
                outChannel.write(byteBuffer);
                byteBuffer.clear();

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(fileOutputStream != null){
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if(fileInputStream != null){
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        //operateFileByBytes();
        long end = System.currentTimeMillis();
        System.out.println("以字节为单位操作文件：" + (end - start));

        start = System.currentTimeMillis();
        operateFileByCharacter();
        end = System.currentTimeMillis();
        System.out.println("以字符为单位操作文件：" + (end - start));

        start = System.currentTimeMillis();
        operateFileByLine();
        end = System.currentTimeMillis();
        System.out.println("以行为单位操作文件：" + (end - start));

        start = System.currentTimeMillis();
        operateFileByByteBuffer();
        end = System.currentTimeMillis();
        System.out.println("以ByteBuffer为单位操作文件：" + (end - start));
    }
}
