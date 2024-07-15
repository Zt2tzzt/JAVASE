# Java 常用 API 之 Math、System、Runtime、Object、Objects

## 一、Math 工具类

Math 类，所在包为 java.lang 包，因此在使用的时候不需要进行导包。

Math 类被 `final` 修饰了，因此该类是不能被继承的。

Math 类是一个帮助我们进行数学计算的**工具类**，包含执行基本数学运算的方法。

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

### 1.abs 静态方法

int 类型的数字，取值范围为 `-2147483647-2147483647`，如果使用 `Math.abs` 方法，传入这个范围之外的数，那么结果有精度错误。

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

### 2.ceil 静态方法

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

### 3.floor 静态方法

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

### 4.pow 静态方法

`Math.pow` 方法，如果第二个参数，传入的是 0-1 之间的小数，表示开平方根。

demo-project/base-code/Day18/src/com/kkcf/myapi/MathDemo01.java

```java
package com.kkcf.myapi;

public class MathDemo01 {
    public static void main(String[] args) {
        System.out.println(Math.pow(4, 2)); // 16.0
        System.out.println(Math.pow(4, 0.5)); // 2.0，相当于 4 开平方根
        System.out.println(Math.pow(2, -2)); // 0.25，相当于 1 / 2^2
    }
}
```

### 5.sqrt、cbrt 静态方法

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

### 6.random 静态方法

使用 `Math.random` 方法，返回一个 `[0.0,1.0)` 的随机值

demo-project/base-code/Day18/src/com/kkcf/myapi/MathDemo01.java

```java
package com.kkcf.myapi;

public class MathDemo01 {
    public static void main(String[] args) {
        System.out.println(Math.floor(Math.random() * 100 + 1));
    }
}
```

- 这种方式获取随机数，在 java 中用的不多，因为底层仍然是使用 `Random` 类来实现的。
- 这种方式在获取随机数，在 JS 中使用比较多。

### 7.min、max 静态方法

使用 `Math.min`、`Math.max` 方法，获取两个数的最小值、最大值。

demo-project/base-code/Day18/src/com/kkcf/myapi/MathDemo01.java

```java
package com.kkcf.myapi;

public class MathDemo01 {
    public static void main(String[] args) {
        System.out.println(Math.min(10, 20)); // 10

        System.out.println(Math.max(10, 20)); // 20
    }
}
```

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

