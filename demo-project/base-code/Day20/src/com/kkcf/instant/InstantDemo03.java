package com.kkcf.instant;

import java.time.Instant;

public class InstantDemo03 {
    public static void main(String[] args) {
        Instant i1 = Instant.ofEpochMilli(3000L);

        System.out.println(i1); // 1970-01-01T00:00:03Z

        Instant i2 = i1.minusMillis(1000L);

        System.out.println(i2); // 1970-01-01T00:00:02Z
    }
}
