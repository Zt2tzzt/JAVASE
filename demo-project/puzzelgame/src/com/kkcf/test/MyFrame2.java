package com.kkcf.test;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MyFrame2 extends JFrame implements MouseListener {
    // 创建一个按钮对象
    JButton jbtn1 = new JButton("点我啊");

    public MyFrame2() {
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

        // 设置按钮位置和宽高
        jbtn1.setBounds(0, 0, 100, 50);
        // 给按钮绑定鼠标事件
        jbtn1.addMouseListener(this);

        this.getContentPane().add(jbtn1);

        this.setVisible(true);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("单击");
    }

    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println("按下");
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        System.out.println("释放");
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        System.out.println("划入");
    }

    @Override
    public void mouseExited(MouseEvent e) {
        System.out.println("划出");
    }
}
