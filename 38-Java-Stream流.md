# Java Stream 流

案例理解：按照下面的要求，完成集合的创建和遍历：

- 创建一个集合，存入多个字符串元素。
- 把集合中所有以"张"开头的元素，存储到一个新的集合。
- 把"张"开头的集合中的长度为 3 的元素，存储到一个新的集合。
- 遍历上一步得到的集合。

demo-project/base-code/Day26/src/com/kkcf/stream/Demo01.java

```java
package com.kkcf.stream;

import java.util.ArrayList;
import java.util.List;

public class Demo01 {
    public static void main(String[] args) {
        //集合的批量添加
        ArrayList<String> list1 = new ArrayList<>(List.of("张三丰","张无忌","张翠山","王二麻子","张良","谢广坤"));

        //遍历 list1 把以张开头的元素添加到 list2 中。
        ArrayList<String> list2 = new ArrayList<>();
        for (String s : list1) {
            if(s.startsWith("张")){
                list2.add(s);
            }
        }

        //遍历 list2 集合，把其中长度为 3 的元素，再添加到 list3 中。
        ArrayList<String> list3 = new ArrayList<>();
        for (String s : list2) {
            if(s.length() == 3){
                list3.add(s);
            }
        }

        for (String s : list3) {
            System.out.println(s);
        }
    }
}
```

使用 Stream 流，重构上方的代码：

demo-project/base-code/Day26/src/com/kkcf/stream/Demo01.java

```java
package com.kkcf.stream;

import java.util.ArrayList;
import java.util.List;

public class Demo01 {
    public static void main(String[] args) {
        //集合的批量添加
        ArrayList<String> list1 = new ArrayList<>(List.of("张三丰","张无忌","张翠山","王二麻子","张良","谢广坤"));

        list1.stream()
                .filter(s -> s.startsWith("张"))
                .filter(s -> s.length() == 3)
                .forEach(s -> System.out.println(s));
    }
}
```

Stream 流，通常结合匿名内部类、Lambda 表达式、或者方法引用使用，用于简化集合、数组操作。

Stream 流的使用步骤：

1. 先得到一条 Stream 流（流水线），并把数据放上去。
2. 利用 Stream 流中的 API 进行各种操作。其中分为：
   - **中间方法**：方法调用完毕后，还可以调用其它方法；比如：过滤、类型转换……操作，
   - **终结方法**：这是流的最后一步，方法调用完毕后，不能调用其它方法；比如：统计、打印……操作。

![01_Stream流思想](NodeAssets/01_Stream流思想.png)

## 一、Stream 流获取

先得到一条 Stream 流（流水线），并把数据放上去。可使用如下方法：

| 集合           | 方法名                                          | 说明                          |
| -------------- | ----------------------------------------------- | ----------------------------- |
| 单列集合       | `default Stream<E> stream()`                    | `Collcetion` 接口中的默认方法 |
| 双列集合       | 无                                              | 无法直接获取 Stream 流        |
| 数组           | `public static <T> Stream<T> stream(T[] array)` | `Arrays` 工具类中的静态方法   |
| 一堆零散的数据 | `public static <T> Stream<T> of(T...values)`    | `Stream` 接口中的静态方法     |

双列集合，要使用 `keySet` 或者 `entrySet` 方法，转为键的单列集合后，再获取 Stream 流。

### 1.单列集合获取 Stream 流

获取单列集合的流水线，并遍历其中的元素；

demo-project/base-code/Day26/src/com/kkcf/stream/Demo02.java

```java
package com.kkcf.stream;

import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Stream;

public class Demo02 {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        Collections.addAll(list, "张无忌", "周芷若", "赵敏", "张强", "张三丰", "张翠山", "张良", "王二麻子", "谢广坤");

        // 获取一条 Stream 流水线，并把集合中的数据，放到这条流水线上。
        Stream<String> stream = list.stream();

        // 使用终结方法，打印以下流水线上的所有数据
        stream.forEach(s -> System.out.println(s));
    }
}
```

### 2.双列集合获取 Stream 流

获取双列集合的流水线，并遍历其中的元素：

demo-project/base-code/Day26/src/com/kkcf/stream/Demo03.java

