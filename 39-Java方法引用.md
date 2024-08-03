# Java 方法引用

Java 方法引用，就是把已经有的方法拿过来用，当作函数式接口中抽象方法的方法体。

## 一、Java 方法引用要求

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

## 二、Java 方法引用的分类

### 1.引用静态方法

格式：`类名::静态方法`；示例：`Integer::parseInt`

案例理解：集合中有以下数字，将它们转为 int 类型。

使用 Stream 流的 `map` 方法，结合匿名内部类的方式实现：

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

使用 Stream 流的 `map` 方法，结合方法引用的方式实现：

- 引用 `Integer.parseInt` 方法

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

- 引用其它类的成员方法，格式：`其它类对象::方法名`
- 引用本类的成员方法，格式：`this::方法名`
- 引用父类的成员方法，格式：`super::方法名`

#### 1.引用其它类的成员方法

案例理解：集合中有一些名字，按照要求过滤数据。

创建一个类 `StringOperator`，里面有一个 `StringOperator` 方法，用于方法引用：

demo-project/base-code/Day26/src/com/kkcf/methodref/StringOperator.java

```java
package com.kkcf.methodref;

public class StringOperator {
    public boolean StringOperator(String s) {
        return s.startsWith("张") && s.length() == 3;
    }
}
```

使用 Stream 流的 `filter` 方法，结合匿名内部类的方式实现：

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

使用 Stream 流的 `filter` 方法，结合方法引用的方式实现：

- 引用  `StringOperator` 类的 `StringOperator` 方法。

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

        StringOperator so = new StringOperator();
        List<String> newList = list.stream()
                .filter(so::stringJudge)
                .collect(Collectors.toList());

        System.out.println(newList); // [张三丰, 张无忌, 张翠山]
    }
}
```

#### 2.引用本类的成员方法

案例理解，在 GUI 界面中，点击事件的方法引用写法：

创建一个类 `LoginJFrame` 实现 `ActionListener` 接口，用于鼠标点击的监听。

在其中定义 `method1` 方法，用于 `ActionListener` 接口中 `actionPerformed` 的方法引用。

demo-project/base-code/Day26/src/com/kkcf/a02game/LoginJFrame.java

```java
public class LoginJFrame implements ActionListener {
    JButton go = new JButton("Go");

    public LoginJFrame() {
        //……

        //添加组件
        initView();
    }

    //添加组件
    public void initView() {
        // ……

        // go.addActionListener(this);
        go.addActionListener(this::method1);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == go) {
            System.out.println("go按钮被点击了");
        }
    }

    public void method1(ActionEvent e) {
        // 不用进行 if (e.getSource() == go) 判断，因为 method1 方法，是固定用于 go.addActionListener 的方法引用。
        System.out.println("go按钮被点击了");
    }
}
```

#### 3.引用父类的成员方法

重构上方的方法：

创建一个 `MyJFrame` 类，继承自 `JFrame` 类：在其中定义 `method1` 方法，用于 `ActionListener` 接口中 `actionPerformed` 的方法引用。

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

`ActionListener` 接口虽然没有加 `@FunctionInterface` 注解，但它其中只有一个抽象方法 `actionPerformed`，所以可以使用方法引用。

demo-project/base-code/Day26/src/com/kkcf/a02game/LoginJFrame.java

```java
public class LoginJFrame extends MyJFrame {
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

注意：静态方法中，没有 `this、super` 关键字，在**静态方法**中，使用方法引用时，不能用 `this::stringJudge` 的写法。







### 3.引用构造方法



### 4.方法引用的其它调用方式

1.使用类名引用成员方法



2.引用数组的构造方法