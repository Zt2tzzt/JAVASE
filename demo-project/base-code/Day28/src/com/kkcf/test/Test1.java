package com.kkcf.test;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Test1 {
    public static void main(String[] args) throws IOException {
        FileReader fr = new FileReader("Day28/src/com/kkcf/test/a.txt");
        fr.read();

        // 创建字符输出流，会清空文件
        FileWriter fw = new FileWriter("Day28/src/com/kkcf/test/b.txt");

        int ch;
        while ((ch = fr.read()) != -1)
            fw.write(ch); // 只能读取到缓冲区中的字节

        fw.close();
        fr.close();
    }
}
