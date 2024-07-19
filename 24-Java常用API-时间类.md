# Java 常用 API 之时间类

早期的世界标准时间，称为格林威治时间（GreenWich Mean Time）简称 GMT。这种方式根据地球自转计算时间，有误差。

现在世界标准时间，已被原子钟时间（简称 UTC）替代。

> 原子钟时间，是利用铯原子的震动频率，计算出来的时间。
>
> 铯原子每秒震动 9,192,631,770 次，2000 万年才会有 1s 误差。

中国（东八区）标准时间，是在世界标准时间的基础上 +8 小时。

时间单位换算：

- 1秒 = 1000毫秒
- 1毫秒 = 1000微秒
- 1微妙 = 1000纳秒

## 一、JDK7 前时间相关类

### 1.Date 类

java.util.Date 类，是一个 JDK 已经写好的 javaBean 类，用来描述时间，精确到毫秒。

Date 类常用的构造方法如下：

- `public Date()`：从运行程序的此时此刻到时间原点（1970年1月1日）经历的毫秒值，转换成 Date 对象（精确到毫秒）。
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

案例理解：使用 Date 类，创建对象，表示当前的时间和指定的时间。

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

### 2.SimpleDateFormat 类

SimpleDateFormat 类，主要有两个作用：

- 作用一：用于格式化时间，按照固定的格式，展示时间。
- 作用二：解析把字符串表示的时间，转为 Date 对象。

SimpleDateFormat 类继承自 DateFormat 抽象类。

#### 1.SimpleDateFormat 类构造方法

SimpleDateFormat 类，常用的构造方法有：

| 构造方法                                  | 说明                                             |
| ----------------------------------------- | ------------------------------------------------ |
| `public SimpleDateFormat`                 | 构造一个 SimpleDateFormat 对象，使用默认格式     |
| `public SimpleDateFormat(String pattern)` | 构造一个 SimpleDateFormat 对象，使用指定的格式。 |

常用的格式规则有：

| 标识字母（区分大小写） | 含义       |
| ---------------------- | ---------- |
| y（小写）              | 年         |
| M（大写）              | 月         |
| d（小写）              | 日         |
| H（大写）              | 时         |
| m（小写）              | 分         |
| s（小写）              | 秒         |
| E（大写）              | 星期几     |
| EE（大写）             | 中文星期几 |

#### 2.SimpleDateFormat 类成员方法

SimpleDateFormat 类，常用的成员方法有：

| 方法名                                  | 说明                          |
| --------------------------------------- | ----------------------------- |
| `public final String format(Date date)` | 格式化（Date 对象 -> 字符串） |
| `public Date parse(String source)`      | 解析（字符串 -> Date 对象）   |

案例理解：利用空参构造，构造一个SimpleDateFormat 对象，并使用默认格式，格式化时间原点的 Date 对象。

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

Calendar 类，代表了系统当前时间的日历对象，可以单独修改，获取时间中的年、月、日。

Calendar 类，是一个抽象类，不能直接创建对象。应使用它的静态方法 getInstance 获取它的实例对象。

#### 1.Calendar 类构造方法

Calendar 类常用的构造方法有：

| 方法名                                 | 说明                     |
| -------------------------------------- | ------------------------ |
| `public static Calendar getInstance()` | 获取当前时间的日历对象。 |

使用 `getInstance` 方法，获取一个日历对象：

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
  - 其中月的范围是 0-11。0 代表 1 月；
  - 其中星期的范围是1-7。1 代表星期日，7 代表星期六。

#### 2.Calendar 类成员方法

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

案例理解，`setTime` 方法的使用，为日历对象，设置一个日期对象。

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

案例理解：`get` 方法的使用，获取日历对象中的年、月、日、星期。

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

案例理解：`set` 方法的使用，设置日历对象中的年、月

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

