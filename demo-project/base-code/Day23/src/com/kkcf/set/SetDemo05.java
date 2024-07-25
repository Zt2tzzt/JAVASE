package com.kkcf.set;

import java.util.Comparator;
import java.util.TreeSet;

public class SetDemo05 {
    public static void main(String[] args) {
        /*TreeSet<String> treeSet = new TreeSet<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                int i = o1.length() - o2.length();

                // 如果长度一样，再使用默认排序规则
                if (i == 0) i = o1.compareTo(o2);

                return i;
            }
        });*/

        // 使用 Lambda 表达式进行优化
        // 如果长度一样，再使用默认排序规则
        TreeSet<String> treeSet = new TreeSet<>(Comparator.comparingInt(String::length).thenComparing(o -> o));


        treeSet.add("qwer");
        treeSet.add("abc");
        treeSet.add("df");
        treeSet.add("c");

        System.out.println(treeSet); // [c, df, abc, qwer]
    }
}
