package com.kkcf.test;

import java.util.ArrayList;
import java.util.Collections;

public class LotteryThread extends Thread {
    public ArrayList<Integer> list;

    public LotteryThread(ArrayList<Integer> list) {
        this.list = list;
    }

    @Override
    public void run() {
        ArrayList<Integer> boxList = new ArrayList<>();

        while (true) {
            synchronized (LotteryThread.class) {
                String name = super.getName();

                if (list.isEmpty()) {
                    //抽奖箱 1 总共产生了 6 个奖项，分别为：10,20,100,500,2,300 最高奖项为 300 元，总计额为 932 元
                    System.out.println(name + "共产生了" + boxList.size() + "个奖项，分别为" + boxList + "，其中最高奖项为" + Collections.max(boxList) + "元，总计额为：" + boxList.stream().reduce(0, Integer::sum) + " 元");
                    return;
                }

                Collections.shuffle(list);
                Integer price = list.remove(0);

                boxList.add(price);
                //System.out.println(name + "又产生了一个大 " + price + "元大奖");
            }

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
