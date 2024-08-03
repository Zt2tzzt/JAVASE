# Java常用API-集合进阶-不可变集合

## 一、不可变集合

不可变集合只能进行数据的查询操作，不能被修改长度、和内容。

不可变集合，应用场景：

- 如果某个数据不能被修改，把它防御性的拷贝到不可变集合中，是个很好的实践。
- 当集合对象被不可信的库调用时，不可变形式是安全的。

在 `List`、`Set`、`Map` 接口中，都存在 `of` 静态方法，可以获取一个不可变的集合。这个集合不能进行添加、删除、修改操作。

| 方法名                                     | 说明                                   |
| ------------------------------------------ | -------------------------------------- |
| `static <E> List<E> of(E...elements)`      | 创建一个具有指定元素的 `List` 集合对象 |
| `static <E> Set<E> of(E...elements)`       | 创建一个具有指定元素的 `Set` 集合对象  |
| `static <K, V> Map<K, V> of(E...elements)` | 创建一个具有指定元素的 `Map` 集合对象  |

## 二、List 不可变集合

案例理解：创建一个 `List` 集合的不可变集合，并遍历：

demo-project/base-code/Day26/src/com/kkcf/test/Test1.java

```java
package com.kkcf.test;

import java.util.List;

public class Test1 {
    public static void main(String[] args) {
        List<String> list = List.of("张三", "李四", "王五", "赵六");

        System.out.println(list.get(0));
        System.out.println(list.get(1));
        System.out.println(list.get(2));
        System.out.println(list.get(3));

        // 遍历结合
        for (String s : list)
            System.out.println(s);

        list.forEach(s -> System.out.println(s));
    }
}
```

## 三、Set 不可变集合

创建一个不可变的 `Set` 集合：

- 初始化集合的元素必须要保持唯一。否则会报错。

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

- 细节 1：初始化 `Map` 集合中键值对对象的键，也是不能重复的，否则会报错。
- 细节 2：`Map` 里面的 `of` 方法，最多只能传递 20 个参数，即 10 个键值对（原因是 Java 方法的参数列表中，不能写两个可变参数）。

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

        for (String key : map.keySet()) {
            String val = map.get(key);
            System.out.println(val);
        }

        for (Map.Entry<String, String> entry : map.entrySet()) {
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
        }

        map.forEach((k, v) -> System.out.println(k + ":" + v));
    }
}
```

如果要传递数量大于 10 个的键值对对象，，要使用 `Map` 接口中方法 `static <K, V> Map<K, V> ofEntries(Entry<? extends K, ? extends V>... entries)`。

### 1.ofEntries 方法

使用 `ofEntries` 方法，创建 `Mao` 集合的不可变集合：

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

        immutableMap.forEach((k, v) -> System.out.println(k + ":" + v));
    }
}
```

重构上方代码：

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

传入一个普通 `Map` 集合对象实例，将它变为一个不可变的集合。

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
