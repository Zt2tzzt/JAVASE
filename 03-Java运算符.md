# Java 运算符

**Java 运算符**：就是对常量或者变量进行操作的符号。

**Java 表达式**：用运算符把常量或者变量连接起来的，符合 Java 语法的式子就是表达式。

```java
int a = 10;
int b = 20;

int c = a + b;
```

- `a + b` 这个整体就是表达式。其中 `+` 是**算术运算符**的一种，所以这个表达式也称之为**算术表达式**。

## 一、Java 算数运算符

Java 算数运算符，分为加（`+`）、减（`-`）、乘（`*`）、除（`/`）、取模（`%`）

### 1整数、小数的算数运算

#### 1.整数的加（+）、减（-）、乘（*）

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

#### 2.小数的加（+）、减（-）、乘（*）

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

#### 3.整数的除（/）运算

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

#### 4.小数的除（/）运算

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

- 结论1：小数参与的除运算，结果也是小数；
- 结论2：小数直接参与运算，得到的结果有可能不精确（具体原因后续介绍）。

#### 5.整数的取模（%）运算

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

#### 6.小数的取模（%）运算

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

- 结论1：小数参与的取模运算，结果也是小数；
- 结论2：小数直接参与运算，得到的结果有可能不精确（具体原因后续介绍）。

> 取模运算的应用场景：
>
> - 判断一个数是否能被另一个数整除。
> - 判断一个数是偶数还是奇数。
> - 模拟斗地主游戏中，为三个玩家发牌，把每一张牌都定义一个序号，拿序号对 3 取模：
>   - 如果结果为 1，就发给第一个玩家；
>   - 如果结果为 2，就发给第二个玩家；
>   - 如果结果为 0，就发给第三个玩家；

### 2.一个数的十位、百位、千位...

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

### 3.数字运算的数据类型转换

数字的数据类型不一样的数值，不能进行运算，需要转成一致的类型，才能运算。

数值进行运算时，数据类型的转换方式，分为两种：

- **隐式转换**（也称**自动类型提升**）；表示取值范围小的数值，转为取值范围大的数值。
- **强制转换**：表示取值范围大的数值，转为取值范围小的数值。

#### 1.数字类型隐式转换

**隐式转换**，是程序执行时，默认进行的。隐式转换的规则有两点：

- 规则一：取值范围小的，和取值范围大的进行运算，取值范围小的值的数据类型会先提升为取值范围大的值的数据类型，再进行运算。
- 规则二：`byte`、`short`、`char` 三种数据类型的值在运算的时候，都会直接先提升为 `int` 数据类型，然后再进行运算。

> 数据类型取值范围大小关系：double > float > long > int > short > byte

案例理解：

案例一：整数类型字面量 `10`，赋值给了一个 double 类型的标识符 `d`；默认会进行隐式转换。

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

- `10` 是 int 整数类型。
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

#### 2.数字类型强制转换

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

### 4.字符串（+）运算

当 `+` 操作中出现字符串时，它就是字符串的连接符，会将前后的数据进行拼接，并产生一个新的字符串。

当连续进行 `+` 操作时，是从左到右逐个执行的。

案例理解：

案例一：将数字与字符串拼接。

demo-project/base-code/day03/src/com/kkcf/arithmetic_operators/ArithmeticOperatorsDemo04.java

```java
package com.kkcf.arithmetic_operators;

public class ArithmeticOperatorsDemo04 {
    public static void main(String[] args) {
        System.out.println(1 + "abc" + 1); // 1abc1
    }
}
```

案例二：将数字相加后，再与字符串进行拼接。

demo-project/base-code/day03/src/com/kkcf/arithmetic_operators/ArithmeticOperatorsDemo04.java

```java
package com.kkcf.arithmetic_operators;

public class ArithmeticOperatorsDemo04 {
    public static void main(String[] args) {
        System.out.println(1 + 2 + "abc" + 2 + 1); // 3abc21
    }
}
```

案例三：将小数，布尔值，字符与字符串拼接：

demo-project/base-code/day03/src/com/kkcf/arithmetic_operators/ArithmeticOperatorsDemo04.java

```java
package com.kkcf.arithmetic_operators;

public class ArithmeticOperatorsDemo04 {
    public static void main(String[] args) {
        System.out.println(3.7 + "abc"); // 3.7abc

        System.out.println("abc" + true); // abctrue

        System.out.println('中' + "abc" + true); // 中abctrue
    }
}
```

### 5.字符（+）运算

我们知道，加（+）运算隐式转换的规则之一是：

- `byte`、`short`、`char` 三种数据类型的值在运算的时候，都会直接先提升为 `int` 数据类型，然后再进行运算。

字符（+）运算的规则：

- 当（+）操作中出现了 char 字符类型的数值，会将字符数值转为计算机内置的 ASCII 码表中对应的数字，然后再进行计算。

> 需要记住，在 ASCLL 码表中，`a` 对应 `97`，`A` 对应 `65`；

案例理解：

案例一，将数字 `1` 与字符 `'a'` 相加。

demo-project/base-code/day03/src/com/kkcf/arithmetic_operators/ArithmeticOperatorsDemo05.java

```java
package com.kkcf.arithmetic_operators;

public class ArithmeticOperatorsDemo05 {
    public static void main(String[] args) {
        System.out.println(1 + 'a'); // 98
    }
}
```

- 结论：char 字符类型与数字相加，char 字符类型的数值，会隐式转为 int 类型。

案例二：将数字 `'a'` 与字符串 `"abc"` 相加。

