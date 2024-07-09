package com.kkcf.innerclass;

public class Test1 {
    public static void main(String[] args) {
        Outer1 o = new Outer1();

        Object i = o.getInstance();
        System.out.println(i); // com.kkcf.innerclass.Outer1$Inner1@41629346
    }
}
