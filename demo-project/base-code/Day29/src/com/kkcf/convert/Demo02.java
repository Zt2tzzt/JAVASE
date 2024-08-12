package com.kkcf.convert;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;

public class Demo02 {
    public static void main(String[] args) throws IOException {
        FileWriter fw = new FileWriter("Day29/src/com/kkcf/convert/gbkFile.txt", Charset.forName("GBK"));

        fw.write("我超级超级喜欢玉子！");

        fw.close();
    }
}
