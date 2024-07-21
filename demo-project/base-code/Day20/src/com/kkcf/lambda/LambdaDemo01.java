package com.kkcf.lambda;

public class LambdaDemo01 {
    public static void main(String[] args) {

        method(new swim() {
            @Override
            public void swimming() {
                System.out.println("正在游泳");
            }
        });

        method(() -> System.out.println("正在游泳"));

    }

    public static void method(swim s) {
        s.swimming();
    }
}

@FunctionalInterface
interface swim {
    void swimming();
}
