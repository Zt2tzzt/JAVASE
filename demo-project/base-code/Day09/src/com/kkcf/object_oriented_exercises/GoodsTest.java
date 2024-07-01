package com.kkcf.object_oriented_exercises;

public class GoodsTest {
    public static void main(String[] args) {
        Goods[] goods = new Goods[3];

        Goods iphone = new Goods("001", "IPhone", 9999.99, 100);
        Goods macBook = new Goods("002", "MacBook", 19999.99, 150);
        Goods ipad = new Goods("003", "iPad", 5999.99, 200);

        goods[0] = iphone;
        goods[1] = macBook;
        goods[2] = ipad;

        for (int i = 0; i < goods.length; i++) {
            Goods good = goods[i];

            System.out.println(good.toString());
        }
    }
}
