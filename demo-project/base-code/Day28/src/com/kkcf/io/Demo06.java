package com.kkcf.io;

import java.io.FileInputStream;
import java.io.IOException;

public class Demo06 {
    public static void main(String[] args) throws IOException {
        FileInputStream fis = new FileInputStream("Day28/src/com/kkcf/io/a.txt");

        byte[] bytes = new byte[2];

        int len;
        while ((len = fis.read(bytes)) != -1) {
            String str = new String(bytes, 0, len);
            System.out.println(str);
        }

        fis.close();
    }
}
