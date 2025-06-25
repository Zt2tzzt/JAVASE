package com.kkcf.iopractice;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Test3 {
    public static void main(String[] args) throws IOException {
        // 读取学生信息，并初始化学生对象
        ArrayList<Student> stus = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader("Day30/src/com/kkcf/iopractice/name.txt"));

        String line;
        while ((line = br.readLine()) != null) {
            String[] str = line.split("-");
            Student stu = new Student(str[0], Integer.parseInt(str[2]), str[1].charAt(0), Double.parseDouble(str[3]));
            stus.add(stu);
        }

        br.close();

        // 计算权重总和
        double weightSum = 0;
        for (Student stu : stus)
            weightSum += stu.getWeight();

        // 计算每一个人的权重占比
        int len = stus.size();
        double[] arr = new double[len];
        for (int i = 0; i < len; i++)
            arr[i] = stus.get(i).getWeight() / weightSum;

        // 计算每一个人的权重占比范围
        for (int i = 1; i < arr.length; i++)
            arr[i] = arr[i] + arr[i - 1];

        // System.out.println(Arrays.toString(arr)); // [0.1, 0.2, 0.30000000000000004, 0.4, 0.5, 0.6, 0.7, 0.7999999999999999, 0.8999999999999999, 0.9999999999999999]

        // 在权重范围内，随机抽取一个数字，将它与权占比数组映射得到学生对象集合索引
        double random = Math.random(); // 0.0 - 1.0 之间的小数
        int i = Arrays.binarySearch(arr, random); // 返回一个 (-插入点 - 1) 整数
        int index = -i - 1;

        Student student = stus.get(index);
        System.out.println("随机点名到学生：" + student.getName());

        // 修改权重
        double newWeight = student.getWeight() / 2;
        student.setWeight(newWeight);

        // 把集合中的学生数据，再次写入到文件中
        BufferedWriter bw = new BufferedWriter(new FileWriter("Day30/src/com/kkcf/iopractice/name.txt"));

        for (Student stu : stus) {
            bw.write(stu.toString());
            bw.newLine();
        }

        bw.close();
    }
}
