package com.kkcf.game;

import com.kkcf.javabean.Poker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

public class GameJFrame extends JFrame implements ActionListener {
    // 界面中的隐藏容器
    public static Container container = null;

    // 抢地主、不抢两个按钮
    JButton[] landlord = new JButton[2];

    // 出牌、不要两个按钮
    JButton[] publishCard = new JButton[2];

    // 游戏界面地主图标：
    JLabel dizhu;

    // 每个玩家出的牌：0 左边玩家；1 自己；2 右边电脑玩家
    ArrayList<ArrayList<Poker>> currentList = new ArrayList<>();

    // 每个玩家的手牌：0 左边玩家；1 自己；2 右边电脑玩家
    ArrayList<ArrayList<Poker>> playerList = new ArrayList<>();

    // 底牌
    ArrayList<Poker> pokerList = new ArrayList<>();

    // 玩家前方的文本提示框：0 左边玩家；1 自己；2 右边电脑玩家
    JTextField[] time = new JTextField[3];

    //底牌
    ArrayList<Poker> lordList = new ArrayList<>();

    public GameJFrame() {
        // 设置任务栏图标
        this.setIconImage(Toolkit.getDefaultToolkit().getImage("image\\dizhu.png"));

        // 设置界面
        initJFrame();

        // 添加组件
        initView();

        // 添加牌
        initCard();

        // 界面显示出来，先显示界面，再发牌，否则无法展现发牌动画
        this.setVisible(true);
    }

    private void initJFrame() {
        // 设置标题
        this.setTitle("斗地主");
        // 设置大小
        this.setSize(830, 620);
        // 设置关闭模式
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // 设置窗口无法进行调节
        this.setResizable(false);
        // 界面居中
        this.setLocationRelativeTo(null);
        // 获取界面中隐藏容器，以后直接用，无需再次调用方法获取
        container = this.getContentPane();
        // 取消内部默认的居中放置
        container.setLayout(null);
        // 设置背景颜色
        container.setBackground(Color.LIGHT_GRAY);
    }

    private void initView() {
        // 创建抢地主的按钮
        JButton robBut = new JButton("抢地主");
        // 设置位置
        robBut.setBounds(320, 400, 75, 20);
        // 添加点击事件
        robBut.addActionListener(this);
        // 设置隐藏
        robBut.setVisible(false);
        // 添加到数组中统一管理
        landlord[0] = robBut;
        // 添加到界面中
        container.add(robBut);

        // 创建不抢地主的按钮
        JButton noBut = new JButton("不     抢");
        // 设置位置
        noBut.setBounds(420, 400, 75, 20);
        // 添加点击事件
        noBut.addActionListener(this);
        // 设置隐藏
        noBut.setVisible(false);
        // 添加到数组中统一管理
        landlord[0] = noBut;
        // 添加到界面中
        container.add(noBut);

        // 创建出牌的按钮
        JButton outCardBut = new JButton("出牌");
        outCardBut.setBounds(320, 400, 60, 20);
        outCardBut.addActionListener(this);
        outCardBut.setVisible(false);
        publishCard[0] = outCardBut;
        container.add(outCardBut);

        // 创建不出牌的按钮
        JButton noCardBut = new JButton("不要");
        noCardBut.setBounds(420, 400, 60, 20);
        noCardBut.addActionListener(this);
        noCardBut.setVisible(false);
        publishCard[0] = noCardBut;
        container.add(noCardBut);

        // 创建三个玩家前方的提示文字
        for (int i = 0; i < 3; i++) {
            time[i] = new JTextField("倒计时：");
            time[i].setEditable(false);
            time[i].setVisible(false);
            container.add(time[i]);
        }
        time[0].setBounds(140, 230, 60, 20);
        time[1].setBounds(374, 360, 60, 20);
        time[2].setBounds(620, 230, 60, 20);

        // 创建地主图标
        dizhu = new JLabel(new ImageIcon("image\\dizhu.png"));
        dizhu.setVisible(false);
        dizhu.setSize(40, 40);
        container.add(dizhu);
    }

    // 初始化牌、准备牌，洗牌，发牌
    public void initCard() {
        //准备牌
        //把所有的牌，包括大小王都添加到牌盒cardList当中
        for (int i = 1; i <= 5; i++) {
            for (int j = 1; j <= 13; j++) {
                if ((i == 5) && (j > 2)) {
                    break;
                } else {
                    Poker poker = new Poker(i + "-" + j, false);
                    poker.setLocation(350, 150);

                    pokerList.add(poker);
                    container.add(poker);
                }
            }
        }

        //洗牌
        Collections.shuffle(pokerList);

        //创建三个集合用来装三个玩家的牌，并把三个小集合放到大集合中方便管理
        ArrayList<Poker> player0 = new ArrayList<>();
        ArrayList<Poker> player1 = new ArrayList<>();
        ArrayList<Poker> player2 = new ArrayList<>();

        for (int i = 0; i < pokerList.size(); i++) {
            //获取当前遍历的牌
            Poker poker = pokerList.get(i);

            //发三张底牌
            if (i <= 2) {
                //移动牌
                Common.move(poker, poker.getLocation(), new Point(270 + (75 * i), 10));
                //把底牌添加到集合中
                lordList.add(poker);
                continue;
            }
            //给三个玩家发牌
            if (i % 3 == 0) {
                //给左边的电脑发牌
                Common.move(poker, poker.getLocation(), new Point(50, 60 + i * 5));
                player0.add(poker);
            } else if (i % 3 == 1) {
                //给中间的自己发牌
                Common.move(poker, poker.getLocation(), new Point(180 + i * 7, 450));
                player1.add(poker);
                //把自己的牌展示正面
                poker.turnFront();

            } else if (i % 3 == 2) {
                //给右边的电脑发牌
                Common.move(poker, poker.getLocation(), new Point(700, 60 + i * 5));
                player2.add(poker);
            }
            //把三个装着牌的小集合放到大集合中方便管理
            playerList.add(player0);
            playerList.add(player1);
            playerList.add(player2);

            //把当前的牌至于最顶端，这样就会有牌依次错开且叠起来的效果
            container.setComponentZOrder(poker, 0);
        }

        //给牌排序
        for (int i = 0; i < 3; i++) {
            //排序
            Common.order(playerList.get(i));
            //重新摆放顺序
            Common.rePosition(this, playerList.get(i), i);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
