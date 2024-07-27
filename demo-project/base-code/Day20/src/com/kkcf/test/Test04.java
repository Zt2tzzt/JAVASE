package com.kkcf.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class Test04 {
    public static void main(String[] args) throws ParseException {
        long days1 = jdk7Calc();
        System.out.println("间隔" + days1 + "天");

        long days2 = jdk8Calc();
        System.out.println("间隔" + days2 + "天");
    }

    /**
     * 此方法用于：使用 JDK8 以后的方式，计算活了所少天
     * @return 活了多少天
     */
    private static long jdk8Calc() {
        LocalDate birthLd = LocalDate.of(1997, 10, 16);
        LocalDate nowLd = LocalDate.now();

        return ChronoUnit.DAYS.between(birthLd, nowLd);
    }

    /**
     * 此方法用于：使用 JDK7 以前的方式，计算活了所少天
     * @return 活了多少天
     * @throws ParseException 时间字符串转化错误
     */
    private static long jdk7Calc() throws ParseException {
        // 生日时间 Date 对象
        String birthStr = "1997-10-16";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date birthDate = sdf.parse(birthStr);
        long birthTime = birthDate.getTime();

        // 当前时间 Date 对象
        long nowTime = System.currentTimeMillis();

        long intervalTime = nowTime - birthTime;

        return intervalTime / (1000 * 60 * 60 * 24);
    }


}
