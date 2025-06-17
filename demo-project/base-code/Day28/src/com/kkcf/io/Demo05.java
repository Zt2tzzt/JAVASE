package com.kkcf.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Demo05 {
    public static void main(String[] args) throws FileNotFoundException {
        long start = System.currentTimeMillis();

        FileInputStream fis = new FileInputStream("Day28/src/com/kkcf/io/a.txt");
        FileOutputStream fos = new FileOutputStream("Day28/src/com/kkcf/io/b.txt");

        try (fis; fos) {
            byte[] bytes = new byte[1024 * 10254 * 5];
            int len;
            while ((len = fis.read(bytes)) != -1) fos.write(bytes, 0, len);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        long end = System.currentTimeMillis();
        System.out.println("用时：" + (end - start) + "ms");
    }
}
