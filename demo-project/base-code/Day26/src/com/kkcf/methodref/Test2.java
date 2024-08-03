package com.kkcf.methodref;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Test2 {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>(List.of("张三丰", "周芷若", "张无忌", "张翠山", "赵敏"));

        StringOperator so = new StringOperator();
        List<String> newList = list.stream()
                .filter(so::stringJudge)
                .collect(Collectors.toList());

        System.out.println(newList); // [张三丰, 张无忌, 张翠山]
    }
}
