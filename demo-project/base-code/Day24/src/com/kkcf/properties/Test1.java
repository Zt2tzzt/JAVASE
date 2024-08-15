package com.kkcf.properties;

import java.util.Properties;

public class Test1 {
    public static void main(String[] args) {
        Properties prop = new Properties();

        prop.put("aaa", "111");
        prop.put("bbb", "222");
        prop.put("ccc", "333");
        prop.put("ddd", "444");

        System.out.println(prop); // {aaa=111, ccc=333, bbb=222, ddd=444}

        for (Object key : prop.keySet()) {
            Object val = prop.get(key);
            System.out.println(key + "=" + val);
            /*aaa=111
            ccc=333
            bbb=222
            ddd=444*/
        }
    }
}
