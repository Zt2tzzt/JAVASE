package com.kkcf.io;

import java.io.FileReader;
import java.io.IOException;

public class Demo07 {
    public static void main(String[] args) throws IOException {
        FileReader fr = new FileReader("Day28/src/com/kkcf/io/abc.txt");

        int ch;
        while ((ch = fr.read()) != -1)
            System.out.print((char) ch);

        /*我超级超级喜欢玉子！
        我也最喜欢饼藏了，请讲！*/

        fr.close();
    }
}
