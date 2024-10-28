# Java 集合进阶之斗地主项目

先完成控制台的三步操作：准备牌、洗牌、发牌

扑克牌种类分为：♠（黑桃）、♣（梅花）、♥（红桃）、♦（方块）

扑克牌大小分为：3、4、5、6、7、8、9、10、J、Q、K、A、2

创建一个 `App`· 类，作为程序的启动入口：

demo-project/landlord/src/com/kkcf/app/App.java

```java
package com.kkcf.app;

import com.kkcf.game.PokerGame

public class App {
    public static void main(String[] args) {
        // 开启游戏
        new PokerGame();
    }
}
```

创建一个 `PokerGame` 类，表示扑克牌；

在其中进行准备牌、洗牌、发牌操作。

## 一、准备牌

将牌定义在静态代码块中，表示无论开多少局扑克牌，都只创建一副牌。

静态代码块，随着类的加载而执行，只执行一次。

demo-project/landlord/src/com/kkcf/game/PokerGame.java

```java
package com.kkcf.game;

import java.util.ArrayList;

public class PokerGame {
    // 牌盒，里要放 54 张扑克牌
    static ArrayList<String> cardBoxList = new ArrayList<>(54);

    static {
        // 准备牌
        String[] color = {"♠", "♣", "♥", "♦"};
        String[] number = {"3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A", "2"};

        for (String c : color) {
            for (String n : number) {
                cardBoxList.add(c + n);
            }
        }

        cardBoxList.add("red joker");
        cardBoxList.add("black joker");

        System.out.println(cardBoxList);
    }

    public PokerGame() {

    }
}
```

## 二、洗牌

使用 `Collections.shuffle` 静态方法，打乱牌盒中牌的顺序。

demo-project/landlord/src/com/kkcf/game/PokerGame.java

```java
package com.kkcf.game;

import java.util.ArrayList;
import java.util.Collections;

public class PokerGame {
    // 牌盒，里要放 54 张扑克牌
    static ArrayList<String> cardBoxList = new ArrayList<>(54);

    static {
        // ……
    }

    public PokerGame() {
        Collections.shuffle(cardBoxList);

        System.out.println(cardBoxList);
    }
}
```

## 三、发牌

创建三个 `ArrayList` 集合，表示三个玩家的手牌。

创建一个 `ArrayList` 集合，表示底牌，其中有三张牌，会发给作为地主的玩家。

demo-project/landlord/src/com/kkcf/game/PokerGame.java

```java
package com.kkcf.game;

import java.util.ArrayList;
import java.util.Collections;

public class PokerGame {
    // 牌盒，里要放 54 张扑克牌
    static ArrayList<String> cardBoxList = new ArrayList<>(54);

    static {
        // 准备牌
        // ……
    }

    public PokerGame() {
        // 洗牌
        Collections.shuffle(cardBoxList);

        // 发牌
        ArrayList<String> lordList = new ArrayList<>(3); // 底牌
        ArrayList<String> player1 = new ArrayList<>(); // 玩家1
        ArrayList<String> player2 = new ArrayList<>(); // 玩家2
        ArrayList<String> player3 = new ArrayList<>(); // 玩家3

        for (int i = 0; i < cardBoxList.size(); i++) {
            String poker = cardBoxList.get(i);

            if (i <= 2) {
                lordList.add(poker);
                continue;
            }

            if (i % 3 == 1) {
                player1.add(poker);
            } else if (i % 3 == 2) {
                player2.add(poker);
            } else {
                player3.add(poker);
            }
        }
    }
}
```

## 四、看牌

定义一个方法，用于看牌：

demo-project/landlord/src/com/kkcf/game/PokerGame.java

```java
package com.kkcf.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.StringJoiner;

public class PokerGame {
    // 牌盒，里要放 54 张扑克牌
    static ArrayList<String> cardBoxList = new ArrayList<>(54);

    static {
        // 准备牌
        // ……
    }

    public PokerGame() {
        // ……

        // 看牌
        lookPoker("底牌", lordList);
        lookPoker("钢脑壳", player1);
        lookPoker("大帅比", player2);
        lookPoker("蛋筒", player3);
    }

    /**
     * 此方法用于，看牌
     *
     * @param player    玩家名
     * @param pokerList 玩家手牌
     */
    public void lookPoker(String player, ArrayList<String> pokerList) {
        StringJoiner sj = new StringJoiner(", ", player + "：", "；");

        for (String poker : pokerList)
            sj.add(poker);

        System.out.println(sj);
    }
}
```

## 五、给牌排序

有两种方式：

- 方式一：利用序号进行排序；
- 方式二：给每一张牌计算价值。

### 1.序号排序

先将牌按照指定顺序进行排序，然后再与自然数字一一对应；

数字越大，对应的牌面也就越大。

