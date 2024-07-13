package com.kkcf.myapi;

public class MathDemo01 {
    public static void main(String[] args) {
        //System.out.println(Math.abs(-2147483648)); // -2147483648
        //System.out.println(Math.abs(-2147483647)); // 2147483647

        //System.out.println(Math.absExact(-2147483648));
        /*
        Overflow to represent absolute value of Integer.MIN_VALUE
            at java.base/java.lang.Math.absExact(Math.java:1448)
            at com.kkcf.myapi.MathDemo01.main(MathDemo01.java:8)
         */

        //System.out.println(Math.ceil(12.34));/* // 13.0
        //System.out.println(Math.ceil(-12.34)); // -12.0*/

        //System.out.println(Math.floor(12.34)); // 12.0
        //System.out.println(Math.floor(-12.34)); // -13.0

        //System.out.println(Math.pow(4, 2)); // 16.0
        //System.out.println(Math.pow(4, 0.5)); // 2.0
        //System.out.println(Math.pow(2, -2)); // 0.25

        //System.out.println(Math.sqrt(4)); // 2.0
        //System.out.println(Math.cbrt(8)); // 2.0

        System.out.println(Math.floor(Math.random() * 100 + 1));
    }
}
