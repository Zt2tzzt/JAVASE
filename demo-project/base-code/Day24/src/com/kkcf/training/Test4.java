package com.kkcf.training;

import java.util.*;

public class Test4 {
    public static void main(String[] args) {
        // 城市集合
        ArrayList<String> jiangsuCityList = new ArrayList<>();
        Collections.addAll(jiangsuCityList, "南京市", "扬州市", "苏州市", "无锡市", "常州市");

        ArrayList<String> hubeiCityList = new ArrayList<>();
        Collections.addAll(hubeiCityList, "武汉市", "孝感市", "十堰市", "宜昌市", "鄂州市");

        ArrayList<String> hebeiCityList = new ArrayList<>();
        Collections.addAll(hebeiCityList, "石家庄市", "唐山市", "邢台市", "保定市", "张家口市");

        // 省市集合
        HashMap<String, ArrayList<String>> map = new HashMap<>();

        map.put("江苏省", jiangsuCityList);
        map.put("湖北省", hubeiCityList);
        map.put("河北省", hebeiCityList);

        // 遍历打印
        for (Map.Entry<String, ArrayList<String>> entries : map.entrySet()) {
            String province = entries.getKey();
            ArrayList<String> cityList = entries.getValue();

            StringJoiner sj = new StringJoiner(", ", province + " = ", "");

            for (String city : cityList)
                sj.add(city);

            System.out.println(sj);
        }
    }
}
