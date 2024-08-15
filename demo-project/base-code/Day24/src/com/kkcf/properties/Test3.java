package com.kkcf.properties;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Test3 {
    public static void main(String[] args) throws IOException {
        Properties p = new Properties();

        FileInputStream fis = new FileInputStream("Day24/src/com/kkcf/properties/a.properties");
        p.load(fis);
        fis.close();

        System.out.println(p); // {aaa=bbb, ccc=ddd, eee=fff}
    }
}