```java
package com.kkcf.stream;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class Demo03 {
    public static void main(String[] args) {
        HashMap<String, String> map = new HashMap<>();
        map.put("001", "张三");
        map.put("002", "李四");
        map.put("003", "王五");
        map.put("004", "赵六");

        // 方式一，遍历双列集合中的键
        Stream<String> stream1 = map.keySet().stream();
        stream1.forEach(s -> System.out.println(s));

        // 方式二，遍历双列集合中的键值对
        Stream<Map.Entry<String, String>> stream2 = map.entrySet().stream();
        stream2.forEach(s -> System.out.println(s.getKey() + ":" + s.getValue()));
    }
}
```

### 3.数组获取 Stream 流

获取数组的流水线，并遍历其中的元素：

demo-project/base-code/Day26/src/com/kkcf/stream/Demo04.java

```java
package com.kkcf.stream;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Demo04 {
    public static void main(String[] args) {
        // 基本数据类型数组
        int[] arr1 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        IntStream stream1 = Arrays.stream(arr1);
        stream1.forEach(i -> System.out.println(i));

        // 引用数据类型数组
        String[] arr2 = {"a", "b", "c", "d", "e"};

        Stream<String> stream2 = Arrays.stream(arr2);
        stream2.forEach(s -> System.out.println(s));
    }
}
```

### 4.一堆零散数据获取 Stream 流

获取一堆零散数据的流水线，并遍历这些数据。

- 细节 1：`Stream.of` 静态方法的形参，是一个可变参数，可以传递一堆零散的数据，也可以传递数组；
- 细节 2：传递的数组，必须是**引用数据类型**的数组，否则，会把整个数组当作一个元素，放到 Stream 流中。

demo-project/base-code/Day26/src/com/kkcf/stream/Demo05.java

```java
package com.kkcf.stream;

import java.util.stream.Stream;

public class Demo05 {
    public static void main(String[] args) {
        Stream<String> stream1 = Stream.of("a", "b", "c", "d", "e");

        stream1.forEach(s -> System.out.println(s));

        Stream<Integer> stream2 = Stream.of(new Integer[]{1, 2, 3, 4, 5});

        stream2.forEach(i -> System.out.println(i));
    }
}
```

## 二、Stream 流中间方法

Stream 流的中间方法，有如下几种：

| 方法名                                                | 说明                                                         |
| ----------------------------------------------------- | ------------------------------------------------------------ |
| `Stream<T> filter(Predicate<? extends T> predicate)`  | 过滤                                                         |
| `Stream<T> limit(long maxSize)`                       | 获取前几个元素                                               |
| `Stream<T> skip(long n)`                              | 跳过前几个元素                                               |
| `Stream<T> distinct()`                                | 元素去重，依赖元素对象的 `hashCode` 和 `equals` 方法         |
| `static <T> Stream<T> concat(Stream a, Stream b)`     | 合并 a 和 b 两个流为一个流，注意两个流类型要保持一致         |
| `Stream<R> map(Function<T, R> mapper)`                | 转换流中的数据类型                                           |
| `Stream<T> sorted()`                                  | 升序排序流中的数据                                           |
| `Stream<T> sorted(Comparator<? super T> comparator)`  | 自定义排序流中的数据                                         |
| `T reduce(T identity, BinaryOperator<T> accumulator)` | 使用提供的标识值和关联累积函数对此流的元素执行缩减，并返回缩减后的值 |

流的中间方法，注意事项：

注意 1：中间方法，返回新的 Stream 流，原来的 Stream 流只能使用一次，

因此，Stream 流在使用中间方法时，一般使用**链式编程**。

demo-project/base-code/Day26/src/com/kkcf/stream/Demo06.java

```java
package com.kkcf.stream;

import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Stream;

public class Demo06 {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        Collections.addAll(list,"张无忌","张强","张三丰","张翠山","张良","王二麻子","谢广坤");

        Stream<String> stream1 = list.stream();
        Stream<String> stream2 = stream1.filter(new Predicate<String>() {
            @Override
            public boolean test(String s) {
                return s.startsWith("张");
            }
        });
        stream2.forEach(s -> System.out.println(s));

        // 此时无法再使用 stream1 流
        Stream<String> stream3 = stream1.filter(s -> s.startsWith("张")); // 报错：stream has already been operated upon or closed
    }
}
```

