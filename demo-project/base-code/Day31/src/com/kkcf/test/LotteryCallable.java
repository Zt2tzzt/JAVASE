package com.kkcf.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.Callable;

public class LotteryCallable implements Callable<Integer> {
    public ArrayList<Integer> list;

    public LotteryCallable(ArrayList<Integer> list) {
        this.list = list;
    }

    @Override
    public Integer call() throws Exception {
        ArrayList<Integer> boxList = new ArrayList<>();

        while (true) {
            synchronized (LotteryThread.class) {
                String name = Thread.currentThread().getName();

                if (list.isEmpty()) {
                    Integer max = Collections.max(boxList);
                    //抽奖箱 1 总共产生了 6 个奖项，分别为：10,20,100,500,2,300 最高奖项为 300 元，总计额为 932 元
                    System.out.println(name + "共产生了" + boxList.size() + "个奖项，分别为" + boxList + "，其中最高奖项为" + max + "元，总计额为：" + boxList.stream().reduce(0, Integer::sum) + " 元");

                    return boxList.isEmpty() ? null : max;
                }

                Collections.shuffle(list);
                Integer price = list.remove(0);

                boxList.add(price);
            }

            Thread.sleep(10);
        }

    }
}
