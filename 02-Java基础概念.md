# Java 基础概念

## 一、Java 的注释

注释是对代码的解释和说明文字。Java 中的注释分为三种：

### 1.单行注释

```java
// 单行注释
```

### 2.多行注释

多行注释不能嵌套使用。

```java
/*
这是多行注释文字
这是多行注释文字
这是多行注释文字
*/
```

### 3.文档注释

使用 Java 自带的 doc 工具，可以根据代码中的文档注释，生成文档。

```java
/**
这是多行注释文字
这是多行注释文字
这是多行注释文字
*/
```

为前面编写的代码，写上注释：

```java
public class HelloWorld {
    // main 方法，表示程序的主入口。
    public static void main(String[] args) {
        /* ——输出语句（打印语句）
         * 可以把小括号里的内容，进行输出打印 */
        System.out.println("你好");
    }
}
```

## 二、Java 关键字

Java 中的关键字，表示被 Java 赋予了特定含义的英文单词。

- 关键字的字母，全部小写；
- 常见的代码编辑器，针对关键字有特殊标记，非常直观。

Java 中有五十多个关键字，不用刻意去记。

| 关键字         | 关键字           | 关键字        | 关键字         | 关键字     |
| -------------- | ---------------- | ------------- | -------------- | ---------- |
| **abstract**   | **assert**       | **boolean**   | **break**      | **byte**   |
| **case**       | **catch**        | **char**      | **class**      | **const**  |
| **continue**   | **default**      | **do**        | **double**     | **else**   |
| **enum**       | **extends**      | **final**     | **finally**    | **float**  |
| **for**        | **goto**         | **if**        | **implements** | **import** |
| **instanceof** | **int**          | **interface** | **long**       | **native** |
| **new**        | **package**      | **private**   | **protected**  | **public** |
| **return**     | **strictfp**     | **short**     | **static**     | **super**  |
| **switch**     | **synchronized** | **this**      | **throw**      | **throws** |
| **transient**  | **try**          | **void**      | **volatile**   | **while**  |

### 1.class 关键字

`class` 关键字，表示定义一个类。创建一个类。

类是 Java 项目最基本的组成单元，一个完整的 Java 项目有可能会有成千上万个类来组成的。

`class` 关键字后面，跟随的就是这个类的名字（类名）。

```java
public class HelloWorld {

}
```

- `class` 表示定义类；
- `HelloWorld` 表示类名；
- HelloWorld 后面的大括号，表示这个类的范围。

## 三、Java 字面量

字面量：用于告诉程序员，数据在程序中的书写格式。

### 1.Java 字面量类型

Java 中的字面量类型，有以下几种：

| **字面量类型** | **说明**                                  | **程序中的写法**               |
| -------------- | ----------------------------------------- | ------------------------------ |
| 整数           | 不带小数的数字                            | `666`，`-88`                   |
| 小数           | 带小数的数字                              | `13.14`，`-5.21`               |
| 字符           | 必须使用单引号，有且仅能一个字符          | `‘A’`，`‘0’`，`‘我’`           |
| 字符串         | 必须使用双引号，内容可有可无              | `“HelloWorld”`，`“你是程序员”` |
| 布尔值         | 布尔值，表示真假，只有两个值：true，false | `true`、`false`                |
| 空值           | 一个特殊的值，表示空值                    | `null`                         |

常见的字面量类型的值，在代码中的书写：

demo-project/demo/src/ValueDemo1.java

```java
public class ValueDemo1 {
    public static void main(String[] args) {
        // 整数
        System.out.println(666);
        System.out.println(-71);

        // 小数
        System.out.println(3.14);
        System.out.println(-3.14);

        // 字符串
        System.out.println("zzt");
        System.out.println("Haha");

        // 字符
        System.out.println('女');
        System.out.println('A');

        // 布尔
        System.out.println(true);
        System.out.println(false);

        // 空（null 不能直接打印，否则会报错，为它加上双引号）
        System.out.println("null");
    }
}
```

字面量类型有以下区分技巧：

