# Java 方法引用

Java 方法引用，是 JDK8 版本引入的特性。

Java 方法引用，就是把**已经存在**的方法拿过来用，当作**函数式接口**中抽象方法的方法体。

## 一、Java 方法引用要求

Java 方法引用，有如下要求：

- 引用处，必须是要传入**函数式接口**的实现类对象的地方；
- 被引用的方法，必须**已经存在**；
- 被引用的方法，它的**形参列表**和**返回值**与函数式接口中抽象方法的保持一致；
- 被引用的方法，功能要满足当前需求；

案例理解：为一个数组进行升序排序：分别使用匿名内部类、Lambda 表达式、方法引用的写法：

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

    // 定义一个方法，用于方法引用
    public static int compare(Integer o1, Integer o2) {
        return o1 - o2;
    }
}
```

`::` 双冒号，是**方法引用符**，

被引用的方法，可以是自己写的，也可以是第三方工具类中存在的。

## 二、Java 方法引用的分类

### 1.引用静态方法

格式：`类名::静态方法`；

示例：`Integer::parseInt`

案例理解：集合中有一些 String 类型的数字，将它们转为 int 类型。

实现方式一：使用 Stream 流的 `map` 方法，结合匿名内部类的方式实现：

demo-project/base-code/Day26/src/com/kkcf/methodref/Test1.java

```java
package com.kkcf.methodref;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Test1 {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>(List.of("1", "2", "3", "4", "5"));

        List<Integer> newList = list.stream().map(new Function<String, Integer>() {
            @Override
            public Integer apply(String s) {
                return Integer.parseInt(s);
            }
        }).collect(Collectors.toList());

        System.out.println(newList); // [1, 2, 3, 4, 5]
    }
}
```

实现方式二：使用 Stream 流的 `map` 方法，结合方法引用的方式实现：

- 引用 `Integer.parseInt` 静态方法。

demo-project/base-code/Day26/src/com/kkcf/methodref/Test1.java

```java
package com.kkcf.methodref;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Test1 {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>(List.of("1", "2", "3", "4", "5"));

        List<Integer> newList = list.stream()
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        System.out.println(newList); // [1, 2, 3, 4, 5]
    }
}
```

### 2.引用成员方法

格式：`对象::成员方法`；示例：

- 引用本类的成员方法，格式：`this::方法名`
- 引用父类的成员方法，格式：`super::方法名`
- 引用其它类的成员方法，格式：`其它类实例对象::方法名`

> 注意：静态方法中，没有 `this`、`super` 关键字；
>
> 在**静态方法**中，使用方法引用时，不能用 `this::xxx` 或者 `super::xxx` 的写法。

#### 1.引用其它类的成员方法

案例理解：集合中有一些 String 类型的名字，按照要求过滤数据。

实现方式一：使用 Stream 流的 `filter` 方法，结合匿名内部类的方式实现：

demo-project/base-code/Day26/src/com/kkcf/methodref/Test2.java

```java
package com.kkcf.methodref;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Test2 {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>(List.of("张三丰", "周芷若", "张无忌", "张翠山", "赵敏"));

        List<String> newList = list.stream()
                .filter(new Predicate<String>() {
                    @Override
                    public boolean test(String s) {
                        return s.startsWith("张") && s.length() == 3;
                    }
                }).collect(Collectors.toList());

        System.out.println(newList); // [张三丰, 张无忌, 张翠山]
    }
}
```

实现方式二：使用 Stream 流的 `filter` 方法，结合方法引用的方式实现：

创建一个类 `StringOperator`，里面有一个 `stringOperator` 方法；

StringOperator 类：

demo-project/base-code/Day26/src/com/kkcf/methodref/StringOperator.java

```java
package com.kkcf.methodref;

