package com.kkcf.test;

import java.io.File;

public class Test5 {
    public static void deleteFile(File src) {
        if (src.isFile()) {
            boolean flag = src.delete();
            System.out.println((flag ? "删除成功" : "删除失败") + src.getAbsoluteFile());
        } else {
            File[] files = src.listFiles();

            if (files == null) return;

            for (File file : files) {
                deleteFile(file);
            }
            boolean flag = src.delete();
            System.out.println((flag ? "删除成功" : "删除失败") + src.getAbsoluteFile());
        }
    }

    public static void main(String[] args) {
        File src = new File("Day27/src/com/kkcf/test/aaa");
        deleteFile(src);
    }
}
