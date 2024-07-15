package com.kkcf.regexp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexpDemo01 {
    public static void main(String[] args) {
        String qqStr = "123e456";

        //boolean flag = qqStr.matches("^[1-9]\\d{4,14}$");
        //System.out.println(flag); // false

        boolean flag2 = "$wangwu".matches("^[a-zA-Z0-9_]{4,16}$");
        System.out.println(flag2);

        boolean flag3 = "430202199710160012".matches("^[0-9]{17}[\\dXx]$");
        System.out.println(flag3);

        String str = "8932658937954712750912347591235";

        Pattern pattern = Pattern.compile("^[a-zA-Z0-9_]{4,16}$");

        Matcher matcher = pattern.matcher(str);
    }
}
