package com.kkcf.convert;

import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;

public class Demo01 {
    public static void main(String[] args) throws IOException {
        //InputStreamReader isr = new InputStreamReader(new FileInputStream("Day29/src/com/kkcf/convert/gbkFile.txt"), "GBK");
        FileReader fr = new FileReader("Day29/src/com/kkcf/convert/gbkFile.txt", Charset.forName("GBK"));

        int ch;
        while ((ch = fr.read()) != -1)
            System.out.print((char) ch);

        fr.close();
    }
}
