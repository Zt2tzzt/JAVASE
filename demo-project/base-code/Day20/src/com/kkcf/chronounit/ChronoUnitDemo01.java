package com.kkcf.chronounit;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class ChronoUnitDemo01 {
    public static void main(String[] args) {
        LocalDateTime today = LocalDateTime.now();

        LocalDateTime birthday = LocalDateTime.of(2000, 1, 1, 0, 00, 00);

        System.out.println("间隔的年数：" + ChronoUnit.YEARS.between(birthday, today)); // 间隔的年数：24
        System.out.println("间隔的月数：" + ChronoUnit.MONTHS.between(birthday, today)); // 间隔的月数：294
        System.out.println("间隔的周数：" + ChronoUnit.WEEKS.between(birthday, today)); // 间隔的周数：1280
        System.out.println("间隔的天数：" + ChronoUnit.DAYS.between(birthday, today)); // 间隔的天数：8966
        System.out.println("间隔的时数：" + ChronoUnit.HOURS.between(birthday, today)); // 间隔的时数：215191
        System.out.println("间隔的分数：" + ChronoUnit.MINUTES.between(birthday, today)); // 间隔的分数：12911510
        System.out.println("间隔的秒数：" + ChronoUnit.SECONDS.between(birthday, today)); // 间隔的秒数：774690643
        System.out.println("间隔的毫秒数：" + ChronoUnit.MILLIS.between(birthday, today)); // 间隔的毫秒数：774690643791
        System.out.println("间隔的微妙数：" + ChronoUnit.MICROS.between(birthday, today)); // 间隔的微妙数：774690643791441
        System.out.println("间隔的纳秒数：" + ChronoUnit.NANOS.between(birthday, today)); // 间隔的纳秒数：774690643791441200
        System.out.println("间隔的半天数：" + ChronoUnit.HALF_DAYS.between(birthday, today)); // 间隔的半天数：17932
        System.out.println("间隔的十年数：" + ChronoUnit.DECADES.between(birthday, today)); // 间隔的十年数：2
        System.out.println("间隔的世纪（百年）数：" + ChronoUnit.CENTURIES.between(birthday, today)); // 间隔的世纪（百年）数：0
        System.out.println("间隔的千年数：" + ChronoUnit.MILLENNIA.between(birthday, today)); // 间隔的千年数：0
        System.out.println("间隔的纪元数：" + ChronoUnit.ERAS.between(birthday, today)); // 间隔的纪元数：0
    }
}
