# Java 常见 API 之 Arrays 工具类、Lambda 表达式、阶段练习

## 一、Arrays 工具类

Java 中的 Arrays 类，是操作数组的工具类。

Arrays 类，私有化了构造方法，不能创建它的实例对象。其中的方法都是 `static` 修饰的静态方法。

Arrays 类中常用的方法如下：

| 方法名                                                        | 说明                     |
| ------------------------------------------------------------- | ------------------------ |
| `public static String toString(数组)`                         | 把数组拼接成一个字符串   |
| `public static int binarySearch(数组, 查找的元素)`            | 二分查找法查找元素       |
| `public static int[] copyOf(原数组, 新数组长度)`              | 拷贝数组                 |
| `public static int[] copyOfRange(原数组, 起始索引. 结束索引)` | 拷贝数组（指定范围）     |
| `public static void fill(数组, 元素)`                         | 填充数组                 |
| `public static void sort(数组)`                               | 按照默认方式进行数组排序 |
| `public static void sort(数组, 排序规则)`                     | 按照指定的规则排序       |

### 1.toString 方法

`public static String toString(数组)` 方法，用于把数组拼接成一个字符串

demo-project/base-code/Day20/src/com/kkcf/arrays/MyArraysDemo01.java

```java
package com.kkcf.arrays;

import java.util.Arrays;

public class MyArraysDemo01 {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};
        String str = Arrays.toString(arr);
        System.out.println(str); // [1, 2, 3, 4, 5]
    }
}
```

### 2.binarySearch 方法

`public static int binarySearch(数组, 查找的元素)` 方法，用于在数组中二分查找法查找元素。

前提条件：

- 数组中的元素必须是**有序**的，且必须是**升序**的。
- 如果要找的元素存在，则返回真实索引；否则，返回该元素 (`-插入点索引 - 1`) 位置的索引；之所以要减 `1`，是为了避免 `-0 = 0` 的情况。

demo-project/base-code/Day20/src/com/kkcf/arrays/MyArraysDemo01.java

```java
package com.kkcf.arrays;

import java.util.Arrays;

public class MyArraysDemo01 {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};
        int index = Arrays.binarySearch(arr, 10);
        System.out.println(index); // -6
    }
}
```

### 3.copyOf 方法

`public static int[] copyOf(愿数组, 新数组长度)` 方法，用于拷贝数组。底层用的是 `System.arraycopy` 方法。

`copyOf` 方法底层会根据第二个参数，来创建新数组：新数组长度：

- 小于老数组长度，会部分拷贝。
- 等于老数组长度，会完全拷贝。
- 大于老数组长度，会补上默认初始值。

demo-project/base-code/Day20/src/com/kkcf/arrays/MyArraysDemo01.java

```java
package com.kkcf.arrays;

import java.util.Arrays;

public class MyArraysDemo01 {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};
        int[] copyArr = Arrays.copyOf(arr, 2);
        System.out.println(Arrays.toString(copyArr)); // [1, 2]
    }
}
```

### 4.copyOfRange 方法

`public static int[] copyOfRange(愿数组, 起始索引. 结束索引)` 方法，用于指定范围，拷贝数组。

这个范围的规则是“包头不包尾，包左不包右”。

demo-project/base-code/Day20/src/com/kkcf/arrays/MyArraysDemo01.java

```java
package com.kkcf.arrays;

import java.util.Arrays;

public class MyArraysDemo01 {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};
        int[] copyArr = Arrays.copyOfRange(arr, 2, 4);
        System.out.println(Arrays.toString(copyArr)); // [3, 4]
    }
}
```

### 5.fill 方法

`public static void fill(数组, 元素)` 方法，用于填充数组

demo-project/base-code/Day20/src/com/kkcf/arrays/MyArraysDemo01.java

```javascript
package com.kkcf.arrays;

import java.util.Arrays;

public class MyArraysDemo01 {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};
        Arrays.fill(arr, 100);
        System.out.println(Arrays.toString(arr)); // [100, 100, 100, 100, 100]
    }
}
```

