package com.kkcf.iopractice;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.ReUtil;
import cn.hutool.http.HttpUtil;

import java.util.*;

public class Test2 {
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
            int index = r.nextInt(lastNameList.size());
            String name = lastNameList.get(index) + maleNameList.get(i);
            if (!tempSet.contains(name)) {
                tempSet.add(name);
                result.add(name + "-男-" + (r.nextInt(23) + 18)); // 18-40
                i++;
            }
        }

        // 生成女性名字
        for (int i = 0; i < femaleCount; ) {
            int index = r.nextInt(lastNameList.size());
            String name = lastNameList.get(index) + femaleNameList.get(i);
            if (!tempSet.contains(name)) {
                tempSet.add(name);
                result.add(name + "-女-" + (r.nextInt(23) + 18));
                i++;
            }
        }

        Collections.shuffle(result);

        return result;
    }

    public static void main(String[] args) {
        String LAST_NAME_URL = "https://hanyu.baidu.com/shici/detail?pid=0b2f26d4c0ddb3ee693fdb1137ee1b0d&from=kg0";
        String MALE_NAME_URL = "http://www.haoming8.cn/baobao/10881.html";
        String FEMALE_NAME_URL = "http://www.haoming8.cn/baobao/7641.html";

        // 爬取数据
        String lastNameStr = HttpUtil.get(LAST_NAME_URL);
        String maleNameStr = HttpUtil.get(MALE_NAME_URL);
        String femaleNameStr = HttpUtil.get(FEMALE_NAME_URL);

        // 处理数据
        List<String> lastNameTempList = ReUtil.findAll("(\\W{4})(，|。)", lastNameStr, 1);
        List<String> maleNameTempList = ReUtil.findAll("([\\u4E00-\\u9FA5]{2})(、|。)", maleNameStr, 1);
        List<String> femaleNameTempList = ReUtil.findAll("([\\u4E00-\\u9FA5]{2} ){4}[\\u4E00-\\u9FA5]{2}", femaleNameStr, 0);

        // 处理数据2
        ArrayList<Character> lastNameList = getLastNameData((ArrayList<String>) lastNameTempList);
        ArrayList<String> maleNameList = getMaleNameData((ArrayList<String>) maleNameTempList);
        ArrayList<String> femaleNameList = getFemaleNameData((ArrayList<String>) femaleNameTempList);

        // 姓氏和名字拼接;
        ArrayList<String> namelist = mockNameList(lastNameList, maleNameList, femaleNameList, 5, 5);

        // 写出数据
        FileUtil.writeLines(namelist, "name.txt", "UTF-8");
    }
}
