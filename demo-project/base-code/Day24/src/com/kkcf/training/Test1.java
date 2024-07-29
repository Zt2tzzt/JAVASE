package com.kkcf.training;

import com.kkcf.javabean.Student;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Test1 {
    public static void main(String[] args) {
        ArrayList<Student> stuList = new ArrayList<>();

        Student stu1 = new Student("张三", 16, '男');
        Student stu2 = new Student("李四", 17, '女');
        Student stu3 = new Student("王五", 18, '男');
        Student stu4 = new Student("赵六", 19, '女');
        Student stu5 = new Student("田七", 20, '男');

        Collections.addAll(stuList, stu1, stu2, stu3, stu4, stu5);

        // 随机点名-方式一：
        Random r = new Random();
        Student randomStu1 = stuList.get(r.nextInt(stuList.size()));
        System.out.println(randomStu1.getName());

        // 随机点名-方式二
        Collections.shuffle(stuList);
        Student randomStu2 = stuList.get(0);
        System.out.println(randomStu2.getName());
    }
}
