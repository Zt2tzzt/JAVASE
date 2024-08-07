package com.kkcf.io;

import java.io.FileInputStream;
import java.io.IOException;

public class Demo03 {
    public static void main(String[] args) throws IOException {
        FileInputStream fis = new FileInputStream("Day28/src/com/kkcf/io/b.txt");

        int b1 = fis.read();

        System.out.println(b1); // 111
        System.out.println((char)b1); // o
    }
}
