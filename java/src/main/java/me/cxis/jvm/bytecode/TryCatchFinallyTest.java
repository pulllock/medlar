package me.cxis.jvm.bytecode;

public class TryCatchFinallyTest {

    public int getSomething() {
        try {
            return 0;
        } catch (Exception e) {
            return 1;
        } finally {
            return 2;
        }
    }

    /*public class me.cxis.jvm.bytecode.TryCatchFinallyTest
    minor version: 0
    major version: 52
    flags: ACC_PUBLIC, ACC_SUPER
    Constant pool:
            #1 = Methodref          #4.#21         // java/lang/Object."<init>":()V
            #2 = Class              #22            // java/lang/Exception
            #3 = Class              #23            // me/cxis/jvm/bytecode/TryCatchFinallyTest
            #4 = Class              #24            // java/lang/Object
            #5 = Utf8               <init>
   #6 = Utf8               ()V
   #7 = Utf8               Code
   #8 = Utf8               LineNumberTable
   #9 = Utf8               LocalVariableTable
  #10 = Utf8               this
            #11 = Utf8               Lme/cxis/jvm/bytecode/TryCatchFinallyTest;
  #12 = Utf8               getSomething
  #13 = Utf8               ()I
  #14 = Utf8               e
  #15 = Utf8               Ljava/lang/Exception;
  #16 = Utf8               StackMapTable
  #17 = Class              #22            // java/lang/Exception
            #18 = Class              #25            // java/lang/Throwable
            #19 = Utf8               SourceFile
  #20 = Utf8               TryCatchFinallyTest.java
  #21 = NameAndType        #5:#6          // "<init>":()V
            #22 = Utf8               java/lang/Exception
  #23 = Utf8               me/cxis/jvm/bytecode/TryCatchFinallyTest
  #24 = Utf8               java/lang/Object
  #25 = Utf8               java/lang/Throwable
    {
  public me.cxis.jvm.bytecode.TryCatchFinallyTest();
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
        0       5     0  this   Lme/cxis/jvm/bytecode/TryCatchFinallyTest;

        public int getSomething();
        descriptor: ()I
        flags: ACC_PUBLIC
        Code:
        stack=1, locals=4, args_size=1
        // 把0推送到栈顶
        0: iconst_0
        // 栈顶的0出栈，存储到第二个局部变量表中
        1: istore_1
        // 把finally的2推送到栈顶
        2: iconst_2
        // 返回栈顶数据，就是finally的2
        3: ireturn

        // 开始处理Exception类型异常
        // 将异常信息出栈，存储到第二个局部变量表中
        4: astore_1
        // 将1推送到栈顶
        5: iconst_1
        // 栈顶的1出栈，存储到第三个局部变量表中
        6: istore_2
        // 把finally的2推送到栈顶
        7: iconst_2
        // 返回栈顶数据，就是finally的2
        8: ireturn

        // 以下处理非Exception异常
        // 将异常出栈，存储到第4个局部变量表中
        9: astore_3
        // 把finally的2推送到栈顶
        10: iconst_2
        // 返回栈顶数据，就是finally的2
        11: ireturn
        Exception table:
        from    to  target type
        0     2     4   Class java/lang/Exception
        0     2     9   any
        4     7     9   any
        LineNumberTable:
        line 7: 0
        line 11: 2
        line 8: 4
        line 9: 5
        line 11: 7
        LocalVariableTable:
        Start  Length  Slot  Name   Signature
        5       4     1     e   Ljava/lang/Exception;
        0      12     0  this   Lme/cxis/jvm/bytecode/TryCatchFinallyTest;
        StackMapTable: number_of_entries = 2
        frame_type = 68 *//* same_locals_1_stack_item *//*
        stack = [ class java/lang/Exception ]
        frame_type = 68 *//* same_locals_1_stack_item *//*
        stack = [ class java/lang/Throwable ]
    }
    SourceFile: "TryCatchFinallyTest.java"*/

}
