package me.cxis.jvm.bytecode;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class TryWithResourcesTest {

    public static void foo() throws IOException {
        try(FileOutputStream outputStream = new FileOutputStream("xxxx")) {
            outputStream.write(1);
        }
}
}
