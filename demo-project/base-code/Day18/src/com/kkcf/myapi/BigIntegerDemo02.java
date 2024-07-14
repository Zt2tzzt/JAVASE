package com.kkcf.myapi;

import java.math.BigInteger;

public class BigIntegerDemo02 {
    public static void main(String[] args) {
        BigInteger bd1 = BigInteger.valueOf(10);
        BigInteger bd2 = BigInteger.valueOf(3);

        //BigInteger bd3 = bd1.add(bd2);
        //
        //System.out.println(bd3); // 15

        //BigInteger[] bds = bd1.divideAndRemainder(bd2);
        //
        //System.out.println(bds[0]); // 3，表示商 3
        //System.out.println(bds[1]); // 1，表示余 1

        //System.out.println(bd1.equals(bd2)); // false
        //
        //BigInteger bd3 = BigInteger.valueOf(15);
        //BigInteger bd4 = BigInteger.valueOf(15);
        //
        //System.out.println(bd3.equals(bd4)); // true

        //BigInteger bd3 = bd1.pow(2);
        //
        //System.out.println(bd3); // 100

        //BigInteger max = bd1.max(bd2);
        //
        //System.out.println(max); // 10
        //System.out.println(max == bd1); // true

        int i = bd1.intValue();

        long l = bd1.longValue();

        double d = bd1.doubleValue();

        System.out.println(i); // 10
        System.out.println(l); // 10
        System.out.println(d); // 10.0
    }
}