### 5.sort 方法

`public static void sort(数组)` 方法，用于为数组排序。默认使用**快速排序**的算法，进行升序排序。

demo-project/base-code/Day20/src/com/kkcf/arrays/MyArraysDemo01.java

```java
package com.kkcf.arrays;

import java.util.Arrays;

public class MyArraysDemo01 {
    public static void main(String[] args) {
        int[] arr = {2, 5, 4, 3, 1};

        Arrays.sort(arr);

        System.out.println(Arrays.toString(arr)); // [1, 2, 3, 4, 5]
    }
}
```

`public static void sort(数组, 排序规则)` 方法，用于指定排序顺序（升序还是降序）。

该方法的前提条件：只能给**引用数据类型**的数组排序；如果数组是基本数据类型，需要变成其对应的包装类。

demo-project/base-code/Day20/src/com/kkcf/arrays/MyArraysDemo02.java

```java
package com.kkcf.arrays;

import java.util.Arrays;
import java.util.Comparator;

public class MyArraysDemo02 {
    public static void main(String[] args) {
        Integer[] arr = {2, 3, 1, 5, 6, 7, 8, 4, 9};

        Arrays.sort(arr, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });

        System.out.println(Arrays.toString(arr)); // [1, 2, 3, 4, 5, 6, 7, 8, 9]
    }
}
```

该方法底层原理：

- 利用**插入排序** + **二分查找**的方式，进行排序。
- 默认把 `0` 索引的元素，作为一个序列，当作是有序的；`1` 索引到最后的元素，认为是无序的。
- 遍历无序的序列，将遍历到的元素 A，往有序序列中进行插入，在插入的时候利用二分查找算法，找到插入位置，
- 遍历到的元素 A，与有序序列插入位置的元素进行比较，比较的规则，就是 `compare` 方法的方法体。
  - 如果 `compare` 方法，返回的是负数，则将 A 元素插入到插入点的前面。
  - 如果 `compare` 方法，返回的是正数，则将 A 元素插入到插入点的后面。
  - 如果 `compare` 方法，返回的是 0，也将 A 元素插入到插入点的后面。
  - 直到确认 A 元素的最终位置位置。

`Comparator` 接口中 `compare` 方法的形参分析：

- 参数一 `o1`，表示在无序序列中，遍历得到的元素；
- 参数二 `o2`，表示有序序列中的元素。
- 结论：`o1 - o2` 表示升序排序；`o2 - o1` 表示降序排序。

## 二、Lambda 表达式

> 在介绍 Lamda 表达式之前，需要先了解函数式编程（Fucntional Programming）的编程范式。
>
> 在 Java 中的函数式编程，可忽略面向对象的复杂语法，强调“做什么”，而不是“谁去做”。
>
> Java 中 Lambda 表达式，就是一种函数式编程思想的体现。

Lambda 表达式，是 JDK8 出现的一种新语法形式。格式为

```java
() -> {

}
```

- `()` 对应着方法的形参列表；
- `->` 是固定格式。
- `{}` 对应着方法的方法体。

Lambda 表达式，可以简化匿名内部类的书写.，然而，只能简化**函数式接口**的匿名内部类的写法。

> Java 中的函数式接口，表示有且仅有一个抽象方法的接口；
>
> 这种接口上方，可以加 `@FunctionalInterface` 注解，用于验证语法是否正确。

Lambda 表达式，主要用于省略代码，省略的原则是“可推导，即可省略”。

使用 Lambda 表达式，重构上面 `sort` 方法的使用。

demo-project/base-code/Day20/src/com/kkcf/arrays/MyArraysDemo02.java

```java
package com.kkcf.arrays;

import java.util.Arrays;
import java.util.Comparator;

public class MyArraysDemo02 {
    public static void main(String[] args) {
        Integer[] arr = {2, 3, 1, 5, 6, 7, 8, 4, 9};

        Arrays.sort(arr, (o1, o2) -> {
            return o1 - o2;
        });

        System.out.println(Arrays.toString(arr)); // [1, 2, 3, 4, 5, 6, 7, 8, 9]
    }
}
```