public class StringOperator {
    public boolean stringOperator(String s) {
        return s.startsWith("张") && s.length() == 3;
    }
}
```

在测试类中，引用 `StringOperator` 类的 `stringOperator` 方法。：

demo-project/base-code/Day26/src/com/kkcf/methodref/Test2.java

```java
package com.kkcf.methodref;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Test2 {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>(List.of("张三丰", "周芷若", "张无忌", "张翠山", "赵敏"));

        // 创建 StringOperator 类实例对象
        StringOperator so = new StringOperator();

        List<String> newList = list.stream()
                .filter(so::stringJudge)
                .collect(Collectors.toList());

        System.out.println(newList); // [张三丰, 张无忌, 张翠山]
    }
}
```

#### 2.引用本类的成员方法

案例理解，在 Java GUI 界面中，点击事件的方法引用写法：

创建一个类 `LoginJFrame` 类，继承自 `JFrame` 类， 表示登录页面；并为它实现 `ActionListener` 接口，用于在页面中监听鼠标点击的事件。

demo-project/base-code/Day26/src/com/kkcf/a02game/LoginJFrame.java

```java
public class LoginJFrame extends JFrame implements ActionListener {
    JButton go = new JButton("Go");

    public LoginJFrame() {
        //……

        //添加组件
        initView();
    }

    //添加组件
    public void initView() {
        // ……

        go.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == go) {
            System.out.println("go按钮被点击了");
        }
    }
}
```

使用方法引用，重构上方代码：

在 `LoginJFrame` 类中，定义 `method1` 方法，用于 `ActionListener` 接口中 `actionPerformed` 方法的方法引用。

> `ActionListener` 接口，虽然没有加函数式接口注解 `@FunctionInterface`，但它其中只有一个抽象方法 `actionPerformed`，所以可以使用方法引用。

demo-project/base-code/Day26/src/com/kkcf/a02game/LoginJFrame.java

```java
public class LoginJFrame extends JFrame implements ActionListener {
    JButton go = new JButton("Go");

    public LoginJFrame() {
        //……

        //添加组件
        initView();
    }

    //添加组件
    public void initView() {
        // ……

        go.addActionListener(this::method1);
    }

    public void method1(ActionEvent e) {
        System.out.println("go按钮被点击了");
    }
}
```

- 在 `method1` 方法中，不用再进行 `if (e.getSource() == go)` 判断；
- 因为 `method1` 方法，是固定用于 `go.addActionListener` 的方法引用。

#### 3.引用父类的成员方法

使用父类的成员方法的方法引用，重构上方的方法：

创建一个 `MyJFrame` 类，继承自 `JFrame` 类：

在其中定义 `method1` 方法，用于 `ActionListener` 接口中 `actionPerformed` 的方法引用。

demo-project/base-code/Day26/src/com/kkcf/a02game/MyJFrame.java

```java
package com.kkcf.a02game;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class MyJFrame extends JFrame {
    public void method1(ActionEvent e) {
        System.out.println("go按钮被点击了");
    }
}
```

再用 `LoginJFrame` 类，继承自 `MyJFrame` 类；

并为里面的 `go.addActionListener` 方法，使用方法引用。

demo-project/base-code/Day26/src/com/kkcf/a02game/LoginJFrame.java

```java
public class LoginJFrame extends MyJFrame implements ActionListener {
    JButton go = new JButton("Go");

    public LoginJFrame() {
        //……

        //添加组件
        initView();
    }

    //添加组件
    public void initView() {
        // ……

        go.addActionListener(super::method1);
    }
}
```

### 3.引用构造方法

格式：`类名::new`；

范例：`Student::new`；

案例理解：集合里面存储姓名和年龄组成的字符串，要求将它们封装成 `Student` 对象，并收集到 `List` 集合中。

实现方式一：使用 Stream 流的 `map` 方法，结合匿名内部类的方式：

demo-project/base-code/Day26/src/com/kkcf/methodref/Demo02.java

```java
package com.kkcf.methodref;

import com.kkcf.javabean.Studnet;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Demo02 {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>(List.of("张无忌,15", "赵敏,20", "张三丰,100", "张翠山,30", "周芷若,40", "王二麻子,50"));

        List<Studnet> newList = list.stream().map(new Function<String, Studnet>() {
            @Override
            public Studnet apply(String s) {
                String[] split = s.split(",");
                String name = split[0];
                int age = Integer.parseInt(split[1]);
                return new Studnet(name, age);
            }
        }).collect(Collectors.toList());

        System.out.println(newList);
    }
}
```

实现方式二：使用 Stream 流的 `map` 方法，结合方法引用的方式：

要在 `Student` 类中，新增一个构造方法；

- 用于 Stream 流的 `map` 抽象方法中传入的 `Function` 函数式接口实现类对象中的 `apply` 抽象方法的方法引用。

demo-project/base-code/Day26/src/com/kkcf/javabean/Studnet.java

```java
package com.kkcf.javabean;

