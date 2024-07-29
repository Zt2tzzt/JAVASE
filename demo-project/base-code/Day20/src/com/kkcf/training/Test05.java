package com.kkcf.training;

import java.time.LocalDate;
import java.util.Calendar;

public class Test05 {
    public static void main(String[] args) {
        int year = 2001;

        jdk7Method(year);

        jdk8Method1(year);

        jdk8Method2(year);
    }

    /**
     * 此方法用于，使用 JDK8 的 API，计算今年是否是闰年。
     * @param year 年份
     */
    private static void jdk8Method2(int year) {
        LocalDate ld1 = LocalDate.of(year, 2, 1);

        boolean flag = ld1.isLeapYear();

        System.out.println(year + (flag ? "是" : "不是") + "闰年");

    }

    /**
     * * 此方法用于，使用 JDK8 的 API，计算今年是否是闰年。
     * @param year 年份
     */
    private static void jdk8Method1(int year) {
        LocalDate date = LocalDate.of(year, 3, 1); // 这里的 3，就表示 3 月。
        date = date.minusDays(1);

        int dayOfMonth = date.getDayOfMonth();

        System.out.println(dayOfMonth);

        System.out.println(year + (dayOfMonth == 29 ? "是" : "不是") + "闰年");
    }

    /**
     * 此方法用于，使用 JDK7 的 API，计算今年是否是闰年。
     * @param year 年份
     */
    private static void jdk7Method(int year) {
        Calendar c = Calendar.getInstance();
        c.set(year, Calendar.MARCH, 1); // 这里 2 表示 3 月

        c.add(Calendar.DAY_OF_MONTH, -1);

        int day = c.get(Calendar.DAY_OF_MONTH);

        System.out.println(day);

        System.out.println(year + (day == 29 ? "是" : "不是") + "闰年");
    }
}
