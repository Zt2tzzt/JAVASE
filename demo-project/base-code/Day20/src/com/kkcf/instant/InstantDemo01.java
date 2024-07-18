package com.kkcf.instant;

import java.time.Instant;

public class InstantDemo01 {
    public static void main(String[] args) {
        //Instant now = Instant.now();
        //
        //System.out.println(now); // 2024-07-18T08:29:14.733176600Z

        Instant i1 = Instant.ofEpochMilli(0L);

        System.out.println(i1); // 1970-01-01T00:00:00Z

        Instant i2 = Instant.ofEpochSecond(1L);

        System.out.println(i2); // 1970-01-01T00:00:01Z

        // 第一个参数：秒；第二个参数：纳秒
        Instant i3 = Instant.ofEpochSecond(1L, 1000000000L);

        System.out.println(i3); // 1970-01-01T00:00:02Z
    }
}
