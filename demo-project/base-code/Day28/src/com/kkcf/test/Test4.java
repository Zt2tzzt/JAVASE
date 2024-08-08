package com.kkcf.test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Test4 {

    private static void encryption(FileInputStream fis, FileOutputStream fos) throws IOException {
        // 加密
        int b;
        while((b = fis.read()) != -1)
            fos.write(b ^ 2);

        fos.close();
        fis.close();
    }

    public static void main(String[] args) throws IOException {
        // 加密
        FileInputStream fis = new FileInputStream("Day28/src/com/kkcf/test/secret.txt");
        FileOutputStream fos = new FileOutputStream("Day28/src/com/kkcf/test/encry.txt");

        encryption(fis, fos);

        // 解密
        fis = new FileInputStream("Day28/src/com/kkcf/test/encry.txt");
        fos = new FileOutputStream("Day28/src/com/kkcf/test/decry.txt");

        encryption(fis, fos);
    }
}
