package com.kkcf.regexp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexDemo05 {
    public static void main(String[] args) {
        String s = "Java自从95年问世以来，经历了很多版本，目前企业中用的最多的是Java8和Java11，" +
                "因为这两个是长期支持版本，下一个长期支持版本是Java17，相信在未来不久Java17也会逐渐登上历史舞台";

        // 1.定义正则表达式
        // ? 理解为前面的数据 Java
        // ?! 表示在 Java 后面不能跟随的数据，
        //需求3:
        String regex1 = "((?i)Java)(?!8|11|17)";

        // regex1、regex2 都可以
        Pattern p = Pattern.compile(regex1);

        Matcher m = p.matcher(s);

        while (m.find()) {
            System.out.println(m.group());
        }
    }
}
