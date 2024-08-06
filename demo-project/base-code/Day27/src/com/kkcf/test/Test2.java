package com.kkcf.test;

import java.io.File;
import java.io.IOException;

public class Test2 {
    public static void main(String[] args) throws IOException {
        File f1 = new File("Day27/src/com/kkcf/test/aaa");

        boolean flag = f1.mkdirs();

        if (flag) {
            File f2 = new File(f1, "a.txt");
            boolean flag2 = f2.createNewFile();
            System.out.println(flag2 ? "创建成功" : "创建失败");
        }
    }
}
