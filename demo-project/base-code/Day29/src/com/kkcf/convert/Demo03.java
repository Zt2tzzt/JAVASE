package com.kkcf.convert;

import java.io.*;
import java.nio.charset.Charset;

public class Demo03 {
    public static void main(String[] args) throws IOException {
        FileReader fr = new FileReader("Day29/src/com/kkcf/convert/gbkFile.txt", Charset.forName("GBK"));
        FileWriter fw = new FileWriter("Day29/src/com/kkcf/convert/utf8File.txt");

        int b;
        while ((b = fr.read()) != -1)
            fw.write(b);

        fw.close();
        fr.close();
    }
}