import java.util.Objects;

public class Studnet {
    private String name;
    private int age;

    public Studnet() {
    }

    // 新增一个构造方法
    public Studnet(String str) {
        String[] split = str.split(",");
        this.name = split[0];
        this.age = Integer.parseInt(split[1]);
    }

    public Studnet(String name, int age) {
        this.name = name;
        this.age = age;
    }

   //……
}
```

构造方法没有返回值，所以构造方法用于方法引用时，

- 要保证生成的对象类型，与函数式接口中抽象方法的返回值类型一致。
- 要保证参数类型，与函数式接口中抽象方法的参数类型一致。

demo-project/base-code/Day26/src/com/kkcf/methodref/Demo02.java

```java
package com.kkcf.methodref;

import com.kkcf.javabean.Studnet;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Demo02 {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>(List.of("张无忌,15", "赵敏,20", "张三丰,100", "张翠山,30", "周芷若,40", "王二麻子,50"));

        List<Studnet> newList = list.stream()
                .map(Studnet::new)
                .collect(Collectors.toList());

        System.out.println(newList);
    }
}
```

## 三、Java 方法引用其它调用方式

### 1.类名引用成员方法

格式：`类名::成员方法`；示例：`String::toUpperCase`；

这种方法引用，有特别的要求：、

- 引用处，必须是**函数式接口**；
- 被引用的方法，必须**已经存在**；
- 被引用的方法的**形参列表**，需要跟抽象方法的**第二个参数到最后一个参数**保持一致；
- 被引用的方法的**返回值**，需要跟抽象方法的**返回值**保持一致；
- 被引用的方法，功能需要满足当前需求；

抽象方法形参的详解：

- **第一个参数**：表示被引用方法的调用者，它的类型，决定了引用的类名；
  - 在 Stream 流当中使用的方法，传入的函数式接口实现类中的抽象方法，第一个参数往往表示流里面的数据；
  - 假设这个数据是字符串类型的，那么就只能引用 `String` 类里的成员方法。
- **第二个参数到最后一个参数**：跟被引用方法的形参保持一致，
  - 抽象方法如果没有第二个参数，那么被引用的方法必须是类中的无参成员方法。

案例理解：集合里面添加一些字符串，要求变成大写后再进行输出

实现方式一：使用 Stream 流的 `map` 方法，结合匿名内部类实现。

demo-project/base-code/Day26/src/com/kkcf/methodref/Demo03.java

```java
package com.kkcf.methodref;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Demo03 {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>(List.of("aaa", "bbb", "ccc", "ddd"));

        List<String> newList = list.stream().map(new Function<String, String>() {
            @Override
            public String apply(String s) {
                return s.toUpperCase();
            }
        }).collect(Collectors.toList());

        System.out.println(newList);
    }
}
```

实现方式二：使用 Stream 流的 `map` 方法，结合方法引用实现。

demo-project/base-code/Day26/src/com/kkcf/methodref/Demo03.java

```java
package com.kkcf.methodref;

import java.util.ArrayList;
import java.util.List;

public class Demo03 {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>(List.of("aaa", "bbb", "ccc", "ddd"));

        list.stream()
                .map(String::toUpperCase)
                .forEach(System.out::println);
    }
}
```

### 2.引用数组的构造方法

格式：`数据类型::new`；

示例：`int[]::new`。

用于创建一个指定类型的数组。

案例理解：集合中存储了一些整数，将它们收集到数组当中。

实现方式一：使用 Stream 流的 `toArray` 方法，结合匿名内部类的方式：

demo-project/base-code/Day26/src/com/kkcf/methodref/Demo04.java

```java
package com.kkcf.methodref;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.IntFunction;

public class Demo04 {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));

        Integer[] arr = list.stream().toArray(new IntFunction<Integer[]>() {
            @Override
            public Integer[] apply(int value) {
                return new Integer[value];
            }
        });

        System.out.println(Arrays.toString(arr));
    }
}
```

实现方式二：使用 Stream 流的 `toArray` 方法，结合方法引用的方式：

- 细节 1：数组的类型，需要跟流中的数据类型保持一致。

```java
package com.kkcf.methodref;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.IntFunction;