注意 2：修改 Stream 流中的数据，不会影响原来集合或者数组中的数据。

使用 Lambda 表达式，重构 `filter` 方法。

demo-project/base-code/Day26/src/com/kkcf/stream/Demo06.java

```java
package com.kkcf.stream;

import java.util.ArrayList;
import java.util.Collections;

public class Demo06 {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        Collections.addAll(list,"张无忌","张强","张三丰","张翠山","张良","王二麻子","谢广坤");

        list.stream()
            .filter(s -> s.startsWith("张"))
            .forEach(s -> System.out.println(s));

        System.out.println(list); // [张无忌, 张强, 张三丰, 张翠山, 张良, 王二麻子, 谢广坤]
    }
}
```

### 1.limit、skip 方法

`Stream<T> limit(long maxSize)` 方法、`Stream<T> skip(long n)` 方法的使用。

demo-project/base-code/Day26/src/com/kkcf/stream/Demo07.java

```java
package com.kkcf.stream;

import java.util.ArrayList;
import java.util.Collections;

public class Demo07 {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        Collections.addAll(list,"张无忌","张强","张三丰","张翠山","张良","王二麻子","谢广坤");

        list.stream().limit(3).forEach(s -> System.out.println(s));
        // 张无忌
        // 张强
        // 张三丰

        list.stream().skip(4).forEach(s -> System.out.println(s));
        // 张良
        // 王二麻子
        // 谢广坤

        // 获取“张三丰”、“张翠山”、“张良”这三个元素
        list.stream().skip(2).limit(3).forEach(s -> System.out.println(s));
    }
}
```

### 2.distinct 方法

`Stream<T> distinct()` 方法的使用：

- 在底层利用 `HashSet` 集合去重；
- 所以需要重写 Stream 流中元素的 `hashCode`、`equals` 方法。

demo-project/base-code/Day26/src/com/kkcf/stream/Demo08.java

```java
package com.kkcf.stream;

import java.util.ArrayList;
import java.util.Collections;

public class Demo08 {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        Collections.addAll(list, "张无忌", "张无忌", "张无忌", "周芷若", "赵敏", "张强", "张三丰", "张翠山", "张良", "王二麻子", "谢广坤");

        list.stream()
            .distinct()
            .forEach(s -> System.out.println(s));
    }
}
```

### 3.map 方法

`Stream<R> map(Function<T, R> mapper)` 方法，用于映射转换流中的数据类型。

案例理解：映射集合中字符串中的数字部分，并遍历。

使用 Stream 流的 `map` 方法，结合匿名内部类的方式：

demo-project/base-code/Day26/src/com/kkcf/stream/Demo10.java

```java
package com.kkcf.stream;

import java.util.ArrayList;
import java.util.Collections;
import java.util.function.Function;

public class Demo10 {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        Collections.addAll(list, "张无忌-15", "张强-20", "张三丰-30", "张翠山-40", "张良-50", "王二麻子-60", "谢广坤-70");

        list.stream().map(new Function<String, Integer>() {
            @Override
            public Integer apply(String s) {
                String[] arr = s.split("-");
                Integer i = Integer.parseInt(arr[1]);
                return i;
            }
        }).forEach(i -> System.out.println(i));
    }
}
```

使用 Lambda 表达式，优化上面的代码：

demo-project/base-code/Day26/src/com/kkcf/stream/Demo10.java

```java
package com.kkcf.stream;

import java.util.ArrayList;
import java.util.Collections;

public class Demo10 {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        Collections.addAll(list, "张无忌-15", "张强-20", "张三丰-30", "张翠山-40", "张良-50", "王二麻子-60", "谢广坤-70");

        list.stream()
                .map(s -> Integer.parseInt(s.split("-")[1]))
                .forEach(i -> System.out.println(i));
    }
}
```

### 4.concat 静态方法

`Stream` 接口中的静态方法 `static <T> Stream<T> concat(Stream a, Stream b)` 方法，用于拼接流中的元素，返回一个新的流。

demo-project/base-code/Day26/src/com/kkcf/stream/Demo09.java

