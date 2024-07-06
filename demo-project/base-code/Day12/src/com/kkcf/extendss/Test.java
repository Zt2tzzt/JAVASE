package com.kkcf.extendss;

public class Test {
    public static void main(String[] args) {
        // 创建布偶猫对象
        CatPlus cp = new CatPlus();
        cp.eat();
        cp.drink();
        cp.eat();
        cp.catchmice();

        // 创建哈士奇对象
        Husky husky = new Husky();
        husky.eat();
        husky.drink();
        husky.breakHouse();
        husky.houseKeeping();
    }
}
