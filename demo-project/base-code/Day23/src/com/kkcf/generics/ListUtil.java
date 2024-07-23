package com.kkcf.generics;

import java.util.List;

public class ListUtil {
    // 私有化静态方法，不让外界创建工具类对象。
    private ListUtil() {}

    public static <E> void addAll(List<E> list, E e1, E e2, E e3) {
        list.add(e1);
        list.add(e2);
        list.add(e3);
    }

    public static <E> void addAll(List<E> list, E...e) {
        for (E ele : e) {
            list.add(ele);
        }
    }
}
