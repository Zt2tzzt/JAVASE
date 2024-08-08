package com.kkcf.test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.StringJoiner;

public class Test5 {
    public static void main(String[] args) throws IOException {
        // 读取
        FileInputStream fis = new FileInputStream("Day28/src/com/kkcf/test/sort.txt");

        StringBuilder sb = new StringBuilder();
        int b;
        while ((b = fis.read()) != -1)
            sb.append((char) b);

        String[] newArr = Arrays.stream(sb.toString().split("-"))
                .map(Integer::parseInt)
                .sorted()
                .map(i -> Integer.toString(i))
                .toArray(String[]::new);


        StringJoiner sj = new StringJoiner("-", "", "");
        Arrays.stream(newArr).forEach(sj::add);
        System.out.println(sj);

        // 写入，创建字节输出流，会清空文件中的内容
        FileOutputStream fos = new FileOutputStream("Day28/src/com/kkcf/test/sort.txt");
        fos.write(sj.toString().getBytes());

        fos.close();
        fis.close();
    }
}
