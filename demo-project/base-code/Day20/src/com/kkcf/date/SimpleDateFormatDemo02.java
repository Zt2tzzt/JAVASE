package com.kkcf.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SimpleDateFormatDemo02 {
    public static void main(String[] args) throws ParseException {
        String dateStr = "2023-11-11 11:11:11";

        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d1 = sdf1.parse(dateStr);

        System.out.println(d1); // Sat Nov 11 11:11:11 CST 2023
    }
}
