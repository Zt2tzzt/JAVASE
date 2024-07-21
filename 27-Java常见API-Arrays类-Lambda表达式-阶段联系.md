# Java 常见 API 之 Arrays 工具类、Lambda 表达式、阶段练习

## 一、Arrays 工具类

Java 中的 Arrays 类，是操作数组的工具类。

Arrays 类，私有化了构造方法，不能创建它的实例对象。其中的方法都是 static 修饰的静态方法。

Arrays 类中常用的方法如下：

| 方法名                                                       | 说明                     |
| ------------------------------------------------------------ | ------------------------ |
| `public static String toString(数组)`                        | 把数组拼接成一个字符串   |
| `public static int binarySearch(数组, 查找的元素)`           | 二分查找法查找元素       |
| `public static int[] copyOf(愿数组, 新数组长度)`             | 拷贝数组                 |
| `public static int[] copyOfRange(愿数组, 起始索引. 结束索引)` | 拷贝数组（指定范围）     |
| `public static void fill(数组, 元素)`                        | 填充数组                 |
| `public static void sort(数组)`                              | 按照默认方式进行数组排序 |
| `public static void sort(数组, 排序规则)`                    | 按照指定的规则排序       |

### 1.toString 方法

toString 方法，用于把数组拼接成一个字符串

demo-project/base-code/Day20/src/com/kkcf/arrays/MyArraysDemo01.java

```java
package com.kkcf.arrays;

import java.util.Arrays;

public class MyArraysDemo01 {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};

        String str = Arrays.toString(arr);

        System.out.println(str);
    }
}
```

### 2.binarySearch 方法

binarySearch 方法，用于在数组中二分查找法查找元素。

前提条件：

- 数组中的元素必须是有序的，且必须是升序的。
- 如果要找的元素存在，则返回真实索引；否则返回该元素 (`-插入点索引 - 1`) 位置的索引；之所以 `-1`，是为了避免 `-0 = 0` 的情况。

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

copyOf  方法，用于拷贝数组。

底层用的是 `System.arraycopy` 方法。

copyOf 方法底层会根据第二个参数，来创建新数组：

- 如果新数组长度小于老数组长度，会部分拷贝。
- 如果新数组长度等于老数组长度，会完全拷贝。
- 如果新数组长度大于老数组长度，会补上默认初始值。

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

copyOfRange 方法，用于指定范围，拷贝数组。这个范围的规则是“包头不包尾，包左不包右”。

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

fill 方法，用于填充数组

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

sort 方法，用于为数组排序。

默认使用快速排序的算法，进行升序排序。

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

指定排序算法，并指定排序顺序（升序、降序）

前提条件：只能给引用数据类型的数组排序，如果数组是基本数据类型，需要变成其对应的包装类。

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

sort 方法底层原理：

- 利用插入排序 + 二分查找的方式，进行排序。
- 默认把 0 索引的数据，当作是有序的；1 索引到最后认为是无序的。
- 遍历无序的序列，将遍历到的元素 A，往有序序列中进行插入，在插入的时候利用二分查找算法，找到插入位置，
- 遍历到的元素 A，与有序序列插入位置的元素进行比较，比较的规则，就是 `compare` 方法的方法体。
  - 如果 `compare` 方法，返回的是负数，则将 A 元素插入到插入点的前面。
  - 如果 `compare` 方法，返回的是正数，则将 A 元素插入到插入点的后面。
  - 如果 `compare` 方法，返回的是 0，则将 A 元素插入到插入点的后面。
  - 直到确认 A 元素的最终位置位置。

`Comparator` 接口中 `compare` 方法的形参分析：

- 参数一 `o1`，表示在无序序列中，遍历得到的元素；
- 参数二 `o2`，表示有序序列中的元素。
- 结论：o1 - o2 表示升序排序；o2 - o1 表示降序排序。

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

- `()` 对应着方法的形参；
- `->` 固定格式
- `{}` 对应着方法的方法体。

Lambda 表达式，可以简化匿名内部类的书写.，然而，只能简化**函数式接口**的匿名内部类的写法。

> Java 中的函数式接口，表示有且仅有一个抽象方法的接口，这种接口上方可以加 `@FunctionalInterface` 注解，用于验证语法是否正确。

Lambda 表达式，主要用于省略代码，省略的原则是：可推导，可省略。

使用 Lambda 表达式，重构上面 sort 方法的使用。

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

- 此时 `{}` 大括号、`;`  分号、`return` 关键字都可以省略；`->` 后面表达式的结果，会作为函数体的返回值返回。
- 如果形参只有一个，`()` 也可以省略。

自行定义一个函数式接口，并使用 Lambda 表达式，使用改嫁口中的方法。

demo-project/base-code/Day20/src/com/kkcf/lambda/LambdaDemo01.java

```java
package com.kkcf.lambda;

public class LambdaDemo01 {
    public static void main(String[] args) {

        method(new swim() {
            @Override
            public void swimming() {
                System.out.println("正在游泳");
            }
        });

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

### 1.练习一

定义数组，并存储一些女朋友对象，利用 Arrays 中的 sort 方法，进行排序。

要求1：属性有姓名、年龄、身高

要求2：按照年龄大小进行排序，年龄一样，按照身高排序，身高一样按照姓名的字母进行排序。

> String 类里有一个成员方法 compareTo，用于字符串对象按照字典顺序进行比较，

JavaBean 类 GirlFriend

demo-project/base-code/Day20/src/com/kkcf/test/GirlFriend.java

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

demo-project/base-code/Day20/src/com/kkcf/test/Test06.java

```java
package com.kkcf.test;

import java.util.Arrays;

public class Test06 {
    public static void main(String[] args) {
        GirlFriend kumiko = new GirlFriend("kumiko", 18, 1.65);
        GirlFriend mayu = new GirlFriend("mayu", 18, 1.60);
        GirlFriend kaori = new GirlFriend("kaori", 19, 1.68);

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
