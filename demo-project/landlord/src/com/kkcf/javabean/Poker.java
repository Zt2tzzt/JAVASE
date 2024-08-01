package com.kkcf.javabean;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Poker extends JLabel implements MouseListener {
    // 属性，牌的名字，格式：数字-数字
    private final String name;

    // 正面还是反面，正面 true；反面 false
    private boolean side;

    // 是否能被点击
    private boolean clickble = false;

    // 是否已被点击
    private boolean clicked = false;

    public Poker(String name, boolean side) {
        this.name = name;
        this.side = side;

        if (side) {
            // 显示正面
            this.turnFront();
        } else {
            // 显示反面
            this.turnBack();
        }

        // 设置牌的宽高
        this.setSize(71, 96);
        // 把牌显示出来
        this.setVisible(true);
        // 给每一张牌添加监听
        this.addMouseListener(this);
    }

    /**
     * 此方法用于，将牌反转为正面
     */
    public void turnFront() {
        // 把牌显示出来
        super.setIcon(new ImageIcon("image/poker/" + this.name + ".png"));
        // 修改牌的状态
        this.side = true;
    }

    /**
     * 此方法用于，将牌反转为反面
     */
    public void turnBack() {
        // 把牌显示出来
        super.setIcon(new ImageIcon("image/poker/rear.png"));
        // 修改牌的状态
        this.side = false;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (!this.clickble) return;

        // 记录 y 轴位移位置
        int step = this.clicked ? 20 : -20;
        this.clicked = !this.clicked;

        // 位移
        Point from = super.getLocation();
        Point to = new Point(from.x, from.y + step);
        super.setLocation(to);
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public String getName() {
        return name;
    }

    public boolean isSide() {
        return side;
    }

    public void setSide(boolean side) {
        this.side = side;
    }

    public boolean isClickble() {
        return clickble;
    }

    public void setClickble(boolean clickble) {
        this.clickble = clickble;
    }

    public boolean isClicked() {
        return clicked;
    }

    public void setClicked(boolean clicked) {
        this.clicked = clicked;
    }

    @Override
    public String toString() {
        return "Poker{" +
                "name='" + name + '\'' +
                ", side=" + side +
                ", clickble=" + clickble +
                ", clicked=" + clicked +
                "} " + super.toString();
    }
}
