package com.kkcf.test;

import java.util.UUID;

public class UUIDTest {
    public static void main(String[] args) {
        UUID uuid = UUID.randomUUID();
        System.out.println(uuid); // a9568ed2-81f1-4665-acef-128f7143fcc7

        String result = uuid.toString().replace("-", "");
        System.out.println(result); // a9568ed281f14665acef128f7143fcc7
    }
}