1. 不带小数点的数字都是整数类型的字面量。
2. 只要带了小数点，那么就是小数类型的字面量。
3. 只要用双引号引起来的，不管里面的内容是什么，不管里面有没有内容，都是字符串类型的字面量。
4. 字符类型的字面量必须用单引号引起来，不管内容是什么，但是个数有且只能有一个。
5. 字符类型的字面量只有两个值，true、false。
6. 空类型的字面量只有一个值，null。

### 2.特殊字符

Java 中的特殊字符字面量，可以使用单引号（''）或者双引号（""）包裹。

`'\t'`，表示制表符，在打印的时候，把前面的字符串长度补齐到 8 或者 8 的整数倍，最少补 1 个空格，最多补 8 个空格。

demo-project/demo/src/ValueDemo2.java

```java
public class ValueDemo2 {
    public static void main(String[] args) {
        System.out.println("name" + '\t' + "age"); // '\t' 补充了 4 个空格。
        System.out.println("tom" + '\t' + 23); // '\t' 补充了 5 个空格
    }
}
```

## 四、Java 变量

变量，就在程序中临时存储数据的容器。但是这个容器中只能存一个值，其值有可能发生改变。

### 1.Java 变量的使用

#### 1.Java 变量的定义

Java 变量的定义格式：`数据类型 变量名 = 数据值;`

- 数据类型：限定了变量当中能存储什么类型的数据。
  - 如果要存 10，那么数据类型就需要写整数类型。
  - 如果要存 10.0，那么数据类型就需要写小数类型。

- 变量名：其实就是这个容器的名字。
  - 当以后想要使用变量里面的数据时，直接使用变量名就可以了。

- 数据值：真正存储在容器中的数据。

- 分号：表示语句的结束，就跟以前写作文时候的句号是一样的。

> Java 中常用的数据类型有如下：
>
> | 数据类型 | 关键字   |
> | -------- | -------- |
> | 整数     | `int`    |
> | 浮点数   | `double` |

变量定义后，可以输出打印，

demo-project/demo/src/VariableDemo1.java

```java
public class VariableDemo1 {
    public static void main(String[] args) {
        int a = 10;

        System.out.println(a); // 10
    }
}
```

#### 2.Java 变量参与计算

变量也可以参与计算。

demo-project/demo/src/VariableDemo1.java

```java
public class VariableDemo1 {
    public static void main(String[] args) {
        int a = 10;
        int b = 20;

        int c = a + b;

        System.out.println(c); // 30
    }
}
```

#### 2.Java 变量的修改

变量也可以修改值。

demo-project/demo/src/VariableDemo1.java

```java
public class VariableDemo1 {
    public static void main(String[] args) {
        int a = 10;
        int b = 20;

        a = 30;

        int c = a + b;

        System.out.println(c); // 50
    }
}
```

### 2.Java 变量的注意事项

#### 1.Java 变量只能保存一个值

demo-project/demo/src/VariableDemo2.java

```java
public class VariableDemo1 {
    public static void main(String[] args) {
        int a = 10;
    }
}
```

#### 2.Java 变量名不能重复定义

demo-project/demo/src/VariableDemo2.java

```java
public class VariableDemo1 {
    public static void main(String[] args) {
        int a = 10;

        int a = 20; // 报错
    }
}
```

#### 3.Java 变量可以在一条语句当中，定义多个

变量之间用 `,` 号分隔。

demo-project/demo/src/VariableDemo2.java

```java
public class VariableDemo2 {
    public static void main(String[] args) {
        int a = 10, b = 20, c = 30;

        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
    }
}
```

#### 4.Java 变量在使用之前，一定要进行赋值

demo-project/demo/src/VariableDemo2.java

```java
public class VariableDemo2 {
    public static void main(String[] args) {
        int d;

        System.out.println(d); // 报错
    }
}
```

应改为：

demo-project/demo/src/VariableDemo2.java

```java
public class VariableDemo2 {
    public static void main(String[] args) {
        int d;
        d = 100;

        System.out.println(d); // 报错
    }
}

```

- 定义变量时，应该直接赋值；
- 不建议将变量的定义和赋值分开。

#### 5.Java 变量只能在作用域范围内使用

后续讲解。