- `()` 表示的方法形参中的类型，可省略。

如果函数体中，只有一行代码，可简化为：

```java
package com.kkcf.arrays;

import java.util.Arrays;
import java.util.Comparator;

public class MyArraysDemo02 {
    public static void main(String[] args) {
        Integer[] arr = {2, 3, 1, 5, 6, 7, 8, 4, 9};

        Arrays.sort(arr, (o1, o2) -> o1 - o2);

        System.out.println(Arrays.toString(arr)); // [1, 2, 3, 4, 5, 6, 7, 8, 9]
    }
}
```

- 此时 `{}` 大括号、`;` 分号、`return` 关键字都可以省略；
- 如果形参只有一个，`()` 也可以省略。
- `->` 后面表达式的结果，会作为函数体的返回值。

自行定义一个函数式接口，并使用 Lambda 表达式，实现该接口中的方法，并创建该接口的匿名内部类，调用该方法。

demo-project/base-code/Day20/src/com/kkcf/lambda/LambdaDemo01.java

```java
package com.kkcf.lambda;

public class LambdaDemo01 {
    public static void main(String[] args) {

        // 方式一：传入匿名内部类的
        method(new swim() {
            @Override
            public void swimming() {
                System.out.println("正在游泳");
            }
        });

        // 方式二：使用 Lambda 表达式
        method(() -> System.out.println("正在游泳"));
    }

    public static void method(swim s) {
        s.swimming();
    }
}

@FunctionalInterface
interface swim {
    void swimming();
}
```

案例理解：按照字符串的长度，进行排序，短的在前面，长的在后面。

demo-project/base-code/Day20/src/com/kkcf/arrays/Test01.java

```java
package com.kkcf.arrays;

import java.util.Arrays;
import java.util.Comparator;

public class Test01 {
    public static void main(String[] args) {
        String[] arr = {"a", "aaaa", "aaa", "aa"};

        Arrays.sort(arr, (o1, o2) -> o1.length() - o2.length());

        System.out.println(Arrays.toString(arr));
    }
}
```

## 三、阶段练习

### 1.练习一：sort 方法的使用

定义数组，并存储一些女朋友对象，利用 `Arrays` 工具类中的 `sort` 方法，进行排序。

要求 1：对象属性有姓名、年龄、身高

要求 2：按照年龄大小进行排序；如果年龄一样，则按照身高排序；如果身高一样，则按照姓名的字母进行排序。

> `String` 类里有一个成员方法 `compareTo`，用于字符串对象，按照 ASCLL 码表顺序进行比较，

JavaBean 类 `GirlFriend`

demo-project/base-code/Day20/src/com/kkcf/training/GirlFriend.java

```java
package com.kkcf.test;

public class GirlFriend {
    private String name;
    private int age;
    private double height;

    public GirlFriend() {
    }

    public GirlFriend(String name, int age, double height) {
        this.name = name;
        this.age = age;
        this.height = height;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return "GirlFriend{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", height=" + height +
                '}';
    }
}
```

demo-project/base-code/Day20/src/com/kkcf/training/Test06.java

```java
package com.kkcf.test;

import java.util.Arrays;

public class Test06 {
    public static void main(String[] args) {
        GirlFriend kumiko = new GirlFriend("wee", 18, 1.66);
        GirlFriend kaori = new GirlFriend("yui", 19, 1.58);
        GirlFriend mayu = new GirlFriend("mio", 18, 1.66);

        GirlFriend[] gfs = {kumiko, mayu, kaori};

        Arrays.sort(gfs, (o1, o2) -> {
            int ageDiff = o1.getAge() - o2.getAge();
            int heightDiff = o1.getHeight() - o2.getHeight() == 0 ? 0
                    : o1.getHeight() - o2.getHeight() > 0 ? 1
                    : -1;

            int nameDiff = o1.getName().compareTo(o2.getName());

            return ageDiff != 0 ? ageDiff
                    : heightDiff != 0 ? heightDiff
                    : nameDiff;
        });

        System.out.println(Arrays.toString(gfs));
    }
}
```