- 0：纪元；对应 Calendar 类中的常量 `Calendar.ERA`
- 1：年；对应 Calendar 类中的常量 `Calendar.YEAR`
- 2：月；对应 Calendar 类中的常量 `Calendar.MONTH`
- 3：一年中的第几周；对应 Calendar 类中的常量 `Calendar.WEEK_OF_YEAR`
- 4：一个月中的第几周；对应 Calendar 类中的常量 `Calendar.WEEK_OF_MONTH`
- 5：一个月中的第几天（日期）；对应 Calendar 类中的常量 `Calendar.DATE` / `Calendar.DAY_OF_MONTH`
- 6：一年中的第几天；对应 Calendar 类中的常量 `Calendar.DAY_OF_YEAR`
- 7：一星期中的第几天；对应 Calendar 类中的常量 `Calendar.DAY_OF_WEEK`

案例理解：`add` 方法的使用，设置日历对象，往后加一个月，往前减一个月

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

## 二、JDK8 新增的时间类

JDK7 和 JDK8 中的时间相关类，有如下的不同：

代码层面：

- JDK7 的时间类，日期对象，在比较、计算时，要转为毫秒值，非常麻烦；
- JDK8 的时间类，判断的方法、计算时间间隔的方法都非常简单。

安全层面：

- JDK7 的时间类，在多线程环境下，会有数据安全的问题。
- JDK8 的时间类，时间日期对象都是不可变的，解决了多线程的安全性问题。

JDK8 中常用的时间类有：

日期类（类似于 JDK7 的 Date 类）

- ZoneId 类，表示时区；
- Instant 类，表示时间戳，只能表示世界标准时间（0 时区）的时间。
- ZoneDateTime 类，表示带时区的时间。

日期格式化类（类似于 JDK7 的 SimpleDateFormat 类）

- DateTimeFormatter 类，用于时间的格式化和解析。

日历类（类似于 JDK7 的 Calendar 类）

- LocalDate 类，表示年、月、日
- LocalTime 类，表示时、分、秒
- LocalDateTime 类，表示年、月、日、时、分、秒

工具类：

- Duration 类，表示时间间隔（秒、纳秒）
- Period 类，表示时间间隔（年、月、日）
- ChronoUnit 类，表示时间间隔（所有单位）

### 1.JDK8 日期类

#### 1.ZoneId 类

ZoneId 类，表示时区；在 Java 中，时区的表示形式是：`洲名/城市名` 或者 `国家名/城市名`。比如：

- `Asia/Shanghai`；`Asia/Taipei`、`Asia/Chongqing`

ZoneId 类，常用的方法如下：

| 方法名                                      | 说明                       |
| ------------------------------------------- | -------------------------- |
| `static Set<String> getAvailableZoneIds()` | 获取 Java 中支持的所有时区 |
| `static ZoneId systemDefault()`             | 获取系统默认时区           |
| `static ZoneId of(String zoneId)`           | 获取一个指定时区           |

##### 1.getAvailableZoneIds 静态方法

案例理解：`getAvailableZoneIds` 静态方法的使用，获取 Java 中可用的时区

demo-project/base-code/Day20/src/com/kkcf/zoneid/ZoneIdDemo01.java

```java
package com.kkcf.zoneid;

import java.time.ZoneId;
import java.util.Set;

public class ZoneIdDemo01 {
    public static void main(String[] args) {
        Set<String> zoneIds = ZoneId.getAvailableZoneIds();

        System.out.println(zoneIds.size()); // 603
        System.out.println(zoneIds); // 其中就有 Asia/Shanghai
    }
}
```

##### 2.systemDefault 静态方法

案例理解：`systemDefault` 静态方法的使用，获取当前计算机系统中的默认时区

demo-project/base-code/Day20/src/com/kkcf/zoneid/ZoneIdDemo01.java

```java
package com.kkcf.zoneid;

import java.time.ZoneId;

public class ZoneIdDemo01 {
    public static void main(String[] args) {
        ZoneId zoneId1 = ZoneId.systemDefault();

        System.out.println(zoneId1); // Asia/Shanghai
    }
}
```

##### 3.of 静态方法

案例理解：`of` 静态方法的使用，获取一个指定时区

demo-project/base-code/Day20/src/com/kkcf/zoneid/ZoneIdDemo01.java

```java
package com.kkcf.zoneid;

import java.time.ZoneId;
import java.util.Set;

public class ZoneIdDemo01 {
    public static void main(String[] args) {
        ZoneId zoneId2 = ZoneId.of("Asia/Pontianak");

        System.out.println(zoneId2); // Asia/Pontianak
    }
}
```

