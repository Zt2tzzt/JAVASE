package com.kkcf.runable;

public class Demo01 {
    public static void main(String[] args) {
        // 创建 MyRun 类的示例对象，表示多线程要实现的任务
        MyRun mr = new MyRun();

        // 创建线程对象
        Thread t1 = new Thread(mr);
        Thread t2 = new Thread(mr);

        // 给线程设置名字
        t1.setName("线程1");
        t2.setName("线程2");

        // 开启线程
        t1.start();
        t2.start();
    }
}
