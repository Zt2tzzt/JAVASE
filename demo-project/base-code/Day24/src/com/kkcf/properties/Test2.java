package com.kkcf.properties;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

public class Test2 {
    public static void main(String[] args) throws IOException {
        Properties p = new Properties();

        p.put("aaa", "bbb");
        p.put("ccc", "ddd");
        p.put("eee", "fff");

        BufferedWriter bw = new BufferedWriter(new FileWriter("Day24/src/com/kkcf/properties/a.properties"));

        // 写出数据 - 普通做法
        /*for (Map.Entry<Object, Object> entry : p.entrySet()) {
            bw.write(entry.getKey() + "=" + entry.getValue());
            bw.newLine();
        }*/

        // 写出数据 - store 方法
        p.store(bw, "This is a test");

        bw.close();
    }
}
