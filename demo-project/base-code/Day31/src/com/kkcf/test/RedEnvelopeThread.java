package com.kkcf.test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

public class RedEnvelopeThread extends Thread {
    // 最小的中奖金额
    static final BigDecimal MIN = BigDecimal.valueOf(0.01);
    // 红包金额
    static BigDecimal money = BigDecimal.valueOf(100);
    // 红包个数
    static int count = 3;

    @Override
    public void run() {
        synchronized (RedEnvelopeThread.class) {
            Random r = new Random();

            if (count == 0) {
                System.out.println(Thread.currentThread().getName() + "没有抢到红包。");
                return;
            }

            // 判断共享数据，是否到末尾
            BigDecimal price;

            if (count == 1) {
                // 最后一个红包，剩余所有的钱，都是中奖金额
                price = money;
            } else {
                BigDecimal bounds = money.subtract(MIN.multiply(BigDecimal.valueOf((count - 1))));
                price = BigDecimal.valueOf(r.nextDouble(bounds.doubleValue()));

                // 抽取到红包的金额，不能小于最小值
                if (price.compareTo(MIN) < 0) price = MIN;
                price = price.setScale(2, RoundingMode.HALF_UP);
            }

            // 从 money 中，去掉当前中奖金额
            money = money.subtract(price);

            // 红包的个数 - 1
            count--;

            System.out.println(Thread.currentThread().getName() + "抢到了" + price + "元");
        }

    }
}
