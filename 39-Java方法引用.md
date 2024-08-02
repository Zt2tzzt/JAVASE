# Java 方法引用

Java 方法引用，就是把已经有的方法拿过来用，当作函数式接口中抽象方法的方法体。

Java 方法引用的要求：

- 引用处，必须是函数式接口；
- 被引用的方法，必须已经存在；
- 被引用的方法，形参和返回值与抽象方法保持一致；
- 被引用的方法，功能要满足当前需求；

案例理解：为一个数组进行排序：使用匿名内部类，Lambda 表达式，和方法引用的写法：

demo-project/base-code/Day26/src/com/kkcf/methodref/Demo01.java

```java
package com.kkcf.methodref;

import java.util.Arrays;
import java.util.Comparator;

public class Demo01 {
    public static void main(String[] args) {
        Integer[] arr = {4, 3, 8, 6, 9, 7, 2, 1};

        // 匿名内部类
        Arrays.sort(arr, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });

        // Lambda 表达式
        Arrays.sort(arr, (o1, o2) -> o1 - o2);

        // 方法引用
        Arrays.sort(arr, Demo01::compare);
        System.out.println(Arrays.toString(arr));
    }

    public static int compare(Integer o1, Integer o2) {
        return o1 - o2;
    }
}
```

`::` 双冒号，是方法引用符，

被引用的方法，可以是自己写的，也可以是第三方工具类写的。