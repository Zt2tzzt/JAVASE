package com.kkcf.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.StringJoiner;

public class PokerGame {
    // 牌盒，里要放 54 张扑克牌
    static ArrayList<String> cardBoxList = new ArrayList<>(54);

    // 牌面，与自定义价值的映射关系
    static HashMap<String, Integer> pokerValMap = new HashMap<>();

    static {
        // 准备牌
        String[] color = {"♠", "♣", "♥", "♦"};
        String[] number = {"3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A", "2"};

        for (String c : color) {
            for (String n : number) {
                cardBoxList.add(c + n);
            }
        }

        cardBoxList.add(" red joker"); // 在前面加上空格，用于比较时截取牌面大小进行比较
        cardBoxList.add(" black joker"); // 在前面加上空格，用于比较时截取牌面大小进行比较

        System.out.println(cardBoxList);

        // 自定义牌面的价值
        pokerValMap.put("J", 11);
        pokerValMap.put("Q", 12);
        pokerValMap.put("K", 13);
        pokerValMap.put("A", 14);
        pokerValMap.put("2", 15);
        pokerValMap.put("black joker", 50);
        pokerValMap.put("red joker", 100);
    }

    public PokerGame() {
        // 洗牌
        Collections.shuffle(cardBoxList);

        // 发牌
        ArrayList<String> lordList = new ArrayList<>(3); // 底牌

        ArrayList<String> player1List = new ArrayList<>(); // 玩家1
        ArrayList<String> player2List = new ArrayList<>(); // 玩家2
        ArrayList<String> player3List = new ArrayList<>(); // 玩家3

        for (int i = 0; i < cardBoxList.size(); i++) {
            String poker = cardBoxList.get(i);

            if (i <= 2) {
                lordList.add(poker);
                continue;
            }

            if (i % 3 == 1) player1List.add(poker);
            else if (i % 3 == 2) player2List.add(poker);
            else player3List.add(poker);
        }

        // 排序
        sortPoker(lordList);
        sortPoker(player1List);
        sortPoker(player2List);
        sortPoker(player3List);

        // 看牌
        lookPoker("底牌", lordList);
        lookPoker("钢脑壳", player1List);
        lookPoker("大帅比", player2List);
        lookPoker("蛋筒", player3List);
    }

    /**
     * 此方法用于，看牌
     *
     * @param player    玩家名
     * @param pokerList 玩家手牌
     */
    public void lookPoker(String player, ArrayList<String> pokerList) {
        StringJoiner sj = new StringJoiner(", ", player + "：", "；");

        for (String poker : pokerList)
            sj.add(poker);

        System.out.println(sj);
    }

    /**
     * 此方法用于：排序集合
     *
     * @param list 手牌集合
     */
    public void sortPoker(ArrayList<String> list) {
        list.sort((o1, o2) -> {
            String color1 = o1.substring(0, 1);
            String num1Str = o1.substring(1);
            int val1 = pokerValMap.containsKey(num1Str) ? pokerValMap.get(num1Str) : Integer.parseInt(num1Str);

            String color2 = o2.substring(0, 1);
            String num2Str = o2.substring(1);
            int val2 = pokerValMap.containsKey(num2Str) ? pokerValMap.get(num2Str) : Integer.parseInt(num2Str);

            int result = val1 - val2;
            if (result == 0) result = color1.compareTo(color2);

            return result;
        });
    }
}