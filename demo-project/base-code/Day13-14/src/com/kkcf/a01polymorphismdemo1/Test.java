package com.kkcf.a01polymorphismdemo1;

public class Test {
    public static void main(String[] args) {
        Teacher t = new Teacher("易爱平", 30);;
        Student s = new Student("张三", 20);
        Administator a = new Administator("李四", 40);

        register(t); // 老师的信息为：易爱平，30
        register(s); // 学生的信息为：张三，20
        register(a); // 管理员的信息为：李四 40
    }

    public static void register(Person p) {
        p.show();
    }
}