demo-project/base-code/day03/src/com/kkcf/arithmetic_operators/ArithmeticOperatorsDemo05.java

```java
package com.kkcf.arithmetic_operators;

public class ArithmeticOperatorsDemo05 {
    public static void main(String[] args) {
        System.out.println('a' + "abc"); // aabc
    }
}
```

- 结论：char 字符类型与字符串类型相加，会应用字符串的拼接规则。

## 二、Java 自增自减运算符

自增自减运算符有两个：

- `++`，表示自增运算符；
  - `++` 放在变量的前面，称为前置自增运算符，比如：`++a`；
  - `++` 放在变量的后面，称为后置自增运算符。 比如：`a++`。
- `--`，表示自减运算符。
  - `--` 放在变量的前面，称为前置自增运算符，比如：`--a`；
  - `--` 放在变量的后面，称为后置自增运算符。 比如：`a--`。

前缀自增/自减运算符，表示”先加/减后用“。

后置自增/自减运算符，表示”先用后加/减“。

案例理解：

demo-project/base-code/day03/src/com/kkcf/arithmetic_operators/ArithmeticOperatorsDemo06.java

```java
package com.kkcf.arithmetic_operators;

public class ArithmeticOperatorsDemo06 {
    public static void main(String[] args) {
        int x = 10;
        int y = x++;
        int z = ++x;

        System.out.println("x = " + x); // x = 12
        System.out.println("y = " + y); // y = 10
        System.out.println("z = " + z); // z = 12
    }
}
```

自增/自减运算符的应用场景：

某些情况下，变量需要进行加 1 或者减 1 的时候使用。

- 比如：过生日多一岁，就用到了自增运算符。
- 比如：购物车中，增加商品数量，也用到了自增或者自减运算符。

## 二、Java 赋值运算符

Java 中的赋值运算符，有如下这些：

| 符号 | 作用       | 说明                                  |
| ---- | ---------- | ------------------------------------- |
| `=`  | 赋值       | `int a = 10`，将 10 赋值给变量 `a`    |
| `+=` | 加后赋值   | `a += b`，将 a + b 的和，赋值给 `a`   |
| `-=` | 减后赋值   | `a -= b`，将 a - b 的差，赋值给 `a`   |
| `*=` | 乘后赋值   | `a *= b`，将 a * b 的积，赋值给 `a`   |
| `/=` | 除后赋值   | `a /= b`，将 a / b 的商，赋值给 `a`   |
| `%=` | 取模后赋值 | `a %= b`，将 a % b 的余数，赋值给 `a` |

除了 `=` 赋值运算符以外，其它赋值运算符在赋值运算时，还包含了一个强制转换。

demo-project/base-code/day03/src/com/kkcf/arithmetic_operators/ArithmeticOperatorsDemo07.java

```java
package com.kkcf.arithmetic_operators;

public class ArithmeticOperatorsDemo07 {
    public static void main(String[] args) {
        int a = 10;
        int b = 20;

        a += b; // 等同于 a = (int)(a + b)

        System.out.println(a); // 30
        System.out.println(b); // 20

        short s = 1;

        s += 1; // 等同于 s = (short)(s + 1)

        System.out.println(s); // 2
    }
}
```

## 三、Java 关系运算符（比较运算符）

Java 中的关系运算符，也成为比较运算符。本质上就是拿运算符左边与右边进行比较。

| 符号 | 解释                                                         |
| ---- | ------------------------------------------------------------ |
| `==` | 就是判断左边跟右边是否相等，如果成立就是 `tru`，如果不成立就是 `false` |
| `!=` | 就是判断左边跟右边是否不相等，如果成立就是 `tru`，如果不成立就是f alse |
| `>`   | 就是判断左边是否大于右边，如果成立就是 `true`，如果不成立就是 `false` |
| `>=`   | 就是判断左边是否大于等于右边，如果成立就是 `tru`，如果不成立就是 `false` |
| `<`   | 就是判断左边是否小于右边，如果成立就是 `true`，如果不成立就是 `false` |
| `<=`   | 就是判断左边是否小于等于右边，如果成立就是 `tru`，如果不成立就是 `false` |

关系运算符的结构，都是布尔类型的，要么是 `true`，要么是 `false`。

demo-project/base-code/day03/src/com/kkcf/Increment_decrement_operators/IncrementDecrementOperatorsDemo01.java

```java
package com.kkcf.Increment_decrement_operators;

public class IncrementDecrementOperatorsDemo01 {
    public static void main(String[] args) {
        int a = 10;
        int b = 10;
        int c = 20;

        System.out.println(a == b); // true
        System.out.println(a == c); // false
    }
}
```

案例：你和你的约会对象，在餐厅约会，键盘录入两个整数，表示你和你约会对象衣服的时髦程度。如果你衣服的时髦成都大于你约会对象的时髦成都，表示约会成功，否则表示约会失败。

demo-project/base-code/day03/src/com/kkcf/Increment_decrement_operators/IncrementDecrementOperatorsTest.java

```java
package com.kkcf.Increment_decrement_operators;

import java.util.Scanner;

public class IncrementDecrementOperatorsTest {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("请输入你的衣服时髦度：");
        int me = sc.nextInt();

        System.out.println("请输入你的约会对象的衣服时髦度：");
        int girl = sc.nextInt();

        sc.close();

        boolean result = me > girl;

        System.out.println("约会" + (result ? "成功" : "失败"));
    }
}
```





## 四、Java 逻辑运算符



## 五、Java 三元运算符

