package com.kkcf.print;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

public class Demo01 {
    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
        PrintStream ps = new PrintStream(new FileOutputStream("Day29/src/com/kkcf/print/a.txt"), true, "UTF-8");

        ps.println(97); // 写入 97
        ps.print(true); // 写入 true
        ps.println();
        ps.printf("%s爱上了%s", "阿珍", "阿强");

        ps.close();
    }
}
