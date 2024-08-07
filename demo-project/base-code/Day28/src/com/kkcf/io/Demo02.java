package com.kkcf.io;

import java.io.FileOutputStream;
import java.io.IOException;

public class Demo02 {
    public static void main(String[] args) throws IOException {
        FileOutputStream fos = new FileOutputStream("Day28/src/com/kkcf/io/a.txt", true);

        fos.write("Dozo!".getBytes());

        fos.close();
    }
}
