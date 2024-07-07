package com.kkcf.extendss;

public class Test2 {
    public static void main(String[] args) {
        Zii zii = new Zii();
        zii.show();
    }
}

class Fuu {
    String name = "Fu";
    String hobby = "喝茶";
}

class Zii extends Fuu {
    String name = "Zi";
    String game = "吃鸡";

    public void show() {
        // 打印 Zi 的方式
        System.out.println(name);
        System.out.println(this.name);

        // 打印 Fu 的方式
        System.out.println(super.name);

        // 打印喝茶的方式
        System.out.println(hobby);
        System.out.println(this.hobby);
        System.out.println(super.hobby);

        // 打印吃鸡
        System.out.println(game);
        System.out.println(this.game);;
    }
}
