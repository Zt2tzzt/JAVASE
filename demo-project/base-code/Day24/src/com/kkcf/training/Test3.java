package com.kkcf.training;

import com.kkcf.javabean.Student;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Test3 {
    public static void main(String[] args) {
        // 学生集合
        ArrayList<Student> stuList = new ArrayList<>();

        Student stu1 = new Student("张三", 18, '男');
        Student stu2 = new Student("李四", 19, '女');
        Student stu3 = new Student("王五", 20, '男');
        Student stu4 = new Student("赵六", 21, '女');
        Student stu5 = new Student("田七", 22, '男');

        boolean flag = Collections.addAll(stuList, stu1, stu2, stu3, stu4, stu5);
        if (!flag) return;

        // 随机点名，点 10 轮
        Random r = new Random();
        ArrayList<Student> tempList = new ArrayList<>();
        int size = stuList.size();

        // 外循环，轮数
        for (int i = 0; i < 10; i++) {
            System.out.println("开始第 " + (i + 1) + " 轮点名");

            // 内循环：点名
            for (int j = 0; j < size; j++) {
                int index = r.nextInt(stuList.size());
                Student stu = stuList.remove(index);
                System.out.println(stu.getName() + "，被点到了");
                boolean flag1 = tempList.add(stu);
                if (!flag1) return;
            }

            stuList.addAll(tempList); // 将 tempList 中的学生对象，添加到 stuList 中。
            tempList.clear();
        }
    }
}
