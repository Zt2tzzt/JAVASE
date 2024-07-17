package com.kkcf.date;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SimpleDateFormatDemo01 {
    public static void main(String[] args) {
        SimpleDateFormat sdf1 = new SimpleDateFormat();

        Date d1 = new Date(0L);

        //String format1 = sdf1.format(d1);

        //System.out.println(format1); // 1970/1/1 上午8:00

        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        String format2 = sdf2.format(d1); // 1970-01-01 08:00:00

        System.out.println(format2);

        SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒 EE");

        String format3 = sdf3.format(d1);

        System.out.println(format3); // 1970年01月01日 08时00分00秒 周周四

    }
}
