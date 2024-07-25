# Java 常用 API 之 JDK7 前时间类

早期的世界标准时间，称为格林威治时间（GreenWich Mean Time）简称 GMT。这种方式根据地球自转计算时间，有误差。

现在世界标准时间，已被原子钟时间（简称 UTC）替代。

> 原子钟时间，是利用铯原子的震动频率，计算出来的时间。
>
> 铯原子每秒震动 9,192,631,770 次，2000 万年才会有 1s 误差。

中国（东八区）标准时间，是在世界标准时间的基础上 +8 小时。

时间单位换算：

- 1 秒 = 1000 毫秒
- 1 毫秒 = 1000 微秒
- 1 微妙 = 1000 纳秒

## 一、Date 类

java.util.Date 类，是一个 JDK 提供的 javaBean 类，用来描述时间，精确到毫秒。

Date 类常用的构造方法如下：

- `public Date()`：从运行程序的此时此刻到时间原点（1970 年 1 月 1 日）经历的毫秒值，转换成 Date 对象（精确到毫秒）。
- `public Date(long date)`：将指定参数的毫秒值，转换成 Date 对象。

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

### 1.Date 类构造方法

案例理解：使用 Date 类，创建对象，表示当前的时间和指定的时间。

demo-project/base-code/Day20/src/com/kkcf/date/DateDemo01.java

```java
package com.kkcf.date;

import java.util.Date;

public class DateDemo01 {
    public static void main(String[] args) {
        // 当前时间
        Date d1 = new Date();

        System.out.println(d1); // Wed Jul 17 15:46:04 CST 2024

        // 指定时间
        Date d2 = new Date(0L);

        System.out.println(d2); // Thu Jan 01 08:00:00 CST 1970
    }
}
```

### 2.getTime、setTime 成员方法

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

- Date 的构造方法，和 `setTime` 方法，都应传入 long 类型的数值，表示指定毫秒数。

案例理解：获取时间原点开始，一年之后的时间。

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

- Date 构造方法，接收的是 long 类型的数值，如果参数为 int 类型，那么会进行隐式转换。

## 二、SimpleDateFormat 类

SimpleDateFormat 类，主要有两个作用：

- 作用一：用于格式化时间，即按照固定的格式，展示时间。
- 作用二：解析把字符串表示的时间，转为 Date 对象。

SimpleDateFormat 类继承自 DateFormat 抽象类。

### 1.SimpleDateFormat 类构造方法

SimpleDateFormat 类，常用的构造方法有：

| 构造方法                                  | 说明                                             |
| ----------------------------------------- | ------------------------------------------------ |
| `public SimpleDateFormat`                 | 构造一个 SimpleDateFormat 对象，使用默认格式     |
| `public SimpleDateFormat(String pattern)` | 构造一个 SimpleDateFormat 对象，使用指定的格式。 |

常用的格式规则有：

| 标识字母（区分大小写） | 含义         |
| ---------------------- | :----------- |
| y                      | 年           |
| M                      | 月           |
| d                      | 日           |
| H                      | 时           |
| m                      | 分           |
| s（小写）              | 秒           |
| E                      | 星期几       |
| EE                     | 中文星期几   |
| a                      | 上午还是下午 |

SimpleDateFormat 类，常用的成员方法有：

| 方法名                                  | 说明                          |
| --------------------------------------- | ----------------------------- |
| `public final String format(Date date)` | 格式化（Date 对象 -> 字符串） |
| `public Date parse(String source)`      | 解析（字符串 -> Date 对象）   |

### 2.format 成员方法

案例理解：利用空参构造，构造一个 SimpleDateFormat 对象，并使用默认格式，格式化时间原点的 Date 对象。

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

### 3.parse 成员方法

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

## 三、Calendar 类

Calendar 类，代表了系统当前时间的日历对象，可以单独修改，获取时间中的年、月、日。

Calendar 类，是一个抽象类，不能直接创建对象。应使用它的静态方法 `getInstance` 获取它的实例对象。

### 1.getInstance 静态方法

Calendar 类常用的静态方法有：

| 方法名                                 | 说明                     |
| -------------------------------------- | ------------------------ |
| `public static Calendar getInstance()` | 获取当前时间的日历对象。 |

使用 `getInstance` 方法，获取当前时间的日历对象：

demo-project/base-code/Day20/src/com/kkcf/calendar/CalendarDemo01.java

```java
package com.kkcf.calendar;

import java.util.Calendar;

public class CalendarDemo01 {
    public static void main(String[] args) {
        Calendar c = Calendar.getInstance();

        System.out.println(c);
    }
}
```

- 该方法，会根据系统的时区，来获取日历对象，默认表示当前时间。
- c 对象，会把时间中的纪元、年、月、日、时、分、秒、星期……都放到一个数组当中
  - 其中月的范围是 0-11。0 代表 1 月，11 代表 12 月（反人类）；
  - 其中星期的范围是1-7。1 代表星期日，7 代表星期六。

Calendar 类中常用的成员方法有：

