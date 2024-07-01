package com.kkcf.object_oriented_exercises;

import java.util.Scanner;

public class CarTest {
    public static void main(String[] args) {
        Car[] cars = new Car[3];
        Scanner sc = new Scanner(System.in);

        for (int i = 0; i < cars.length; i++) {
            System.out.print("请输入第 " + (i + 1) + " 辆汽车的品牌：");
            String brand = sc.next();

            System.out.print("请输入第 " + (i + 1) + " 辆汽车的价格：");
            int price = sc.nextInt();

            System.out.print("请输入第 " + (i + 1) + " 辆汽车的颜色：");
            String color = sc.next();

            Car car = new Car(brand, price, color);
            cars[i] = car;
        }

        sc.close();

        for (int i = 0; i < cars.length; i++) {
            System.out.println(cars[i].toString());
        }
    }
}
