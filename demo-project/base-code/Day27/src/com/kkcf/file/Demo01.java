package com.kkcf.file;

import java.io.File;

public class Demo01 {
    public static void main(String[] args) {
       /* File f1 = new File("D:\\workshop\\tutorial\\JAVA\\demo-project\\base-code\\Day27\\src\\com\\kkcf\\file\\a.txt");
        System.out.println(f1); // D:\workshop\tutorial\JAVA\demo-project\base-code\Day27\src\com\kkcf\file\a.txt

        File f2 = new File("D:\\workshop\\tutorial\\JAVA\\demo-project\\base-code\\Day27\\src\\com\\kkcf\\file", "a.txt");
        System.out.println(f2); // D:\workshop\tutorial\JAVA\demo-project\base-code\Day27\src\com\kkcf\file\a.txt*/

        File f3 = new File("D:\\workshop\\tutorial\\JAVA\\demo-project\\base-code\\Day27\\src\\com\\kkcf\\file");

        File f4 = new File(f3, "a.txt"); // D:\workshop\tutorial\JAVA\demo-project\base-code\Day27\src\com\kkcf\file\a.txt

        System.out.println(f4);
    }
}
