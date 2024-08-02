package com.kkcf.stream;

import java.util.ArrayList;
import java.util.List;

public class Demo01 {
    public static void main(String[] args) {
        //集合的批量添加
        ArrayList<String> list1 = new ArrayList<>(List.of("张三丰","张无忌","张翠山","王二麻子","张良","谢广坤"));

        //遍历 list1 把以张开头的元素添加到 list2 中。
        /*ArrayList<String> list2 = new ArrayList<>();
        for (String s : list1) {
            if(s.startsWith("张")){
                list2.add(s);
            }
        }

        //遍历 list2 集合，把其中长度为 3 的元素，再添加到 list3 中。
        ArrayList<String> list3 = new ArrayList<>();
        for (String s : list2) {
            if(s.length() == 3){
                list3.add(s);
            }
        }

        for (String s : list3) {
            System.out.println(s);
        }*/

        list1.stream()
                .filter(s -> s.startsWith("张"))
                .filter(s -> s.length() == 3)
                .forEach(s -> System.out.println(s));
    }
}