> 映射思想：
>
> - 如果原始数据规律非常复杂，那么可以先手动排序，让每一个原始数据，与一个序号产生对应关系；
> - 序号，就是自然数，规律非常简单；
> - 当真正要操作原始数据时，再通过序号，找到原始数据。

需要使用到 `Map` 集合，将序号与牌面一一对应起来

这里选择使用 `HashMap` 集合，将序号作为键存入其中，因为不需要对序号进行排序。

然后，再创建一个存放牌盒的 `ArrayList` 集合，里面存放着扑克牌的序号。

demo-project/landlord/src/com/kkcf/game/PokerGame.java

```java
package com.kkcf.game;

import java.util.*;

public class PokerGame {
    // 序号，与牌面映射关系
    static HashMap<Integer, String> cardSizeMap = new HashMap<>();

    // 牌盒，里要放 54 张扑克牌，存的是牌的序号
    static ArrayList<Integer> cardBoxList = new ArrayList<>(54);

    static {
        // 准备牌
        String[] color = {"♠", "♣", "♥", "♦"};
        String[] number = {"3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A", "2"};

        // 定义自然数序号
        int serial = 1;

        for (String n : number) {
            for (String c : color) {
                cardBoxList.add(serial);
                cardSizeMap.put(serial++, c + n);
            }
        }

        cardBoxList.add(serial);
        cardSizeMap.put(serial++, "black joker");
        cardBoxList.add(serial);
        cardSizeMap.put(serial, "red joker");

        System.out.println(cardBoxList);
        System.out.println(cardSizeMap);
    }

    public PokerGame() {
        // 洗牌
        Collections.shuffle(cardBoxList);

        // 发牌并排序，使用 TreeSet 作为手牌集合，可以实现排序
        TreeSet<Integer> lordSet = new TreeSet<>(); // 底牌
        TreeSet<Integer> player1 = new TreeSet<>(); // 玩家1
        TreeSet<Integer> player2 = new TreeSet<>(); // 玩家2
        TreeSet<Integer> player3 = new TreeSet<>(); // 玩家3

        for (int i = 0; i < cardBoxList.size(); i++) {
            Integer pokerseiral = cardBoxList.get(i);

            // 发底牌
            if (i < 3) {
                lordSet.add(pokerseiral);
                continue;
            }

            // 发 1、2、3 号玩家牌
            if (i % 3 == 1) player1.add(pokerseiral);
            else if (i % 3 == 2) player2.add(pokerseiral);
            else player3.add(pokerseiral);
        }

        // 看牌
        lookPoker("底牌", lordSet);
        lookPoker("钢脑壳", player1);
        lookPoker("大帅比", player2);
        lookPoker("蛋筒", player3);
    }

    /**
     * 此方法用于，看牌
     *
     * @param player    玩家名
     * @param pokerList 玩家手牌
     */
    public void lookPoker(String player, TreeSet<Integer> pokerList) {
        StringJoiner sj = new StringJoiner(", ", player + "：", "；");

        for (Integer serial : pokerList)
            sj.add(cardSizeMap.get(serial));

        System.out.println(sj);
    }
}
```

### 2.计算价值再排序

为每个牌面，赋予一个自定义的价值，用于排序；

使用 `HashMap` 集合，将牌面，和价值对应起来。

额外定义一个用于集合排序的方法，用于给手牌排序。

demo-project/landlord/src/com/kkcf/game/PokerGame.java

