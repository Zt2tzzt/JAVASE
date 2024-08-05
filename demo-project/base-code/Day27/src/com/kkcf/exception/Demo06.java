package com.kkcf.exception;

public class Demo06 {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};

        try {
            System.out.println(arr[10]);
            System.out.println(2/0);
            String s = null;
            System.out.println(s.equals("abc"));
        } catch (ArrayIndexOutOfBoundsException | ArithmeticException | NullPointerException err) {
            assert System.out != null;
            System.out.println("出现异常：" + err);
            err.printStackTrace();
        }
    }
}
