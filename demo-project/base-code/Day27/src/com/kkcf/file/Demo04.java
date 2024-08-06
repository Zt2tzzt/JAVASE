package com.kkcf.file;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;

public class Demo04 {
    public static void main(String[] args) {
        File f1 = new File("D:\\Workshop\\tutorial\\JAVA\\demo-project\\base-code\\Day27\\src\\com\\kkcf\\file");

        File[] files = f1.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                File file = new File(dir, name);
                return file.isFile() && file.getName().endsWith(".txt");
            }
        });

        System.out.println(Arrays.toString(files)); // [D:\Workshop\tutorial\JAVA\demo-project\base-code\Day27\src\com\kkcf\file\a.txt, D:\Workshop\tutorial\JAVA\demo-project\base-code\Day27\src\com\kkcf\file\b.txt]
    }
}
