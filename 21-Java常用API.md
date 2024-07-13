# Java 常用 API

## 一、Math 类

Math 类，所在包为 java.lang 包，因此在使用的时候不需要进行导包。

Math 类被 final 修饰了，因此该类是不能被继承的。

Math 类是一个帮助我们进行数学计算的工具类，包含执行基本数学运算的方法。

- Math 类里面所有的方法都是静态的。
- Math 类私有化了构造方法，外界不能创建它的实例对象。

Math 类中，常用的方法如下：

| 方法名                                        | 说明                                       |
| --------------------------------------------- | ------------------------------------------ |
| `public static int abs(int a)`                | 有很多参数类型的重载方法，返回参数的绝对值 |
| `public static double ceil(double a)`         | 返回大于或等于参数的最小整数               |
| `public static double floor(double a)`        | 返回小于或等于参数的最大整数               |
| `public static int round(float a)`            | 按照四舍五入返回最接近参数的 int 类型的值  |
| `public static int max(int a,int b)`          | 获取两个 int 值中的较大值                  |
| `public static int min(int a,int b)`          | 获取两个 int 值中的较小值                  |
| `public static double pow(double a,double b)` | 计算 a 的 b 次幂的值                       |
| `public static double random()`               | 返回一个 `[0.0,1.0)` 的随机值              |

int 类型的数字，取值范围为 -2147483647-2147483647，如果使用 `Math.abs` 方法，传入这个范围之外的数，那么结果有误。

为解决上面的 bug，推荐使用 JDK 15 新特性：`Math.absExact` 方法，传入 int 范围之外的数，会直接报错。

demo-project/base-code/Day18/src/com/kkcf/myapi/MathDemo01.java

```java
package com.kkcf.myapi;

public class MathDemo01 {
    public static void main(String[] args) {
        System.out.println(Math.abs(-2147483648)); // -2147483648
        System.out.println(Math.abs(-2147483647)); // 2147483647

        System.out.println(Math.absExact(-2147483648));
        /*
        Overflow to represent absolute value of Integer.MIN_VALUE
            at java.base/java.lang.Math.absExact(Math.java:1448)
            at com.kkcf.myapi.MathDemo01.main(MathDemo01.java:8)
         */
    }
}
```

`Math.cell` 方法，是往数轴的正方向取整。

demo-project/base-code/Day18/src/com/kkcf/myapi/MathDemo01.java

```java
package com.kkcf.myapi;

public class MathDemo01 {
    public static void main(String[] args) {
        System.out.println(Math.ceil(12.34)); // 13.0
        System.out.println(Math.ceil(-12.34)); // -12.0
    }
}

```

`Math.floor` 方法，是往数轴的负方向取整。

demo-project/base-code/Day18/src/com/kkcf/myapi/MathDemo01.java

```java
package com.kkcf.myapi;

public class MathDemo01 {
    public static void main(String[] args) {
        System.out.println(Math.floor(12.34)); // 12.0
        System.out.println(Math.floor(-12.34)); // -13.0
    }
}
```

`Math.pow` 方法，如果第二个参数，传入的是 0-1 之间的小鼠，表示开平方根。

demo-project/base-code/Day18/src/com/kkcf/myapi/MathDemo01.java

```java
package com.kkcf.myapi;

public class MathDemo01 {
    public static void main(String[] args) {
        System.out.println(Math.pow(4, 2)); // 16.0
        System.out.println(Math.pow(4, 0.5)); // 2.0
        System.out.println(Math.pow(2, -2)); // 0.25
    }
}
```

`Math.sqrt` 和 `Math.cbrt` 方法，分别表示开平方根，开立方根。

demo-project/base-code/Day18/src/com/kkcf/myapi/MathDemo01.java

```java
package com.kkcf.myapi;

public class MathDemo01 {
    public static void main(String[] args) {
        System.out.println(Math.sqrt(4)); // 2.0
        System.out.println(Math.cbrt(8)); // 2.0
    }
}
```

使用 `Math.random` 方法，获取一个 1-100 的随机数/

demo-project/base-code/Day18/src/com/kkcf/myapi/MathDemo01.java

