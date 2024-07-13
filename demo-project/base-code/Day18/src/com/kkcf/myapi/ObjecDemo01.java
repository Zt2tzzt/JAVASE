package com.kkcf.myapi;

public class ObjecDemo01 {
    public static void main(String[] args) {
        Object obj = new Object();

        System.out.println(obj.toString()); // java.lang.Object@4eec7777
        System.out.println(obj); // java.lang.Object@4eec7777

        Student stu = new Student();

        System.out.println(stu.toString()); // com.kkcf.myapi.Student@41629346
        System.out.println(stu); // com.kkcf.myapi.Student@41629346
    }
}
