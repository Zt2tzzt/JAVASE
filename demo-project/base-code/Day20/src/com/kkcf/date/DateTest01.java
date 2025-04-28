package com.kkcf.date;

import java.util.Date;
import java.util.Random;

public class DateTest01 {
    public static void main(String[] args) {
        //extracted();

        // 随机获取两个时间对象
        Random r = new Random();
        Date d1 = new Date(Math.abs(r.nextInt()));
        Date d2 = new Date(Math.abs(r.nextInt()));
        System.out.println(d1 + " " + d2);

        long time1 = d1.getTime();
        long time2 = d2.getTime();
        System.out.println(time1 + " " + time2);

        System.out.println(time1 > time2 ? "第一个时间，大于第二个时间"
                : time1 < time2 ? "第二个时间，大于第一个时间"
                : "第一个时间，等于第二个时间");
    }

    private static void extracted() {
        Date d1 = new Date(0L);
        long time = d1.getTime();
        time = time + 1000L * 60 * 60 * 24 * 365;

        d1.setTime(time);
        System.out.println(d1); // Fri Jan 01 08:00:00 CST 1971
    }
}
