# Java 运算符

## 一、Java 表达式

Java 表达式：用运算符把常量或者变量连接起来的，符合 Java 语法的式子就是表达式。

## 二、Java 运算符

Java 运算符：就是对常量或者变量进行操作的符号。

```java
int a = 10;
int b = 20;

int c = a + b;
```

- `a + b` 这个整体就是表达式。其中 `+` 是**算术运算符**的一种，所以这个表达式也称之为**算术表达式**。

### 1.Java 算数运算符

Java 算数运算符，分为加（`+`）、减（`-`）、乘（`*`）、除（`/`）、取模（`%`）

#### 1整数、小数与算数运算符

整数的加（`+`）、减（`-`）、乘（`*`）算数表达式：

demo-project/base-code/day03/src/com/kkcf/arithmetic_operators/ArithmeticOperatorsDemo01.java

```java
package com.kkcf.arithmetic_operators;

public class ArithmeticOperatorsDemo01 {
    public static void main(String[] args) {
        // 整数的 +、-、*
        System.out.println(3 + 2); // 5

        System.out.println(5 - 1); // 4

        System.out.println(7 * 9); // 63
    }
}
```

小数的加（`+`）、减（`-`）、乘（`*`）算数表达式：

demo-project/base-code/day03/src/com/kkcf/arithmetic_operators/ArithmeticOperatorsDemo01.java

```java
package com.kkcf.arithmetic_operators;

public class ArithmeticOperatorsDemo01 {
    public static void main(String[] args) {
        // 小数的 +、-、*
        System.out.println(1.1 + 1.1); // 2.2

        System.out.println(1.1 + 1.01); // 2.1100000000000003

        System.out.println(1.1 - 1.01); // 0.09000000000000008

        System.out.println(1.1 * 1.01); // 1.1110000000000002
    }
}
```

- 结论：小数直接参与运算，得到的结果有可能不精确（具体原因后续介绍）。

整数的除（`/`）运算

demo-project/base-code/day03/src/com/kkcf/arithmetic_operators/ArithmeticOperatorsDemo01.java

```java
package com.kkcf.arithmetic_operators;

public class ArithmeticOperatorsDemo01 {
    public static void main(String[] args) {
        // 整数 /
        System.out.println(10 / 2); // 5

        System.out.println(10 / 3); // 3
    }
}
```

- 结论：整数相除结果只能得到整除。

小数的除（`/`）运算

demo-project/base-code/day03/src/com/kkcf/arithmetic_operators/ArithmeticOperatorsDemo01.java

```java
package com.kkcf.arithmetic_operators;

public class ArithmeticOperatorsDemo01 {
    public static void main(String[] args) {
        // 小数 /
        System.out.println(10.0 / 3); // 3.3333333333333335
    }
}
```

- 结论：小数参与的除运算，结果也是小数；小数直接参与运算，得到的结果有可能不精确（具体原因后续介绍）。

整数的取模（`%`）运算

demo-project/base-code/day03/src/com/kkcf/arithmetic_operators/ArithmeticOperatorsDemo01.java

```java
package com.kkcf.arithmetic_operators;

public class ArithmeticOperatorsDemo01 {
    public static void main(String[] args) {
        // 整数 %
        System.out.println(10 % 3); // 1
    }
}
```

小数的取模（`%`）运算

demo-project/base-code/day03/src/com/kkcf/arithmetic_operators/ArithmeticOperatorsDemo01.java

```java
package com.kkcf.arithmetic_operators;

public class ArithmeticOperatorsDemo01 {
    public static void main(String[] args) {
        // 小数 %
        System.out.println(10.1 % 3); // 1.0999999999999996
    }
}
```

- 结论：小数参与的取模运算，结果也是小数；小数直接参与运算，得到的结果有可能不精确（具体原因后续介绍）。

取模运算的应用场景：

- 判断一个数是否能被另一个数整除。
- 判断一个数是偶数还是奇数。
- 模拟斗地主游戏中，为三个玩家发牌，把每一张牌都定义一个序号，拿序号对 3 取模：
  - 如果结果为 1，就发给第一个玩家；
  - 如果结果为 2，就发给第二个玩家；
  - 如果结果为 3，就发给第三个玩家；

4.算数运算练习（一）

练习一；需求：键盘录入一个三位数，将其拆分为个位、十位、百位后，打印在控制台

公式总结：

- 个位：数值 % 10
- 十位：数值 / 10 % 10
- 百位：数值 / 100 % 10
- 千位：数值 / 1000 % 10

demo-project/base-code/day03/src/com/kkcf/arithmetic_operators_test/Test1.java

```java
package com.kkcf.arithmetic_operators_test;

import java.util.Scanner;

public class Test1 {
    public static void main(String[] args) {
        // 键盘输入
        System.out.println("请输入一个三位数：");
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        sc.close();

        // 获取数值的个、十、百位
        int ge = num % 10;
        int shi = num / 10 % 10;
        int bai = num / 100 % 10;

        System.out.println("个位：" + ge + " 十位：" + shi + " 百位：" + bai);
    }
}
```

