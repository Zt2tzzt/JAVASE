package com.kkcf.compression;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Demo03 {
    /**
     * 此方法用于：压缩一个文件夹下的所有内容
     * @param src 压缩的文件夹
     * @param zos 压缩流
     * @param name 要压缩文件、文件夹的名称
     */
    public static void toZip(File src, ZipOutputStream zos, String name) throws IOException {
        File[] files = src.listFiles();

        assert files != null;
        for (File file : files) {
            if (file.isFile()) {
                ZipEntry zipEntry = new ZipEntry((name == null ? file.getName() : name + "/") + file.getName()); // 难点
                zos.putNextEntry(zipEntry);

                FileInputStream fis = new FileInputStream(file);
                int b;
                while ((b = fis.read()) != -1)
                    zos.write(b);

                fis.close();
                zos.closeEntry();
            } else {
                toZip(file, zos, (name == null ? file.getName() : name + "/") + file.getName());
            }
        }
    }

    public static void main(String[] args) throws IOException {
        // 要压缩的文件夹
        File src = new File("Day29/src/com/kkcf/compression/bbb");

        // 压缩后的文件
        File destParent = src.getParentFile();
        File dest = new File(destParent, src.getName() + ".zip");

        // 压缩
        ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(dest));

        toZip(src, zos, null);

        zos.close();
    }
}
