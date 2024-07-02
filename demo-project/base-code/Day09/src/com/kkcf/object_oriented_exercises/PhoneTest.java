package com.kkcf.object_oriented_exercises;

public class PhoneTest {
    public static void main(String[] args) {
        Phone[] phones = new Phone[3];

        phones[0] = new Phone("小米", 2999, "黑色");
        phones[1] = new Phone("华为", 5999, "白色");
        phones[2] = new Phone("苹果", 9999, "金色");

        int sum = 0;
        for (int i = 0; i < phones.length; i++)
            sum += phones[i].getPrice();

        double avg = sum * 1.0 / phones.length;
        System.out.println("平均价格：" + avg); // 6332.333333333333
    }
}
