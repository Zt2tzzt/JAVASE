# Java 常用 API 之时间类

早期世界标准时间，称为格林威治时间（GreenWich Mean Time）简称 GMT。这种方式根据地球自转计算时间，有误差。

目前世界标准时间，已被原子钟时间（简称 UTC）替代。

> 原子钟时间，是利用铯原子的震动频率，计算出来的时间。
>
> 铯原子每秒震动 9,192,631,770 次，2000 万年才会有 1s 误差。

中国（东八区）标准时间，是在世界标准时间的基础山 +8 小时。

时间单位换算：

- 1秒 = 1000毫秒
- 1毫秒 = 1000微秒
- 1微妙=1000纳秒

## 一、JDK7 前时间相关类

### 1.Date 类

java.util.Date 类，是一个 JDK 已经写好的 javaBean 类，用来描述时间，精确到毫秒。

Date 类常用的构造方法如下：

- `public Date()`：从运行程序的此时此刻到时间原点经历的毫秒值,转换成Date对象，分配Date对象并初始化此对象，以表示分配它的时间（精确到毫秒）。
- `public Date(long date)`：将指定参数的毫秒值date,转换成Date对象，分配Date对象并初始化此对象，以表示自从标准基准时间（称为“历元（epoch）”，即1970年1月1日00:00:00 GMT）以来的指定毫秒数。

Date 类原码分析：

```java
public class Date {
    private transient long fastTime; // 用于表示当前时间的毫秒值
  
    // 空参构造
    public Date() {
        this(System.currentTimeMillis());
    }
  
    // 带参构造
    public Date(long date) {
        fastTime = date;
    }
}
```

案例理解：使用 Date 类，创建对象，表示当前的时间；使用 Date 类，创建对象，表示指定的时间

demo-project/base-code/Day20/src/com/kkcf/date/DateDemo01.java

```java
package com.kkcf.date;

import java.util.Date;

public class DateDemo01 {
    public static void main(String[] args) {
        Date d1 = new Date();
        
        System.out.println(d1); // Wed Jul 17 15:46:04 CST 2024

        Date d2 = new Date(0L);

        System.out.println(d2); // Thu Jan 01 08:00:00 CST 1970
    }
}
```

案例理解：使用 Date 实例对象的 `getTime`、`setTime` 方法，获取和设置实例对象的时间。

demo-project/base-code/Day20/src/com/kkcf/date/DateDemo01.java

```java
package com.kkcf.date;

import java.util.Date;

public class DateDemo01 {
    public static void main(String[] args) {
        Date d2 = new Date(0L);

        d2.setTime(1000L);

        System.out.println(d2); // Thu Jan 01 08:00:01 CST 1970

        long d2Time = d2.getTime();

        System.out.println(d2Time); // 1000
    }
}
```

- Date 的构造方法，和 `setTime` 方法，都应传入 long 类型的数值。

案例理解：打印时间原点开始，一年之后的时间。

demo-project/base-code/Day20/src/com/kkcf/date/DateTest01.java

```java
package com.kkcf.date;

import java.util.Date;

public class DateTest01 {
    public static void main(String[] args) {
        Date d1 = new Date(0L);

        long time = d1.getTime();

        time = time + 1000L * 60 * 60 * 24 * 365;

        d1.setTime(time);

        System.out.println(d1); // Fri Jan 01 08:00:00 CST 1971
    }
}
```

- `1000L` 表示使用 long 类型的数字参与计算，得到的结果会隐式转换位 long 类型。

案例理解：定义任意两个 Date 对象，比较一下哪个时间在前，哪个时间在后。

demo-project/base-code/Day20/src/com/kkcf/date/DateTest01.java

```java
package com.kkcf.date;

import java.util.Date;
import java.util.Random;

public class DateTest01 {
    public static void main(String[] args) {
        // 随机获取两个时间对象
        Random r = new Random();

        Date d1 = new Date(Math.abs(r.nextInt()));
        Date d2 = new Date(Math.abs(r.nextInt()));

        System.out.println(d1 + " " + d2);

        // 比较两个时间
        long time1 = d1.getTime();
        long time2 = d2.getTime();

        System.out.println(time1 + " " + time2);

        System.out.println(time1 > time2 ? "第一个时间，大于第二个时间"
                : time1 < time2 ? "第二个时间，大于第一个时间"
                : "第一个时间，等于第二个时间");
    }
}

```

- `new Date()` 构造方法，接收的是 long 类型的数值，如果参数为 int 类型，那么会进行隐式转换。

### 2.SimpleDateFormat 类

这个类，主要有两个作用：

- 作用一：用于格式化时间，你找固定的格式，展示时间。
- 作用二：解析把字符串表示的时间，转为 Date 对象。

DateFormat 是抽象类，不能直接使用，所以需要常用的子类`java.text.SimpleDateFormat`。

#### 1.SimpleDateFormat 类构造方法

SimpleDateFormat 类，常用的构造方法有：

| 构造方法                                  | 说明                                            |
| ----------------------------------------- | ----------------------------------------------- |
| `public SimpleDateFormat`                 | 构造一个SimpleDateFormat 对象，使用默认格式     |
| `public SimpleDateFormat(String pattern)` | 构造一个SimpleDateFormat 对象，使用指定的格式。 |

常用的格式规则有：

| 标识字母（区分大小写） | 含义 |
| ---------------------- | ---- |
| y（小写）              | 年   |
| M（大写）              | 月   |
| d（小写）              | 日   |
| H（大写）              | 时   |
| m（小写）              | 分   |
| s（小写）              | 秒   |

#### 2.SimpleDateFormat 类成员方法

SimpleDateFormat 类，常用的成员方法有：

| 方法名                                  | 说明                          |
| --------------------------------------- | ----------------------------- |
| `public final String format(Date date)` | 格式化（Date 对象 -> 字符串） |
| `public Date parse(String source)`      | 解析（字符串 -> Date 对象）   |

案例理解：利用空参构造，构造一个SimpleDateFormat 对象。

demo-project/base-code/Day20/src/com/kkcf/date/SimpleDateFormatDemo01.java

```java
package com.kkcf.date;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SimpleDateFormatDemo01 {
    public static void main(String[] args) {
        SimpleDateFormat sdf1 = new SimpleDateFormat();

        Date d1 = new Date(0L);

        String format = sdf1.format(d1);

        System.out.println(format); // 1970/1/1 上午8:00
    }
}
```

案例理解：利用带参构造方法，构造一个 SimpleDateFormat 对象，指定格式化的模式。

demo-project/base-code/Day20/src/com/kkcf/date/SimpleDateFormatDemo01.java

```java
package com.kkcf.date;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SimpleDateFormatDemo01 {
    public static void main(String[] args) {
        SimpleDateFormat sdf1 = new SimpleDateFormat();

        Date d1 = new Date(0L);

        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format2 = sdf2.format(d1); // 1970-01-01 08:00:00
        System.out.println(format2);

        SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒 EE");
        String format3 = sdf3.format(d1);
        System.out.println(format3); // 1970年01月01日 08时00分00秒 周四
    }
}
```

案例理解：将一个字符串表示的时间，解析成 Date 对象。

demo-project/base-code/Day20/src/com/kkcf/date/SimpleDateFormatDemo02.java

```java
package com.kkcf.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SimpleDateFormatDemo02 {
    public static void main(String[] args) throws ParseException {
        String dateStr = "2023-11-11 11:11:11";

        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d1 = sdf1.parse(dateStr);

        System.out.println(d1); // Sat Nov 11 11:11:11 CST 2023
    }
}
```

### 3.Calendar 类

