package com.kkcf.ArrayList;

import java.util.ArrayList;

public class Demo01 {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();

        // 增
        list.add("aaa");
        list.add("bbb");
        list.add("ccc");
        list.add("ddd");

        System.out.println(list); // [aaa, bbb, ccc, ddd]

        // 删
        boolean isRemove1 = list.remove("aaa");

        System.out.println(isRemove1); // true

        System.out.println(list); // [bbb, ccc, ddd]

        String removeString = list.remove(0);

        System.out.println(removeString); // bbb

        // 改
        String settedString = list.set(1, "xxx");

        System.out.println(settedString); // ddd

        // 查
        String s = list.get(1);

        System.out.println(s); // xxx

        // 遍历
        for (int i = 0; i < list.size(); i++) {
            String s1 = list.get(i);
            System.out.println(s1);
        }
    }
}
