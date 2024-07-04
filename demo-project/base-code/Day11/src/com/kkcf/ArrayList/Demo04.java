package com.kkcf.ArrayList;

import java.util.ArrayList;
import java.util.Scanner;

public class Demo04 {
    public static void main(String[] args) {
        ArrayList<Student> stus = new ArrayList<>();

        Scanner sc = new Scanner(System.in);

        for (int i = 0; i < 3; i++) {
            System.out.println("请输入第" + (i + 1) + "个学生的姓名：");
            String name = sc.next();

            System.out.println("请输入第" + (i + 1) + "个学生的年龄：");
            int age = sc.nextInt();

            stus.add(new Student(name, age));
        }

        sc.close();

        for (int i = 0; i < stus.size(); i++) {
            Student stu = stus.get(i);
            System.out.println(stu.getName() + " " + stu.getAge());
        }
    }
}
