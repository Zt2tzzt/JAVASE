package com.kkcf.extendss;

public class ChineseDog extends Dog1{
    @Override
    public void eat() {
        // 因为用不到父类方法中的代码，所以不需要使用 super 进行嗲用
        System.out.println("狗在吃剩菜");
    }
}
