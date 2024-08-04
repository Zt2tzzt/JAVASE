package com.kkcf.methodref;

import com.kkcf.javabean.Studnet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test5 {
    public static void main(String[] args) {
        ArrayList<Studnet> stuList = new ArrayList<>(List.of(
                new Studnet("张三", 18),
                new Studnet("李四", 19),
                new Studnet("王五", 20),
                new Studnet("赵六", 21)
        ));

        String[] newStuList = stuList.stream().map(Studnet::getNameAge).toArray(String[]::new);

        System.out.println(Arrays.toString(newStuList));
    }
}
