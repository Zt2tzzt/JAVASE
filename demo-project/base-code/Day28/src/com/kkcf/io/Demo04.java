package com.kkcf.io;

import java.io.FileInputStream;
import java.io.IOException;

public class Demo04 {
    public static void main(String[] args) throws IOException {
        FileInputStream fis = new FileInputStream("Day28/src/com/kkcf/io/a.txt");

        int b;
        while ((b = fis.read()) != -1)
            System.out.print((char) b);

        fis.close();
    }
}
