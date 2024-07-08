package com.kkcf.a01finaldemo01;

public class Test {
    public static void main(String[] args) {
        final int[] ARR = {1, 2, 3, 4};

        // 报错 👇
        //ARR = {1, 2, 3, 4};
    }
}

class Fu {
    public void show() {
        System.out.println("父类的show方法");
    }
}

// 报错 👇
class zi extends Fu {
    @Override
    public void show() {
        System.out.println("子类的show方法");
    }
}
