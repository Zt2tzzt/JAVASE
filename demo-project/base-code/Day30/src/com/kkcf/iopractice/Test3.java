package com.kkcf.iopractice;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Test3 {
    public static void main(String[] args) throws IOException {
        // 读取学生信息，并初始化学生对象
        ArrayList<Student> stus = new ArrayList<>();

        BufferedReader br = new BufferedReader(new FileReader("Day30/src/com/kkcf/iopractice/name.txt"));

        String line;
        while ((line = br.readLine()) != null) {
            String[] str = line.split("-");
            Student stu = new Student(str[0], Integer.parseInt(str[2]), str[1].charAt(0), 1);
            stus.add(stu);
        }

        br.close();

        stus.forEach(System.out::println);
    }
}
