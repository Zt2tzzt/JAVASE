package com.kkcf.object_riented;

public class PhoneTest {
    public static void main(String[] args) {
        Phone phone = new Phone();

        phone.price = 1999.98;
        phone.brand = "小米";

        System.out.println(phone.price);
        System.out.println(phone.brand);

        phone.call();
        phone.playGame();
    }
}
