package com.kkcf.method;

public class MethodOverloadDemo01 {
    public static void main(String[] args) {
        System.out.println(compare(10, 20));
        System.out.println(compare((byte)10, (byte)20));
        System.out.println(compare((short)10, (short)20));
        System.out.println(compare((long)10, (long)20));
    }

    public static boolean compare(byte b1, byte b2) {
        return b1 == b2;
    }

    public static boolean compare(short s1, short s2) {
        return s1 == s2;
    }

    public static boolean compare(int i1, int i2) {
        return i1 == i2;
    }

    public static boolean compare(long l1, long l2) {
        return l1 == l2;
    }
}