#### 2.Instant 类

Instant 类，表示时间戳，只能表示世界标准时间（0 时区）的时间。

Instant 类，常用的方法有如下：

| 方法名                                         | 说明                                    |
| ---------------------------------------------- | --------------------------------------- |
| `static Instant now()`                         | 获取当前时间的 Instant 对象（标准时间） |
| `static Instant ofEpochMilli(long epochMilli)` | 根据（秒/毫秒/纳秒）获取 Instant 对象   |
| `ZonedDateTime atZone(ZoneId zone)`            | 指定时区                                |
| `boolean isXxx(Instant otherInstant)`          | 判断系列的方法                          |
| `Instant minusXxx(long millisToSubtract)`      | 减少时间系列的方法                      |
| `Instant plusXxx(long millisToAdd)`            | 增加时间系列的方法                      |

##### 1.now 静态方法（Instant）

案例理解：`now` 静态方法的使用，获取当前标准时间。

demo-project/base-code/Day20/src/com/kkcf/instant/InstantDemo01.java

```java
package com.kkcf.instant;

import java.time.Instant;

public class InstantDemo01 {
    public static void main(String[] args) {
        Instant now = Instant.now();

        System.out.println(now); // 2024-07-18T08:29:14.733176600Z（世界标准时间（0 时区））
    }
}
```

##### 2.ofEpochMilli 静态方法

案例理解：`ofEpochMilli(long epochMilli)`、`ofEpochSecond(long epochSecond)`、`ofEpochSecond(long epochSecond, long nanoAdjustment)` 静态方法的使用，根据秒、毫秒、纳秒获取 Instant 对象。

demo-project/base-code/Day20/src/com/kkcf/instant/InstantDemo01.java

```java
package com.kkcf.instant;

import java.time.Instant;

public class InstantDemo01 {
    public static void main(String[] args) {
        // 获取时间原点 Instant 对象
        Instant i1 = Instant.ofEpochMilli(0L);

        System.out.println(i1); // 1970-01-01T00:00:00Z

        // 获取时间原点后 1s Instant 对象
        Instant i2 = Instant.ofEpochSecond(1L);

        System.out.println(i2); // 1970-01-01T00:00:01Z

        // 获取时间原点后 2s Instant 对象第一个参数；秒；第二个参数：纳秒
        Instant i3 = Instant.ofEpochSecond(1L, 1000000000L);

        System.out.println(i3); // 1970-01-01T00:00:02Z
    }
}
```

##### 3.atZone 方法

案例理解：`atZone` 方法，为 Instant 对象，指定时区；

demo-project/base-code/Day20/src/com/kkcf/instant/InstantDemo02.java

```java
package com.kkcf.instant;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class InstantDemo02 {
    public static void main(String[] args) {
        ZonedDateTime zonedDateTime = Instant.now().atZone(ZoneId.of("Asia/Shanghai"));

        System.out.println(zonedDateTime); // 2024-07-18T16:51:07.222483900+08:00[Asia/Shanghai]
    }
}
```

##### 4.isAfter、isBefore 方法

案例理解：`isAfter([Instant otherInstant)`、`isBefore(Instant otherInstant)` 方法的使用，用于判断 Instant 对象的时间前后顺序。

demo-project/base-code/Day20/src/com/kkcf/instant/InstantDemo02.java

```java
package com.kkcf.instant;

import java.time.Instant;

public class InstantDemo02 {
    public static void main(String[] args) {
        Instant now = Instant.now();

        Instant i1 = Instant.ofEpochMilli(0L);

        System.out.println(now.isAfter(i1)); // true
        System.out.println(i1.isBefore(now)); // true
    }
}
```

##### 5.minus、plus 开头的方法

案例理解：`minusMillis(long millisToSubtract)` 方法的使用，将 Instant 对象，往后减 1s。

demo-project/base-code/Day20/src/com/kkcf/instant/InstantDemo03.java

