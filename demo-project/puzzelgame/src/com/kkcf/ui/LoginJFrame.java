package com.kkcf.ui;

import cn.hutool.core.io.FileUtil;
import com.kkcf.javabean.User;
import com.kkcf.util.VerificationCode;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

public class LoginJFrame extends JFrame implements MouseListener {
    // 登录、注册按钮
    JButton loginBtn = new JButton();
    JButton registerBtn = new JButton();

    // 用户名、密码
    JTextField username = new JTextField();
    JTextField password = new JTextField();

    // 验证码
    JLabel rightCode = new JLabel();
    String codeStr = VerificationCode.getVerificationCode();
    JTextField codeInput = new JTextField();

    //创建一个集合存储正确的用户名和密码
    //static ArrayList<User> list = new ArrayList<>();
    ArrayList<User> allUsers = new ArrayList<>();

    /*static {
        list.add(new User("zhangsan", "123"));
        list.add(new User("lisi", "1234"));
    }*/

    public LoginJFrame() {
        // 读取文笔文件中的用户信息
        readUserInfo();

        // 初始化界面
        initJFrame();

        // 在这个界面中，添加内容
        initView();

        // 设置窗口可见
        this.setVisible(true);
    }

    private void readUserInfo() {
        // 读取文件中的用户信息
        List<String> userInfoList = FileUtil.readUtf8Lines("D:\\Workshop\\tutorial\\JAVASE\\demo-project\\puzzelgame\\src\\com\\kkcf\\ui\\userinfo.txt");

        for (String userinfo : userInfoList) {
            String[] arr = userinfo.split("&");
            String[] arr1 = arr[0].split("=");
            String[] arr2 = arr[1].split("=");

            allUsers.add(new User(arr1[1], arr2[1]));
        }
        System.out.println(allUsers);
    }

    /**
     * 此方法用于，初始化窗体
     */
    private void initJFrame() {
        this.setSize(488, 430);

        // 设置窗口标题
        this.setTitle("拼图小游戏登录 V1.0");
        // 设置窗口始终置顶
        this.setAlwaysOnTop(true);
        // 设置窗口居中展示
        this.setLocationRelativeTo(null);
        // 设置窗口的关闭模式
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // 表示关闭窗口后，虚拟机停止运行。

        // 取消内部默认布局
        this.setLayout(null);
    }

    /**
     * 此方法用于，初始化界面 ui
     */
    private void initView() {
        //1. 添加用户名文字
        JLabel usernameText = new JLabel(new ImageIcon("image/login/用户名.png"));
        usernameText.setBounds(116, 135, 47, 17);
        this.getContentPane().add(usernameText);

        //2.添加用户名输入框
        username.setBounds(195, 134, 200, 30);
        this.getContentPane().add(username);

        //3.添加密码文字
        JLabel passwordText = new JLabel(new ImageIcon("image/login/密码.png"));
        passwordText.setBounds(130, 195, 32, 16);
        this.getContentPane().add(passwordText);

        //4.密码输入框
        password.setBounds(195, 195, 200, 30);
        this.getContentPane().add(password);

        //验证码提示
        JLabel codeText = new JLabel(new ImageIcon("image/login/验证码.png"));
        codeText.setBounds(133, 256, 50, 30);
        this.getContentPane().add(codeText);

        //验证码的输入框
        codeInput.setBounds(195, 256, 100, 30);
        this.getContentPane().add(codeInput);

        //设置内容
        rightCode.setText(codeStr);
        //位置和宽高
        rightCode.setBounds(300, 256, 50, 30);
        // 点击事件监听
        rightCode.addMouseListener(this);
        //添加到界面
        this.getContentPane().add(rightCode);

        //5.添加登录按钮
        loginBtn.setBounds(123, 310, 128, 47);
        loginBtn.setIcon(new ImageIcon("image/login/登录按钮.png"));
        //去除按钮的默认边框
        loginBtn.setBorderPainted(false);
        //去除按钮的默认背景
        loginBtn.setContentAreaFilled(false);
        // 事件监听
        loginBtn.addMouseListener(this);
        this.getContentPane().add(loginBtn);

        //6.添加注册按钮
        registerBtn.setBounds(256, 310, 128, 47);
        registerBtn.setIcon(new ImageIcon("image/login/注册按钮.png"));
        //去除按钮的默认边框
        registerBtn.setBorderPainted(false);
        //去除按钮的默认背景
        registerBtn.setContentAreaFilled(false);
        // 事件监听
        registerBtn.addMouseListener(this);
        this.getContentPane().add(registerBtn);

        //7.添加背景图片
        JLabel background = new JLabel(new ImageIcon("image/login/background.png"));
        background.setBounds(0, 0, 470, 390);
        this.getContentPane().add(background);
    }

    /**
     * 此芳芳用于，弹框提示
     *
     * @param content 提示内容
     */
    public void showJDialog(String content) {
        //创建一个弹框对象
        JDialog jDialog = new JDialog();
        //给弹框设置大小
        jDialog.setSize(200, 150);
        //让弹框置顶
        jDialog.setAlwaysOnTop(true);
        //让弹框居中
        jDialog.setLocationRelativeTo(null);
        //弹框不关闭永远无法操作下面的界面
        jDialog.setModal(true);

        //创建Jlabel对象管理文字并添加到弹框当中
        JLabel warning = new JLabel(content);
        warning.setBounds(0, 0, 200, 150);
        jDialog.getContentPane().add(warning);

        //让弹框展示出来
        jDialog.setVisible(true);
    }

    /**
     * 此方法用于，检查登录的用户名和密码
     *
     * @param username 用户名
     * @param password 密码
     * @return true 表示登录失败，false 表示登录成功
     */
    public boolean checkLogin(String username, String password) {
        for (User user : allUsers) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password))
                return false;
        }

        return true;
    }

    /**
     * 此芳芳用于，检查用户输入的验证码
     *
     * @param code 用户输入的验证码
     * @return true 表示验证码正确，false 表示验证码错误
     */
    public boolean checkCode(String code) {
        return !code.equalsIgnoreCase(this.codeStr);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        Object obj = e.getSource();

        if (obj == loginBtn)
            loginBtn.setIcon(new ImageIcon("image/login/登录按下.png"));
        else if (obj == registerBtn)
            registerBtn.setIcon(new ImageIcon("image/login/注册按下.png"));

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        Object obj = e.getSource();

        if (obj == loginBtn) {
            loginBtn.setIcon(new ImageIcon("image/login/登录按钮.png"));
            String usernameStr = username.getText();
            String passwordStr = password.getText();

            // 判断验证码
            if (checkCode(codeInput.getText())) {
                showJDialog("验证码错误");
                return;
            }

            // 验证用户名、密码
            if (checkLogin(usernameStr, passwordStr)) {
                showJDialog("用户名或者密码错误");
                return;
            }

            // 隐藏登录界面，打开游戏界面
            this.setVisible(false);
            new GameJFrame();

        } else if (obj == registerBtn) {
            registerBtn.setIcon(new ImageIcon("image/login/注册按钮.png"));
            // 关闭当前登陆页面
            this.setVisible(false);
            // 打开注册界面
            new RegisterJFrame(allUsers);
        } else if (obj == rightCode) {
            System.out.println("验证码点击了");
            codeStr = VerificationCode.getVerificationCode();
            rightCode.setText(codeStr);
        }

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
