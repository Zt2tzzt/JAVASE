package com.kkcf.datetimeformatter;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class StringToZonedDateTimeExample {
    public static void main(String[] args) {
        // 要解析的时间字符串，带时区信息
        String dateTimeString = "2024-11-16T15:30:45+08:00[Asia/Shanghai]";

        // 定义与字符串匹配的格式
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX'['VV']'");

        // 使用 ZonedDateTime 的 parse 方法解析字符串
        ZonedDateTime zonedDateTime = ZonedDateTime.parse(dateTimeString, formatter);

        // 输出解析后的 ZonedDateTime 对象
        System.out.println(zonedDateTime); // 2024-11-16T15:30:45+08:00[Asia/Shanghai]
    }
}
