package com.kkcf.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Test3 {
    /**
     * 此方法用于：拷贝文件夹
     * @param src 源文件夹
     * @param dest 目标文件夹
     * @throws IOException IO异常
     */
    private static void copy(File src, File dest) throws IOException {
        // 创建目标文件夹
        boolean flag = dest.mkdirs();
        if (flag) System.out.println("新建了文件夹：" + dest.getName());

        File[] files = src.listFiles();
        if (files == null) return;

        for (File file : files) {
            if (file.isFile()) {
                // 拷贝
                FileInputStream fis = new FileInputStream(file);
                FileOutputStream fos = new FileOutputStream(new File(dest, file.getName()));

                byte[] bytes = new byte[1024 * 1024 * 5];
                int len;
                while ((len = fis.read(bytes)) != -1)
                    fos.write(bytes, 0, len);

                fos.close();
                fis.close();
            } else {
                copy(file, new File(dest, file.getName()));
            }
        }
    }

    public static void main(String[] args) throws IOException {
        File src = new File("Day28/src/com/kkcf/test/abc");
        File dest = new File("Day28/src/com/kkcf/test/dest");

        copy(src, dest);
    }
}
