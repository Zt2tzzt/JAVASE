package com.kkcf.original_inverse_complement_code;

public class Test02 {
    public static void main(String[] args) {
        int a = 200; // 0000 0000 0000 0000 0000 0000 1100 1000

        byte b = (byte) a; // 1100 1000

        System.out.println(b); // -56
    }
}
