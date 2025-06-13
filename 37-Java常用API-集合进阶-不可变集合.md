# Java 常用 API 集合进阶之不可变集合

## 一、不可变集合

不可变集合只能进行**数据的查询**操作，不能进行数据的添加、删除、修改操作；

不可变集合，应用场景：

- 如果某个数据不能被修改，把它防御性的拷贝到不可变集合中，是个很好的实践。
- 当集合对象，被不可信的库调用时，不可变形式是安全的。

在 `List`、`Set`、`Map` 接口中，都存在 `of` 静态方法，可以获取一个不可变的集合。

| 方法名                                     | 说明                                         |
| ------------------------------------------ | -------------------------------------------- |
| `static <E> List<E> of(E...elements)`      | 创建一个具有指定元素的 `List` 不可变集合对象 |
| `static <E> Set<E> of(E...elements)`       | 创建一个具有指定元素的 `Set` 不可变集合对象  |
| `static <K, V> Map<K, V> of(E...elements)` | 创建一个具有指定元素的 `Map` 不可变集合对象  |

## 二、List 不可变集合

案例理解：创建一个 `List` 集合的不可变集合，并遍历：

demo-project/base-code/Day26/src/com/kkcf/test/Test1.java

```java
package com.kkcf.test;

import java.util.List;

public class Test1 {
    public static void main(String[] args) {
        List<String> list = List.of("张三", "李四", "王五", "赵六");

        // 遍历结合
        for (String s : list)
            System.out.println(s);

        list.forEach(s -> System.out.println(s));
    }
}
```

## 2.1.List 集合初始化

方案一：使用 `List.of` 静态方法

```java
List<String> list = new ArrayList(List.of("张三", "李四", "王五", "赵六"))
```

方案二：使用 `Arrays.asList` 静态方法

```java
ArrayList<Integer> list = new ArrayList<>(Arrays.asList(10, 5, 20, 50, 100, 200, 500, 800, 2, 80, 300, 700));
```

## 三、Set 不可变集合

创建一个不可变的 `Set` 集合：

- 细节 1：初始化集合的元素，必须要保持唯一。否则会报错。

demo-project/base-code/Day26/src/com/kkcf/test/Test1.java

```java
package com.kkcf.test;

import java.util.Set;

public class Test1 {
    public static void main(String[] args) {
        Set<String> set = Set.of("张三", "李四", "王五", "赵六");

        for (String s : set)
            System.out.println(s);

        set.forEach(s -> System.out.println(s));
    }
}
```

## 四、Map 不可变集合

创建一个不可变的 `Map` 集合

- 细节 1：初始化 `Map` 集合中键值对对象的键，必须要保持唯一，否则会报错。
- 细节 2：`Map` 里面的 `of` 方法，最多只能传递 20 个参数，即 10 个键值对，原因是：
  - `Map` 集合中存储的是键值对对象，需要存储键和值，然而，Java 方法的参数列表中，不能写两个可变参数。

demo-project/base-code/Day26/src/com/kkcf/test/Test1.java

```java
package com.kkcf.test;

import java.util.Map;

public class Test1 {
    public static void main(String[] args) {
        Map<String, String> map = Map.of("张三", "南京", "李四", "北京", "王五", "上海",
                "赵六", "广州", "孙七", "深圳", "周八", "杭州",
                "吴九", "宁波", "郑十", "苏州", "刘一", "无锡",
                "陈二", "嘉兴");

        // 遍历 Map 集合
        for (String key : map.keySet()) {
            String val = map.get(key);
            System.out.println(val);
        }

        // 遍历 Map 集合
        for (Map.Entry<String, String> entry : map.entrySet()) {
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
        }

        // 遍历 Map 集合
        map.forEach((k, v) -> System.out.println(k + ":" + v));
    }
}
```

如果要传递数量大于 10 个的键值对对象，要使用 `Map` 接口中方法 `static <K, V> Map<K, V> ofEntries(Entry<? extends K, ? extends V>... entries)`。

### 1.ofEntries 方法

使用 `ofEntries` 方法，创建 `Mao` 集合的**不可变集合**：

demo-project/base-code/Day26/src/com/kkcf/test/Test2.java