```java
package com.kkcf.instant;

import java.time.Instant;

public class InstantDemo03 {
    public static void main(String[] args) {
        Instant i1 = Instant.ofEpochMilli(3000L);

        System.out.println(i1); // 1970-01-01T00:00:03Z

        Instant i2 = i1.minusMillis(1000L);

        System.out.println(i2); // 1970-01-01T00:00:02Z
    }
}
```

- `plusXxx` 方法，用法也是类似的。

#### 3.ZoneDateTime 类

ZoneDateTime 类，用于表示带有时区的时间。

ZoneDateTime 类，常用的方法如下：

| 方法名                         | 说明                               |
| ------------------------------ | ---------------------------------- |
| `static ZonedDateTime now()`   | 获取当前时间的 ZoneDateTime 对象。 |
| `static ZonedDateTime of(xxx)` | 获取指定时间的 ZoneDateTime 对象。 |
| `ZonedDateTime withXxx(时间)`  | 修改时间系列的方法                 |
| `ZonedDateTime minusXxx(时间)` | 减少时间系列的方法                 |
| `ZonedDateTime plusXxx(时间)`  | 增加时间系列的方法                 |

##### 1.now 静态方法（ZoneDateTime）

案例理解：获取当前时间（带时区）的对象

demo-project/base-code/Day20/src/com/kkcf/zonedatetime/ZoneDateTimeDemo01.java

```java
package com.kkcf.zonedatetime;

import java.time.ZonedDateTime;

public class ZoneDateTimeDemo01 {
    public static void main(String[] args) {
        ZonedDateTime now = ZonedDateTime.now();

        System.out.println(now); // 2024-07-18T17:40:33.619900600+08:00[Asia/Shanghai]
    }
}
```

##### 2.of 静态方法（ZoneDateTime）

案例理解：获取指定的时间对象，有两种种方式：

- 方式一：使用 `static ZonedDateTime
  of(int year, int month, int dayOfMonth, int hour, int minute, int second, int nanoOfSecond, ZoneId zone)` 方法获取。
- 方式二：结合使用 Instant 类，ZoneId 类和 ZoneDateTime 类。

```java
package com.kkcf.zonedatetime;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class ZoneDateTimeDemo01 {
    public static void main(String[] args) {
        // 方式一
        ZoneId zoneId1 = ZoneId.of("Asia/Shanghai");
        ZonedDateTime zdt1 = ZonedDateTime.of(2024, 7, 18, 17, 40, 33, 619900600, zoneId1);

        System.out.println(zdt1); // 2024-07-18T17:40:33.619900600+08:00[Asia/Shanghai]

        // 方式二
        Instant i1 = Instant.ofEpochMilli(0L);
        ZoneId zoneId2 = ZoneId.of("Asia/Shanghai");
        ZonedDateTime zdt2 = ZonedDateTime.ofInstant(i1, zoneId2);

        System.out.println(zdt2); // 1970-01-01T08:00+08:00[Asia/Shanghai]
    }
}
```

##### 3.minus、plus 开头的方法

案例理解，给 ZoneDateTime 对象表示的时间，减 1 年；

demo-project/base-code/Day20/src/com/kkcf/zonedatetime/ZoneDateTimeDemo01.java

```java
package com.kkcf.zonedatetime;

import java.time.ZoneId;
import java.time.ZonedDateTime;

public class ZoneDateTimeDemo01 {
    public static void main(String[] args) {
        ZoneId zoneId1 = ZoneId.of("Asia/Shanghai");
        ZonedDateTime zdt1 = ZonedDateTime.of(2024, 7, 18, 17, 40, 33, 619900600, zoneId1);

        ZonedDateTime zdt3 = zdt1.minusYears(1L);

        System.out.println(zdt3); // 2023-07-18T17:40:33.619900600+08:00[Asia/Shanghai]
    }
}
```

- `minusXxx`、`plusXxx` 的其它方法用法类似。
- 注意：JDK8 新增的时间类，对应的时间对象都是不可变的，使用的方法返回的时间对象都是新创建的对象。

##### 4.with 开头的方法

案例理解：将 ZoneDatTime 对象的年份，修改为 2015.

demo-project/base-code/Day20/src/com/kkcf/zonedatetime/ZoneDateTimeDemo01.java