```java
package com.kkcf.myapi;

public class MathDemo01 {
    public static void main(String[] args) {
        System.out.println(Math.floor(Math.random() * 100 + 1));
    }
}
```

- 这种方式在 java 中用的不多，因为底层仍使用 `Random` 类来实现的。
- 这种方式在获取随机数，在 JS 中使用比较多。

案例理解：判断一个数，是否是质数。

- 使用平方根来优化。

demo-project/base-code/Day18/src/com/kkcf/myapi/MathDemo02.java

```java
package com.kkcf.myapi;

public class MathDemo02 {
    public static void main(String[] args) {
        int num = 9;
        System.out.println(num + (isPrime(num) ? " 是质数" : " 不是质数"));
    }

    public static boolean isPrime(int num) {
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0)
                return false;
        }

        return true;
    }
}
```

案例理解：自幂数，表示一个 n 位自然数，等于自身各个数位上数字的 n 次幂之和。

- 比如：三位数：1^3^ + 5^3^ + 3^3^ = 153。
- 比如：四位数：1^4^ + 6^4^ + 3^4^ + 4^4^ = 1634

如果自幂数是

- 一位数，也叫做：独身数；
- 三位自幂数：水仙花数；
- 四位自幂数：四叶玫瑰数；
- 五位数：五角星数；
- ……

要求 1：统计一共有多少个水仙花数。

要求 2：证明没有 2 位的自幂数。

要求 3：统计有多少个四叶玫瑰数，和五角心数。

demo-project/base-code/Day18/src/com/kkcf/myapi/MathDemo03.java

```java
package com.kkcf.myapi;

public class MathDemo03 {
    public static void main(String[] args) {
        System.out.println("水仙花数有 " + daffodils() + " 个");

        System.out.println(twoDigitExponentiation() > 0 ? "有" : "没有" + "两位的自幂数");

        System.out.println("四叶玫瑰数有 " + fourleafRose() + " 个");

        System.out.println("五角星数有 " + pentagram() + " 个");
    }

    /**
     * 此芳芳用于，统计两位自幂数的个数
     * @return 两位自幂数的个数
     */
    public static int twoDigitExponentiation() {
        int count = 0;

        for (int i = 10; i <= 99; i++) {
            int ge = i % 10;
            int shi = i / 10 % 10;

            if (Math.pow(ge, 2) + Math.pow(shi, 2) == i) {
                count++;
                System.out.println(i);
            }
        }

        return count;
    }

    /**
     * 此芳芳用于，统计水仙花数的个数
     * @return 水仙花数的个数
     */
    public static int daffodils() {
        int count = 0;
        for (int i = 100; i <= 999; i++) {
            int ge = i % 10;
            int shi = i / 10 % 10;
            int bai = i / 100 % 10;

            if (Math.pow(ge, 3) + Math.pow(shi, 3) + Math.pow(bai, 3) == i) {
                count++;
                System.out.println(i);
            }
        }

        return count;
    }

    /**
     * 此芳芳用于，统计四叶玫瑰数的个数
     * @return 四叶玫瑰数的个数
     */
    public static int fourleafRose() {
        int count = 0;

        for (int i = 1000; i <= 9999; i++) {
            int ge = i % 10;
            int shi = i / 10 % 10;
            int bai = i / 100 % 10;
            int qian = i / 1000 % 10;

            if (Math.pow(ge, 4) + Math.pow(shi, 4) + Math.pow(bai, 4) + Math.pow(qian, 4) == i) {
                count++;
                System.out.println(i);
            }
        }

        return count;
    }

    /**
     * 此芳芳用于，统计五角星数的个数
     * @return 五角星数的个数
     */
    public static int pentagram() {
        int count = 0;

        for (int i = 10000; i <= 99999; i++) {
            int ge = i % 10;
            int shi = i / 10 % 10;
            int bai = i / 100 % 10;
            int qian = i / 1000 % 10;
            int wan = i / 10000 % 10;

            if (Math.pow(ge, 5) + Math.pow(shi, 5) + Math.pow(bai, 5) + Math.pow(qian, 5) + Math.pow(wan, 5) == i) {
                count++;
                System.out.println(i);
            }
        }

        return count;
    }
}
```

