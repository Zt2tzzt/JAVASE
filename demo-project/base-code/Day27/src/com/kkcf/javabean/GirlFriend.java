package com.kkcf.javabean;

import com.kkcf.exception.AgeOutOfBoundException;
import com.kkcf.exception.NameFormatException;

public class GirlFriend {
    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        int len = name.length();
        if (len < 3 || len > 10) throw new NameFormatException(name + "长度不能小于 3 或大于 10");

        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (age < 18 || age > 40) throw new AgeOutOfBoundException(age + "年龄不能小于 18 或大于 40");

        this.age = age;
    }

    @Override
    public String toString() {
        return "GirlFriend{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
