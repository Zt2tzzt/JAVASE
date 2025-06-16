# Java 异常

异常，表示程序运行时，出现的问题；

学习异常，就是为了处理程序中出现的异常。

Java 异常体系结构，如下图所示：

![Java异常体系结构](NodeAssets/Java异常体系结构.png)

`Error` 代表系统级错误，属于严重问题；

- 计算机系统一旦出现问题，Sun 公司会把这些错误，封装成 `Error` 对象；
- `Error` 是给 Sun 公司自己用的，不是给开发者用的，因此开发人员不需要管它。

## 一、Java 异常分类

`Exception` 表示异常，代表程序编译或运行时，可能出现的问题。

- 开发者，通常使用 `Exception` 或者它的子类，来封装程序编译或运行时，可能出现的问题。

`Exception` 有如下分类：

- `RuntimeException` 及其子类，是**运行时异常**，表示程序在运行时出现的异常（编译阶段不会出现）。
  - 比如：数组索引越界异常。

- 其它异常，除 `RuntimeException` 以外，继承自 `Exception` 的都是**编译时异常**，表示在程序编译时出现的异常。
  - 比如：日期解析异常；

![编译时异常和运行时异常](NodeAssets/编译时异常和运行时异常.png)

### 1.1.编译时异常

案例理解：使用 `SimpleDateFormat` 类，将字符串类型的时间，转为 `Date` 类型的时间，这个过程，可能会出现编译时异常：

demo-project/base-code/Day27/src/com/kkcf/exception/Demo01.java

```java
package com.kkcf.exception;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Demo01 {
    public static void main(String[] args) throws ParseException {
        String dateStr = "2024年8月5日";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年mm月dd日");
        Date date = sdf.parse(dateStr);
        System.out.println(date);
    }
}
```

编译时异常，**必须**要在代码中（编译阶段）进行处理，一般做抛出处理。

在编译阶段，Java 不会运行代码，只会检查语法是否错误，或者做一些性能优化。

因此，编译时异常，主要是**提醒开发者**，检查本地信息。

### 1.2.运行时异常

案例理解：

demo-project/base-code/Day27/src/com/kkcf/exception/Demo02.java

```java
package com.kkcf.exception;

public class Demo02 {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};
        System.out.println(arr[5]);
    }
}
```

上面的案例，是一个明显的数组越界异常，它属于**运行时异常**；

运行时异常，在代码中（编译阶段）是不需要处理的。它是代码运行时，出现的异常。

## 二、Java 异常作用

Java 异常，主要有两个作用：

### 2.1.作用一：bug 排查

异常的堆栈信息，是用来检查 bug 的关键参考信息；

创建一个 `Student` 类，在其中添加一个带参构造方法：

demo-project/base-code/Day27/src/com/kkcf/javabean/Student.java

```java
package com.kkcf.javabean;

public class Student {
    private String name;
    private int age;

    // ……

    public Student(String str) {
        String[] arr = str.split("-");
        this.name = arr[0];
        this.age = Integer.parseInt(arr[1]);
    }

    // ……
}
```

测试类：

demo-project/base-code/Day27/src/com/kkcf/exception/Demo03.java

```java
package com.kkcf.exception;

import com.kkcf.javabean.Student;

public class Demo03 {
    public static void main(String[] args) {
        Student stu = new Student("张三,23");
        System.out.println(stu);
    }
}
```

异常信息如下：

```sh
Exception in thread "main" java.lang.ArrayIndexOutOfBoundsException: Index 1 out of bounds for length 1
    at com.kkcf.javabean.Student.<init>(Student.java:13)
    at com.kkcf.exception.Demo03.main(Demo03.java:7)
```

异常的堆栈信息，应该**从下往上**看。

### 2.2.作用二：特殊返回值

作用二：异常可以作为方法内部的一种**特殊返回值**，以便通知调用者底层的执行情况；

案例理解：为 `Student` 设置年龄，限定一个范围，在 `setAge` 方法中进行限定。

Student 类：

demo-project/base-code/Day27/src/com/kkcf/javabean/Student.java

```java
package com.kkcf.javabean;

public class Student {
    private String name;
    private int age;

    public Student() {
    }

    public Student(String str) {
        String[] arr = str.split("-");
        this.name = arr[0];
        this.age = Integer.parseInt(arr[1]);
    }

    // ……

    public void setAge(int age) {
        if (age >= 18 && age <= 40) {
            this.age = age;
        } else {
            // System.out.println("年龄必须在18-40之间");
            throw new RuntimeException("年龄必须在18-40之间");
        }
    }
}
```

测试类：

demo-project/base-code/Day27/src/com/kkcf/exception/Demo03.java

