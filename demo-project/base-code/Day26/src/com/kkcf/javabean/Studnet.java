package com.kkcf.javabean;

import java.util.Objects;

public class Studnet {
    private String name;
    private int age;

    public Studnet() {
    }

    public Studnet(String str) {
        String[] split = str.split(",");
        this.name = split[0];
        this.age = Integer.parseInt(split[1]);
    }


    public Studnet(String name, int age) {
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

    @Override
    public String toString() {
        return "Studnet{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Studnet studnet = (Studnet) o;
        return age == studnet.age && Objects.equals(name, studnet.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }

    /**
     * 此方法用于，获取“姓名,年龄”g格式的字符串
     * @return “姓名,年龄”g格式的字符串
     */
    public String getNameAge() {
        return this.getName() + "," + this.getAge();
    }
}
