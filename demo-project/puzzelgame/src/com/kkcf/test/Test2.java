package com.kkcf.test;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Test2 {
    public static void main(String[] args) {
        JFrame jFrame = new JFrame();

        jFrame.setSize(603, 680);
        // 设置窗口标题
        jFrame.setTitle("拼图小游戏 V1.0");
        // 设置窗口始终置顶
        jFrame.setAlwaysOnTop(true);
        // 设置窗口居中展示
        jFrame.setLocationRelativeTo(null);
        // 设置游戏的关闭模式
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // 表示关闭窗口后，虚拟机停止运行。
        // 取消默认的居中设置，这样才能按照 x、y 轴的形式添加组件
        jFrame.setLayout(null);

        // 创建一个按钮对象
        JButton jbtn = new JButton("点我啊");
        // 设置位置和宽高
        jbtn.setBounds(0, 0, 100, 50);

        // 为 jbtn 添加事件监听。
        // addActionListener 方法，表示动作监听，只能监听鼠标左键点击，和键盘空格；传入的参数，表示事件触发后要执行的代码
        // 创建一个自己的实现类 MyActionListenner，实现 ActionListener 接口，并传入 addActionListener 方法中。
        // jbtn.addActionListener(new MyActionListenner());
        // 因为 MyActionListenner 类只会在这里使用到一次，所以使用匿名内部类，简化代码。
        jbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("不要点我啊");
            }
        });

        // 把 jbtn 添加到页面中。
        jFrame.getContentPane().add(jbtn);

        jFrame.setVisible(true);
    }
}