## 二、System 类

`System` 也是一个工具类，提供了一些与系统相关的方法。

`System` 类中常用的方法有：

| 方法名                                                                               | 说明                                   |
| ------------------------------------------------------------------------------------ | -------------------------------------- |
| `public static void exit(int status)`                                                | 终止当前运行的 Java 虚拟机             |
| `public static long currentTimeMillis()`                                             | 返回时间原点到当前系统的时间毫秒值形式 |
| `public static void arraycopy(数据源数组, 起始索引, 目的地数组, 起始索引, 拷贝个数)` | 拷贝数组                               |

> 计算机中的时间原点
>
> 1970 年 1 月 1 号 00:00:00
>
> 中国（东八区）有八小时时差。即 1970 年 1 月 1 号 08:00:00

`System.exit` 的使用：

demo-project/base-code/Day18/src/com/kkcf/myapi/SystemDemo01.java

```java
package com.kkcf.myapi;

public class SystemDemo01 {
    public static void main(String[] args) {
        // 传 0，表示正常停止
        // 传非 0，表示异常停止
        System.exit(0);

        System.out.println("haha"); // 未执行
    }
}
```

`System.currentTimeMillis` 的使用：

demo-project/base-code/Day18/src/com/kkcf/myapi/SystemDemo01.java

```java
package com.kkcf.myapi;

public class SystemDemo01 {
    public static void main(String[] args) {
        System.out.println(System.currentTimeMillis()); // 1720863235923
    }
}
```

注意：

- 如果数据源数组，和目的地数组都是基本数据类型，那么两者的**数据类型必须保持一致**，否则会报错。
- 如果数据源数组，和目的地数组都是引用数据类型，那么子类数据类型，可以赋值给父类数据类型。
- 在拷贝的时候，需要考虑数组的长度，如果超出范围也会报错。

## 三、Runtime 类

`Runtime` 类，表示当前虚拟机的运行环菌。

`Runtime` 类，里面的方法不是静态，需要先获取它的实例对象，才能调用其中的方法。

- 需要使用类中提供的静态方法 `getRuntime` 获取实例对象，因为 `Runtime` 类在程序运行时，只能创建一个对象。
- 因为 `Runtime` 类，表示的时 Java 虚拟机的运行环菌，而在一台计算机中，只能由一个 Java 虚拟机运行环菌。

`Runtime` 类常用方法如下：

| 方法名                                | 说明                                        |
| ------------------------------------- | ------------------------------------------- |
| `public static Runtime getRuntime()`  | 当前系统的运行环境对象                      |
| `public void exit(int status)`        | 停止虚拟机                                  |
| `public int availableProcessors()`    | 获得 CPU 的线程数                           |
| `public long maxMemory()`             | JVM 能从系统中获取总内存大小（单位 byte）   |
| `public long totalMemory()`           | JVM 已经从系统中获取总内存大小（单位 byte） |
| `public long freeMemory()`            | JVM 剩余内存大小（单位 byte）               |
| `public Process exec(String command)` | 运行 cmd 命令                               |

> `System.exit` 底层调用的就是 `Runtime.exit` 的方法。

`Runtime.getRuntime` 方法的使用，获取系统的运行环菌实例对象。

demo-project/base-code/Day18/src/com/kkcf/myapi/RuntimeDemo01.java

```java
package com.kkcf.myapi;

public class RuntimeDemo01 {
    public static void main(String[] args) {
        Runtime r1 = Runtime.getRuntime();
        Runtime r2 = Runtime.getRuntime();

        System.out.println(r1 == r2); // true
    }
}
```

`exit` 方法的使用：

demo-project/base-code/Day18/src/com/kkcf/myapi/RuntimeDemo01.java

```java
package com.kkcf.myapi;

public class RuntimeDemo01 {
    public static void main(String[] args) {
        Runtime.getRuntime().exit(0);

        System.out.println("看看我执行了码"); // 未执行
    }
}
```

`availableProcessors` 方法的使用

