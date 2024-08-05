package com.kkcf.exception;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Demo01 {
    public static void main(String[] args) throws ParseException {
        String dateStr = "2024年8月5日";

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年mm月dd日");

        Date date = sdf.parse(dateStr);

        System.out.println(date);
    }
}