```java
package com.kkcf.stream;

import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Stream;

public class Demo09 {
    public static void main(String[] args) {
        ArrayList<String> list1 = new ArrayList<>();
        Collections.addAll(list1, "张无忌", "周芷若", "赵敏", "张强", "张三丰", "张翠山", "张良", "王二麻子", "谢广坤");

        ArrayList<String> list2 = new ArrayList<>();
        Collections.addAll(list2, "你好", "青蛙");

        Stream<String> concatStream = Stream.concat(list1.stream(), list2.stream());
        concatStream.forEach(s -> System.out.println(s));
    }
}
```

## 三、Stream 流终结方法

Stream 流有如下终结方法：

| 方法名                          | 说明                       |
| ------------------------------- | -------------------------- |
| `void forEach(Consumer action)` | 遍历                       |
| `long count()`                  | 统计                       |
| `toArray()`                     | 收集流中的数据，放到数组中 |
| `collect()`                     | 收集流中的数据，放到集合中 |

### 1.forEach 方法

`void forEach(Consumer action)` 方法，用于遍历流中的数据。

使用 Stream 流的 `forEach` 方法，结合匿名内部类的方式：

demo-project/base-code/Day26/src/com/kkcf/stream/Demo11.java

```java
package com.kkcf.stream;

import java.util.ArrayList;
import java.util.Collections;
import java.util.function.Consumer;

public class Demo11 {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        Collections.addAll(list, "张无忌", "张强", "张三丰", "张翠山", "张良", "王二麻子", "谢广坤");

        list.stream().forEach(new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        });

        list.stream().forEach(s -> System.out.println(s));
    }
}
```

### 2.count 方法

`long count()` 方法，用于统计流中的数据。

demo-project/base-code/Day26/src/com/kkcf/stream/Demo11.java

```java
package com.kkcf.stream;

import java.util.ArrayList;
import java.util.Collections;

public class Demo11 {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        Collections.addAll(list, "张无忌", "张强", "张三丰", "张翠山", "张良", "王二麻子", "谢广坤");

        long count = list.stream().count();
        System.out.println(count); // 7
    }
}
```

### 3.toArray 方法

`toArray()` 方法，用于将流中的数据转为数组。

- `toArray` 方法的参数的作用，负责创建一个指定类型的数组；
- `toArray` 方法的底层，会依次得到流里面的每一个数据，并把数据放到数组中。
- `toArray` 方法的返回值，是一个装着流里面所有数据的数组。

使用 `toArray` 方法，将流中的数据，转为 `Object` 类型的数组，不指定具体类型：

demo-project/base-code/Day26/src/com/kkcf/stream/Demo12.java

```java
package com.kkcf.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.function.IntFunction;

public class Demo12 {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        Collections.addAll(list, "张无忌", "张强", "张三丰", "张翠山", "张良", "王二麻子", "谢广坤");

        Object[] arr1 = list.stream().toArray();
        System.out.println(Arrays.toString(arr1)); // [张无忌, 张强, 张三丰, 张翠山, 张良, 王二麻子, 谢广坤]
    }
}
```

使用 `toArray` 方法，将流中的数据，转为数组，指定数组的类型：

使用 `toArray` 方法，结合匿名内部类的方式：

```java
package com.kkcf.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.function.IntFunction;

public class Demo12 {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        Collections.addAll(list, "张无忌", "张强", "张三丰", "张翠山", "张良", "王二麻子", "谢广坤");

        // 转数组时，指定数组的类型
        // IntFunction 的泛型，指定数组的具体类型
        // apply 方法的形参 int value：表示流中数据的个数，要跟数组的长度保持一致
        // apply 方法的返回值：表示具体类型的数组
        String[] array = list.stream().toArray(new IntFunction<String[]>() {
            @Override
            public String[] apply(int value) {
                return new String[value];
            }
        });
        System.out.println(Arrays.toString(array)); // [张无忌, 张强, 张三丰, 张翠山, 张良, 王二麻子, 谢广坤]
    }
}
```

使用 `toArray` 方法，将流中的数据，转为数组，指定类型：

使用 `toArray` 方法，结合 Lambda 表达式的方式：

```java
package com.kkcf.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.function.IntFunction;

public class Demo12 {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        Collections.addAll(list, "张无忌", "张强", "张三丰", "张翠山", "张良", "王二麻子", "谢广坤");

        // 使用 Lambda 表达式的方案
        String[] arr = list.stream().toArray(i -> new String[i]);
        System.out.println(Arrays.toString(arr)); // [张无忌, 张强, 张三丰, 张翠山, 张良, 王二麻子, 谢广坤]
    }
}
```

