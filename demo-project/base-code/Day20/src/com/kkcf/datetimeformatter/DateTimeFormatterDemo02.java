package com.kkcf.datetimeformatter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeFormatterDemo02 {
    public static void main(String[] args) {
        // 创建一个 LocalDateTime 对象
        LocalDateTime cdt1 = LocalDateTime.now();

        // 解析 / 格式化器
        DateTimeFormatter dtf1 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss EE a");

        // 格式化 LocalDateTime 为字符串
        String res2 = cdt1.format(dtf1);
        System.out.print(res2); // 2024-11-16 16:32:06 周六 下午
    }
}
