# Java 方法

Java 的方法，是 Java 程序中最小的执行单元。

Java 方法的作用，是将重复的代码，具有独立功能的代码，抽取出来。以便

- 就是提高代码的复用性；
- 提高代码的可维护性。

方法必须先创建才可以使用，该过程称为“**方法定义**”。

方法创建后并不是直接可以运行的，需要手动使用后，才能执行，该过称为“**方法调用**”。

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

定义一个方法 `palyGame`，在其中放入一段代码，并调用该方法：

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

带参数的方法调用时，传入的实参的数量与类型，必须与方法定义中，小括号里的形参一一对应，否则程序将报错。

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

案例理解：定义一个方法，求园的面积，将结果在方法中打印。

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

形参：全称”形式参数“，表示方法定义中的参数，等同于变量定义格式，例如：`int number`。

实参：全称”实际参数“，表示方法调用中的参数。等同于使用变量或常量，例如： `10`，`number`。

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

赋值调用（用的最多）：

```java
数据类型 变量名 = 方法名 ( 实参 ) ;
```

输出调用：

```java
System.out.println(方法名 ( 实参 ));
```

案例理解：计算一个商场四个季度的营业额，并将它们相加，求出全年营业额。

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

- `return` 语句，在 Java 方法没有返回值时，可以省略不写；如果写了，表示结束方法；
- `return` 语句，在 Java 方法有返回值时，必须写，表示结束方法和返回结果。

## 三、Java 方法的重载

Java 方法重载，指的是在同一个类中，定义了多个同名的方法，这些同名的方法，具有相同的功能。每个方法具有不同的参数类型，或参数个数，或参数顺序，这些同名的方法，就构成了重载关系。

简单的说：在同一个类中，**方法名相同**，**参数（个数，或类型，或顺序）不同**的方法，就构成了方法重载。与返回值无关。

判断以下类中的方法，是否构成重载关系：

第一个类中的方法，不构成重载关系。

demo-project/base-code/Day06/src/com/kkcf/method/MethodOverloadDemo01.java

```java
package com.kkcf.method;

public class MethodOverloadDemo01 {
    public static void fn (int a) {

    }

    public static int fn (int a) {

    }
}
```

- 因为两个同名方法的形参，完全相同。

第二个类中的方法，构成重载关系：

demo-project/base-code/Day06/src/com/kkcf/method/MethodOverloadDemo01.java

```java
package com.kkcf.method;

public class MethodOverloadDemo01 {
    public static float fn (int a) {

    }

    public static int fn (int a, int b) {

    }
}
```

第三个类中的方法，不构成重载关系：

demo-project/base-code/Day06/src/com/kkcf/method/MethodOverloadDemo01.java

```java
package com.kkcf.method;

public class MethodOverloadDemo01 {
    public static void fn (int a) {

    }
}

public class MethodOverloadDemo02 {
    public static int fn (double a) {

    }

}
```

- 因为这两个同名方法，不在一个类中。

第四个类中的方法，构成重载关系：

demo-project/base-code/Day06/src/com/kkcf/method/MethodOverloadDemo01.java

```java
package com.kkcf.method;

public class MethodOverloadDemo01 {
    public static void fn (int a) {

    }

    public static void fn (double a) {

    }
}
```

第五个类中的方法，构成重载关系：

demo-project/base-code/Day06/src/com/kkcf/method/MethodOverloadDemo01.java

```java
package com.kkcf.method;

public class MethodOverloadDemo01 {
    public static void fn (int a, double b) {

    }

    public static void fn (double a, int b) {

    }
}
```

- 形参顺序的不同，可以构成重载关系，但在开发中一般不这么写，意义不大。

案例理解；使用方法重载的思想，设计比较两个整数，是否相同的方法，要求兼容全部整数类型（byte, short, int, long）。

demo-project/base-code/Day06/src/com/kkcf/method/MethodOverloadDemo01.java

```java
package com.kkcf.method;

public class MethodOverloadDemo01 {
    public static void main(String[] args) {
        System.out.println(compare(10, 20));
        System.out.println(compare((byte)10, (byte)20));
        System.out.println(compare((short)10, (short)20));
        System.out.println(compare((long)10, (long)20));
    }

    public static boolean compare(byte b1, byte b2) {
        return b1 == b2;
    }

    public static boolean compare(short s1, short s2) {
        return s1 == s2;
    }

    public static boolean compare(int i1, int i2) {
        return i1 == i2;
    }

    public static boolean compare(long l1, long l2) {
        return l1 == l2;
    }
}
```

## 四、Java 方法的练习

案例理解：定义一个方法 `copyOfRange(int[] src, int from, int to)`，将数组 arr，从索引 `from`（包含 `frome`）开始，到索引 `to`（不包含 `to`）的元素复制到新数组中，将新数组返回：

demo-project/base-code/Day06/src/com/kkcf/method/MethodTest03.java

```java
package com.kkcf.method;

public class MethodTest03 {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        int[] range = copyOfRange(arr, 2, 8);
        printArray(range);
    }

    public static int[] copyOfRange(int[] src, int from, int to) {
        int[] dest = new int[to - from];

        for (int i = 0; i < dest.length; i++)
            dest[i] = src[from + i];

        return dest;
    }

    // 打印数组
    public static void printArray(int[] arr) {
        System.out.print("[");

        for (int i = 0; i < arr.length; i++)
            System.out.print(i == arr.length - 1 ? arr[i] + "" : arr[i] + ", ");

        System.out.println("]");
    }

}
```

## 五、Java 方法的内存

### 1.Java 方法调用时的基本内存原理

- 方法被调用时，压入到栈内存中；
- 方法执行完毕，从栈内存中弹出。
- 方法被压入栈内存中，是先进后出的。

### 2.Java 方法传递基本数据类型的内存原理

基本数据类型，栈内存变量里面，存储的是栈内存里的真实的数据。

- 特点：赋值给其它变量，也是赋的真实值。

在方法中，传递基本数据类型，代码如下：

demo-project/base-code/Day06/src/com/kkcf/method/MethodDemo05.java

```java
package com.kkcf.method;

public class MethodDemo05 {
    public static void main(String[] args) {
        int num = 100;

        System.out.println(num); // 100
        change(num);
        System.out.println(num); // 100
    }

    public static void change(int num) {
        num = 200;
    }
}
```

### 3.Java 方法传递引用数据类型的内存原理

引用数据类型，栈内存变量里面，存储的是堆内存中的地址值。

- 特点：赋值给其它变量，赋的是地址值。

在方法中，传递引用数据类型，代码如下：

demo-project/base-code/Day06/src/com/kkcf/method/MethodDemo06.java

```java
package com.kkcf.method;

public class MethodDemo06 {
    public static void main(String[] args) {
        int[] arr = {10, 20, 30};

        System.out.println(arr[1]); // 10
        change(arr);
        System.out.println(arr[1]); // 200
    }

    public static void change(int[] arr) {
        arr[1] = 200;
    }
}
```
