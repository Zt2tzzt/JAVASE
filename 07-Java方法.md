# Java 方法

方法是程序中最小的执行单元。

方法的作用，将重复的代码，具有独立功能的代码，抽取出来。以

- 就是提高代码的复用性；
- 提高代码的可维护性。

方法必须先创建才可以使用，该过程成为方法定义

方法创建后并不是直接可以运行的，需要手动使用后，才执行，该过程成为方法调用。

## 一、Java 方法的定义和调用

方法要先定义，然后才能调用。

### 1.无参数方法定义和调用

Java 无参数的方法，定义格式如下：

```java
public static void 方法名 () {
 方法体;
}
```

Java 无参数的方法，调用格式如下：

```java
方法名();
```

定义一个方法 `palyGame`，在其中放入一段代码，并条用该方法：

demo-project/base-code/Day06/src/com/kkcf/method/MethodDemo01.java

```java
package com.kkcf.method;

public class MethodDemo01 {
    public static void main(String[] args) {
        playGanme();
    }

    public static void playGanme() {
        System.out.println("唱");
        System.out.println("跳");
        System.out.println("rap");
        System.out.println("篮球");
    }
}
```

看如下代码，说出代码的执行结果：

demo-project/base-code/Day06/src/com/kkcf/method/MethodDemo02.java

```java
package com.kkcf.method;

public class MethodDemo02 {
    public static void main(String[] args) {
        System.out.println('a');
        foo();
        System.out.println('b');
    }

    public static void foo() {
        System.out.println('c');
        System.out.println('d');
    }
}
```

输出结果为：

```cmd
a
c
d
b
```

- 在执行代码时，遇到方法，进入方法；
- 方法执行完毕，回到调用处。

案例理解：定义一个方法，计算 10, 20 的和。

demo-project/base-code/Day06/src/com/kkcf/method/MethodDemo03.java

```java
package com.kkcf.method;

public class MethodDemo03 {
    public static void main(String[] args) {
        getSum();
    }

    public static void getSum() {
        int a = 10;
        int b = 20;

        System.out.println(a + b);
    }
}
```

### 2.带参数方法定义和调用

Java 带参数的方法，定义格式如下：

```java
public static void 方法名 (参数1) {
  方法体;
}

public static void 方法名 (参数1, 参数2, 参数3...) {
  方法体;
}
```

Java 带参数的方法，调用格式如下：

```java
方法名(参数)；

方法名(参数1,参数2);
```

带参数的方法调用时，插入的实参的数量与类型，必须与方法定义中，小括号里的形参一一对应，否则程序将报错。

案例理解：定义一个方法，传入两个参数，计算它们的和。

demo-project/base-code/Day06/src/com/kkcf/method/MethodDemo04.java

```java
package com.kkcf.method;

public class MethodDemo04 {
    public static void main(String[] args) {
        getsum(10, 20);
    }

    public static void getsum(int a, int b) {
        int result = a + b;
        System.out.println(result);
    }
}
```

案例理解：定义一个方法，求愿的面积，将结果在方法中打印。

demo-project/base-code/Day06/src/com/kkcf/method/MethodTest01.java

```java
package com.kkcf.method;

public class MethodTest01 {
    public static void main(String[] args) {
        getCirleArea(5);
    }

    public static void getCirleArea(double radius) {
        double result = Math.PI * radius * radius;

        System.out.println(result);
    }
}
```

#### 1.方法中的形参、实参

形参：全称”形式参数“，方法定义中的参数，等同于变量定义格式，例如：`int number`。

实参：全称”实际参数“，方法调用中的参数。等同于使用变量或常量，例如： `10`，`number`。

### 3.带返回值方法的定义和调用

方法的返回值，其实就是方法运行的最终结果。

Java 带返回值的方法，定义格式如下：

```java
public static 返回值数据类型 方法名 ( 参数 ) {
  方法体;
	return 数据 ;
}
```

Java 带返回值的方法，调用格式如下：

直接调用：

```java
方法名 ( 实参 ) ;
```

赋值调用：

```java
数据类型 变量名 = 方法名 ( 实参 ) ;
```

输出调用：

```java
System.out.println(方法名 ( 实参 ));
```

案例理解：季孙一个商场四个季度的营业额，并将它们相加，求出全年营业额。

demo-project/base-code/Day06/src/com/kkcf/method/MethodTest02.java

```java
package com.kkcf.method;

public class MethodTest02 {
    public static void main(String[] args) {
        double sea1 = getSum(10, 20, 30);
        double sea2 = getSum(30, 10, 20);
        double sea3 = getSum(10, 20, 29);
        double sea4 = getSum(10, 40, 20);

        double year = sea1 + sea2 + sea3 + sea4;
        System.out.println("年销售总额为" + year);
    }

    public static double getSum(double mon1, double mon2, double mon3) {
        return mon1 + mon2 + mon3;
    }
}
```

## 二、Java 方法的注意事项

- 方法不调用，就不执行。
- 方法与方法之间，是平级的关系，不能嵌套定义。
- 方法的编写顺序，与它的执行顺序无关。
- 方法的返回值类型为 `void` 时，表示该方法没有返回值，可以省略 `return` 语句不写，如果要编写 `return` 语句，后面不能跟具体数据。
- 方法的 `return` 语句下面，不能编写代码，因为永远执行不到，属于无效代码。

### 1.return 关键字

`return` 关键字用于 Java 方法中的 return 语句，表示结束方法，并返回值。

- `return` 语句在 Java 方法没有返回值时，可以省略不写；如果写了，表示结束方法；
- `return` 语句在 Java 方阿有返回值时，必须写，表示结束方法和返回结果。