#### 2.加（+）运算符

##### 1.数字相加

数据类型不一样的数值，不能进行运算，需要转成一致的类型，才能运算。

数值进行运算时，数据类型的转换方式，分为两种：

- **隐式转换**（也称**自动类型提升**）；表示取值范围小的数值，转为取值范围大的数值。
- **强制转换**：表示取值范围大的数值，转为取值范围小的数值。

###### 1.隐式转换

**隐式转换**，是程序执行时，默认进行的。隐式转换的规则有两点：

- 规则一：取值范围小的，和取值范围大的进行运算，取值范围小的值的数据类型会先提升为取值范围大的值的数据类型，再进行运算。
- 规则二：`byte`、`short`、`char` 三种数据类型的值在运算的时候，都会直接先提升为 `int` 数据类型，然后再进行运算。

> 数据类型取值范围大小关系：double > float > long > int > short > byte

案例理解：

案例一：整数类型字面量 10，赋值给了一个 double 类型的标识符 d；默认会进行隐式转换。

demo-project/base-code/day03/src/com/kkcf/arithmetic_operators/ArithmeticOperatorsDemo02.java

```java
package com.kkcf.arithmetic_operators;

public class ArithmeticOperatorsDemo02 {
    public static void main(String[] args) {
        double d = 10;
        System.out.println(d); // 10.0
    }
}
```

- 10 是 int 整数类型。
- 在取值范围的顺序中：byte < short < int < long < float <double
- 在赋值的时候把一个 int 类型的值，赋值给了一个 double 类型的标识符。相当于把一个取值范围小的数据类型，赋值给一个取值范围大的数据类型，这是被允许的，程序执行时默认会进行隐式转换。

案例二：将 byte 类型的数值，赋值给 int 类型的数值；默认会进行隐式转换。

demo-project/base-code/day03/src/com/kkcf/arithmetic_operators/ArithmeticOperatorsDemo02.java

```java
package com.kkcf.arithmetic_operators;

public class ArithmeticOperatorsDemo02 {
    public static void main(String[] args) {
        byte b = 100;
        int i = b;
        System.out.println(i);
    }
}
```

案例三：int 类型的数据与 double 类型的数据相加，int 类型的数据隐式转换为了 double 类型：

demo-project/base-code/day03/src/com/kkcf/arithmetic_operators/ArithmeticOperatorsDemo02.java

```java
package com.kkcf.arithmetic_operators;

public class ArithmeticOperatorsDemo02 {
    public static void main(String[] args) {
        int a = 10;
        double b = 12.3;

        double c = a + b; // a 的数据类型被隐式转为了 double，再进行运算
        System.out.println(c);
    }
}
```

案例四：byte 类型的数据与 byte 类型的数据相加，它们的数据类型都被隐式转换为了 int 类型：

demo-project/base-code/day03/src/com/kkcf/arithmetic_operators/ArithmeticOperatorsDemo02.java

```java
package com.kkcf.arithmetic_operators;

public class ArithmeticOperatorsDemo02 {
    public static void main(String[] args) {
        byte d = 10;
        byte e = 20;

        int f = d + e; //  d、e 的数据类型被隐式转为了 double，再进行运算
        System.out.println(f);
    }
}
```

###### 2.强制转换

如果要把一个取值范围大的数据类型的数值，赋值给另一个取值范围小的数据类型的标识符。是不允许直接操作的。

如果一定要这么干，就需要加入**强制转换**。格式如下：

- `目标数据类型 变量名 = （目标数据类型）被强转的数据;`

案例理解：

案例一：将 `double` 类型的变量 `a`，强制转换成 `int` 类型：

demo-project/base-code/day03/src/com/kkcf/arithmetic_operators/ArithmeticOperatorsDemo03.java

```java
package com.kkcf.arithmetic_operators;

public class ArithmeticOperatorsDemo03 {
    public static void main(String[] args) {
        double a = 12.3;

        int b = (int) a;

        System.out.println(b); // 12
    }
}
```

- 结论：强制转换有可能会导致数据发生错误（数据的精度丢失）。

案例二：将 `byte` 类型的变量 `b1`、`b1` 相加，在运算时，它们会隐式转为 `int` 类型；

将它们运算的结果，强制转为 `byte` 类型

demo-project/base-code/day03/src/com/kkcf/arithmetic_operators/ArithmeticOperatorsDemo03.java

```java
package com.kkcf.arithmetic_operators;

public class ArithmeticOperatorsDemo03 {
    public static void main(String[] args) {
        byte b1 = 100;
        byte b2 = 100;

        byte result = (byte)(b1 + b2);
        System.out.println(result); // -56
    }
}
```

- 结论：强制转换有可能会导致数据发生错误（数据的精度丢失）；byte 类型数值的取值范围是 `-128~127`。

### 2.Java 赋值运算符

### 3.Java 关系运算符

### 4.Java 逻辑运算符

### 5.Java 三元运算符
