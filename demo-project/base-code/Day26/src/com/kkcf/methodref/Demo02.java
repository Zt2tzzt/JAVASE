package com.kkcf.methodref;

import com.kkcf.javabean.Studnet;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Demo02 {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>(List.of("张无忌,15", "赵敏,20", "张三丰,100", "张翠山,30", "周芷若,40", "王二麻子,50"));

            /*List<Studnet> newList = list.stream().map(new Function<String, Studnet>() {
                @Override
                public Studnet apply(String s) {
                    String[] split = s.split(",");
                    String name = split[0];
                    int age = Integer.parseInt(split[1]);
                    return new Studnet(name, age);
                }
            }).collect(Collectors.toList());*/

        List<Studnet> newList = list.stream().map(Studnet::new).collect(Collectors.toList());

        System.out.println(newList);
    }
}
