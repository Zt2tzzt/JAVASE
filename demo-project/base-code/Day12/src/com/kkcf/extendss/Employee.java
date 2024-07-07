package com.kkcf.extendss;

public class Employee {
    private String workNo;
    private String name;
    private double salary;

    public Employee() {
    }

    public Employee(String workNo, String name, double salary) {
        this.workNo = workNo;
        this.name = name;
        this.salary = salary;
    }

    public String getWorkNo() {
        return workNo;
    }

    public void setWorkNo(String workNo) {
        this.workNo = workNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public void work() {
        System.out.println("员工再工作");
    }

    public void eat() {
        System.out.println("员工在吃米饭");
    }
}
