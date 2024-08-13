package com.kkcf.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Test4 {

    private static void encryption(File src, File dest) throws IOException {
        FileInputStream fis = new FileInputStream(src);
        FileOutputStream fos = new FileOutputStream(dest);

        int b;
        while((b = fis.read()) != -1)
            fos.write(b ^ 2);

        fos.close();
        fis.close();
    }

    public static void main(String[] args) throws IOException {
        // 加密
        File src = new File("Day28/src/com/kkcf/test/secret.txt");
        File dest = new File("Day28/src/com/kkcf/test/encry.txt");

        encryption(src, dest);

        // 解密
        src = new File("Day28/src/com/kkcf/test/encry.txt");
        dest = new File("Day28/src/com/kkcf/test/decry.txt");

        encryption(src, dest);
    }
}
