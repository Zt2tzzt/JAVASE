package com.kkcf.localdatetime;

import java.time.LocalDate;
import java.time.MonthDay;

public class LocalDateTimeDemo02 {
    public static void main(String[] args) {
        LocalDate birthday = LocalDate.of(1997, 10, 16);
        LocalDate nowDate = LocalDate.now();

        MonthDay birthMd = MonthDay.of(birthday.getMonthValue(), birthday.getDayOfMonth());
        MonthDay nowMd = MonthDay.from(nowDate);

        System.out.println(birthMd.equals(nowMd) ? "今天是你的生日" : "今天不是你的生日");;
    }
}