### 2.练习二：不死神兔

有一个很有名的数学逻辑问题称为不死神兔：有一对兔子，从出生第三个月起，每个月生一对兔子，小兔子长到第三个月后，每个月又生一对兔子，假如兔子都不死，问第十二个月的兔子对数是多少？

找规律：

- 第一个月：1 对
- 第二个月：1 对
- 第三个月：2 对
- 第四个月：3 对
- 第五个月：5 对
- 第六个月：8 对
- ……

我们发现，这个问题，本质上是**斐波那契数列**。使用 Java 解题的两个思路：

思路一：循环求解；定义一个长度为 12 的数组，并初始化第一、二个元素为 1，然后利用循环求解。

demo-project/base-code/Day21/src/com/kkcf/test/Test01.java

```java
package com.kkcf.test;

import java.util.Arrays;

public class Test01 {
    public static void main(String[] args) {
        int[] arr = new int[12];
        arr[0] = 1;
        arr[1] = 1;

        for (int i = 2; i < arr.length; i++)
            arr[i] = arr[i - 1] + arr[i - 2];

        // 打印数组
        System.out.println(Arrays.toString(arr)); // [1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144]
    }
}
```

思路二：递归求解：

递归的两个核心要素：

- 找到出口；
- 找到规律。

demo-project/base-code/Day21/src/com/kkcf/test/Test02.java

```java
package com.kkcf.test;

public class Test02 {
    public static void main(String[] args) {
        int result = recursion(12);

        System.out.println(result); // 144
    }

    public static int recursion(int month) {
        if (month == 1 || month == 2)
            return 1;
        else
            return recursion(month - 1) + recursion(month - 2);
    }
}
```

### 3.练习三：猴子吃桃子

有一堆桃子，猴子第一天吃了其中的一半，并多吃了一个！

以后每天，猴子都吃当前剩下的一半，然后再多吃一个；

第 10 天（还没吃）发现只剩下一个桃子了；请问，最初总共有多少个桃子？

使用递归的方式解题，这里要使用反向递归的思路：

demo-project/base-code/Day21/src/com/kkcf/training/Test03.java

```java
package com.kkcf.test;

public class Test03 {
    public static void main(String[] args) {
        int result1 = recursion1(10, 1);
        System.out.println(result1); // 1534

        int result2 = recursion2(1);
        System.out.println(result2); // 1534
    }

    /**
     * 此方芳用于，递归计算
     *
     * @param day 最后一天的天数
     * @param count 最后一天剩下的桃子数
     * @return 第一天的桃子数
     */
    public static int recursion1(int day, int count) {
        if (day == 1) return count;

        return recursion1(day - 1, (count + 1) * 2);
    }

    /**
     * 此方法用于，递归计算
     *
     * @param day 第一天的天数
     * @return 第一天的桃子数
     */
    public static int recursion2(int day) {
        if (day == 10) return 1;

        return (recursion2(day + 1) + 1) * 2;
    }
}
```

### 4.练习四：爬楼梯

小明特别喜欢爬楼梯，他有的时候一次爬一个台阶，有的时候一次爬两个台阶。

如果这个楼梯有 20 个台阶，小明一共有多少种爬法呢？

本质上，也是一个**斐波那契数列**。

demo-project/base-code/Day21/src/com/kkcf/training/Test4.java

```java
package com.kkcf.test;

public class Test4 {
    public static void main(String[] args) {
        int result = recursion(20);

        System.out.println(result); // 10946
    }

    /**
     * 此方芳用于，递归计算爬楼梯的方式
     * 
     * @param stairs 楼梯的台阶数
     * @return 爬楼梯有的方式有几种
     */
    public static int recursion(int stairs) {
        if (stairs == 1) return 1;
        if (stairs == 2) return 2;
        return recursion(stairs - 1) + recursion(stairs - 2);
    }
}
```
