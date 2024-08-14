# Java IO 综合练习

## 一、制造假数据

制造假数据，也是开发中的一个能力。在各个网站上爬取数据，是其中一个办法。

下面是三个用于爬取数据的网站：

- [获取姓氏](https://hanyu.baidu.com/shici/detail?pid=0b2f26d4c0ddb3ee693fdb1137ee1b0d&from=kg0)

- [获取男生名字](http://www.haoming8.cn/baobao/10881.html)

- [获取女生名字](http://www.haoming8.cn/baobao/7641.html)

demo-project/base-code/Day30/src/com/kkcf/iopractice/Test1.java

```java
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
        ArrayList<String> namelist = mockNameList(lastNameList, maleNameList, femaleNameList, 70, 70);

        // 写出数据
        BufferedWriter bw = new BufferedWriter(new FileWriter("Day30/src/com/kkcf/iopractice/name.txt"));

        for (String s : namelist) {
            bw.write(s);
            bw.newLine();
        }

        bw.close();
    }
}
```

- 正则表达式匹配器 `matcher.group(index)`，`index` 为 `0`，默认获取匹配到的所有；

### 1.Hutool 包中的爬取工具

利用 Hutool 包中的工具类，进行爬取，详见[示例](https://www.hutool.cn/docs/#/http/%E6%A1%88%E4%BE%8B1-%E7%88%AC%E5%8F%96%E5%BC%80%E6%BA%90%E4%B8%AD%E5%9B%BD%E7%9A%84%E5%BC%80%E6%BA%90%E8%B5%84%E8%AE%AF)

```java
//请求列表页
String listContent = HttpUtil.get("https://www.oschina.net/action/ajax/get_more_news_list?newsType=&p=2");
//使用正则获取所有标题
List<String> titles = ReUtil.findAll("<span class=\"text-ellipsis\">(.*?)</span>", listContent, 1);
for (String title : titles) {
    //打印标题
    Console.log(title);
}
```

重构上方的代码：

```java
package com.kkcf.iopractice;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.ReUtil;
import cn.hutool.http.HttpUtil;

import java.util.*;

public class Test2 {
    // ……

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
        // ……

        // 姓氏和名字拼接;
        // ……

        // 写出数据
        FileUtil.writeLines(namelist, "name.txt", "UTF-8");
    }
}

```

- 细节 1：使用 Hutool 包，指定的相对路径，是相对 .class 编译文件来说的

## 二、随机点名器

有一个文本文件中，存储了班级里同学的信息，每一个信息占一行；

格式为：张三-男-23

要求，每次被点到的学生，再次被点到的概率，在原先的基础上，降低一半；

举例：80 个学生，点名 5 次，每次都点到小A，概率变化情况如下：

- 第一次每人概率：1.25%
- 第二次小A概率：0.625%，其它人概率：1.2579%
- 第三次小A概率：0.3125%，其它人概率：1.261867%
- ……

思路：带权重的随机。

- 为每一个学生，设置一个权重，它的权重占比为：个人权重 / 总权重。
- 假设有 10 个学生，那么每个学生的权重占比就是 0.1；
- 在数轴上，可以把 0.0 - 1.0 分成 10 等份，每一个学生占据其中的一份；

![随机数的权重占比思想](NodeAssets/带权重的随机占比思想.jpg)

创建一个 JavaBean 类 Student，里面有属性：姓名、性别、年龄、权重；
