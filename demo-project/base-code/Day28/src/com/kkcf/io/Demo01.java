package com.kkcf.io;

import java.io.FileOutputStream;
import java.io.IOException;

public class Demo01 {
    public static void main(String[] args) throws IOException {
        // 1.创建字节输出对象；
        FileOutputStream fos = new FileOutputStream("Day28/src/com/kkcf/io/a.txt");

        // 2.写入数据
        String str1 = "Ore,MechakuchaTamakoGaSukiTa!";
        byte[] bytes1 = str1.getBytes();
        fos.write(bytes1);

        fos.write("\n".getBytes()); // 换行

        String str2 = "Watashi,MochizouDaisuki.";
        byte[] bytes2 = str2.getBytes();
        fos.write(bytes2);

        // 3.释放资源。
        fos.close();
    }
}
