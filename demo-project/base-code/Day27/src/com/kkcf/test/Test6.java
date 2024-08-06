package com.kkcf.test;

import java.io.File;

public class Test6 {
    public static long getFileSize(File src) {
        //File[] files = src.listFiles();
        if(src.isFile()) {
            return src.length();
        } else {
            File[] files = src.listFiles();

            if (files == null) return 0;

            long size = 0;
            for(File file : files) {
                size += getFileSize(file);
            }
            return size;
        }
    }
    public static void main(String[] args) {
        File src = new File("Day27/src/com/kkcf/test/aaa");

        long fileSize = getFileSize(src);

        System.out.println("文件大小为：" + fileSize);
    }
}
