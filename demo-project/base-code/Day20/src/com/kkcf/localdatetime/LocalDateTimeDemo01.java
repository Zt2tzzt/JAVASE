package com.kkcf.localdatetime;

import java.time.LocalDate;

public class LocalDateTimeDemo01 {
    public static void main(String[] args) {
        LocalDate ld1 = LocalDate.now();

        System.out.println(ld1); // 2024-07-18

        //int year = ld1.getYear();
        //System.out.println(year); // 2024
        //
        //// 获取月份方式一
        //Month m = ld1.getMonth();
        //int month1 = m.getValue();
        //System.out.println(month1); // t
        //
        //// 获取月份方式二
        //int month2 = ld1.getMonthValue();
        //System.out.println(month2); // 7

        //System.out.println(ld1.isBefore(ld1)); // false
        //System.out.println(ld1.isAfter(ld1)); // false

        LocalDate ld2 = ld1.withYear(2015);

        System.out.println(ld2); // 2015-07-18

        LocalDate ld3 = ld2.minusYears(1); // 2014-07-18

        System.out.println(ld3);

    }
}
