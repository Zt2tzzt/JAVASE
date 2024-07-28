package com.kkcf.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Test2 {
    public static void main(String[] args) {
        // 景点
        String[] attractions = {"A", "B", "C", "D"};

        // 投票
        ArrayList<String> votes = new ArrayList<>();

        Random r = new Random();

        for (int i = 0; i < 80; i++) {
            int index = r.nextInt(attractions.length);
            String attraction = attractions[index];
            votes.add(attraction);
        }

        System.out.println(votes);

        // 统计投票
        HashMap<String, Integer> statistics = new HashMap<>();

        for (String vote : votes) {
            if (statistics.containsKey(vote)) {
                Integer count = statistics.get(vote);
                statistics.put(vote, count + 1);
            } else {
                statistics.put(vote, 1);
            }
        }

        System.out.println(statistics);

        // 计算票数最多的景点
        int max = 0;
        String maxAttraction = "";

        for (String attraction : attractions) {
            if (statistics.containsKey(attraction)) {
                int count = statistics.get(attraction);

                if (count > max) {
                    max = count;
                    maxAttraction = attraction;
                }
            }
        }

        System.out.println("票数最多的景点是：" + maxAttraction + "，票数为：" + max);
    }
}
