package com.kkcf.regexp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexDemo03 {
    public static void main(String[] args) throws IOException {
        // 创建一个URL对象
        URL url = new URL("https://m.sengzan.com/jiaoyu/29104.html?ivk sa=1025883i");

        // 连接上这个网址（保证网络是畅通）
        URLConnection conn = url.openConnection();

        // 创建一个缓冲字符输入流对象，读取网络中的数据
        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

        String line;
        // 获取正则表达式的对象 pattern
        String regex = "[1-9]\\d{17}";
        Pattern pattern = Pattern.compile(regex);//在读取的时候每次读一整行

        while ((line = br.readLine()) != null) {
            // 拿着文本匹配器的对象 matcher 按照 pattern 的规则去读取当前的这一行信息
            Matcher matcher = pattern.matcher(line);

            while (matcher.find()) {
                System.out.println(matcher.group());
            }
        }

        br.close();
    }
}
