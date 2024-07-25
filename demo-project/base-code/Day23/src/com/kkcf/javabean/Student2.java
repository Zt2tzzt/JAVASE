package com.kkcf.javabean;

public class Student2 implements Comparable<Student2> {
    private String name;
    private int age;
    private int chinese;
    private int math;
    private int english;

    public Student2() {
    }

    public Student2(String name, int age, int chinese, int math, int english) {
        this.name = name;
        this.age = age;
        this.chinese = chinese;
        this.math = math;
        this.english = english;
    }

    // getter、setter
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

    public int getChinese() {
        return chinese;
    }

    public void setChinese(int chinese) {
        this.chinese = chinese;
    }

    public int getMath() {
        return math;
    }

    public void setMath(int math) {
        this.math = math;
    }

    public int getEnglish() {
        return english;
    }

    public void setEnglish(int english) {
        this.english = english;
    }

    @Override
    public String toString() {
        return "Student2{" + "name='" + name + '\'' + ", age=" + age + ", chinese=" + chinese + ", math=" + math + ", english=" + english + '}';
    }


    @Override
    public int compareTo(Student2 o) {
        String nowName = this.getName();
        int nowAge = this.getAge();
        int nowChinese = this.getChinese();
        int nowMath = this.getMath();
        int nowEnglish = this.getEnglish();
        int nowSum = nowChinese + nowMath + nowEnglish;

        String oldName = o.getName();
        int oldAge = o.getAge();
        int oldChinese = o.getChinese();
        int oldMath = o.getMath();
        int oldEnglish = o.getEnglish();
        int oldSum = oldChinese + oldMath + oldEnglish;

        int result = oldSum - nowSum;
        if (result == 0) result = oldChinese - nowChinese;
        if (result == 0) result = oldMath - nowMath;
        if (result == 0) result = oldEnglish - nowEnglish;
        if (result == 0) result = oldAge - nowAge;
        if (result == 0) result = oldName.compareTo(nowName);

        return result;
    }
}
