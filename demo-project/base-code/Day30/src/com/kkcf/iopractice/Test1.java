package com.kkcf.iopractice;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test1 {

    /**
     * 此方法用于：爬取网页数据
     *
     * @param url 网站 url
     * @return 网站数据
     * @throws IOException 异常
     */
    public static String webCrawler(String url) throws IOException {
        // 用于拼接爬取到的数据
        StringBuilder sb = new StringBuilder();

        // 创建 URL 对象
        URL url1 = new URL(url);

        // 打开 url
        URLConnection urlConnection = url1.openConnection();

        // 获取字节流，又因为网站上有中文，所以要转成字符流
        InputStreamReader isr = new InputStreamReader(urlConnection.getInputStream());

        // 读取数据
        char[] chs = new char[1024 * 1024 * 5];
        int len;
        while ((len = isr.read(chs)) != -1)
            sb.append(chs, 0, len);

        isr.close();
        return sb.toString();
    }

    /**
     * 此方法用于：正则表达式匹配
     *
     * @param str   网站数据
     * @param regex 正则表达式
     * @param index 匹配正则表达式中第几组
     * @return 匹配到的数据
     */
    public static ArrayList<String> getData(String str, String regex, int index) {
        ArrayList<String> lastNameList = new ArrayList<>();

        // 正则表达式匹配
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        while (matcher.find())
            lastNameList.add(matcher.group(index));

        return lastNameList;
    }

    /**
     * 此方法用于：处理姓氏数据
     *
     * @param lastNameTempList 姓氏数据
     * @return 存储姓氏的集合
     */
    private static ArrayList<Character> getLastNameData(ArrayList<String> lastNameTempList) {
        ArrayList<Character> result = new ArrayList<>();

        for (String s : lastNameTempList)
            for (int i = 0; i < s.length(); i++)
                result.add(s.charAt(i));

        return result;
    }

    /**
     * 此方法用于：处理男性名字数据（去重）
     *
     * @param maleNameTempList 男性名字数据
     * @return 男性名字
     */
    private static ArrayList<String> getMaleNameData(ArrayList<String> maleNameTempList) {
        HashSet<String> set = new HashSet<>(maleNameTempList);
        return new ArrayList<>(set);
    }

    /**
     * 此方法用于：处理女性名字数据
     *
     * @param femaleNameTempList 女性名字数据
     * @return 女性名字
     */
    private static ArrayList<String> getFemaleNameData(ArrayList<String> femaleNameTempList) {
        ArrayList<String> result = new ArrayList<>();

        for (String s : femaleNameTempList)
            result.addAll(Arrays.asList(s.split(" ")));

        return result;
    }

    /**
     * 此方法用于：生成姓名列表假数据
     *
     * @param lastNameList   姓氏列表
     * @param maleNameList   男性名称列表
     * @param femaleNameList 女性名称列表
     * @param maleCount      女性名称个数
     * @param femaleCount    男性名称个数
     * @return 姓名列表
     */
    private static ArrayList<String> mockNameList(ArrayList<Character> lastNameList, ArrayList<String> maleNameList, ArrayList<String> femaleNameList, int maleCount, int femaleCount) {
        ArrayList<String> result = new ArrayList<>();

        Random r = new Random();

        HashSet<String> tempSet = new HashSet<>();
        // 生成男性名字
        for (int i = 0; i < maleCount; ) {
            int index1 = r.nextInt(lastNameList.size());
            int index2 = r.nextInt(maleNameList.size());

            String name = lastNameList.get(index1) + maleNameList.get(index2);
            if (!tempSet.contains(name)) {
                tempSet.add(name);
                result.add(name + "-男-" + (r.nextInt(23) + 18)); // 18-40
                i++;
            }
        }

        // 生成女性名字
        for (int i = 0; i < femaleCount; ) {
            int index1 = r.nextInt(lastNameList.size());
            int index2 = r.nextInt(femaleNameList.size());

            String name = lastNameList.get(index1) + femaleNameList.get(index2);
            if (!tempSet.contains(name)) {
                tempSet.add(name);
                result.add(name + "-女-" + (r.nextInt(23) + 18));
                i++;
            }
        }

        Collections.shuffle(result);

        return result;
    }

    public static void main(String[] args) throws IOException {
        String LAST_NAME_URL = "https://hanyu.baidu.com/shici/detail?pid=0b2f26d4c0ddb3ee693fdb1137ee1b0d&from=kg0";
        String MALE_NAME_URL = "http://www.haoming8.cn/baobao/10881.html";
        String FEMALE_NAME_URL = "http://www.haoming8.cn/baobao/7641.html";

        // 爬取数据
        String lastNameStr = webCrawler(LAST_NAME_URL); // 赵钱孙李，周吴郑王。
        String maleNameStr = webCrawler(MALE_NAME_URL); // 修永, 浩恒
        String femaleNameStr = webCrawler(FEMALE_NAME_URL); // 彤舞 芊静 艾丝 惠蕙 语月, 依莹 瑶馨 曼珍 逸云 微婉

        // 处理数据
        ArrayList<String> lastNameTempList = getData(lastNameStr, "(\\W{4})(，|。)", 1);
        ArrayList<String> maleNameTempList = getData(maleNameStr, "([\\u4E00-\\u9FA5]{2})(、|。)", 1);
        ArrayList<String> femaleNameTempList = getData(femaleNameStr, "([\\u4E00-\\u9FA5]{2} ){4}[\\u4E00-\\u9FA5]{2}", 0);

        // 处理数据2
        ArrayList<Character> lastNameList = getLastNameData(lastNameTempList);
        ArrayList<String> maleNameList = getMaleNameData(maleNameTempList);
        ArrayList<String> femaleNameList = getFemaleNameData(femaleNameTempList);

        // 姓氏和名字拼接;
        ArrayList<String> namelist = mockNameList(lastNameList, maleNameList, femaleNameList, 5, 5);

        // 写出数据
        BufferedWriter bw = new BufferedWriter(new FileWriter("Day30/src/com/kkcf/iopractice/name.txt"));

        for (String s : namelist) {
            bw.write(s);
            bw.newLine();
        }

        bw.close();
    }
}
