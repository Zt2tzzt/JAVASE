package com.kkcf.extendss;

public class Test5 {
    public static void main(String[] args) {
        Manager m = new Manager("1", "张三", 16000, 8000);
        System.out.println(m.getName() + " " + m.getWorkNo() + " " + m.getSalary() + " " + m.getManagementBonus());
        m.eat();
        m.work();

        Cook c = new Cook();
        c.setWorkNo("2");
        c.setName("李四");
        c.setSalary(10000);
        c.work();
        c.eat();
        System.out.println(c.getName() + " " + c.getWorkNo() + " " + c.getSalary());

    }
}
