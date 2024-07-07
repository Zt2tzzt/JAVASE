package com.kkcf.extendss;

public class SharPai extends Dog1{
    // 父类方法，不能满足子类方法需求
    @Override
    public void eat() {
        super.eat();
        System.out.println("狗在吃骨头");
    }
}
