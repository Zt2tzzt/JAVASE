package com.kkcf.compression;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.StringJoiner;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class Demo01 {
    /**
     * 此方法用于：解压压缩包
     * @param src 解压的压缩包
     * @param dest 解压到的目录
     * @throws IOException 抛出异常
     */
    public static void unzip(File src, File dest) throws IOException {
        // 创建父级目录
        String[] split = src.getName().split("\\.");
        StringJoiner sj = new StringJoiner(".", "", "");
        for (int i = 0; i < split.length - 1; i++)
            sj.add(split[i]);
        String dirName = sj.toString();
        File destDir = new File(dest, dirName);
        destDir.mkdirs();

        // 解压文件
        ZipInputStream zis = new ZipInputStream(new FileInputStream(src));

        ZipEntry entry;
        while ((entry = zis.getNextEntry()) != null) {
            System.out.println(entry);
            File file = new File(destDir, entry.toString());

            if (entry.isDirectory()) {
                // 在目的地 distdir 处，创建一个同名的文件夹
                file.mkdirs();
            } else {
                // 读取压缩包中的文件，并把它存放到目的地 destdir 文件夹中
                FileOutputStream fos = new FileOutputStream(file);
                int b;
                while ((b = zis.read()) != -1)
                    fos.write(b);

                fos.close();
                // 表示压缩包中的一个文件处理完毕
                zis.closeEntry();
            }
        }

        zis.close();
    }
    public static void main(String[] args) throws IOException {
        // 解压的压缩包
        File src = new File("Day29/src/com/kkcf/compression/aaa.zip");
        // 解压到的目录
        File destDir = new File("Day29/src/com/kkcf/compression/");

        unzip(src, destDir);
    }
}
