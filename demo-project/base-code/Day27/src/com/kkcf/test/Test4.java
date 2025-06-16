package com.kkcf.test;

import java.io.File;

public class Test4 {
    public static void findAvi(File src) {
        File[] files = src.listFiles();
        if (files == null) return;

        for (File file : files) {
            if (file.isDirectory()) {
                findAvi(file);
            } else if (file.isFile()) {
                if (file.getName().endsWith(".avi")) {
                    System.out.println(file.getAbsolutePath());
                }
            }
        }
    }

    public static void main(String[] args) {
        File[] pans = File.listRoots();

        for (File pan : pans) {
            findAvi(pan);
        }
    }
}
