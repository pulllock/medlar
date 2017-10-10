package me.cxis.instrument;

import jdk.internal.org.objectweb.asm.ClassReader;
import jdk.internal.org.objectweb.asm.ClassWriter;
import jdk.internal.org.objectweb.asm.Opcodes;
import jdk.internal.org.objectweb.asm.tree.*;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

/**
 * Created by cheng.xi on 16/12/2016.
 */
public class Transformer implements ClassFileTransformer{
    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
        System.out.println("Transforming " + className);
        ClassReader classReader = new ClassReader(classfileBuffer);
        ClassNode classNode = new ClassNode();
        classReader.accept(classNode,0);
        for(Object object : classNode.methods){
            MethodNode methodNode = (MethodNode)object;
            if("<init>".endsWith(methodNode.name) || "<clinit>".equals(methodNode.name)){
                System.out.println(methodNode.name + " 不处理，继续执行...");
                continue;
            }

            InsnList insnList = methodNode.instructions;
            InsnList newInsnList = new InsnList();
            newInsnList.add(new FieldInsnNode(Opcodes.GETSTATIC,"java/lang/System","out","Ljava/io/PrintStream;"));
            newInsnList.add(new LdcInsnNode("进入方法：" + classNode.name + "." + methodNode.name));
            newInsnList.add(new MethodInsnNode(Opcodes.INVOKEVIRTUAL,"java/io/PrintStream","println","(Ljava/lang/String;)V"));
            insnList.insert(newInsnList);
            methodNode.maxStack += 3;
        }
        ClassWriter classWriter = new ClassWriter(0);
        classNode.accept(classWriter);
        return classWriter.toByteArray();
    }
}
