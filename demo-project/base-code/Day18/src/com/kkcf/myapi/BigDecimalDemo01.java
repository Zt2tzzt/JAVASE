package com.kkcf.myapi;

import java.math.BigDecimal;

public class BigDecimalDemo01 {
    public static void main(String[] args) {
        //BigDecimal bd1 = new BigDecimal("0.01");
        //BigDecimal bd2 = new BigDecimal("0.09");
        //
        //System.out.println(bd1); // 0.01
        //System.out.println(bd2); // 0.09
        //
        //BigDecimal bd3 = bd1.add(bd2);
        //
        //System.out.println(bd3); // 0.10

        BigDecimal bd1 = BigDecimal.valueOf(10);
        BigDecimal bd2 = BigDecimal.valueOf(10);

        System.out.println(bd1 == bd2); // true


        BigDecimal bd3 = BigDecimal.valueOf(10.0);
        BigDecimal bd4 = BigDecimal.valueOf(10.0);

        System.out.println(bd3 == bd4); // false
    }
}
