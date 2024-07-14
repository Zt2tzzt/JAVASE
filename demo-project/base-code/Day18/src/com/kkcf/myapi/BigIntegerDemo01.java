package com.kkcf.myapi;

import java.math.BigInteger;
import java.util.Objects;
import java.util.Random;

public class BigIntegerDemo01 {

    public static void main(String[] args) {
        //BigInteger bigInteger = new BigInteger(4, new Random());
        //
        //System.out.println(bigInteger);

        //BigInteger bigInteger = new BigInteger("12345");
        //
        //System.out.println(bigInteger); // 12345

        //BigInteger bigInteger1 = new BigInteger("1.1"); //  java.lang.NumberFormatException: For input string: "1.1"
        //
        //BigInteger bigInteger2 = new BigInteger("abc"); // java.lang.NumberFormatException: For input string: "abc"

        //BigInteger bigInteger = new BigInteger("4", 10);
        //
        //System.out.println(bigInteger); // 4
        //
        //BigInteger bigInteger1 = new BigInteger("100", 2);
        //
        //System.out.println(bigInteger1); // 4

        //System.out.println(BigInteger.valueOf(100)); // 100

        BigInteger b1 = BigInteger.valueOf(16);
        BigInteger b2 = BigInteger.valueOf(16);

        System.out.println(b1 == b2); // true
        System.out.println(b1.equals(b2)); // true
        System.out.println(Objects.equals(b1, b2)); // true

        BigInteger b3 = BigInteger.valueOf(17);
        BigInteger b4 = BigInteger.valueOf(17);

        System.out.println(b3 == b4); // false
    }
}
