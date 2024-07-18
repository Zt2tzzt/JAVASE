package com.kkcf.instant;

import java.time.Instant;

public class InstantDemo02 {
    public static void main(String[] args) {
        //ZonedDateTime zonedDateTime = Instant.now().atZone(ZoneId.of("Asia/Shanghai"));
        //
        //System.out.println(zonedDateTime); // 2024-07-18T16:51:07.222483900+08:00[Asia/Shanghai]

        Instant now = Instant.now();

        Instant i1 = Instant.ofEpochMilli(0L);

        System.out.println(now.isAfter(i1)); // true
        System.out.println(i1.isBefore(now)); // true
    }
}