- 一位数：独身数；
- 二位数：没有二位的自幂数
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
        System.out.println(twoDigitExponentiation() > 0 ? "有" : "没有" + "两位的自幂数");

        System.out.println("水仙花数有 " + daffodils() + " 个");

        System.out.println("四叶玫瑰数有 " + fourleafRose() + " 个");

        System.out.println("五角星数有 " + pentagram() + " 个");
    }

    /**
     * 此方法用于，统计两位自幂数的个数
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
     * 此方法用于，统计水仙花数的个数
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
     * 此方法用于，统计四叶玫瑰数的个数
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
     * 此方法用于，统计五角星数的个数
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

## 二、System 工具类

`System` 也是一个**工具类**，提供了一些与系统相关的方法。

`System` 类中常用的方法有：

| 方法名                                                       | 说明                                   |
| ------------------------------------------------------------ | -------------------------------------- |
| `public static void exit(int status)`                        | 终止当前运行的 Java 虚拟机             |
| `public static long currentTimeMillis()`                     | 返回时间原点到当前系统的时间毫秒值形式 |
| `public static void arraycopy(数据源数组, 起始索引, 目的地数组, 起始索引, 拷贝个数)` | 拷贝数组（浅拷贝）                     |

> 计算机中的时间原点：
>
> 1970 年 1 月 1 号 00:00:00；中国（东八区）有八小时时差。即 1970 年 1 月 1 号 08:00:00

### 1.exit 静态方法

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

### 2.currentTimeMillis 静态方法

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

### 3.arraycopy 静态方法

demo-project/base-code/Day18/src/com/kkcf/myapi/SystemDemo01.java

```java
package com.kkcf.myapi;

public class SystemDemo01 {
    public static void main(String[] args) {
        int[] arr1 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int[] arr2 = new int[10];

        // 从 0 索引的位置拷贝 arr1 到 arr2 的 0 索引上，拷贝 arr1.length 个元素。
        System.arraycopy(arr1, 0, arr2, 0, arr1.length);
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

- 不能使用 `new` 操作符创建实例对象，而是要使用类中提供的静态方法 `getRuntime` 获取实例对象；
- 因为 `Runtime` 类在程序运行时，只能创建一个对象。
- `Runtime` 类，表示的是 Java 虚拟机的运行环菌，而在一台计算机中，只能有一个 Java 虚拟机运行环菌。

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

### 1.getRuntime 静态方法

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

### 2.exit 方法

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

### 3.availableProcessors 方法

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

### 4.maxMemory 方法

`maxMemory` 方法的使用，获取的结果，单位是字节。

demo-project/base-code/Day18/src/com/kkcf/myapi/RuntimeDemo01.java

```java
package com.kkcf.myapi;

public class RuntimeDemo01 {
    public static void main(String[] args) {
        System.out.println(Runtime.getRuntime().maxMemory() / 1024 / 1024); // 4084 即 4G 内存
    }
}
```

### 5.totalMemory 方法

`totalMemory` 方法的使用，获取的结果，单位是字节。

demo-project/base-code/Day18/src/com/kkcf/myapi/RuntimeDemo01.java

```java
package com.kkcf.myapi;

public class RuntimeDemo01 {
    public static void main(String[] args) {
        System.out.println(Runtime.getRuntime().totalMemory() / 1024 / 1024); // 256 即 256MB 内存
    }
}
```

### 6.freeMemory 方法

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

### 7.exec 方法

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

`Object` 类所在包是 `java.lang` 包。

`Object` 类，是 Java 中的顶级父类，所有类都直接或间接的继承自 `Object` 类。该类所具备的方法，其他所有类都继承了。

`Object` 类，没有成员变量，只有一个空参构造方法

| 方法名            | 说明     |
| ----------------- | -------- |
| `public Object()` | 空参构造 |

`Object` 类，一共有 11 个成员方法，这里先介绍 3 个：

| 方法名                              | 说明                                                        |
| ----------------------------------- | ----------------------------------------------------------- |
| `public String toString()`          | 返回该对象的字符串表示形式（可以看做是对象的内存地址值）    |
| `public boolean equals(Object obj)` | 比较两个对象地址值是否相等；true 表示相同，false 表示不相同 |
| `protected Object clone()`          | 对象克隆                                                    |

### 1.toString 方法

`toString`  方法的使用，默认返回的是对象地址值。

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

Java 中，对象地址值的打印格式分析：

- `java.lang.Object` 是该对象的包名；
- `@` 是固定格式；
- `4eec7777` 是对象的地址值。

直接打印对象，和对象调用 `toString` 方法返回的结果，效果是一样的。这是因为：

1. 当使用 `System.out.println` 打印语句，打印一个对象的时候，底层会调用对象的 `toString` 方法，把对象变成字符串。
2. 然后再打印在控制台上，打印完毕做换行处理。

所以，如果要使用 `System.out.println` 打印语句，打印一个对象中的属性值，那么重写这个对象所对应的类中的 `toString` 方法即可。

demo-project/base-code/Day18/src/com/kkcf/myapi/Student.java

```java
package com.kkcf.myapi;

import java.util.Objects;

public class Student {
    private String name;
    private int age;

    public Student() {
    }

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // getter、setter……

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
```

### 2.equals 方法

`equals` 方法的使用

demo-project/base-code/Day18/src/com/kkcf/myapi/ObjectDemo02.java

```java
package com.kkcf.myapi;

public class ObjectDemo02 {
    public static void main(String[] args) {
        Student stu1 = new Student();
        Student stu2 = new Student();

        System.out.println(stu1.equals(stu2)); // false
    }
}
```

在 `Object` 类中，`equals` 方法，默认比较的，是对象的地址值。

所以，如果要比较一个对象中的属性值，那么重写这个对象所对应的类中的 `equals` 方法即可。

demo-project/base-code/Day18/src/com/kkcf/myapi/Student.java

```java
package com.kkcf.myapi;

import java.util.Objects;

public class Student {
    private String name;
    private int age;

    public Student() {
    }

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // getter、setter……

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return age == student.age && Objects.equals(name, student.name);
    }
}
```

理解下方代码：

demo-project/base-code/Day18/src/com/kkcf/myapi/ObjectDemo02.java

```java
package com.kkcf.myapi;

public class ObjectDemo02 {
    public static void main(String[] args) {
        String s = new String("haha");
        StringBuilder sb = new StringBuilder("hehe");

        System.out.println(s.equals(sb)); /// false
        System.out.println(sb.equals(s)); /// false
    }
}
```

`String` 类中的 `equals` 方法，

1. 先判断比较的两个 `String` 对象的地址值，是否相等；

2. 再判断参数是否为 `String` 类的实例（源码如下）：

   - 如果是，再比较内部的属性；

   - 如果不是，直接返回 `false`。

java/lang/Object.java

```java
public boolean equals(Object anObject) {
    if (this == anObject) {
        return true;
    }
    return (anObject instanceof String aString)
            && (!COMPACT_STRINGS || this.coder == aString.coder)
            && StringLatin1.equals(value, aString.value);
}
```

`StringBuilder` 类中，没有重写 `equals` 方法，

- 因此使用 `sb` 调用 `equals` 方法时，会默认使用 `Object` 类里的 `equals` 方法。
- 又因为 `Object` 类里的 `equals` 方法，比较的是对象的地址值，所以返回的也是 `false`。

### 3.clone 方法

`clone` 方法，用于把 A 对象的属性值，完全拷贝给 B 对象，也陈为对象拷贝，对象复制。

案例理解：创建一个 JavaBean 类 `User`，在代码中克隆它的实例对象。

- 在 `User` 类中，先要重写 `Object` 类中的 `clone` 方法；
- 然后，为 `User` 类实现 `Cloneable` 接口。

> `Cloneable` 接口里面没有任何抽象方法。
>
> - 实现该接口的类，它的实例对象，就可以被克隆；
> - 没有实现该接口，那么这个类的实例对象，就不可以被克隆。
>
> 如果一个接口，里面没有抽象方法，表示这个接口是一个标记性的接口，

demo-project/base-code/Day18/src/com/kkcf/myapi/User.java

```java
package com.kkcf.myapi;

import java.util.Arrays;

public class User implements Cloneable {
    private String id; // 游戏角色
    private String name; // 用户名
    private String password; // 密码
    private String path; // 图片
    private int[] data; // 游戏进度

    public User() {
    }

    public User(String id, String name, String password, String path, int[] data) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.path = path;
        this.data = data;
    }

    // getter、setter……

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", path='" + path + '\'' +
                ", data=" + Arrays.toString(data) +
                '}';
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        // 用于调用 Object 类中的 clone 方法，克隆一个对象，并把它返回出去
        return super.clone();
    }
}
```

demo-project/base-code/Day18/src/com/kkcf/myapi/ObjectDemo02.java

```java
package com.kkcf.myapi;

public class ObjectDemo02 {
    public static void main(String[] args) throws CloneNotSupportedException {
        User u1 = new User("1", "zhangsan", "123456", "abc/cba/nba", new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15});

        Object obj = u1.clone();
        User u2 = (User) obj;

        System.out.println(u1); // User{id='1', name='zhangsan', password='123456', path='abc/cba/nba', data=[1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15]}
        System.out.println(u2); // User{id='1', name='zhangsan', password='123456', path='abc/cba/nba', data=[1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15]}
    }
}
```

- `clone` 方法，在底层会创建一个对象，并把原对象中的属性，拷贝过去。
- 使用 `Object` 类中的 `clone` 方法，
  1. 先要在要克隆的对象的类中，重写 `clone` 方法，
  2. 再为这个类实现 `Cloneable` 标记性的接口。
  3. 创建类的实例对象，并调用重写的 `clone` 方法。

> Java 中的浅拷贝、深拷贝
>
> - Java 中的浅拷贝：不管对象内部的属性，是基本数据类型，还是引用数据类型，都完全拷贝过来。
> - Java 中的深拷贝：对象中的基本数据类型，会直接拷贝过来；字符串会复用 StringTable 串池中已存在的地址值；引用数据类型会创建一个新对象。

`Object` 类中的 `clone` 方法，属于浅拷贝。

重构 `User` 类中的 `clone` 方法，实现深拷贝的效果：

demo-project/base-code/Day18/src/com/kkcf/myapi/User.java

```java
package com.kkcf.myapi;

import java.util.Arrays;

public class User implements Cloneable {
    private String id; // 游戏角色
    private String name; // 用户名
    private String password; // 密码
    private String path; // 图片
    private int[] data; // 游戏进度

    public User() {
    }

    public User(String id, String name, String password, String path, int[] data) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.path = path;
        this.data = data;
    }

    // getter、setter……

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", path='" + path + '\'' +
                ", data=" + Arrays.toString(data) +
                '}';
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        int[] newData = new int[data.length];

        System.arraycopy(data, 0, newData, 0, newData.length);

        // 用于调用 Object 类中的 clone 方法，克隆一个对象，并把它返回出去
        User u = (User) super.clone();
        u.data = newData;

        return u;
    }
}
```

在项目中，导入第三方包，用于深拷贝。

1. 在 module 模块中，创建一个目录 `lib`，将第三方包 `gson-2.6.2.jar` 放在该目录下。
2. 在 idea 中，右键单击第三方包，选择“Add as Library”。

使用 `gson` 包，对 `User` 类的实例对象，进行深拷贝：

demo-project/base-code/Day18/src/com/kkcf/myapi/ObjectDemo02.java

```java
package com.kkcf.myapi;

import com.google.gson.Gson;

public class ObjectDemo02 {
    public static void main(String[] args) throws CloneNotSupportedException {
        User u1 = new User("1", "zhangsan", "123456", "abc/cba/nba", new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15});

        Gson gson = new Gson();
        String u1JsontStr = gson.toJson(u1);
        System.out.println(u1JsontStr);

        User u3 = gson.fromJson(u1JsontStr, User.class);
        System.out.println(u3);
    }
}
```

## 五、Objects 工具类

`Objects` 是一个**工具类**，提供了一些方法，去完成一些功能。

常用的方法如下：

| 方法名                                             | 说明                                 |
| -------------------------------------------------- | ------------------------------------ |
| `public static String toString(Object o)`          | 获取对象的字符串表现形式             |
| `public static boolean equals(Object a, Object b)` | 先做非空判断，再比较两个对象是否相等 |
| `public static boolean isNull(Object obj)`         | 判断对象是否为 null                  |
| `public static boolean nonNull(Object obj)`        | 判断对象是否不为 null                |

### 1.equals 静态方法

当使用 `实例对象.equals` 方法时，经常会出现 `NullPointException` 空指针异常的错误，原因是比较的两个对象，其中至少有一个是 `null`；

为了避免这类错误，可以使用 `Objects.equals` 静态方法。

demo-project/base-code/Day18/src/com/kkcf/myapi/ObjectsDemo01.java

```java
package com.kkcf.myapi;

import java.util.Objects;

public class ObjectsDemo01 {
    public static void main(String[] args) {
        Student s1 = new Student("zhangsan", 23);
        Student s2 = new Student("zhangsan", 23);

        System.out.println(Objects.equals(s1, s2)); // true
    }
}
```

`Objects.equals` 静态方法细节分析：

1. 该方法的底层， 会先判断 `s1` 是否为 `null`，如果为 `null`，直接返回 `false`；
2. 如果 `s1` 不为 `null`，那么就通过 `s1` 调用该对象中的 `equals` 方法，即 `Student` 类中重写的 `equals` 方法。

`Objects.equals` 静态方法源码如下：

java/util/Objects.java

```java
public static boolean equals(Object a, Object b) {
    return (a == b) || (a != null && a.equals(b));
}
```

### 2.isNull 静态方法

`Objects.isNull` 静态方法的使用：

demo-project/base-code/Day18/src/com/kkcf/myapi/ObjectsDemo01.java

```java
package com.kkcf.myapi;

import java.util.Objects;

public class ObjectsDemo01 {
    public static void main(String[] args) {
        Student s3 = new Student();
        Student s4 = null;

        System.out.println(Objects.isNull(s3)); // false
        System.out.println(Objects.isNull(s4)); // true
    }
}
```

### 3.nonNull 静态方法

`Objects.nonNull` 静态方法的使用：

demo-project/base-code/Day18/src/com/kkcf/myapi/ObjectsDemo01.java

```java
package com.kkcf.myapi;

import java.util.Objects;

public class ObjectsDemo01 {
    public static void main(String[] args) {
        Student s3 = new Student();
        Student s4 = null;

        System.out.println(Objects.nonNull(s3)); // true
        System.out.println(Objects.nonNull(s4)); // false
    }
}
```
