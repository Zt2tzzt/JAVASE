package com.kkcf.test;

import java.io.FileWriter;
import java.io.IOException;

public class Test2 {
    public static void main(String[] args) throws IOException {
        FileWriter fw = new FileWriter("Day28/src/com/kkcf/test/b.txt");

        fw.write("静静");
        fw.flush();
        fw.write("最棒！");

        fw.close();
    }
}
