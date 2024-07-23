package com.kkcf.generics;

import java.util.ArrayList;

public class GenericsDemo02 {
    public static void method(ArrayList<? extends Ye> list) {
    }

    public static void main(String[] args) {
        ArrayList<Ye> list1 = new ArrayList<>();
        ArrayList<Fu> list2 = new ArrayList<>();
        ArrayList<Zi> list3 = new ArrayList<>();

        // 数据具有继承性
        method(list1);
        method(list2);
        method(list3);
    }
}


class Ye {}

class Fu extends Ye {}

class Zi extends Fu {}
