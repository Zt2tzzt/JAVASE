package com.kkcf.hutool;

import cn.hutool.core.io.FileUtil;

import java.io.File;
import java.util.List;

public class Demo01 {
    public static void main(String[] args) {
        File file = FileUtil.file("D:\\Workshop\\tutorial\\JAVASE\\demo-project\\base-code\\Day29\\src\\com\\kkcf\\hutool", "haha", "aaa", "a.txt"); // 必须是绝对路径

        List<String> list = FileUtil.readLines(file, "UTF-8");

        System.out.println(list); // [abc, cba, nba, wee, yui, mio]
    }
}
