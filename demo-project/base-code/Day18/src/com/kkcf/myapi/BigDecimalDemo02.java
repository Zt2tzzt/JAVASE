package com.kkcf.myapi;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class BigDecimalDemo02 {
    public static void main(String[] args) {
        BigDecimal bd1 = BigDecimal.valueOf(10.0);
        BigDecimal bd2 = BigDecimal.valueOf(2.0);
        BigDecimal bd3 = BigDecimal.valueOf(3.0);
        BigDecimal bd4 = BigDecimal.valueOf(4.0);


        //BigDecimal bd3 = bd1.add(bd2);
        //System.out.println(bd3); // 12.0
        //
        //BigDecimal bd4 = bd1.subtract(bd2);
        //System.out.println(bd4); // 8.0
        //
        //BigDecimal bd5 = bd1.multiply(bd3);
        //System.out.println(bd5); // 120.00

        BigDecimal bd5 = bd1.divide(bd2);
        System.out.println(bd3); // 5

        BigDecimal bd6 = bd1.divide(bd4);
        System.out.println(bd6); // 2.5

        //BigDecimal bd7 = bd1.divide(bd3); //  java.lang.ArithmeticException: Non-terminating decimal expansion; no exact representable decimal result.
        BigDecimal bd7 = bd1.divide(bd3, 2, RoundingMode.HALF_UP); // RoundingMode.HALF_UP 表示四舍五入模式
        System.out.println(bd7);

    }
}