```java
package com.kkcf.zonedatetime;

import java.time.ZoneId;
import java.time.ZonedDateTime;

public class ZoneDateTimeDemo01 {
    public static void main(String[] args) {
        ZoneId zoneId1 = ZoneId.of("Asia/Shanghai");
        ZonedDateTime zdt1 = ZonedDateTime.of(2024, 7, 18, 17, 40, 33, 619900600, zoneId1);

        ZonedDateTime zdt2 = zdt1.withYear(2015);

        System.out.println(zdt2); // 2015-07-18T17:40:33.619900600+08:00[Asia/Shanghai]
    }
}
```

### 2.JDK8 日期格式化类

#### 1.DateTimeFormatter 类

DateTimeFormatter 类，用于时间格式化和解析。

DateTimeFormatter 类，常用的方法有如下：

| 方法名                                     | 说明               |
| ------------------------------------------ | ------------------ |
| `static DateTimeFormatter ofPattern(格式)` | 获取格式对象       |
| `String format(TemporalAccessor temporal)` | 按照指定方式格式化 |

##### 1.ofPattern 静态方法、format 方法

案例理解：创建 DateTimeFormatter 解析器，并按照指定格式，格式化 ZonedDateTime 对象。

demo-project/base-code/Day20/src/com/kkcf/datetimeformatter/DateTimeFormatterDemo01.java

```java
package com.kkcf.datetimeformatter;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeFormatterDemo01 {
    public static void main(String[] args) {
        // 获取时间对象
        ZonedDateTime zdt1 = Instant.now().atZone(ZoneId.of("Asia/Shanghai"));

        // 解析 / 格式化器
        DateTimeFormatter dtf1 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss EE a");

        String format = dtf1.format(zdt1);

        System.out.println(format);
    }
}
```

### 3.JDK8 日历类

#### 1.LocalDate、LocalTime、LocalDateTime 类

日历类 LocalDate 类，用于表示年、月、日的日历对象

日历类 LocalTime 类，用于表示时、分、秒的日历对象

日历类 LocalDateTime  类，用于表示年、月、日、时、分、秒的日历对象

它们的常用方法如下：

| 方法名                | 说明                                           |
| --------------------- | ---------------------------------------------- |
| `static xxx now()`    | 获取当前时间日历对象                           |
| `static xxx of(...)`  | 获取指定时间的日历对象                         |
| `get` 开头的方法      | 获取日历对象中的年、月、日、时、分、秒等等信息 |
| `isBefore`、`isAfter` | 比较两个日历对象                               |
| `with` 开头的方法     | 修改时间系列的方法                             |
| `minus` 开头的方法    | 减少时间系列的方法                             |
| `plus` 开头的方法     | 增加时间系列的方法。                           |

LocalDateTime  类，可以转换成 LocalDate 类或者 LocalTime 类，用到的方法如下：

| 方法名                    | 说明                                     |
| ------------------------- | ---------------------------------------- |
| `LocalDate toLocalDate()` | LocalDateTime  对象，转成 LocalDate 对象 |
| `LocalTime toLocalTime()` | LocalDateTime  对象，转成 LocalTime 对象 |

##### 1.now 静态方法（JDK8 日历类）

案例理解：使用 `now` 静态方法，获取一个当前时间的 LocalDate 日历对象

demo-project/base-code/Day20/src/com/kkcf/localdatetime/LocalDateTimeDemo01.java

```java
package com.kkcf.localdatetime;

import java.time.LocalDate;

public class LocalDateTimeDemo01 {
    public static void main(String[] args) {
        LocalDate ld1 = LocalDate.now();

        System.out.println(ld1); // 2024-07-18
    }
}
```

##### 2.of 静态方法（JDK8 日历类）

`of` 方法的使用，用于创建一个指定时间的日历对象。

案例理解：判断用户今天是否过生日。

demo-project/base-code/Day20/src/com/kkcf/localdatetime/LocalDateTimeDemo02.java