| 方法名                                     | 说明                                      |
| ------------------------------------------ | ----------------------------------------- |
| `public final Date getTime()`              | 获取日期对象                              |
| `public final void setTime(Date date)`     | 给日历设置日期对象                        |
| `public long getTimeInMillis()`            | 获取时间毫秒值                            |
| `public void setTimeInMillis(long millis)` | 给日历设置时间毫秒值                      |
| `public int get(int field)`                | 获取日历中某个字段信息                    |
| `public void set(int field, int value)`    | 修改日历的某个字段信息                    |
| `public void add(int field, int amount)`   | 为某个日历对象的某个字段增加/减少指定的值 |

### 2.setTime 成员方法

案例理解，`public final void setTime(Date date)` 方法的使用，为日历对象，设置一个日期对象。

demo-project/base-code/Day20/src/com/kkcf/calendar/CalendarDemo01.java

```java
package com.kkcf.calendar;

import java.util.Calendar;
import java.util.Date;

public class CalendarDemo01 {
    public static void main(String[] args) {
        Calendar c = Calendar.getInstance();

        c.setTime(new Date(0L));

        System.out.println(c);
    }
}
```

### 3.get、set 成员方法

案例理解：`public int get(int field)` 方法的使用，获取日历对象中的年、月、日、星期。

demo-project/base-code/Day20/src/com/kkcf/calendar/CalendarDemo01.java

```java
package com.kkcf.calendar;

import java.util.Calendar;
import java.util.Date;

public class CalendarDemo01 {
    public static void main(String[] args) {
        Calendar c = Calendar.getInstance();

        c.setTime(new Date(0L));

        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH) + 1;
        int day = c.get(Calendar.DAY_OF_MONTH);
        int weekDay = c.get(Calendar.DAY_OF_WEEK);

        System.out.println(year + "年" + month + "月" + day + "日" + "星期" + getWeekDay(weekDay));
    }

    public static String getWeekDay(int weekDay) {
        String[] arr ={"", "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};

        return arr[weekDay];
    }
}
```

- 封装一个方法 `getWeekDay`，使用查表法，获取对应的星期数。

> 查表法中的“表”，指的是容器，上方案例中的容器就是数组

案例理解：`public void set(int field, int value)` 方法的使用，设置日历对象中的年、月

demo-project/base-code/Day20/src/com/kkcf/calendar/CalendarDemo01.java

```java
package com.kkcf.calendar;

import java.util.Calendar;
import java.util.Date;

public class CalendarDemo01 {
    public static void main(String[] args) {
        Calendar c = Calendar.getInstance();

        c.setTime(new Date(0L));

        c.set(Calendar.YEAR, 2000);
        c.set(Calendar.MONTH, 12); // 12 表示 13 月，然而实际没有 13 月，日期会自动计算得到明年 1 月

        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH) + 1;
        int day = c.get(Calendar.DAY_OF_MONTH);
        int weekDay = c.get(Calendar.DAY_OF_WEEK);

        System.out.println(year + "年" + month + "月" + day + "日" + getWeekDay(weekDay)); // 2001年1月1日星期一
    }

    public static String getWeekDay(int weekDay) {
        String[] arr ={"", "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};

        return arr[weekDay];
    }
}
```

- `set` 方法，设置大于实际日历的数字，会按照实际日历自动计算，往后递增。

使用 `get`、`set` 方法，获取、设置日历中的纪元、年、月、日、时、分、秒、星期……,，所传的参数，有以下对应关系：

- `0`：纪元；对应 Calendar 类中的常量 `Calendar.ERA`
- `1`：年；对应 Calendar 类中的常量 `Calendar.YEAR`
- `2`：月；对应 Calendar 类中的常量 `Calendar.MONTH`
- `3`：一年中的第几周；对应 Calendar 类中的常量 `Calendar.WEEK_OF_YEAR`
- `4`：一个月中的第几周；对应 Calendar 类中的常量 `Calendar.WEEK_OF_MONTH`
- `5`：一个月中的第几天（日期）；对应 Calendar 类中的常量 `Calendar.DATE` / `Calendar.DAY_OF_MONTH`
- `6`：一年中的第几天；对应 Calendar 类中的常量 `Calendar.DAY_OF_YEAR`
- `7`：一星期中的第几天；对应 Calendar 类中的常量 `Calendar.DAY_OF_WEEK`

### 4.add 成员方法

案例理解：`public void add(int field, int amount)` 方法的使用，设置日历对象，往后加一个月，往前减一个月

demo-project/base-code/Day20/src/com/kkcf/calendar/CalendarDemo01.java

```java
package com.kkcf.calendar;

import java.util.Calendar;
import java.util.Date;

public class CalendarDemo01 {
    public static void main(String[] args) {
        Calendar c = Calendar.getInstance();

        c.setTime(new Date(0L));

        c.set(Calendar.YEAR, 2000);
        c.set(Calendar.MONTH, 12); // 12 表示 13 月，然后实际没有 13 月，日期会自动计算

        c.add(Calendar.MONTH, 1); // 往后加一个月
        c.add(Calendar.MONTH, -1); // 往前减一个月
    }
}
```
