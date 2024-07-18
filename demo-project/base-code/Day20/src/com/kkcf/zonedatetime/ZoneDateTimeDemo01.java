package com.kkcf.zonedatetime;

import java.time.ZoneId;
import java.time.ZonedDateTime;

public class ZoneDateTimeDemo01 {
    public static void main(String[] args) {
        //ZonedDateTime now = ZonedDateTime.now();
        //
        //System.out.println(now); // 2024-07-18T17:40:33.619900600+08:00[Asia/Shanghai]

        // 方式一
        ZoneId zoneId1 = ZoneId.of("Asia/Shanghai");
        ZonedDateTime zdt1 = ZonedDateTime.of(2024, 7, 18, 17, 40, 33, 619900600, zoneId1);

        //System.out.println(zdt1); // 2024-07-18T17:40:33.619900600+08:00[Asia/Shanghai]

        // 方式二
        //Instant i1 = Instant.ofEpochMilli(0L);
        //ZoneId zoneId2 = ZoneId.of("Asia/Shanghai");
        //ZonedDateTime zdt2 = ZonedDateTime.ofInstant(i1, zoneId2);
        //
        //System.out.println(zdt2); // 1970-01-01T08:00+08:00[Asia/Shanghai]

        ZonedDateTime zdt3 = zdt1.minusYears(1L);

        System.out.println(zdt3); // 2023-07-18T17:40:33.619900600+08:00[Asia/Shanghai]
    }
}
