package com.kkcf.test;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class MyFrame extends JFrame implements ActionListener {
    // 创建一个按钮对象
    JButton jbtn1 = new JButton("点我啊");

    // 创建一个按钮对象
    JButton jbtn2 = new JButton("再点我啊");


    public MyFrame(){
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

        // 设置位置和宽高
        jbtn1.setBounds(0, 0, 100, 50);
        // MyFrame 类实现了 ActionListener 接口，所以可以直接传入 MyFrame 类的对象。
        jbtn1.addActionListener(this);

        // 设置位置和宽高
        jbtn2.setBounds(100, 0, 100, 50);
        // MyFrame 类实现了 ActionListener 接口，所以可以直接传入 MyFrame 类的对象。
        jbtn2.addActionListener(this);

        this.getContentPane().add(jbtn1);
        this.getContentPane().add(jbtn2);

        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Random r = new Random();

        // 对当前按钮，进行判断
        Object source = e.getSource();
        if (source == jbtn1)
            jbtn1.setSize(200, 200);
        else if (source == jbtn2)
            jbtn2.setLocation(r.nextInt(500), r.nextInt(500));
    }
}
