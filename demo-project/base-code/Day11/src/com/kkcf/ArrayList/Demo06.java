package com.kkcf.ArrayList;

import java.util.ArrayList;

public class Demo06 {
    public static void main(String[] args) {
        ArrayList<Phone> list = new ArrayList<>();

        list.add(new Phone("小米", 1999));
        list.add(new Phone("苹果", 8999));
        list.add(new Phone("锤子", 2999));

        ArrayList<Phone> list2 = findPhone(list);
        System.out.println(list2);
    }

    public static ArrayList<Phone> findPhone(ArrayList<Phone> list) {
        ArrayList<Phone> result = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            Phone p = list.get(i);

            if (p.getPrice() < 3000)
                result.add(p);
        }

        return result;
    }
}
