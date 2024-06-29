package com.kkcf.trainning;

import java.util.Scanner;

public class Test01 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("请输入机票原价：");
        double originPrice = sc.nextDouble();

        System.out.println("请输入月份：");
        int mon = sc.nextInt();

        System.out.println("请输入舱位类型：");
        int seat = sc.nextInt(); // 0 头等舱，1 经济舱

        sc.close();

        double price = caculateFlightTicket(originPrice, mon, seat);

        System.out.println(price);
    }

    public static double caculateFlightTicket(double originalPrice, int mon, int seat) {
        double price = 0;

        if (mon < 1 || mon > 12) {
            System.out.println("输入月份有误！");

            return price;
        } else if (mon >= 5 && mon <= 10) {
            double[] counts = {0.9, 0.85};

            price = caculateFlightTicketWithSeat(seat, originalPrice, counts);
        } else {
            double[] counts = {0.7, 0.65};

            price = caculateFlightTicketWithSeat(seat, originalPrice, counts);
        }

        return price;
    }

    public static double caculateFlightTicketWithSeat(int seat, double originalPrice, double[] counts) {
        double price = 0;

        switch (seat) {
            case 0 -> price = originalPrice * counts[0];
            case 1 -> price = originalPrice * counts[1];
            default -> System.out.println("输入错误");
        }

        return price;
    }
}