public class Demo04 {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));

        Integer[] arr = list.stream().toArray(Integer[]::new);

        System.out.println(Arrays.toString(arr));
    }
}
```

## 四、Java 方法引用综合练习

### 1.练习一

集合中存储一些字符串类型的数据，比如：""张三,23"；将它们收集到 `Student` 类型的数组中（使用方法引用完成）。

在 `Student` 类中，新增一个构造方法：用于方法引用。

demo-project/base-code/Day26/src/com/kkcf/javabean/Studnet.java

```java
public class Studnet {
    private String name;
    private int age;

    public Studnet() {
    }

    // 新增的构造方法
    public Studnet(String str) {
        String[] split = str.split(",");
        this.name = split[0];
        this.age = Integer.parseInt(split[1]);
    }

    // ……
}
```

测试类：

demo-project/base-code/Day26/src/com/kkcf/methodref/Test3.java

```java
package com.kkcf.methodref;

import com.kkcf.javabean.Studnet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test3 {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>(List.of("张三,23", "李四,24", "王五,25", "赵六,26"));

        Studnet[] stus = list.stream()
            .map(Studnet::new)
            .toArray(Studnet[]::new);

        System.out.println(Arrays.toString(stus));
    }
}
```

### 2.练习二

创建集合，添加 Student 学生对象，学生对象属性：`name`、`age`，要求只获取姓名，并收集到数组当中（使用方法引用完成）

- 思路：这里要引用 `Student` 类中，关于 `name` 属性的 getter 方法 `getName`：

demo-project/base-code/Day26/src/com/kkcf/methodref/Test4.java

```java
package com.kkcf.methodref;

import com.kkcf.javabean.Studnet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class Test4 {
    public static void main(String[] args) {
        ArrayList<Studnet> stuList = new ArrayList<>(List.of(
                new Studnet("张三", 18),
                new Studnet("李四", 19),
                new Studnet("王五", 20),
                new Studnet("赵六", 21)
        ));

        String[] arr = stuList.stream()
            .map(Studnet::getName)
            .toArray(String[]::new);

        System.out.println(Arrays.toString(arr));
    }
}
```

### 3.练习三

创建集合，添加学生对象，学生对象属性：`name`、`age`；

要求把姓名和年龄拼接成："张三-23" 这样的字符串，并收集数组中（使用方法引用完成）

- 思路：在 `Student` 类中，新增 `getNameAge` 成员方法，用于方法引用

demo-project/base-code/Day26/src/com/kkcf/javabean/Studnet.java

```java
package com.kkcf.javabean;

import java.util.Objects;

public class Studnet {
    private String name;
    private int age;

    public Studnet() {
    }

    public Studnet(String name, int age) {
        this.name = name;
        this.age = age;
    }

    //……

    /**
     * 此方法用于，获取“姓名,年龄”格式的字符串
     * @return “姓名,年龄”格式的字符串
     */
    public String getNameAge() {
        return this.getName() + "," + this.getAge();
    }
}
```

测试类：

demo-project/base-code/Day26/src/com/kkcf/methodref/Test4.java

```java
package com.kkcf.methodref;

import com.kkcf.javabean.Studnet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test5 {
    public static void main(String[] args) {
        ArrayList<Studnet> stuList = new ArrayList<>(List.of(
                new Studnet("张三", 18),
                new Studnet("李四", 19),
                new Studnet("王五", 20),
                new Studnet("赵六", 21)
        ));

        String[] newStuList = stuList.stream()
            .map(Studnet::getNameAge)
            .toArray(String[]::new);

        System.out.println(Arrays.toString(newStuList)); // [张三,18, 李四,19, 王五,20, 赵六,21]
    }
}
```

Java 方法引用，使用总结：

1. 先观察，有没有一个已经存在的方法，符合当前的需求；
2. 再判断，这个方法是否满足方法引用的规则：
   - 静态方法：`类名::方法名`；
   - 成员方法：
     - 方法在本类中：`this::方法名`；
     - 方法在父类中：`super::方法名`；
     - 普通情况：`对象::方法名`；
     - 特殊情况：`类名::方法名`；
   - 构造方法：`类名::new`；