```java
package com.kkcf.localdatetime;

import java.time.LocalDate;
import java.time.MonthDay;

public class LocalDateTimeDemo02 {
    public static void main(String[] args) {
        LocalDate birthday = LocalDate.of(1997, 10, 16);
        LocalDate nowDate = LocalDate.now();

        MonthDay birthMd = MonthDay.of(birthday.getMonthValue(), birthday.getDayOfMonth());
        MonthDay nowMd = MonthDay.from(nowDate);

        System.out.println(birthMd.equals(nowMd) ? "今天是你的生日" : "今天不是你的生日");;
    }
}
```

- `MonthDay` 是 Java 中封装的用于表示月、日的类。

> LocalTime 类，专注于时间（秒、毫秒、纳秒）的表示，可用于“秒杀”等业务场景。

##### 3.get 开头的方法

案例理解：使用 `get` 开头的方法，获取一个 LocalDate 日历对象中的年、月

demo-project/base-code/Day20/src/com/kkcf/localdatetime/LocalDateTimeDemo01.java

```java
package com.kkcf.localdatetime;

import java.time.LocalDate;
import java.time.Month;

public class LocalDateTimeDemo01 {
    public static void main(String[] args) {
        LocalDate ld1 = LocalDate.now();

        System.out.println(ld1); // 2024-07-18

        int year = ld1.getYear();
        System.out.println(year); // 2024

        // 获取月份方式一
        Month m = ld1.getMonth();
        int month1 = m.getValue();
        System.out.println(month1); // t

        // 获取月份方式二
        int month2 = ld1.getMonthValue();
        System.out.println(month2); // 7
    }
}
```

- 其它 `get` 开头的方法，使用是类似的。

##### 4.isBefor、isAfter 方法

案例理解：判断两个时间的日历对象的先后顺序。

demo-project/base-code/Day20/src/com/kkcf/localdatetime/LocalDateTimeDemo01.java

```java
package com.kkcf.localdatetime;

import java.time.LocalDate;

public class LocalDateTimeDemo01 {
    public static void main(String[] args) {
        LocalDate ld1 = LocalDate.now();

        System.out.println(ld1); // 2024-07-18

        System.out.println(ld1.isBefore(ld1)); // false
        System.out.println(ld1.isAfter(ld1)); // false
    }
}
```

##### 5.with 开头的方法

`with` 开头的方法，用于修改日历对象，只能修改年、月、日。

案例理解：将日期对象中的年，修改为 2015。

demo-project/base-code/Day20/src/com/kkcf/localdatetime/LocalDateTimeDemo01.java

```java
package com.kkcf.localdatetime;

import java.time.LocalDate;

public class LocalDateTimeDemo01 {
    public static void main(String[] args) {
        LocalDate ld1 = LocalDate.now();

        System.out.println(ld1); // 2024-07-18

        LocalDate ld2 = ld1.withYear(2015);

        System.out.println(ld2); // 2015-07-18
    }
}
```

##### 6.minus、plus 开头的方法

`minus` 方法的使用，用于修改日历对象的年、月、日，

案例理解：将日历对象表示的日期，减一年。

demo-project/base-code/Day20/src/com/kkcf/localdatetime/LocalDateTimeDemo01.java

```java
package com.kkcf.localdatetime;

import java.time.LocalDate;

public class LocalDateTimeDemo01 {
    public static void main(String[] args) {
        LocalDate ld1 = LocalDate.now();

        LocalDate ld2 = ld1.minusYears(1);

        System.out.println(ld3); // 2023-07-18
    }
}
```

### 4.JDK8 时间间隔工具类

#### 1.Duration 类

Duration 类，用于计算两个时间间隔（秒、纳秒）

案例理解：使用 Duration 获取两个 LocalDateTime 对象的时间间隔。

demo-project/base-code/Day20/src/com/kkcf/duration/DurationDemo01.java

```java
package com.kkcf.duration;

import java.time.Duration;
import java.time.LocalDateTime;

public class DurationDemo01 {
    public static void main(String[] args) {
        LocalDateTime today = LocalDateTime.now();

        LocalDateTime birthday = LocalDateTime.of(2000, 1, 1, 0, 00, 00);

        Duration duration = Duration.between(birthday, today);

        System.out.println("相差的时间间隔：" + duration); // 相差的时间间隔：PT215191H36M51.4040646S

        System.out.println(duration.toDays()); // 8966
        System.out.println(duration.toHours()); // 215191
        System.out.println(duration.toMinutes()); // 12911497
        System.out.println(duration.toMillis()); // 774689864805
        System.out.println(duration.toNanos()); // 774689864805800400
    }
}
```

