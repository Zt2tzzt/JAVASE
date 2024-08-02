package com.kkcf.test;

import com.kkcf.javabean.Actor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Test5 {
    public static void main(String[] args) {
        ArrayList<String> maleList = new ArrayList<>(List.of("蔡坤坤,23", "叶猴先,23", "刘不甜,22", "吴签,24", "谷嘉,30", "萧良梁,27"));
        ArrayList<String> femaleList = new ArrayList<>(List.of("赵晓颖,23", "杨颖,36", "高元元,43", "张天天,31", "刘诗,35", "杨小密,33"));

        Stream<String> stream1 = maleList.stream()
                .filter(s -> s.split(",")[0].length() == 3)
                .limit(2);

        Stream<String> stream2 = femaleList.stream()
                .filter(s -> s.split(",")[0].startsWith("杨"))
                .skip(1);

        List<Actor> newList = Stream.concat(stream1, stream2)
                .map(s -> new Actor(s.split(",")[0], Integer.parseInt(s.split(",")[1])))
                .collect(Collectors.toList());

        System.out.println(newList);
    }
}
