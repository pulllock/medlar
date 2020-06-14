package me.cxis.jvm.bytecode;

public class TryCatchFinallyTest1 {

    public int getSomething() {
        int i = 1;
        try {
            i++;
            return i;
        } catch (Exception e) {
            i = 0;
            return i;
        } finally {
            i = 10;
        }
    }

    /*public class me.cxis.jvm.bytecode.TryCatchFinallyTest1
    minor version: 0
    major version: 52
    flags: ACC_PUBLIC, ACC_SUPER
    Constant pool:
            #1 = Methodref          #4.#24         // java/lang/Object."<init>":()V
            #2 = Class              #25            // java/lang/Exception
            #3 = Class              #26            // me/cxis/jvm/bytecode/TryCatchFinallyTest1
            #4 = Class              #27            // java/lang/Object
            #5 = Utf8               <init>
   #6 = Utf8               ()V
   #7 = Utf8               Code
   #8 = Utf8               LineNumberTable
   #9 = Utf8               LocalVariableTable
  #10 = Utf8               this
            #11 = Utf8               Lme/cxis/jvm/bytecode/TryCatchFinallyTest1;
  #12 = Utf8               getSomething
  #13 = Utf8               ()I
  #14 = Utf8               e
  #15 = Utf8               Ljava/lang/Exception;
  #16 = Utf8               i
  #17 = Utf8               I
  #18 = Utf8               StackMapTable
  #19 = Class              #26            // me/cxis/jvm/bytecode/TryCatchFinallyTest1
            #20 = Class              #25            // java/lang/Exception
            #21 = Class              #28            // java/lang/Throwable
            #22 = Utf8               SourceFile
  #23 = Utf8               TryCatchFinallyTest1.java
  #24 = NameAndType        #5:#6          // "<init>":()V
            #25 = Utf8               java/lang/Exception
  #26 = Utf8               me/cxis/jvm/bytecode/TryCatchFinallyTest1
  #27 = Utf8               java/lang/Object
  #28 = Utf8               java/lang/Throwable
    {
  public me.cxis.jvm.bytecode.TryCatchFinallyTest1();
        descriptor: ()V
        flags: ACC_PUBLIC
        Code:
        stack=1, locals=1, args_size=1
        0: aload_0
        1: invokespecial #1                  // Method java/lang/Object."<init>":()V
        4: return
            LineNumberTable:
        line 3: 0
        LocalVariableTable:
        Start  Length  Slot  Name   Signature
        0       5     0  this   Lme/cxis/jvm/bytecode/TryCatchFinallyTest1;

        public int getSomething();
        descriptor: ()I
        flags: ACC_PUBLIC
        Code:
        stack=1, locals=5, args_size=1
        // 将1推送到栈顶
        0: iconst_1
        // 将栈顶1出栈，存储到第二个局部变量表中
        1: istore_1
        // 将第二个局部变量加1，第二个局部变量值变为2
        2: iinc          1, 1
        // 将第二个局部变量表中的2加载到栈顶
        5: iload_1
        // 将栈顶中的2存储到第三个局部变量表中
        6: istore_2
        // 将finally的10推送到栈顶
        7: bipush        10
        // 将栈顶的10存储到第二个局部变量表中
        9: istore_1
        // 将第三个局部变量表中的2推送到栈顶
        10: iload_2
        // 将栈顶的2出栈返回
        11: ireturn

        // 异常情况
        // 将异常信息存储到第三个局部变量表
        12: astore_2
        // 将0推送到栈顶
        13: iconst_0
        // 栈顶0出栈存储到第二个局部变量表
        14: istore_1
        // 加载第二个局部变量中的0到栈顶
        15: iload_1
        // 将栈顶的0存储到第四个局部变量表
        16: istore_3
        // 将finally的10推送到栈顶
        17: bipush        10
        // 将栈顶的10存储到第二个局部变量表中
        19: istore_1
        // 将第四个局部变量表中的0推送到栈顶
        20: iload_3
        // 栈顶的0出栈返回
        21: ireturn

        // 将除了Exception的异常存储到第五个局部变量表中
        22: astore        4
        // 将finally的10推送到栈顶
        24: bipush        10
        // 栈顶的10存储到第二个局部变量表中
        26: istore_1
        // 加载第五个局部变量表中的异常信息到栈顶
        27: aload         4
        // 将栈顶的异常抛出
        29: athrow
        Exception table:
        from    to  target type
        2     7    12   Class java/lang/Exception
        2     7    22   any
        12    17    22   any
        22    24    22   any
        LineNumberTable:
        line 6: 0
        line 8: 2
        line 9: 5
        line 14: 7
        line 9: 10
        line 10: 12
        line 11: 13
        line 12: 15
        line 14: 17
        line 12: 20
        line 14: 22
        line 15: 27
        LocalVariableTable:
        Start  Length  Slot  Name   Signature
        13       9     2     e   Ljava/lang/Exception;
        0      30     0  this   Lme/cxis/jvm/bytecode/TryCatchFinallyTest1;
        2      28     1     i   I
        StackMapTable: number_of_entries = 2
        frame_type = 255 *//* full_frame *//*
        offset_delta = 12
        locals = [ class me/cxis/jvm/bytecode/TryCatchFinallyTest1, int ]
        stack = [ class java/lang/Exception ]
        frame_type = 73 *//* same_locals_1_stack_item *//*
        stack = [ class java/lang/Throwable ]
    }
    SourceFile: "TryCatchFinallyTest1.java"*/

}
