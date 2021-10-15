package me.cxis.sample.asm.example1;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class Test {

    public static void main(String[] args) throws IOException {
        ClassReader classReader = new ClassReader("me/cxis/sample/asm/example1/UserManager");
        ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_MAXS);

        ClassVisitor classVisitor = new UserManagerClassVisitor(classWriter);
        classReader.accept(classVisitor, ClassReader.SKIP_DEBUG);
        byte[] data = classWriter.toByteArray();

        File file = new File("asm-sample/target/classes/me/cxis/sample/asm/example1/UserManager.class");
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        fileOutputStream.write(data);
        fileOutputStream.close();
        System.out.println("generate code done...");

        UserManager userManager = new UserManager();
        String userName = userManager.queryUserName(123L);
        System.out.println(userName);
    }
}
