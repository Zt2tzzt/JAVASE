package com.kkcf.file;

import java.io.File;
import java.io.IOException;

public class Demo03 {
    public static void main(String[] args) throws IOException {
        File f1 = new File("D:\\Workshop\\tutorial\\JAVA\\demo-project\\base-code\\Day27\\src\\com\\kkcf\\file");

        File[] files = f1.listFiles();

        for (File file : files) {
            System.out.println(file);
        }
    }
}
