package com.kkcf.extendss;

public class Cook extends Employee{
    public Cook() {
    }

    public Cook(String workNo, String name, double salary) {
        super(workNo, name, salary);
    }

    @Override
    public void work() {
        System.out.println("厨师在炒菜");
    }
}