#### 2.Period 类

Period 类，用于计算两个日期间隔（年、月、日）

案例理解：使用 Period 获取两个 LocalDate 对象的时间间隔

demo-project/base-code/Day20/src/com/kkcf/period/PeriodDemo01.java

```java
package com.kkcf.period;

import java.time.LocalDate;
import java.time.Period;

public class PeriodDemo01 {
    public static void main(String[] args) {
        LocalDate today = LocalDate.now();
        LocalDate birthday = LocalDate.of(2000, 1, 1);

        Period period = Period.between(birthday, today);

        System.out.println(period); // P24Y6M18D
        System.out.println("相隔" + period.getYears() + "年" + period.getMonths() + "月" + period.getDays() + "天"); // 相隔24年6月18天

        // 获取相隔总月份
        System.out.println(period.toTotalMonths()); // 294
    }
}
```

#### 3.ChronoUnit 类（重点）

ChronoUnit 类，用于计算两个日期间隔（所有单位）。

案例理解：使用 ChronoUnit 获取两个 LocalDateTime 对象的时间间隔

demo-project/base-code/Day20/src/com/kkcf/chronounit/ChronoUnitDemo01.java

```java
package com.kkcf.chronounit;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class ChronoUnitDemo01 {
    public static void main(String[] args) {
        LocalDateTime today = LocalDateTime.now();

        LocalDateTime birthday = LocalDateTime.of(2000, 1, 1, 0, 00, 00);

        System.out.println("间隔的年数：" + ChronoUnit.YEARS.between(birthday, today)); // 间隔的年数：24
        System.out.println("间隔的月数：" + ChronoUnit.MONTHS.between(birthday, today)); // 间隔的月数：294
        System.out.println("间隔的周数：" + ChronoUnit.WEEKS.between(birthday, today)); // 间隔的周数：1280
        System.out.println("间隔的天数：" + ChronoUnit.DAYS.between(birthday, today)); // 间隔的天数：8966
        System.out.println("间隔的时数：" + ChronoUnit.HOURS.between(birthday, today)); // 间隔的时数：215191
        System.out.println("间隔的分数：" + ChronoUnit.MINUTES.between(birthday, today)); // 间隔的分数：12911510
        System.out.println("间隔的秒数：" + ChronoUnit.SECONDS.between(birthday, today)); // 间隔的秒数：774690643
        System.out.println("间隔的毫秒数：" + ChronoUnit.MILLIS.between(birthday, today)); // 间隔的毫秒数：774690643791
        System.out.println("间隔的微妙数：" + ChronoUnit.MICROS.between(birthday, today)); // 间隔的微妙数：774690643791441
        System.out.println("间隔的纳秒数：" + ChronoUnit.NANOS.between(birthday, today)); // 间隔的纳秒数：774690643791441200
        System.out.println("间隔的半天数：" + ChronoUnit.HALF_DAYS.between(birthday, today)); // 间隔的半天数：17932
        System.out.println("间隔的十年数：" + ChronoUnit.DECADES.between(birthday, today)); // 间隔的十年数：2
        System.out.println("间隔的世纪（百年）数：" + ChronoUnit.CENTURIES.between(birthday, today)); // 间隔的世纪（百年）数：0
        System.out.println("间隔的千年数：" + ChronoUnit.MILLENNIA.between(birthday, today)); // 间隔的千年数：0
        System.out.println("间隔的纪元数：" + ChronoUnit.ERAS.between(birthday, today)); // 间隔的纪元数：0
    }
}
```

总结：JDK8 时间类，方法的共同性；

- 获取当前时间都是 `now` 方法。
- 获取指定的时间，方法都以 `of` 开头。
- 修改时间，方法都是以 `with` 开头。
- 将时间往前推，方法都是以 `minus` 开头。
- 将时间往后加，方法都是以 `plus` 开头。
