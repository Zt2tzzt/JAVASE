package com.kkcf.duration;

import java.time.Duration;
import java.time.LocalDateTime;

public class DurationDemo01 {
    public static void main(String[] args) {
        // 本地日期时间对象
        LocalDateTime today = LocalDateTime.now();

        LocalDateTime birthday = LocalDateTime.of(2000, 1, 1, 0, 00, 00);

        Duration duration = Duration.between(birthday, today);

        System.out.println("相差的时间间隔：" + duration); // 相差的时间间隔：PT215191H36M51.4040646S

        System.out.println(duration.toDays()); // 8966
        System.out.println(duration.toHours()); // 215191
        System.out.println(duration.toMinutes()); // 12911497
        System.out.println(duration.toMillis()); // 774689864805
        System.out.println(duration.toNanos()); // 774689864805800400
    }
}
