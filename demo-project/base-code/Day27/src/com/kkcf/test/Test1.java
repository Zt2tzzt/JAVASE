package com.kkcf.test;

import com.kkcf.exception.AgeOutOfBoundException;
import com.kkcf.exception.NameFormatException;
import com.kkcf.javabean.GirlFriend;

import java.util.Scanner;

public class Test1 {
    public static void main(String[] args) {
        //GirlFriend wee = new GirlFriend("wee", 26);
        //System.out.println(wee);

        Scanner sc = new Scanner(System.in);
        GirlFriend gf = new GirlFriend();

        do {
            try {
                System.out.println("请输入你心仪的女朋友的名字：");
                String nameStr = sc.nextLine();
                gf.setName(nameStr);

                System.out.println("请输入你心仪的女朋友的年龄：");
                String ageStr = sc.nextLine();
                int age = Integer.parseInt(ageStr);
                gf.setAge(age);

                break;
            } catch (NumberFormatException e) {
                System.out.println("年龄的格式有误，请输入数字");
                System.out.println(e.getMessage());
            } catch (NameFormatException e) {
                System.out.println("姓名的长度范围有误");
                System.out.println(e.getMessage());
            } catch (AgeOutOfBoundException e) {
                System.out.println("年龄的范围有误");
                System.out.println(e.getMessage());
            }
        } while (true);

        System.out.println(gf);
    }
}
