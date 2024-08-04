package com.kkcf.methodref;

import com.kkcf.javabean.Studnet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test3 {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>(List.of("张三,23", "李四,24", "王五,25", "赵六,26"));

        Studnet[] stus = list.stream().map(Studnet::new).toArray(Studnet[]::new);

        System.out.println(Arrays.toString(stus));
    }
}
