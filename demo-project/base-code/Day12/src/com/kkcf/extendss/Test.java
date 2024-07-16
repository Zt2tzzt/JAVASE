package com.kkcf.extendss;

public class Test {
    public static void main(String[] args) {
        // 创建布偶猫对象
        CatPlus cp = new CatPlus();
        cp.eat(); // 动物吃食物
        cp.drink(); //动物喝水
        cp.eat(); // 动物吃食物
        cp.catchmice(); // 猫在抓老鼠

        // 创建哈士奇对象
        Husky husky = new Husky();
        husky.eat(); // 动物吃食物
        husky.drink(); // 动物喝水
        husky.breakHouse(); // 哈士奇在拆家
        husky.houseKeeping(); // 狗在看家
    }
}
