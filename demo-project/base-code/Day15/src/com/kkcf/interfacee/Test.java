package com.kkcf.interfacee;

public class Test {
    public static void main(String[] args) {
        Frog f = new Frog("小青", 1);
        System.out.println(f);
        f.eat();
        f.swim();

        Rabbit r = new Rabbit("小白", 2);
        System.out.println(r);
        r.eat();
    }
}
