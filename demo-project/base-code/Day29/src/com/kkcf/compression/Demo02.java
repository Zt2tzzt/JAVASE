package com.kkcf.compression;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.StringJoiner;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Demo02 {
    public static void toZip(File src, File dest) throws IOException {
        // 压缩包名称
        StringJoiner sj = new StringJoiner(".", "", "");
        String[] split = src.getName().split("\\.");
        for (int i = 0; i < split.length - 1; i++)
            sj.add(split[i]);

        sj.add("zip");
        String zipName = sj.toString();

        // 创建压缩流，关联压缩包
        File destFile = new File(dest, zipName);
        ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(destFile));

        // 创建 ZipEntry 对象
        ZipEntry zipEntry = new ZipEntry(src.getName());

        // 把 ZipEntry 对象放入到压缩包中
        zos.putNextEntry(zipEntry);

        // 把 src 文件中的数据，写入到压缩包文件中。
        FileInputStream fis = new FileInputStream(src);

        int b;
        while ((b = fis.read()) != -1)
            zos.write(b);

        fis.close();

        zos.closeEntry();
        zos.close();
    }

    public static void main(String[] args) throws IOException {
        File src = new File("Day29/src/com/kkcf/compression/a.txt");
        File dest = new File("Day29/src/com/kkcf/compression/");

        toZip(src, dest);
    }
}
