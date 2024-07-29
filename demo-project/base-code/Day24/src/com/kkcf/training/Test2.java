package com.kkcf.training;

import com.kkcf.javabean.Student;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Test2 {
    public static void main(String[] args) {
        // 男、女学生列表集合
        ArrayList<Student> maleStu = new ArrayList<>();
        ArrayList<Student> femaleStu = new ArrayList<>();

        Student stu1 = new Student("张三", 16, '男');
        Student stu3 = new Student("王五", 18, '男');
        Student stu5 = new Student("田七", 20, '男');
        Collections.addAll(maleStu, stu1, stu3, stu5);

        Student stu2 = new Student("李四", 17, '女');
        Student stu4 = new Student("赵六", 19, '女');
        Collections.addAll(femaleStu, stu2, stu4);

        // 随即面
        int[] arr = {0, 0, 0, 1, 1, 1, 1, 1, 1, 1};

        Random r = new Random();
        // 打乱顺序
        for (int i = 0; i < arr.length; i++) {
            int index = r.nextInt(arr.length);
            int temp = arr[i];
            arr[i] = arr[index];
            arr[index] = temp;
        }

        int i = r.nextInt(arr.length);
        if (i > 0) {
            System.out.println("点名男学生");
            int i1 = r.nextInt(maleStu.size());
            System.out.println(maleStu.get(i1));
        } else {
            System.out.println("点名女学生");
            int i1 = r.nextInt(femaleStu.size());
            System.out.println(femaleStu.get(i1));
        }
    }
}
