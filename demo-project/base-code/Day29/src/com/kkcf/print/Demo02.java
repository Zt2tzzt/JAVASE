package com.kkcf.print;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Demo02 {
    public static void main(String[] args) throws IOException {
        PrintWriter pw = new PrintWriter(new FileWriter("Day29/src/com/kkcf/print/a.txt"), true);

        pw.println("今天你终于叫我名字了，虽然叫错了，但是没关系，我马上改");
        pw.print("侬好侬好");
        pw.printf("%s爱上了%s", "阿珍", "阿强");

        pw.close();
    }
}