```java
package com.kkcf.exception;

import com.kkcf.javabean.Student;

public class Demo03 {
    public static void main(String[] args) {
        Student stu = new Student("张三-23");
        stu.setAge(66);
        System.out.println(stu);
    }
}
```

异常信息如下：

```java
Exception in thread "main" java.lang.RuntimeException: 年龄必须在18-40之间
    at com.kkcf.javabean.Student.setAge(Student.java:38)
    at com.kkcf.exception.Demo03.main(Demo03.java:9)
```

## 三、Java 异常处理

### 3.1.JVM 默认处理方式

在发生异常后，如果代码没有进行任何异常处理，那么 Java 会把异常，**交给 JVM 虚拟机**进行处理。

- 它会把异常的名称，原因，出现的位置等信息，输出在控制台。
- 它会停止程序执行（异常下方的代码，不会再执行了）。

demo-project/base-code/Day27/src/com/kkcf/exception/Demo04.java

```java
package com.kkcf.exception;

public class Demo04 {
    public static void main(String[] args) {
        System.out.println("Hello，frog！");
        System.out.println(2/0); // 异常
        System.out.println("你好，青蛙！"); // 未打印
        System.out.println("侬好，🐸"); // 未打印
    }
}
```

异常信息：

```sh
Exception in thread "main" java.lang.ArithmeticException: / by zero
    at com.kkcf.exception.Demo04.main(Demo04.java:6)
```

### 3.2.try…catch 捕获异常的情况

自行处理异常，又称为**捕获异常**；格式是：

```java
try {
    可能出现异常的代码;
} catch (异常类名 变量名) {
    异常的处理代码;
}
```

捕获异常的目的：当异常出现时，可以让程序继续往下执行。

demo-project/base-code/Day27/src/com/kkcf/exception/Demo05.java

```java
package com.kkcf.exception;

public class Demo05 {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};

        try {
            System.out.println(arr[10]);
        } catch (ArrayIndexOutOfBoundsException err) {
            System.out.println(err); // java.lang.ArrayIndexOutOfBoundsException: Index 10 out of bounds for length 5
        }

        System.out.println("看看我执行了吗？"); // 打印了
    }
}
```

捕获异常的步骤：

1. 当 `try` 代码块中，出现异常时，程序就会在这里创建一个异常对象（比如：`ArrayIndexOutOfBoundsException` 对象）；
2. 然后拿着这个对象，与 `catch` 小括号中的参数进行对比，看这个参数是否可以接收对象。
   - 如果能被接收，表示异常被成功捕获；执行 `catch` 代码块中的代码；
3. 当 `catch` 代码块中的代码执行完毕，继续执行后面的代码。

使用 `try……catch` 代码块，捕获异常，会出现以下几种情况：

#### 3.2.1.情况一：未出现异常

情况一：`try` 代码块中，没有出现异常：

- 那么会把 `try` 代码块中的代码，全部执行完；不会执行 `catch` 代码块中的代码。

#### 3.2.2.情况二：出现多个异常

情况二：`try` 代码块中，遇到了多个异常：

- 只会生成第一个异常的对象，并与 `catch` 小括号里的异常类型进行匹配，后面的代码都不会执行。

处理这种情况，规范的做法是：写多个 `catch` 代码块，与 `try` 代码块中可能会出现的所有异常一一对应。

- 细节 1：多个异常对象中，如果存在父子关系，父类一定要写在下面。

demo-project/base-code/Day27/src/com/kkcf/exception/Demo06.java

```java
package com.kkcf.exception;

public class Demo06 {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};

        try {
            System.out.println(arr[10]);
            System.out.println(2/0);
            String s = null;
            System.out.println(s.equals("abc"));
        } catch (ArrayIndexOutOfBoundsException err) {
            System.out.println("数组越界：" + err);
        } catch (ArithmeticException err) {
            System.out.println("除数为0：" + err);
        } catch (NullPointerException err) {
            System.out.println("空指针：" + err);
        } catch (Exception err) {
            System.out.println("其他异常：" + err);
        }
    }
}
```

JDK7 新特性，允许 `catch` 小括号中的异常类型，可以使用逻辑或（`|`）符号连接，表示多个异常类型的通用处理方式：

demo-project/base-code/Day27/src/com/kkcf/exception/Demo06.java

```java
package com.kkcf.exception;

public class Demo06 {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};

        try {
            System.out.println(arr[10]);
            System.out.println(2/0);
            String s = null;
            System.out.println(s.equals("abc"));
        } catch (ArrayIndexOutOfBoundsException | ArithmeticException | NullPointerException err) {
            System.out.println("出现异常：" + err);
        }
    }
}
```

