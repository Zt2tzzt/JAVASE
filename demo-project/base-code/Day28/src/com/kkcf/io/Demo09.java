package com.kkcf.io;

import java.io.FileWriter;
import java.io.IOException;

public class Demo09 {
    public static void main(String[] args) throws IOException {
        FileWriter fw = new FileWriter("Day28/src/com/kkcf/io/cba.txt");

        char[] chs = {'w', 'e', 'e', '静'};
        fw.write(chs);

        fw.close();
    }
}
