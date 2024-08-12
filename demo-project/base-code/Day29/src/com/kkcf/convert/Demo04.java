package com.kkcf.convert;

import java.io.*;

public class Demo04 {
    public static void main(String[] args) throws IOException {
        FileInputStream fis = new FileInputStream("Day29/src/com/kkcf/convert/utf8File.txt");
        InputStreamReader isr = new InputStreamReader(fis);
        BufferedReader br = new BufferedReader(isr);

        String line;
        while ((line = br.readLine()) != null)
            System.out.println(line);

        br.close();
    }
}
