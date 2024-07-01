package com.kkcf.object_riented;

public class GirlFriend {
    private String name = "aoi";
    private int age = 18;
    private String gender = "萌妹子";

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setAge(int age) {
        if (age > 18 && age < 50)
            this.age = age;
        else
            System.out.println("非法参数");
    }

    public int getAge() {
        return this.age;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getGender() {
        return this.gender;
    }
}
