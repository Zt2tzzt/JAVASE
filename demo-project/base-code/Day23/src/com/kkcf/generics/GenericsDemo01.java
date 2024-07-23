package com.kkcf.generics;

import java.util.ArrayList;

public class GenericsDemo01 {
    private static class Student {
        private String name;
        private int age;


        public Student() {
        }

        public Student(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String toString() {
            return "Student{name = " + name + ", age = " + age + "}";
        }
    }

    public static void main(String[] args) {
        ArrayList list = new ArrayList();

        list.add("aaa");
        list.add(123);
        list.add(new Student("zhangsan", 23));
    }
}
