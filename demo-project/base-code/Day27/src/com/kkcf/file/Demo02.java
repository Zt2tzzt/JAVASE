package com.kkcf.file;

import java.io.File;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class Demo02 {
    public static void main(String[] args) {
        File f1 = new File("D:\\Workshop\\tutorial\\JAVA\\demo-project\\base-code\\Day27\\src\\com\\kkcf\\file\\a.txt");

        System.out.println(f1.isDirectory()); // false
        System.out.println(f1.isFile()); // true
        System.out.println(f1.exists()); // true

        System.out.println(f1.length()); // 6

        System.out.println(f1.getAbsoluteFile()); // D:\Workshop\tutorial\JAVA\demo-project\base-code\Day27\src\com\kkcf\file\a.txt
        System.out.println(f1.getPath()); // D:\Workshop\tutorial\JAVA\demo-project\base-code\Day27\src\com\kkcf\file\a.txt
        System.out.println(f1.getName()); // a.txt

        long lastModifyTime = f1.lastModified();
        System.out.println(lastModifyTime); // 1722908867598
        ZonedDateTime zdt = Instant.ofEpochMilli(lastModifyTime).atZone(ZoneId.systemDefault());
        System.out.println(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss EE a").format(zdt)); // 2024-08-06 09:47:47 周二 上午
    }
}
