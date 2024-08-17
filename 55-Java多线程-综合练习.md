# Java 多线程之综合练习

## 一、练习一：卖电影票

一共有 1000 张电影票，可以在两个窗口领取，假设每次领取的时间为 3000 毫秒；

要求，请用多线程模拟卖票过程，并打印剩余点银票的数量。

MovieTicketSaleThread2 线程类。

demo-project/base-code/Day31/src/com/kkcf/test/MovieTicketSaleThread2.java

```java
package com.kkcf.test;

public class MovieTicketSaleThread2 extends Thread {
    public static int ticketCount = 0;

    @Override
    public void run() {
        while (true) {
            synchronized (MovieTicketSaleThread2.class) {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                if (++ticketCount > 1000) break;

                System.out.println(Thread.currentThread().getName() + "正在卖第" + (ticketCount) + "张票");
            }
        }
    }
}
```

测试类：

demo-project/base-code/Day31/src/com/kkcf/test/Test4.java

```java
package com.kkcf.test;

public class Test4 {
    public static void main(String[] args) {
        MovieTicketSaleThread2 t1 = new MovieTicketSaleThread2();
        MovieTicketSaleThread2 t2 = new MovieTicketSaleThread2();

        t1.setName("窗口1");
        t2.setName("窗口2");

        t1.start();
        t2.start();
    }
}
```

## 二、练习二：送礼物

有 100 份礼品，两个人同时发送，当剩下的礼品小于 10 份的时候，则不再送出。

利用多线程模拟该过程，并将线程的名字和礼物的剩余数量打印出来。

SendGiftsThread 线程类：

demo-project/base-code/Day31/src/com/kkcf/test/SendGiftsThread.java

```java
package com.kkcf.test;

public class SendGiftsThread extends Thread {
    public static int giftCount = 100;

    public SendGiftsThread() {
    }

    public SendGiftsThread(String name) {
        super(name);
    }

    @Override
    public void run() {
        while (true) {
            synchronized (SendGiftsThread.class) {

                if (--giftCount < 9) break;

                System.out.println(Thread.currentThread().getName() + "送出了一份礼物，还剩" + giftCount + "个礼物");
            }
        }
    }
}
```

测试类

demo-project/base-code/Day31/src/com/kkcf/test/Test05.java

```java
package com.kkcf.test;

public class Test05 {
    public static void main(String[] args) {
        SendGiftsThread t1 = new SendGiftsThread("同学A");
        SendGiftsThread t2 = new SendGiftsThread("同学B");

        t1.start();
        t2.start();
    }
}
```

## 三、练习三：打印奇数数字

同时开启两个线程，共同获取 1-100 之间的所有数字。

要求：将输出所有的奇数。

OddThread 线程类：

demo-project/base-code/Day31/src/com/kkcf/test/OddThread.java

```java
package com.kkcf.test;

public class OddThread extends Thread {
    public static int num = 100;

    public OddThread() {
    }

    public OddThread(String name) {
        super(name);
    }

    @Override
    public void run() {
        while (true) {
            synchronized (OddThread.class) {
                if (--num < 0) break;

                if (num % 2 == 1)
                    System.out.println(Thread.currentThread().getName() + "获取到奇数：" + num);
            }
        }
    }
}
```

测试类：

demo-project/base-code/Day31/src/com/kkcf/test/Test06.java、

```java
package com.kkcf.test;

public class Test06 {
    public static void main(String[] args) {
        OddThread t1 = new OddThread("线程1");
        OddThread t2 = new OddThread("线程2");

        t1.start();
        t2.start();
    }
}
```

## 四、练习四：抢红包

抢红包也用到了多线程，假设 100 块，分成了 3 个红包，现在又 5 个人去枪；

其中，红包是共享数据。5 个人是 5 条数据。打印结果如下：

- xxx 抢到了 xxx 元
- xxx 抢到了 xxx 元
- xxx 抢到了 xxx 元
- xxx 没有抢到，
- xxx 没有抢到，

## 五、练习五：抽奖箱抽奖

有一个抽奖池，其中存放了奖励的金额 {10, 5, 20, 50, 100, 200, 500, 800, 2, 80, 300, 700};

创建两个抽奖箱（线程）设置线程名称分别为”抽奖箱1”、“抽奖箱2”，随机从抽奖池中获取奖项元素并打印在控制台上，格式如下：

- 抽奖箱 1 又产生了一个 10 元大奖
- ……
