package com.kkcf.ui;

import com.kkcf.javabean.GameInfo;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.event.*;
import java.io.*;
import java.util.Objects;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

public class GameJFrame extends JFrame implements KeyListener, ActionListener, MouseListener {
    // 用于存放图片的随即索引
    int[][] data = new int[4][4];

    // 记录空白方块，在二维数组中的位置
    int x = 0;
    int y = 0;

    // 记录图片路径
    String path = "image/animal/animal3/";

    // 记录正确顺序的数组
    int[][] win = new int[][]{
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 11, 12},
            {13, 14, 15, 0}
    };

    // 记录玩了多少步
    int stepCount = 0;

    // 创建菜单条目对象
    JMenuItem replayItem = new JMenuItem("重新游戏");
    JMenuItem reLoginItem = new JMenuItem("重新登录");
    JMenuItem closeItem = new JMenuItem("关闭游戏");
    JMenuItem accountItem = new JMenuItem("公众号");

    // “美女”，“动物”，“运动”图片菜单选项
    JMenuItem beautyItem = new JMenuItem("美女");
    JMenuItem animalItem = new JMenuItem("动物");
    JMenuItem sportItem = new JMenuItem("运动");

    JMenu saveJMenu = new JMenu("存档");
    JMenu loadJMenu = new JMenu("读档");

    JMenuItem saveItem0 = new JMenuItem("存档0（空）");
    JMenuItem saveItem1 = new JMenuItem("存档1（空）");
    JMenuItem saveItem2 = new JMenuItem("存档2（空）");
    JMenuItem saveItem3 = new JMenuItem("存档3（空）");
    JMenuItem saveItem4 = new JMenuItem("存档4（空）");

    JMenuItem loadItem0 = new JMenuItem("读档0（空）");
    JMenuItem loadItem1 = new JMenuItem("读档1（空）");
    JMenuItem loadItem2 = new JMenuItem("读档2（空）");
    JMenuItem loadItem3 = new JMenuItem("读档3（空）");
    JMenuItem loadItem4 = new JMenuItem("读档4（空）");

    public GameJFrame() {
        // 初始化界面窗口
        initJFrame();

        // 初始化菜单栏
        initJMenuBar();

        // 初始化数据
        initData();

        // 初始化图片
        initImage();

        // 窗口设为可见。
        this.setVisible(true);
    }

    /**
     * 此方法用于，初始化游戏窗口
     */
    private void initJFrame() {
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

        // 为整个窗体，添加键盘监听事件
        this.addKeyListener(this);
    }

    /**
     * 此方法用于，初始化菜单栏
     */
    private void initJMenuBar() {
        // 初始化菜单栏对象
        JMenuBar jMenuBar = new JMenuBar();

        // 初始化菜单选项对象
        JMenu functionMenu = new JMenu("功能");
        JMenu aboutMenu = new JMenu("关于我们");
        JMenu moreMenu = new JMenu("更多图片");

        // 将菜单条目，添加到更多图片菜单中
        moreMenu.add(beautyItem);
        moreMenu.add(animalItem);
        moreMenu.add(sportItem);

        // 给菜单条目添加事件监听
        beautyItem.addMouseListener(this);
        animalItem.addMouseListener(this);
        sportItem.addMouseListener(this);

        saveJMenu.add(saveItem0);
        saveJMenu.add(saveItem1);
        saveJMenu.add(saveItem2);
        saveJMenu.add(saveItem3);
        saveJMenu.add(saveItem4);

        loadJMenu.add(loadItem0);
        loadJMenu.add(loadItem1);
        loadJMenu.add(loadItem2);
        loadJMenu.add(loadItem3);
        loadJMenu.add(loadItem4);

        // 将菜单条目，添加到惨选项中
        functionMenu.add(moreMenu);
        functionMenu.add(replayItem);
        functionMenu.add(reLoginItem);
        functionMenu.add(closeItem);
        functionMenu.add(saveJMenu);
        functionMenu.add(loadJMenu);
        aboutMenu.add(accountItem);

        // 给条目绑定事件
        replayItem.addActionListener(this);
        reLoginItem.addActionListener(this);
        closeItem.addActionListener(this);
        accountItem.addActionListener(this);

        saveItem0.addActionListener(this);
        saveItem1.addActionListener(this);
        saveItem2.addActionListener(this);
        saveItem3.addActionListener(this);
        saveItem4.addActionListener(this);
        loadItem0.addActionListener(this);
        loadItem1.addActionListener(this);
        loadItem2.addActionListener(this);
        loadItem3.addActionListener(this);
        loadItem4.addActionListener(this);

        // 将菜单选项，添加到菜单栏中
        jMenuBar.add(functionMenu);
        jMenuBar.add(aboutMenu);

        initGameinfo();

        // 给整个界面，设置菜单
        this.setJMenuBar(jMenuBar);
    }

    /**
     * 此方法用于，加载存档，取每一个存档中，记录的步数
     */
    private void initGameinfo() {
        File file = new File("save\\");

        for (File f : Objects.requireNonNull(file.listFiles())) {
            GameInfo gi = null;
            try {
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
                gi = (GameInfo) ois.readObject();
                ois.close();
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }

            int stepCount1 = gi.getStepCount();

            String fname = f.getName();
            int index = fname.charAt(4) - '0';
            this.saveJMenu.getItem(index).setText("存档" + index + "(" + stepCount1 + ")");
            this.loadJMenu.getItem(index).setText("读档" + index + "(" + stepCount1 + ")");
        }
    }

    /**
     * 此方法用于初始化图片随机位置
     */
    private void initData() {
        Random r = new Random();

        int[] arr = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};

        int len = arr.length;
        for (int i = 0; i < len; i++) {
            int randomIndex = r.nextInt(len);

            int temp = arr[i];
            arr[i] = arr[randomIndex];
            arr[randomIndex] = temp;
        }

        for (int i = 0; i < len; i++) {
            if (arr[i] == 0) {
                this.x = i / 4;
                this.y = i % 4;
            }

            this.data[i / 4][i % 4] = arr[i];
        }
    }

    /**
     * 此方法用于：初始化图片
     */
    private void initImage() {
        // 清空容器中的所有图片
        this.getContentPane().removeAll();

        // 如果胜利了，就加载“胜利”的图片
        if (checkVictory()) {
            JLabel winJLabel = new JLabel(new ImageIcon("image/win.png"));
            winJLabel.setBounds(203, 283, 197, 73);
            this.getContentPane().add(winJLabel);
        }

        // 在页面上显示计数器
        JLabel stepJLabel = new JLabel("步数：" + stepCount);
        stepJLabel.setBounds(50, 30, 100, 20);
        this.getContentPane().add(stepJLabel);

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                // 创建 ImageIcon 对象
                ImageIcon icon = new ImageIcon(path + this.data[i][j] + ".jpg");

                // 创建 JLabel 对象（管理容器）
                JLabel jLabel = new JLabel(icon);

                // 指定图片的位置
                jLabel.setBounds(105 * j + 83, 105 * i + 134, 105, 105);  // 图片都是 105 * 105 的尺寸。

                // 给图片添加功能
                jLabel.setBorder(new BevelBorder(BevelBorder.LOWERED));

                // 获取隐藏的容器，再添图片容器
                this.getContentPane().add(jLabel);
            }
        }

        // 添加被禁图片，细节：先加载的图片，会放在图层的上方；后加载的图片，会放在图层的下方。
        JLabel background = new JLabel(new ImageIcon("image/background.png"));
        background.setBounds(40, 40, 508, 560);
        this.getContentPane().add(background);

        // 刷新容器
        this.getContentPane().repaint();
    }

    /**
     * 此函数用于：判断当前二维数组中的数字顺序是否正确
     *
     * @return 二维数组中的数字顺序是否正确
     */
    public boolean checkVictory() {
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                if (data[i][j] != win[i][j]) return false;
            }
        }
        return true;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // 如果游戏胜利，直接结束方法
        if (checkVictory()) return;

        int keyCode = e.getKeyCode();

        // 键盘按下 a 键
        if (keyCode == KeyEvent.VK_A) {// 删除容器中所有图片
            this.getContentPane().removeAll();

            // 加载一张完整图片
            JLabel allPic = new JLabel(new ImageIcon(path + "all.jpg"));
            allPic.setBounds(83, 134, 420, 420);
            this.getContentPane().add(allPic);

            // 加载背景图片
            JLabel background = new JLabel(new ImageIcon("image/background.png"));
            background.setBounds(40, 40, 508, 560);
            this.getContentPane().add(background);

            // 刷新容器
            this.getContentPane().repaint();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // 如果游戏胜利，直接结束方法
        if (checkVictory()) return;

        // ↑、↓、←、→ 键判断
        int keyCode = e.getKeyCode();

        switch (keyCode) {
            case KeyEvent.VK_UP -> {
                System.out.println("向上移动");
                if (x == 3) return;
                // 空白处下方的图片，上移
                // x, y 表示空白方块的索引，x + 1, y 表示空白方块下方的索引
                data[x][y] = data[x + 1][y];
                data[x + 1][y] = 0;
                x++;

                // 步数 + 1
                stepCount++;

                initImage();

            }
            case KeyEvent.VK_DOWN -> {
                System.out.println("向下移动");
                if (x == 0) return;
                // 空白处上方的图片，下移
                // x, y 表示空白方块的索引，x - 1, y 表示空白方块上方的索引
                data[x][y] = data[x - 1][y];
                data[x - 1][y] = 0;
                x--;

                // 步数 + 1
                stepCount++;

                initImage();
            }
            case KeyEvent.VK_LEFT -> {
                System.out.println("向左移动");
                if (y == 3) return;
                // 空白处右方的图片，左移
                // x, y 表示空白方块的索引，x, y + 1 表示空白方块右
                data[x][y] = data[x][y + 1];
                data[x][y + 1] = 0;
                y++;

                // 步数 + 1
                stepCount++;

                initImage();
            }
            case KeyEvent.VK_RIGHT -> {
                System.out.println("向右移动");
                if (y == 0) return;
                // 空白处左方的图片，右移
                // x, y 表示空白方块的索引，x, y - 1 表示空白方块左方的索引
                data[x][y] = data[x][y - 1];
                data[x][y - 1] = 0;
                y--;

                // 步数 + 1
                stepCount++;

                initImage();
            }
            // 当松开 A 键
            case KeyEvent.VK_A -> initImage();
            // 当松开 W 键
            case KeyEvent.VK_W -> {
                data = new int[][]{
                        {1, 2, 3, 4},
                        {5, 6, 7, 8},
                        {9, 10, 11, 12},
                        {13, 14, 15, 0}
                };

                initImage();
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // 获取当前被点击的菜单条目（item）对象
        Object obj = e.getSource();

        if (obj == replayItem) {
            // 计数器清零
            stepCount = 0;

            // 再次打乱二维数组中的数据
            initData();

            // 重新加载图片
            initImage();
        } else if (obj == reLoginItem) {
            // 隐藏当前页面
            this.setVisible(false);

            // 返回登录界面
            new LoginJFrame();
        } else if (obj == closeItem) {
            // 关闭虚拟机
            System.exit(0);
        } else if (obj == accountItem) {
            // 创建一个弹框对象
            JDialog jDialog = new JDialog();
            // 创建一个管理图片的容器对象 JLabel
            JLabel jLabel = new JLabel(new ImageIcon(loadAccountPath()));
            // 设置位置和宽高
            jLabel.setBounds(0, 0, 100, 100);
            // 把图片添加到弹框当中
            jDialog.getContentPane().add(jLabel);
            // 给弹框设置大小
            jDialog.setSize(344, 344);
            // 让弹框置顶
            jDialog.setAlwaysOnTop(true);
            // g让弹框居中
            jDialog.setLocationRelativeTo(null);
            // 弹框不关闭则无法操作下面的界面
            jDialog.setModal(true);
            // 让弹框显示出来
            jDialog.setVisible(true);
        } else if (obj == this.saveItem0 || obj == this.saveItem1 || obj == this.saveItem2 || obj == this.saveItem3 || obj == this.saveItem4) {
            // 获取点击存档的序号
            JMenuItem item = (JMenuItem) obj;
            String text = item.getText();
            int index = text.charAt(2) - '0'; // 获取存档的索引

            // 写出存档数据
            try {
                ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("save\\save" + index + ".data"));
                GameInfo gi = new GameInfo(this.data, this.x, this.y, this.path, this.stepCount);
                oos.writeObject(gi);
                oos.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            // 修改存档的展示信息
            item.setText("存档" + index + "（" + this.stepCount + "步）");

            // 修改读档的展示信息
            this.loadJMenu.getItem(index).setText("读档" + index + "（" + this.stepCount + "步）");
        } else if (obj == this.loadItem0 || obj == this.loadItem1 || obj == this.loadItem2 || obj == this.loadItem3 || obj == this.loadItem4) {
            // 获取点击读档的序号
            JMenuItem item = (JMenuItem) obj;
            String text = item.getText();
            int index = text.charAt(2) - '0';

            GameInfo gi = null;
            try {
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream("save\\save" + index + ".data"));
                gi = (GameInfo) ois.readObject();
                ois.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            }

            this.data = gi.getData();
            this.x = gi.getX();
            this.y = gi.getY();
            this.path = gi.getPath();
            this.stepCount = gi.getStepCount();

            initImage();
        }
    }

    /**
     * 此方法用于，从 account.properties 配置文件中，加载公众号图片路径
     * @return
     */
    public String loadAccountPath() {
        Properties p = new Properties();

        try {
            FileInputStream fis = new FileInputStream("account.properties");
            p.load(fis);
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        return (String) p.get("account");
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        Random r = new Random();

        Object obj = e.getSource();

        if (obj == beautyItem) {
            int i = r.nextInt(12) + 1;
            path = "image/girl/girl" + i + "/";
        } else if (obj == sportItem) {
            int i = r.nextInt(9) + 1;
            path = "image/sport/sport" + i + "/";
        } else if (obj == animalItem) {
            int i = r.nextInt(7) + 1;
            path = "image/animal/animal" + i + "/";
        }

        // 计数器清零
        stepCount = 0;

        // 初始化数据
        initData();

        // 初始化图片
        initImage();
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