```java
package com.kkcf.test;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Test2 {
    public static void main(String[] args) {
        HashMap<String, String> map = new HashMap<>();
        map.put("张三", "南京");
        map.put("李四", "北京");
        map.put("王五", "上海");
        map.put("赵六", "北京");
        map.put("孙七", "深圳");
        map.put("周八", "杭州");
        map.put("吴九", "宁波");
        map.put("郑十", "苏州");
        map.put("刘一", "无锡");
        map.put("陈二", "嘉兴");
        map.put("aaa", "111");

        Set<Map.Entry<String, String>> entrySet = map.entrySet();

        Map.Entry[] arr = new Map.Entry[0];

        // toArray 方法，在底层会比较集合长度，数组长度两者的大小
        //  - 集合的长度 > 数组的长度，会根据实际 entry 对象的个数，重新创建数组；
        //  - 集合的长度 <= 数组的长度：不会创建新的数组，而是直接用原数组。
        Map.Entry[] entries = entrySet.toArray(arr);

        // 创建不可变集合
        Map immutableMap = Map.ofEntries(entries);

        // 遍历 Map 集合
        immutableMap.forEach((k, v) -> System.out.println(k + ":" + v));
    }
}
```

重构上方代码，使用一行代码创建 Map 不可变集合：

demo-project/base-code/Day26/src/com/kkcf/test/Test2.java

```java
package com.kkcf.test;

import java.util.HashMap;
import java.util.Map;

public class Test2 {
    public static void main(String[] args) {
        HashMap<String, String> map = new HashMap<>();
        map.put("张三", "南京");
        map.put("李四", "北京");
        map.put("王五", "上海");
        map.put("赵六", "北京");
        map.put("孙七", "深圳");
        map.put("周八", "杭州");
        map.put("吴九", "宁波");
        map.put("郑十", "苏州");
        map.put("刘一", "无锡");
        map.put("陈二", "嘉兴");
        map.put("aaa", "111");

        // 一行代码传，创建不可变集合
        Map<String, String> immutableMap = Map.ofEntries(map.entrySet().toArray(new Map.Entry[0]));

        immutableMap.put("bbb", "222"); // 报错

        immutableMap.forEach((k, v) -> System.out.println(k + ":" + v));
    }
}
```

### 2.copyOf 方法

JDK10 的新特性：`Map` 接口的 `copyOf` 方法；

传入一个普通 `Map` 集合对象实例，将它变为一个**不可变的集合**。

再次重构上方代码：

demo-project/base-code/Day26/src/com/kkcf/test/Test2.java

```java
package com.kkcf.test;

import java.util.HashMap;
import java.util.Map;

public class Test2 {
    public static void main(String[] args) {
        HashMap<String, String> map = new HashMap<>();
        map.put("张三", "南京");
        map.put("李四", "北京");
        map.put("王五", "上海");
        map.put("赵六", "北京");
        map.put("孙七", "深圳");
        map.put("周八", "杭州");
        map.put("吴九", "宁波");
        map.put("郑十", "苏州");
        map.put("刘一", "无锡");
        map.put("陈二", "嘉兴");
        map.put("aaa", "111");

        Map<String, String> vimmutableMap = Map.copyOf(map);

        vimmutableMap.put("bbb", "222"); // 报错

        vimmutableMap.forEach((k, v) -> System.out.println(k + ":" + v));
    }
}
```

## 五、HashMap 初始化（补充）

补充：初始化一个 HashMap 集合，并放入一些数据：

方法一：使用匿名内部类初始化

demo-project/base-code/Day26/src/com/kkcf/test/Test6.java

```java
package com.kkcf.test;

import java.util.HashMap;
import java.util.Map;

public class Test6 {
    public static void main(String[] args) {
        // 使用匿名内部类初始化 HashMap
        Map<String, Integer> map = new HashMap<String, Integer>() {{
            put("apple", 1);
            put("banana", 2);
            put("cherry", 3);
        }};

        // 打印 HashMap 的内容
        for (String key : map.keySet()) {
            System.out.println(key + ": " + map.get(key));
        }
    }
}
```

- 在上面这个示例中，我们使用匿名内部类来初始化 `HashMap` 并添加初始数据。
- 注意，双大括号 `{{ }}` 语法是为了创建一个**匿名子类**，并在其**构造代码块**（在构造函数执行前执行）中初始化数据。

方法二：使用 `Map.of` 方法（Java 9 及以上）

```java
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        // 使用 Map.of 方法初始化 HashMap，然后转换为可变的 HashMap
        Map<String, Integer> map = new HashMap<>(Map.of(
            "apple", 1,
            "banana", 2,
            "cherry", 3
        ));

        // 打印 HashMap 的内容
        for (String key : map.keySet()) {
            System.out.println(key + ": " + map.get(key));
        }
    }
}
```

- 在这个示例中，我们使用 `Map.of` 方法来创建一个不可变的 `Map`。
- 如果需要一个可变的 `HashMap` 集合，你可以使用 `new HashMap<>(Map.of(...))` 来初始化。
