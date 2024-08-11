package com.kkcf.io;

import java.io.*;

public class Demo01 {
    public static void main(String[] args) throws IOException {
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream("Day29/src/com/kkcf/io/a.txt"));
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("Day29/src/com/kkcf/io/b.txt"));

        int b;
        while ((b = bis.read()) != -1)
            bos.write((char) b);

        bos.close();
        bis.close();
    }
}
