package com.kkcf.io;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Demo03 {
    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter("Day29/src/com/kkcf/io/b.txt", true));

        bw.write("你嘴角上扬的样子，百度搜索不到");
        bw.newLine();
        bw.write("如果我结婚了，你一定要来哦，没有新娘我会很尴尬");
        bw.newLine();

        bw.close();
    }
}
