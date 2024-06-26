package com.kkcf.original_inverse_complement_code;

public class Test03 {
    public static void main(String[] args) {
        int a  = 200; // 0000 0000 0000 0000 0000 0000 1100 1000

        int b = 10; // 0000 0000 0000 0000 0000 0000 0000 1010

        System.out.println(a | b); // 0000 0000 0000 0000 0000 0000 1100 1010
        // 即 202
    }
}
