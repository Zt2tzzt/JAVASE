package com.kkcf.extendss;

public class Manager extends Employee {
    private double managementBonus;

    public Manager() {
    }

    public Manager(String workNo, String name, double salary, double managementBonus) {
        super(workNo, name, salary);
        this.managementBonus = managementBonus;
    }

    public double getManagementBonus() {
        return managementBonus;
    }

    public void setManagementBonus(double managementBonus) {
        this.managementBonus = managementBonus;
    }

    @Override
    public void work() {
        System.out.println("管理其他人");
    }
}