#### 3.2.3.情况三：未捕获到异常

`try` 代码块中出现的异常，`catch` 代码块中没有进行捕获。

- 那么异常会被交给 JVM 虚拟机进行处理；后面的代码也不在执行了。

### 3.3.异常对象的方法

`Throwable` 异常类中，常见的方法如下：

| 方法吗                          | 说明                                       |
| ------------------------------- | ------------------------------------------ |
| `public String getMessage()`    | 返回此 `throwable` 的详细消息字符串。      |
| `public String toString()`      | 返回此 `throwable` 的简短描述。            |
| `public void printStackTrace()` | 以红色字体把异常的错误信息，输出在控制台。 |

`printStackTrace` 方法，不会结束程序的运行，打印的信息，包含了 `getMessage` 和 `toString` 方法中的信息；

demo-project/base-code/Day27/src/com/kkcf/exception/Demo07.java

```java
package com.kkcf.exception;

public class Demo07 {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};

        try {
            System.out.println(arr[10]);
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
        }

        System.out.println("看看我执行了吗"); // 打印了
    }
}
```

控制台输出信息如下：

```sh
java.lang.ArrayIndexOutOfBoundsException: Index 10 out of bounds for length 5
    at com.kkcf.exception.Demo07.main(Demo07.java:8)
看看我执行了吗
```

`printStackTrace` 方法，源码分析：

java/lang/Throwable.java

```java
public void printStackTrace() {
    printStackTrace(System.err);
}
```

可见，`printStackTrace` 方法底层，使用了 `System.err`，用于在控制台，以红色字体输出信息，表示输出错误信息。

- `System.err` 一般用于 Java 底层源码，和一些第三方框架中；

> `System.err` 语句与 `System.out` 输出语句一起使用时，输出的顺序，可能有变化，这与多线程有关。
>
> ```java
> package com.kkcf.exception;
> 
> public class Demo07 {
>     public static void main(String[] args) {
>         System.out.println("哈哈");
>         System.err.println("哈哈");
>     }
> }
> ```

### 4.向外抛出异常

抛出异常，有两种处理方式：

#### 1.throws 关键字

`throws` 关键字，写在**方法定义处**，表示声明一个异常；

用于告诉调用者，使用该方法可能会出现的（编译时）异常；

格式如下：

```java
public void 方法名() throws 异常类名1, 异常类名2…… {

}
```

编译时异常，必须要声明；

运行时异常，可以不声明。

#### 2.throw 关键字

`throw` 关键字，写在**方法内**，用于手动抛出异常对象，把它交给方法调用者；

在方法中使用 `throw` 抛出异常后，后面的代码就不再执行了，方法结束。

格式如下：

```java
public void 方法名() {
  throw new 异常类名();
}
```

案例理解：定义一个方法，用于求数组中的最大值。

demo-project/base-code/Day27/src/com/kkcf/exception/Demo08.java

```java
package com.kkcf.exception;

public class Demo08 {
    // NullPointerException, ArrayIndexOutOfBoundsException 都是运行时异常，所以没必要使用 throws
    // public static int getMax(int[] arr) throws NullPointerException, ArrayIndexOutOfBoundsException {
    public static int getMax(int[] arr) {
        if (arr == null)
            throw new NullPointerException("数组不能为 null");

        if (arr.length == 0)
            throw new ArrayIndexOutOfBoundsException("数组长度不能为0");

        int max = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (max < arr[i])
                max = arr[i];
        }

        return max;
    }

    public static void main(String[] args) {
        int[] arr1 = null;
        try {
            int max1 = getMax(arr1);
        } catch (Exception e) {
            e.printStackTrace();
        }

        int[] arr2 = {};
        try {
            int max2 = getMax(arr2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```

异常的使用总结：

- 抛出异常，一般写在**方法里面**，用于告诉调用处，方法中出现了异常；
- 捕获异常，一般写在**方法的调用处**，用于处理可能出现的异常，并继续执行下面的代码。

## 四、Java 异常综合练习

需求：键盘录入自己心仪的女朋友姓名、年龄。

- 姓名的长度在 3~10 之间，年龄的范围为 18-40 岁；
- 超出这个范围是异常数据不能赋值，需要重新录入，一直录到正确为止。

提示：需要考虑用户在键盘录入时的所有情况。比如：

- 录入年龄时超出范围；
- 录入年龄时录入了"abc"等情况

GirlFriend 类：

demo-project/base-code/Day27/src/com/kkcf/javabean/GirlFriend.java

