# Java 常见算法

## 一、Java 查找算法

常见的查找算法有：基本查找（顺序查找）、二分查找（折半查找）、分块查找

### 1.Java 基本查找

基本查找，又称为顺序查找，用于在一系列数据中，查找某一数据是否存在。

一般是定义一个容器，将数据放入容器当中，再从 0 索引开始，挨个往后查找。

案例理解：在一系列数字 {131, 127, 147, 81, 103, 23, 7, 79} 中，查找数字 81 是否存在，并返回它的索引值，考虑数据重复的情况。

demo-project/base-code/Day21/src/com/kkcf/search/BasicSearchDemo01.java

```java
package com.kkcf.search;

import java.util.ArrayList;

public class BasicSearchDemo01 {
    public static void main(String[] args) {
        int[] arr = {131, 127, 147, 81, 103, 23, 7, 79, 81};
        int i = 81;

        ArrayList<Integer> indexs = findIndex(arr, i);
        System.out.println(indexs);
    }

    /**
     * 此方法用于，普通查找
     * @param arr 数据列表
     * @param i 查找的数据
     * @return 查找数据的索引
     */
    private static ArrayList<Integer> findIndex(int[] arr, int i) {
        ArrayList<Integer> list = new ArrayList();

        for (int j = 0; j < arr.length; j++)
            if (arr[j] == i)
                list.add(j);

        return list;
    }
}
```

### 2.Java 二分查找

二分查找，前提条件是数组中的数据，必须是有序的。

二分查找，核心思路是每次排除一半的查找范围。

二分查找算法步骤实现：

1. 定义两个索引值 min 和 max，表示当前要查找的范围；
2. 再定义一个索引值 mid，表示 min、max 中间的元素索引；
3. 如果要查找的元素，在 mid 的左边，缩小范围时 min 不变，max 等于 mid - 1；
4. 反之，max 不变，min 等于 mid + 1。
5. 当 min > max 时，如果还未找到，则表示要查询的元素不存在，结束查询。

了解：插值查找、斐波那契查找、树表查找、哈希查找。

## 二、Java 排序算法

冒泡排序、选择排序、插入排序、快速排序

## 三、Java 字符串匹配算法

基本查找、KMP 算法。

正则表达式中，用的就是 KMP 算法

