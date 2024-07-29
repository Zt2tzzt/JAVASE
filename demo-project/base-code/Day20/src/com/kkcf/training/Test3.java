package com.kkcf.training;

public class Test3 {
    public static void main(String[] args) {
        int i = 123;

        StringBuilder sb = new StringBuilder();
        while (i > 0) {
            int i1 = i % 2;
            i /= 2;
            //sb.append(i1);
            sb.insert(0, i1);
        }

        //String result = sb.reverse().toString();
        String result = sb.toString();
        System.out.println(result);
    }
}
