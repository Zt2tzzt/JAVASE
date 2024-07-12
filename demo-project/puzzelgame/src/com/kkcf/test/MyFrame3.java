package com.kkcf.test;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MyFrame3 extends JFrame implements KeyListener {
    public MyFrame3() {
        this.setSize(603, 680);
        // 设置窗口标题
        this.setTitle("拼图小游戏 V1.0");
        // 设置窗口始终置顶
        this.setAlwaysOnTop(true);
        // 设置窗口居中展示
        this.setLocationRelativeTo(null);
        // 设置游戏的关闭模式
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // 表示关闭窗口后，虚拟机停止运行。
        // 取消默认的居中设置，这样才能按照 x、y 轴的形式添加组件
        this.setLayout(null);

        // 一般是给整个窗体，添加及那盘监听
        // 调用者 this，表示本类对象，当前的界面对象，表示要给整个界面添加监听
        // addKeyListener：表示要给本界面添加键盘监听
        // 参数 this，当事件被触发之后，会执行本类中对应的带啊吗
        this.addKeyListener(this);

        this.setVisible(true);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        //System.out.println("键入");
    }

    // 细节 1：如果按下一个按键，没有松开，那么会去重复调用 keyPressed 方法
    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("按下");
    }

    // 细节 2：键盘中的按键，如何区分，使用 e.getKeyCode() 获取键盘按键的代号（与 ASCLL 码表无关）
    @Override
    public void keyReleased(KeyEvent e) {
        System.out.println("松开");
        int keyCode = e.getKeyCode();
        System.out.println("keyCode: " + keyCode);
    }
}