```java
package com.kkcf.javabean;

public class GirlFriend {
    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        int len = name.length();
        if (len < 3 || len > 10)
            throw new RuntimeException();

        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (age < 18 || age > 40)
            throw new RuntimeException();

        this.age = age;
    }

    @Override
    public String toString() {
        return "GirlFriend{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
```

测试类：

demo-project/base-code/Day27/src/com/kkcf/test/Test1.java

```java
package com.kkcf.test;

import com.kkcf.javabean.GirlFriend;

import java.util.Scanner;

public class Test1 {
    public static void main(String[] args) {
        //GirlFriend wee = new GirlFriend("wee", 26);
        //System.out.println(wee);

        Scanner sc = new Scanner(System.in);
        GirlFriend gf = new GirlFriend();

        do {
            try {
                System.out.println("请输入你心仪的女朋友的名字：");
                String nameStr = sc.nextLine();
                gf.setName(nameStr);

                System.out.println("请输入你心仪的女朋友的年龄：");
                String ageStr = sc.nextLine();
                int age = Integer.parseInt(ageStr);
                gf.setAge(age);

                break;
            } catch (NumberFormatException e) {
                System.out.println("年龄的格式有误，请输入数字");
                System.out.println(e.getMessage());
            } catch (NameFormatException e) {
                System.out.println("姓名的长度范围有误");
                System.out.println(e.getMessage());
            }
        } while (true);

        System.out.println(gf);
    }
}
```

## 五、java自定义异常

Java 自定义异常，是为了在控制台输出的异常信息，能够更加的见名知意。

Java 自定义异常类，有如下几步：

1. 定义异常类
   - 起名格式：xxx + Exception；xxx 是自定义的异常名。
2. 写继承关系
   - 编译时异常，继承 `Exception` 类；
   - 运行时异常，继承 `RuntimeException` 类。
3. 写空参构造
4. 写带参构造

自定义异常类 `NameFormatException`，继承自 `RuntimeException` 异常，表示名字格式化异常。

demo-project/base-code/Day27/src/com/kkcf/exception/NameFormatException.java

```java
package com.kkcf.exception;

public class NameFormatException extends RuntimeException {
    public NameFormatException() {
    }

    public NameFormatException(String message) {
        super(message);
    }
}
```

- 一般来说，实现上面两个构造方法即可；

自定义异常类 `AgeOutOfBoundException`，继承自 `RuntimeException` 异常：表示年龄格式化异常。

demo-project/base-code/Day27/src/com/kkcf/exception/AgeOutOfBoundException.java

```java
package com.kkcf.exception;

public class AgeOutOfBoundException extends RuntimeException {
    public AgeOutOfBoundException() {
    }

    public AgeOutOfBoundException(String message) {
        super(message);
    }
}
```

使用这两个自定义异常，重构 `GirlFriend` 类和测试类。

demo-project/base-code/Day27/src/com/kkcf/javabean/GirlFriend.java

```java
package com.kkcf.javabean;

import com.kkcf.exception.AgeOutOfBoundException;
import com.kkcf.exception.NameFormatException;

public class GirlFriend {
    private String name;
    private int age;

    public GirlFriend() {
    }

    public GirlFriend(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        int len = name.length();
        if (len < 3 || len > 10)
            throw new NameFormatException(name + "长度不能小于 3 或大于 10");

        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (age < 18 || age > 40)
            throw new AgeOutOfBoundException(age + "年龄不能小于 18 或大于 40");

        this.age = age;
    }
}

```

测试类：

demo-project/base-code/Day27/src/com/kkcf/test/Test1.java

```java
package com.kkcf.test;

import com.kkcf.exception.AgeOutOfBoundException;
import com.kkcf.exception.NameFormatException;
import com.kkcf.javabean.GirlFriend;

import java.util.Scanner;

public class Test1 {
    public static void main(String[] args) {
        //GirlFriend wee = new GirlFriend("wee", 26);
        //System.out.println(wee);

        Scanner sc = new Scanner(System.in);
        GirlFriend gf = new GirlFriend();

        do {
            try {
                System.out.println("请输入你心仪的女朋友的名字：");
                String nameStr = sc.nextLine();
                gf.setName(nameStr);

                System.out.println("请输入你心仪的女朋友的年龄：");
                String ageStr = sc.nextLine();
                int age = Integer.parseInt(ageStr);
                gf.setAge(age);

                break;
            } catch (NumberFormatException e) {
                System.out.println("年龄的格式有误，请输入数字");
                e.printStackTrace();
            } catch (NameFormatException e) {
                System.out.println("姓名的长度范围有误");
                e.printStackTrace();
            } catch (AgeOutOfBoundException e) {
                System.out.println("年龄的范围有误");
                e.printStackTrace();
            }
        } while (true);

        System.out.println(gf);
    }
}
```
