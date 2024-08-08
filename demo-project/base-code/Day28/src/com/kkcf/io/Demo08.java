package com.kkcf.io;

import java.io.FileReader;
import java.io.IOException;

public class Demo08 {
    public static void main(String[] args) throws IOException {
        FileReader fr = new FileReader("Day28/src/com/kkcf/io/abc.txt");

        char[] chs = new char[2];
        int len = 0;
        while ((len = fr.read(chs)) != -1)
            System.out.print(new String(chs, 0, len));

        /*我超级超级喜欢玉子！
        我也最喜欢饼藏了，请讲！*/

        fr.close();
    }
}
