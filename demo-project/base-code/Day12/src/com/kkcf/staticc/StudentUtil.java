package com.kkcf.staticc;

import java.util.ArrayList;

public class StudentUtil {
    private StudentUtil() {}

    public static int getMaxAgeStu(ArrayList<Student> list) {
        int maxAge = list.get(0).getAge();

        for (int i = 1; i < list.size(); i++) {
            int age = list.get(i).getAge();
            if (age > maxAge) maxAge = age;
        }

        return maxAge;
    }
}
