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
