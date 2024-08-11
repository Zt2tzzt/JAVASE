package com.kkcf.test;

import java.io.*;

public class Test2 {
    public static void main(String[] args) throws IOException {
        // 读取
        BufferedReader br = new BufferedReader(new FileReader("Day29/src/com/kkcf/test/count.txt"));

        String s = br.readLine();

        br.close();

        // 判断
        int count = Integer.parseInt(s);
        System.out.println(++count <= 3 ? "欢迎使用本软件，第 " + count + " 次使用免费" : "本软件只能免费使用 3 次，欢迎您注册会员后，继续使用");

        // 写入
        BufferedWriter bw = new BufferedWriter(new FileWriter("Day29/src/com/kkcf/test/count.txt"));

        bw.write(String.valueOf(count));

        bw.close();

    }
}