demo-project/base-code/Day18/src/com/kkcf/myapi/RuntimeDemo01.java

```java
package com.kkcf.myapi;

public class RuntimeDemo01 {
    public static void main(String[] args) {
        System.out.println(Runtime.getRuntime().availableProcessors()); // 12 （当前电脑是 6 核 12 线程）
    }
}
```

`maxMemory` 方法的使用

demo-project/base-code/Day18/src/com/kkcf/myapi/RuntimeDemo01.java

```java
package com.kkcf.myapi;

public class RuntimeDemo01 {
    public static void main(String[] args) {
        System.out.println(Runtime.getRuntime().maxMemory() / 1024 / 1024); // 4084 即 4G 内存
    }
}
```

`totalMemory` 方法的使用

demo-project/base-code/Day18/src/com/kkcf/myapi/RuntimeDemo01.java

```java
package com.kkcf.myapi;

public class RuntimeDemo01 {
    public static void main(String[] args) {
        System.out.println(Runtime.getRuntime().totalMemory() / 1024 / 1024); // 256 即 256MB 内存
    }
}
```

`freeMemory` 方法的使用

demo-project/base-code/Day18/src/com/kkcf/myapi/RuntimeDemo01.java

```java
package com.kkcf.myapi;

public class RuntimeDemo01 {
    public static void main(String[] args) {
        System.out.println(Runtime.getRuntime().freeMemory() / 1024 / 1024); // 253 即 253MB 内存
    }
}

```

`exec` 方法的使用，要处理该方法的异常。

demo-project/base-code/Day18/src/com/kkcf/myapi/RuntimeDemo01.java

```java
package com.kkcf.myapi;

import java.io.IOException;

public class RuntimeDemo01 {
    public static void main(String[] args) throws IOException {
        Runtime.getRuntime().exec("java -version");
    }
}
```

> cmd 命令补充：
>
> - `shutdown`，表示关机。
> - `shutdown -s`，默认在一分钟后关机。
> - `shutdown -s -t 指定的时间（秒）`，在指定时间后关机。
> - `shutdown -a`，取消关机。
> - `shutdown -r`，关机并重启。

## 四、Object 类

Object 类所在包是 java.lang 包。

Object 类，是 Java 中的顶级父类，所有类都直接或间接的继承自 Object 类。该类所具备的方法，其他所有类都继承了。

Object 类，没有成员变量，只有一个空参构造方法

| 方法名            | 说明     |
| ----------------- | -------- |
| `public Object()` | 空参构造 |

Object 类，一共有 11 个成员方法，这里先介绍 3 个：

| 方法名                              | 说明                                                        |
| ----------------------------------- | ----------------------------------------------------------- |
| `public String toString()`          | 返回该对象的字符串表示形式(，可以看做是对象的内存地址值)    |
| `public boolean equals(Object obj)` | 比较两个对象地址值是否相等；true 表示相同，false 表示不相同 |
| `protected Object clone()`          | 对象克隆                                                    |

`toString` 方法的使用：

demo-project/base-code/Day18/src/com/kkcf/myapi/ObjecDemo01.java

```java
package com.kkcf.myapi;

public class ObjecDemo01 {
    public static void main(String[] args) {
        Object obj = new Object();

        System.out.println(obj.toString()); // java.lang.Object@4eec7777
        System.out.println(obj); // java.lang.Object@4eec7777

        Student stu = new Student();

        System.out.println(stu.toString()); // com.kkcf.myapi.Student@41629346
        System.out.println(stu); // com.kkcf.myapi.Student@41629346
    }
}
```

- `java.lang.Object` 是该对象的报名；
- `@` 是固定格式；
- `4eec7777` 是对象的地址值。

直接打印对象，和打印对象调用 `toString` 方法返回的结果，效果是一样的。这是因为：

1. 当使用 `System.out.println` 打印语句，打印一个对象的时候，底层会调用对象的 `toString` 方法，把对象变成字符串。
2. 然后再打印在控制台上，打印完毕做换行处理。

所以，如果要打印一个对象中的属性值，那么重写这个对象所对应的类中的 `toString` fang
