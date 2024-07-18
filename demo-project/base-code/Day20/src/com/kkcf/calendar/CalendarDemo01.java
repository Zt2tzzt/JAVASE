package com.kkcf.calendar;

import java.util.Calendar;
import java.util.Date;

public class CalendarDemo01 {
    public static void main(String[] args) {
        Calendar c = Calendar.getInstance();

        c.setTime(new Date(0L));

        c.set(Calendar.YEAR, 2000);
        c.set(Calendar.MONTH, 12); // 12 表示 13 月，然后实际没有 13 月，日期会自动计算

        c.add(Calendar.MONTH, 1); // 往后加一个月
        c.add(Calendar.MONTH, -1); // 往前减一个月

        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH) + 1;
        int day = c.get(Calendar.DAY_OF_MONTH);
        int weekDay = c.get(Calendar.DAY_OF_WEEK);

        System.out.println(year + "年" + month + "月" + day + "日" + "星期" + getWeekDay(weekDay)); // 2001年1月1日星期星期一
    }

    public static String getWeekDay(int weekDay) {
        String[] arr ={"", "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};

        return arr[weekDay];
    }
}
