package com.kkcf.test;

import com.kkcf.javabean.*;

import java.util.ArrayList;

public class Test04 {
    /**
     * 此方法用于，养狗
     * @param list 狗列表
     */
    public static void keepCat(ArrayList<? extends Cat> list) {
        for (Cat cat : list)
            cat.eat();
    }

    /**
     * 此方法用于，养猫
     * @param list 猫列表
     */
    public static void keepDog(ArrayList<? extends Dog> list) {
        for (Dog dog : list)
            dog.eat();
    }

    /**
     * 此方法用于，养动物
     * @param list 猫列表
     */
    public static void keepPet(ArrayList<? extends Animal> list) {
        for (Animal animal : list)
            animal.eat();
    }

    public static void main(String[] args) {
        ArrayList<PersianCat> pcs = new ArrayList<>();
        pcs.add(new PersianCat("小a", 1));
        pcs.add(new PersianCat("小b", 1));
        pcs.add(new PersianCat("小c", 2));

        ArrayList<LihuaCat> lhs = new ArrayList<>();
        lhs.add(new LihuaCat("小d", 1));
        lhs.add(new LihuaCat("小e", 2));
        lhs.add(new LihuaCat("小f", 1));

        keepCat(pcs);
        keepCat(lhs);

        ArrayList<TeditDog> teditList = new ArrayList<>();
        teditList.add(new TeditDog("小g", 2));
        teditList.add(new TeditDog("小h", 2));
        teditList.add(new TeditDog("小i", 1));

        ArrayList<HuskyDog> huskyList = new ArrayList<>();
        huskyList.add(new HuskyDog("小j", 1));
        huskyList.add(new HuskyDog("小k", 2));
        huskyList.add(new HuskyDog("小l", 1));

        keepDog(teditList);
        keepDog(huskyList);
    }
}
