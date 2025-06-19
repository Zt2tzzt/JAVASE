package com.kkcf.test;

import java.io.*;
import java.util.Map;
import java.util.TreeMap;

public class Test1 {
    public static void main(String[] args) throws IOException {
        // 读取
        BufferedReader br = new BufferedReader(new FileReader("Day29/src/com/kkcf/test/csb.txt"));

        //ArrayList<String> list = new ArrayList<>();
        TreeMap<Integer, String> tm = new TreeMap<>();
        String line;
        while ((line = br.readLine()) != null) {
            String[] strArr = line.split("\\.");
            tm.put(Integer.parseInt(strArr[0]), strArr[1]);
        }

        br.close();

        // 排序
        /*list.sort((Comparator<String>) (o1, o2) -> {
            String numStr1 = o1.split("\\.")[0];
            String numStr2 = o2.split("\\.")[0];

            return Integer.parseInt(numStr1) - Integer.parseInt(numStr2);
        });*/

        // 写入
        BufferedWriter bw = new BufferedWriter(new FileWriter("Day29/src/com/kkcf/test/result.txt"));
        for (Map.Entry<Integer, String> entry : tm.entrySet()) {
            bw.write(entry.getKey() + "." + entry.getValue());
            bw.newLine();
        }

        bw.close();
    }
}
