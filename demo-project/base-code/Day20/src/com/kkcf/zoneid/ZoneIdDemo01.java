package com.kkcf.zoneid;

import java.time.ZoneId;
import java.util.Set;

public class ZoneIdDemo01 {
    public static void main(String[] args) {
        Set<String> zoneIds = ZoneId.getAvailableZoneIds();

        System.out.println(zoneIds.size()); // 603
        System.out.println(zoneIds); // 其中就有 Asia/Shanghai

        //ZoneId zoneId1 = ZoneId.systemDefault();
        //System.out.println(zoneId1); // Asia/Shanghai

        ZoneId zoneId2 = ZoneId.of("Asia/Pontianak");

        System.out.println(zoneId2); // Asia/Pontianak
    }
}
