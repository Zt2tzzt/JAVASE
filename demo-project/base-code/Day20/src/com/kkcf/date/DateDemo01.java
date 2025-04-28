package com.kkcf.date;

import java.util.Date;

public class DateDemo01 {
    public static void main(String[] args) {
        Date d1 = new Date();
        System.out.println(d1); // Wed Jul 17 15:46:04 CST 2024

        Date d2 = new Date(0L);
        System.out.println(d2); // Thu Jan 01 08:00:00 CST 1970

        d2.setTime(1000L);
        System.out.println(d2); // Thu Jan 01 08:00:01 CST 1970

        long d2Time = d2.getTime();
        System.out.println(d2Time); // 1000
    }
}
