package com.kkcf.period;

import java.time.LocalDate;
import java.time.Period;

public class PeriodDemo01 {
    public static void main(String[] args) {
        LocalDate today = LocalDate.now();
        LocalDate birthday = LocalDate.of(2000, 1, 1);

        Period period = Period.between(birthday, today);

        System.out.println(period); // P24Y6M18D
        System.out.println("相隔" + period.getYears() + "年" + period.getMonths() + "月" + period.getDays() + "天"); // 相隔24年6月18天

        // 获取总月份
        System.out.println(period.toTotalMonths()); // 294
    }
}