### 4.collect 方法

`collect()` 方法，用于将流中的数据转为集合。

#### 1.List 集合收集

为 `collect()` 方法，传入 `Collectors.toList` 的返回值；

案例理解：收集 `ArrayList` 集合中，的所有带“男”的元素，放到一个新的 `ArrayList` 集合中。

demo-project/base-code/Day26/src/com/kkcf/stream/Demo13.java

```java
package com.kkcf.stream;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Demo13 {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        Collections.addAll(list, "张无忌-男-16", "周芷若-女-17", "赵敏-女-18", "张强-男-19", "张三丰-男-20", "张翠山-男-21", "张良-男-22", "王二麻子-男-23", "谢广坤-男-24");

        // 收集 List 集合当中，所有的男性，放入集合中
        List<String> newList = list.stream()
                .filter(s -> "男".equals(s.split("-")[1]))
                .collect(Collectors.toList());

        System.out.println(newList);
    }
}
```

- `Collectors` 是一个工具类，里面有 `toList` 静态方法，用于创建一个 `ArrayList` 列表集合，

> 一般用字面量或常量，来调用 `equals` 方法，这样能防止空指针异常。

#### 2.Set 集合收集

为 `collect()` 方法，传入 `Collectors.toSet` 方法的返回值；

案例理解：收集集合中的所有带“女”的元素，到一个新的 `HashSet` 集合中。

- `Set` 集合可以去重重复元素。

demo-project/base-code/Day26/src/com/kkcf/stream/Demo13.java

```java
package com.kkcf.stream;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Demo13 {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        Collections.addAll(list, "张无忌-男-16", "周芷若-女-17", "赵敏-女-18", "张强-男-19", "张三丰-男-20", "张翠山-男-21", "张良-男-22", "王二麻子-男-23", "谢广坤-男-24");

        // 收集 List 集合中，所有带“女”的元素，到一个新的 HashSet 集合中。
        Set<String> newSet = list.stream()
                        .filter(s -> "女".equals(s.split("-")[1]))
                        .collect(Collectors.toSet());

        System.out.println(newSet); // [周芷若-女-17, 赵敏-女-18]
    }
}
```

#### 3.Map 集合收集

为 `collect()` 方法，传入 `Collectors.toMap` 的返回值；

案例理解：收集集合中的所有带“女”的元素，到一个新的 `HashSet` 集合中。

- 细节 1：收集到 `Map` 集合中的键值对对象元素，键不能重复。否则会报错。

使用  `Collectors.toMap`  方法，结合匿名内部类的方式：

demo-project/base-code/Day26/src/com/kkcf/stream/Demo13.java

```java
package com.kkcf.stream;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Demo13 {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        Collections.addAll(list, "张无忌-男-16", "周芷若-女-17", "赵敏-女-18", "张强-男-19", "张三丰-男-20", "张翠山-男-21", "张良-男-22", "王二麻子-男-23", "谢广坤-男-24");

        // 收集 List 集合中，所有带“女”的元素，到一个新的 HashMap 集合中，键为姓名，值为年龄。
        Map<String, Integer> newMap = list.stream()
                .filter(s -> "女".equals(s.split("-")[1]))
                .collect(Collectors.toMap(new Function<String, String>() {
                    @Override
                    public String apply(String s) {
                        return s.split("-")[0];
                    }
                }, new Function<String, Integer>() {
                    @Override
                    public Integer apply(String s) {
                        return Integer.parseInt(s.split("-")[2]);
                    }
                }));

        System.out.println(newMap);
    }
}
```

`Collectors` 工具类中的 `toMap` 方法：

- 参数一：表示键的生成规则；
  - `Function` 接口泛型一：表示流中数据的类型；泛型二：表示 `Map` 集合中**键**的类型。
  - `apply` 方法形参，依次表示流里面的每一个数据；
  - `apply` 方法体，是生成键的代码。
- 参数二：表示值的生成规则。
  - `Function` 接口泛型一：表示流中数据的类型；泛型二：表示 `Map` 集合中**值**的类型。
  - `apply` 方法形参，依次表示流里面的每一个数据；
  - `apply` 方法体，是生成值的代码。

