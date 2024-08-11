package com.kkcf.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Demo02 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("Day29/src/com/kkcf/io/a.txt"));

        String s;
        while ((s = br.readLine()) != null)
            System.out.println(s);

        br.close();
    }
}
