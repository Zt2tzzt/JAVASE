package com.kkcf.integer;

public class IntegerDemo03 {
    public static void main(String[] args) {
        Integer i1 = Integer.valueOf(1);
        Integer i2 = Integer.valueOf(2);

        int result = i1.intValue() + i2.intValue();
        Integer i3 = new Integer(result);

        System.out.println(i3); // 3
    }
}
