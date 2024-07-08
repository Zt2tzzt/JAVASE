package com.kkcf.student;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class App {
    private static final ArrayList<User> userList = new ArrayList<>();

    static {
        userList.add(new User("admin", "123456", "123456789012345678", "12345678901"));
        userList.add(new User("zetian", "654321", "123456789012345678", "12345678901"));
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        loop:
        do {
            System.out.println();
            System.out.println("-------------欢迎来到北宇治学生管理系统-------------");
            System.out.print("请选择操作：1登录；2注册；3忘记密码：4退出");
            String chose = sc.next();

            switch (chose) {
                case "1" -> login(userList);
                case "2" -> register(userList);
                case "3" -> forgetPassword(userList);
                case "4" -> {
                    System.out.println("退出成功！");
                    break loop;
                }
                default -> System.out.println("输入错误，请重新输入");
            }
        } while (true);
    }

    /**
     * 此函数用于：忘记密码
     *
     * @param list 用户集合列表
     */
    private static void forgetPassword(ArrayList<User> list) {
        Scanner sc = new Scanner(System.in);

        // 输入用户名
        System.out.print("请输入用户名：");
        String username = sc.next();
        User user = findUserByUsername(list, username);
        if (user == null) {
            System.out.println("用户未注册");
            return;
        }

        // 输入身份证号
        System.out.print("请输入身份证号：");
        String personId = sc.next();

        // 输入手机号
        System.out.print("请输入手机号：");
        String phone = sc.next();

        // 修改密码
        if (!(user.getPersonId().equalsIgnoreCase(personId) && user.getPhoneNumber().equals(phone))) {
            System.out.println("身份证号和手机号信息不匹配，修改失败");
            return;
        }

        String newPassword1 = "";
        do {
            System.out.print("请输入新密码：");
            newPassword1 = sc.next();

            System.out.println("请再次输入新密码：");
            String newPassword2 = sc.next();

            if (newPassword1.equals(newPassword2)) break;
        } while (true);

        user.setPassword(newPassword1);
        System.out.println("密码修改成功");
    }

    /**
     * 此函数用于：注册
     *
     * @param list 用户集合列表
     */
    private static void register(ArrayList<User> list) {
        Scanner sc = new Scanner(System.in);

        // 输入用户名
        String username = "";
        do {
            System.out.print("请输入用户名：");
            username = sc.next();

            int len = username.length();
            if (len < 3 || len > 15)
                System.out.println("用户名长度应在 3-15 之间，请重新输入");
            else if (checkUsername(username))
                System.out.println("用户只能是字母加数字的组合，请重新输入");
            else if (isUsernameUnique(list, username))
                System.out.println("用户名已存在，请重新输入");
            else
                break;
        } while (true);

        // 输入密码
        String password1 = "";
        do {
            System.out.print("请输入密码");
            password1 = sc.next();

            System.out.print("请再次输入密码");
            String password2 = sc.next();

            if (password1.equals(password2))
                break;
            else
                System.out.println("两次输入的密码不一致，请重新输入");
        } while (true);

        // 输入身份证号
        String personId = "";
        do {
            System.out.print("请输入身份证号");
            personId = sc.next();

            if (personId.length() != 18)
                System.out.println("身份证号长度应为18位，请重新输入");
            else if (personId.startsWith("0"))
                System.out.println("身份证号不能以0开头，请重新输入");
            else if (checkPersonId(personId))
                System.out.println("身份证号不合法，请重新输入");
            else
                break;

        } while (true);

        // 输入手机号
        String phone = "";
        do {
            System.out.print("请输入手机号：");
            phone = sc.next();

            if (phone.length() != 11)
                System.out.println("手机号长度应为11位，请重新输入");
            else if (phone.startsWith("0"))
                System.out.println("手机号不能以0开头，请重新输入");
            else if (checkPhoneNumber(phone))
                System.out.println("手机号不合法，请重新输入");
            else
                break;

        } while (true);

        // 将用户信息添加到集合中
        list.add(new User(username, password1, personId, phone));
        System.out.println("注册成功");
        System.out.println(list);
    }

    /**
     * 此方法用于：登录
     *
     * @param list 用户集合列表
     */
    private static void login(ArrayList<User> list) {
        Scanner sc = new Scanner(System.in);

        // 输入用户名
        System.out.print("请输入用户名：");
        String username = sc.next();
        User user = findUserByUsername(list, username);
        if (user == null) {
            System.out.println("用户名未注册，请先注册");
            return;
        }

        // 输入密码，有 3 次机会
        String password = "";
        for (int i = 0; true; i++) {
            System.out.print("请输入密码：");
            password = sc.next();

            if (user.getPassword().equals(password))
                break;
            else {
                if (i == 2) {
                    System.out.println("密码错误，登录失败");
                    return;
                }
                System.out.println("密码错误，请重新输入");
            }
        }

        // 输入验证码
        String codeInput = "";
        do {
            String code = generateCode();
            System.out.println("请输入验证码：" + code);
            codeInput = sc.next();
            if (code.equalsIgnoreCase(codeInput))
                break;
            else
                System.out.println("验证码错误，请重新输入");
        } while (true);

        System.out.println("username：" + username + "，personId：" + password + "，code：" + codeInput);
        System.out.println("登陆成功！");
        // 进入学生管理系统
        StudentSystem.startStudentSystem();
    }

    /**
     * 此函数用于：检查用户名是否只能是字母加数字的组合
     *
     * @param username 用户名
     * @return true:不是；false:是
     */
    public static boolean checkUsername(String username) {
        int letterCount = 0;

        for (int i = 0; i < username.length(); i++) {
            char c = username.charAt(i);

            if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z'))
                letterCount++;
            else if (c < '0' || c > '9')
                return true;
        }

        return letterCount == 0;
    }

    /**
     * 此函数用于： 检查用户名是否唯一
     *
     * @param list     用户集合列表
     * @param username 用户名
     * @return true:不唯一；false:唯一
     */
    public static boolean isUsernameUnique(ArrayList<User> list, String username) {
        for (User user : list)
            if (user.getUsername().equals(username))
                return true;

        return false;
    }

    /**
     * 此函数用于：检查身份证号是否合法
     *
     * @param personId 身份证号
     * @return true:不合法；false:合法
     */
    public static boolean checkPersonId(String personId) {
        int len = personId.length();

        for (int i = 1; i < len - 1; i++) {
            char c = personId.charAt(i);

            if (c < '0' || c > '9')
                return true;
        }

        char c = personId.charAt(len - 1);
        return (c < '0' || c > '9') && c != 'X' && c != 'x';
    }

    /**
     * 此函数用于：检查手机号是否合法
     *
     * @param phone 手机号
     * @return true:不合法；false:合法
     */
    private static boolean checkPhoneNumber(String phone) {
        return StudentSystem.checkNum(phone);
    }

    /**
     * 此函数用于：生成一个随机的 5 位验证码
     *
     * @return 验证码
     */
    public static String generateCode() {
        // 填充 letters
        ArrayList<Character> letters = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            letters.add((char) ('a' + i));
            letters.add((char) ('A' + i));
        }

        // 填充 nums
        ArrayList<Character> nums = new ArrayList<>();
        for (int i = 0; i < 10; i++)
            nums.add((char) ('0' + i));

        Random r = new Random();

        char[] chs = new char[5];

        // 生成字母
        for (int i = 0; i < 4; i++)
            chs[i] = letters.get(r.nextInt(letters.size()));

        // 生成数字
        chs[4] = nums.get(r.nextInt(nums.size()));

        // 将最后一位，与前面随机一位字符交换
        int index = r.nextInt(4);
        char temp = chs[4];
        chs[4] = chs[index];
        chs[index] = temp;

        // 返回结果
        return new String(chs);
    }

    /**
     * 此函数用于：检查用户名是否存在
     *
     * @param list     用户集合列表
     * @param username 用户名
     * @return true:不存在；false:存在
     */
    public static User findUserByUsername(ArrayList<User> list, String username) {
        for (User user : list)
            if (user.getUsername().equals(username))
                return user;

        return null;
    }
}
