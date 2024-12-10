package com.kkcf.datetimeformatter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class StringToLocalDateTimeExample {
    public static void main(String[] args) {
        // 定义时间字符串
        String dateTimeString = "2024-11-16 15:30:45";

        // 定义解析的格式
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        // 将字符串解析为 LocalDateTime
        LocalDateTime parsedDateTime = LocalDateTime.parse(dateTimeString, formatter);

        // 输出结果
        System.out.println(parsedDateTime); // 2024-11-16T15:30:45
    }
}

