package com.kkcf.test;

import java.io.File;

public class Test3 {
    /**
     * 此方法用于：判断目录下是否有avi文件
     * @param file 目录
     * @return 是否有 avi 文件
     */
    public static boolean hasAvi(File file) {
        File[] files = file.listFiles();

        for (File f : files)
            if (f.getName().endsWith(".avi")) return true;

        return false;
    }

    public static void main(String[] args) {
        File file = new File("Day27/src/com/kkcf/test/aaa");
        boolean flag = hasAvi(file);
        System.out.println(flag ? "有 avi 文件" : "没有 avi 文件");
    }
}