使用  `Collectors.toMap`  方法，集合 Lambda 表达式的方式。

demo-project/base-code/Day26/src/com/kkcf/stream/Demo13.java

```java
package com.kkcf.stream;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

public class Demo13 {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        Collections.addAll(list, "张无忌-男-16", "周芷若-女-17", "赵敏-女-18", "张强-男-19", "张三丰-男-20", "张翠山-男-21", "张良-男-22", "王二麻子-男-23", "谢广坤-男-24");

        Map<String, Integer> newMap = list.stream()
                .filter(s -> "女".equals(s.split("-")[1]))
                .collect(Collectors.toMap(
                        s -> s.split("-")[0],
                        s -> Integer.parseInt(s.split("-")[2]))
                );

        System.out.println(newMap);
    }
}
```

## 四、Stream 流练习

### 1.练习一

定义一个集合，并添加一些整数 1, 2, 3, 4, 5, 6, 7, 8, 9, 10，过滤集合中的奇数，只保留偶数。并将结果保存起来。

demo-project/base-code/Day26/src/com/kkcf/test/Test3.java

```java
package com.kkcf.test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Test3 {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));

        List<Integer> newList = list.stream()
                .filter(x -> x % 2 == 0)
                .collect(Collectors.toList());

        System.out.println(newList); // [2, 4, 6, 8, 10]
    }
}
```

### 2.练习二

创建一个 `ArrayList` 集合，并添加 "zhangsan,23"、"lisi,24"，"wangwu,25" 这些字符串，字符串中前面是姓名，后面是年龄：

保留年龄大于等于 24 岁的人，并将结果收集到 `Map` 集合中，姓名为键，年龄为值。

demo-project/base-code/Day26/src/com/kkcf/test/Test4.java

```java
package com.kkcf.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Test4 {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>(List.of("zhangsan,23", "lisi,24", "wangwu,25"));

        Map<String, Integer> newMap = list.stream()
                .filter(s -> Integer.parseInt(s.split(",")[1]) >= 24)
                .collect(Collectors.toMap(s -> s.split(",")[0], s -> Integer.parseInt(s.split(",")[1])));

        System.out.println(newMap); // {lisi=24, wangwu=25}
    }
}
```

### 3.练习三

有两个 `ArrayList` 集合：第一个集合中，存储 6 名男演员的名字和年龄；第二个集合中，存储 6 名女演员的名字和年龄；

姓名和年龄中间用逗号隔开；比如："张三,23"

要求完成如下的操作：

1. 男演员只要名字为三个字的前两人；
2. 女演员只要姓杨的，并且不要第一个；
3. 把过滤后的男演员姓名，和女演员姓名合并到一起；
4. 将上一步的演员信息封装成 `Actor` 对象；
5. 将所有的演员对象，都保存到 `List` 集合中；

演员 `Actor` 类，属性有：`name`、`age`

demo-project/base-code/Day26/src/com/kkcf/test/Test5.java

```java
package com.kkcf.test;

import com.kkcf.javabean.Actor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Test5 {
    public static void main(String[] args) {
        ArrayList<String> maleList = new ArrayList<>(List.of("蔡坤坤,23", "叶猴先,23", "刘不甜,22", "吴签,24", "谷嘉,30", "萧良梁,27"));
        ArrayList<String> femaleList = new ArrayList<>(List.of("赵晓颖,23", "杨颖,36", "高元元,43", "张天天,31", "刘诗,35", "杨小密,33"));

        Stream<String> stream1 = maleList.stream()
                .filter(s -> s.split(",")[0].length() == 3)
                .limit(2);

        Stream<String> stream2 = femaleList.stream()
                .filter(s -> s.split(",")[0].startsWith("杨"))
                .skip(1);

        List<Actor> newList = Stream.concat(stream1, stream2)
                .map(s -> new Actor(s.split(",")[0], Integer.parseInt(s.split(",")[1])))
                .collect(Collectors.toList());

        System.out.println(newList); // [Actor{name='蔡坤坤', age=23}, Actor{name='叶猴先', age=23}, Actor{name='杨小密', age=33}]
    }
}
```
