package com.kkcf.set;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class SetDemo01 {
    public static void main(String[] args) {

        Set<String> set = new HashSet<>();

        // 使用 Set 系列结合的 add 方法，要处理返回值，因为有可能添加不成功。
        boolean flag1 = set.add("张三");
        boolean flag2 = set.add("张三");

        System.out.println(flag1 + " " + flag2); // true false

        set.add("李四");
        set.add("王五");

        // Set 系列结合是无序的
        System.out.println(set); // [李四, 张三, 王五]

        // 使用迭代器遍历
        Iterator<String> it = set.iterator();
        while (it.hasNext()) {
            String s = it.next();
            System.out.println(s);
        }

        // 使用增强 for 遍历
        for (String s : set) {
            System.out.println(s);
        }

        // 使用 forEach + Lambda 表达式遍历
        /*set.forEach(new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        });*/
        set.forEach(System.out::println);
    }
}
