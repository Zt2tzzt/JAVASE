package com.kkcf.datetimeformatter;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeFormatterDemo01 {
    public static void main(String[] args) {
        // 获取时间对象
        ZonedDateTime zdt1 = Instant.now().atZone(ZoneId.of("Asia/Shanghai"));

        // 解析 / 格式化器
        DateTimeFormatter dtf1 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss EE a");

        String format = dtf1.format(zdt1);

        System.out.println(format);
    }
}