```java
package com.kkcf.game;

import java.util.*;

public class PokerGame {
    // 牌盒，里要放 54 张扑克牌
    static ArrayList<String> cardBoxList = new ArrayList<>(54);

    // 牌面，与自定义价值的映射关系
    static HashMap<String, Integer> pokerValMap = new HashMap<>();

    static {
        // 自定义牌面的价值
        pokerValMap.put("J", 11);
        pokerValMap.put("Q", 12);
        pokerValMap.put("K", 13);
        pokerValMap.put("A", 14);
        pokerValMap.put("2", 15);
        pokerValMap.put("black joker", 50);
        pokerValMap.put("red joker", 100);

        // 准备牌
        String[] color = {"♠", "♣", "♥", "♦"};
        String[] number = {"3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A", "2"};

        for (String c : color) {
            for (String n : number) {
                cardBoxList.add(c + n);
            }
        }

        cardBoxList.add(" red joker"); // 在前面加上空格，用于比较时截取牌面大小进行比较
        cardBoxList.add(" black joker"); // 在前面加上空格，用于比较时截取牌面大小进行比较

        System.out.println(cardBoxList);
    }

    public PokerGame() {
        // 洗牌
        Collections.shuffle(cardBoxList);

        // 发牌
        ArrayList<String> lordList = new ArrayList<>(3); // 底牌
        ArrayList<String> player1List = new ArrayList<>(); // 玩家1
        ArrayList<String> player2List = new ArrayList<>(); // 玩家2
        ArrayList<String> player3List = new ArrayList<>(); // 玩家3

        for (int i = 0; i < cardBoxList.size(); i++) {
            String poker = cardBoxList.get(i);

            // 发底牌
            if (i <= 2) {
                lordList.add(poker);
                continue;
            }

            // 发 1、2、3 号玩家牌
            if (i % 3 == 1) player1List.add(poker);
            else if (i % 3 == 2) player2List.add(poker);
            else player3List.add(poker);
        }

        // 排序
        sortPoker(lordList);
        sortPoker(player1List);
        sortPoker(player2List);
        sortPoker(player3List);

        // 看牌
        lookPoker("底牌", lordList);
        lookPoker("钢脑壳", player1List);
        lookPoker("大帅比", player2List);
        lookPoker("蛋筒", player3List);
    }

    /**
     * 此方法用于，看牌
     *
     * @param player    玩家名
     * @param pokerList 玩家手牌
     */
    public void lookPoker(String player, ArrayList<String> pokerList) {
        StringJoiner sj = new StringJoiner(", ", player + "：", "；");

        for (String poker : pokerList)
            sj.add(poker);

        System.out.println(sj);
    }

    /**
     * 此方法用于：排序集合
     *
     * @param list 手牌集合
     */
    public void sortPoker(ArrayList<String> list) {
        list.sort((o1, o2) -> {
            String color1 = o1.substring(0, 1);
            String num1Str = o1.substring(1);
            int val1 = pokerValMap.containsKey(num1Str) ? pokerValMap.get(num1Str) : Integer.parseInt(num1Str);

            String color2 = o2.substring(0, 1);
            String num2Str = o2.substring(1);
            int val2 = pokerValMap.containsKey(num2Str) ? pokerValMap.get(num2Str) : Integer.parseInt(num2Str);

            int result = val1 - val2;
            if (result == 0) result = color1.compareTo(color2);

            return result;
        });
    }
}
```

## 六、用户登录

创建一个用户 `User` 类，表示登录游戏的用户。

demo-project/landlord/src/com/kkcf/javabean/User.java

创建一个登录界面 `LoginJFrame` 类，继承 `JFrame` 类，并实现 `MouseListener` 接口用于事件监听。

demo-project/landlord/src/com/kkcf/game/LoginJFrame.java

创建一个 `CodeUtil` 工具类，用于随机生成验证码。

demo-project/landlord/src/com/kkcf/util/CodeUtil.java

## 七、游戏界面

在游戏界面，要用到 8 个集合：

- 牌盒：一个集合
- 底牌：一个集合；
- 手中的牌：三个集合（对应三个玩家）；放入一个大集合中管理。
- 打出的牌：三个集合（对应三个玩家）。放入一个大集合中管理。
- 游戏提示：三个数组（对应三个玩家一局所剩的时间）
- 按钮组合1：一个数组：抢地主、不抢
- 按扭组合2：一个数组：出牌、不要
- 游戏图标：地主图片，放在地主玩家的前面。

> 当数据量比较少，没有频繁的添加、删除操作时，可以用数组；否则就用集合。

### 1.游戏界面搭建

demo-project/landlord/src/com/kkcf/game/GameJFrame.java

```java
package com.kkcf.game;

import com.kkcf.javabean.Poker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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

    public GameJFrame() {
        // 设置任务栏图标
        this.setIconImage(Toolkit.getDefaultToolkit().getImage("image\\dizhu.png"));

        // 设置界面
        initJFrame();

        // 添加组件
        initView();

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

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
```

> 面向对象设计：不能脱离业务场景，业务需求。

扑克牌 `Poker` 类的设计：

在某些系统中，不能使用“♠”这样的特殊字符，需要使用数字，来替代花色。

因此，一张扑克牌，要使用“数字-数字”的表现方式。

- 1-黑桃
- 2-红桃
- 3-梅花
- 4-方块
- 5-大小王

`Poker` 类中，还有以下属性：

- 牌的状态：正反面。
- 牌是否可以被点击。
- 是否已经被点击。

创建 `Poker` 类，

- 使用 `Poker` 类，继承自 `JLabal` 类，表示它本身是一个 `JLabal` 容器。
- 以上属性都是必填的，所以不需要空参构造，直接写带参构造。
- 为 `Poker` 类，实现 `MouseListener` 接口，重写里面的抽象方法，用于鼠标事件监听。
- 编写两个成员方法 `turnFront` 和 `turnBack`，用于显示牌的正、反面。
- 编写 `initCard` 方法，用于初始化牌、准备牌，洗牌，发牌

demo-project/landlord/src/com/kkcf/game/GameJFrame.java

```java
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
```
