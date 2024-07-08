# Java 面向对象之包、final 关键字、权限修饰符、代码块

## 一、Java 包

包，在操作系统中，其实就是一个文件夹。

在 Java 项目中，包，用来分门别类的管理 Java 类，不同的 Java 类，放在不同的包下，方便管理和维护。

### 1.Java 包的命名

在 Java 项目中，包的命名规则，遵循：`公司域名反写 + 包的作用`；由英文小写字母组成，做到见名知意。

- 比如：`com.kkcf.domain`，`domain` 表示包的作用，可以替换为：`util`、`javabean`、……

在声明一个 Java 类（编写一个 .java 文件）时，文件的第一行，应该写上该类所在的包名：

```java
package com.kkcf.domain;

public class Person {
}
```

### 2.Java 类的全类名

加上包名的类名，在项目中应该是唯一的，比如上面的 Java 类可写作：`com.kkcf.domain.Person`。

以上这种写法，又称为类的“**全类名**”，或者“**全限定名**”

所以，创建上方类的对象实例的完整写法，如下：

```java
package com.kkcf.a01packagedemo01;

public class Test {
    public static void main(String[] args) {
        com.kkcf.domain.Person p = new com.kkcf.domain.Person();
    }
}
```

- 这种全类名写法，在同时使用不同包下的两个相同类名的类时，才会使用到。

### 3.Java 包的导包

使用 `import` 导包，可以简化上面的代码：

```java
package com.kkcf.a01packagedemo01;

import com.kkcf.domain.Person

public class Test {
    public static void main(String[] args) {
        Person p = new Person();
    }
}
```

不需要使用导包 `import` 的情况有：

- 情况一：在使用 Java 核心包（`java.lang`）中的类时。
- 情况二：在使用同一个包中的类时。

需要使用导包 `import` 的情况有：

- 情况一：在使用 Java 非核心包（`java.lang`）中的类时。
- 情况二：使用其它包中的类时。

## 二、final 关键字

在 Java 中，`final` 关键字可以修饰变量、方法、类。

在 Java 中，`final` 关键字，表示最终的，不可被改变的，当使用 `final` 关键字修饰：

- 变量，表示该变量是常量，**有且仅能被赋值一次**。
- 方法，表示该方法是最终方法，**不能被重写**。
- 类，表示该类是最终类，**不能被继承**。

### 1.final 修饰方法

demo-project/base-code/Day13-14/src/com/kkcf/a01finaldemo01/Test.java

```java
package com.kkcf.a01finaldemo01;

public class Test {
    public static void main(String[] args) {

    }
}

class Fu {
    public final void show() {
        System.out.println("父类的show方法");
    }
}

class zi extends Fu {
    @Override
    // 报错 👇
    public void show() {
        System.out.println("子类的show方法");
    }
}
```

当一个方法，表示一种既定的规则时，应该使用 `final` 修饰，表示不能被改变。

- 比如 `Object` 类中的 `getClass` 方法，就使用 `final` 修饰。

### 2.final 修饰类

demo-project/base-code/Day13-14/src/com/kkcf/a01finaldemo01/Test.java

```java
package com.kkcf.a01finaldemo01;

public class Test {
    public static void main(String[] args) {

    }
}

final class Fu {
    public void show() {
        System.out.println("父类的show方法");
    }
}

// 报错 👇
class zi extends Fu {
    @Override
    public void show() {
        System.out.println("子类的show方法");
    }
}
```

当一个类，不能被外界继承，并重写其中的方法时，应该使用 `final` 修饰。

在业务开发中很少使用，而在阅读框架原码时会经常看到：

- 比如：`String` 类，就是使用 `final` 来修饰的。

### 3.final 修饰变量

demo-project/base-code/Day13-14/src/com/kkcf/a01finaldemo01/Test.java

```java
package com.kkcf.a01finaldemo01;

public class Test {
    public static void main(String[] args) {
        final int a = 10;

        // 报错 👇
        a = 20;
    }
}
```

当一个变量，不再被改变，应该用 `final` 修饰它，表示一个常量。

- 比如 `Math` 类中的 `PI` 就用 `final` 修饰，表示圆周率的常量。

#### 1.常量的使用规范

实际开发中，常量一般作为系统的配置信息，方便代码维护，提高代码可读性。

Java 代码中，常量的命名规范如下：

- 单个单词：全部大写；
- 多个单词：全部大写，单词之间用下划线分开。

#### 2.常量的特点

Java 中，`final` 修饰的常量，有如下特点：

- `final` 修饰的变量是基本数据类型，那么其中存储的数据值，不能发生改变。
- `final` 修饰的变量是引用数据类型，那么其中存储的地址值，不能发生改变，对象内部的属性可以改变。

demo-project/base-code/Day13-14/src/com/kkcf/a01finaldemo01/Test.java

```java
package com.kkcf.a01finaldemo01;

public class Test {
    public static void main(String[] args) {
        final int[] ARR = {1, 2, 3, 4};

        // 报错 👇
        ARR = {1, 2, 3, 4};
    }
}
```

> `String` 类中，记录字符串的 `byte[]` 数组，是被 `private` 和 `final` 修饰的，所以字符串是不可变的。
>
> - `private final byte[] value;`

案例理解，重构学生管理系统中的代码：

demo-project/base-code/Day11-StudentSystem/src/com/kkcf/student/StudentSystem.java

```java
//...

public class StudentSystem {
    private static final String ADD_STU = "1";
    private static final String REMOVE_STU = "2";
    private static final String MODIFY_STU = "3";
    private static final String SHOW_STU = "4";
    private static final String EXIT = "5";


    public static void startStudentSystem() {

        Scanner sc = new Scanner(System.in);

        ArrayList<Student> stus = new ArrayList<>();

        // 给 while 循环体标号为 loop，方便 break 关键字推出循环体。
        loop:
        while (true) {
            System.out.println("”-------------欢迎来到北宇治学生管理系统----------------“");
            System.out.println("”1：添加学生“");
            System.out.println("”2：删除学生“");
            System.out.println("”3：修改学生“");
            System.out.println("”4：查询学生“");
            System.out.println("”5：退出“");
            System.out.print("”请输入您的选择:“");
            String input = sc.next();

            switch (input) {
                case ADD_STU -> addStu(stus);
                case REMOVE_STU -> removeStu(stus);
                case MODIFY_STU -> modifyStu(stus);
                case SHOW_STU -> showStu(stus);
                case EXIT -> {
                    System.out.println("”退出“");
                    // System.exit(0);
                    break loop;
                }
                default -> System.out.println("”输入错误“");
            }
        }
    }
}

//...
```

## 三、Java 权限修饰符

Java 中的权限修饰符，用于控制一个成员能够被访问的范围。

Java 中的权限修饰符，可以修饰：成员变量、方法、构造方法、内部类；

Java 中，有四种权限修饰符：它们的作用范围，从大到小是 `public > protected > 空着不写（缺省、默认） > private`

| 修饰符      | 同一个类中 | 同一个包中其它类 | 不同包下的子类 | 不同包下的无关类 |
| ----------- | ---------- | ---------------- | -------------- | ---------------- |
| `private`   | ✔          | ❌                | ❌              | ❌                |
| `空着不写`  | ✔          | ✔                | ❌              | ❌                |
| `protected` | ✔          | ✔                | ✔              | ❌                |
| `public`    | ✔          | ✔                | ✔              | ✔                |

实际开发中，一般只用 `private` 和 `public`。

- 比如，JavaBean 类中的成员变量一般都要私有化，成员方法一般都要公开。

特例：如果方法中的代码，是抽取其它方法的共性代码，那么这个方法一般也私有化。

- 比如：`ArrayList` 类中的 `grow` 方法，用于给集合列表扩容，该方法表示一种共性的规则，不需要给外界调用，所以用 `private` 修饰。

## 四、Java 代码块

Java 中的代码块，根据出现位置不同，可以分为三类：

- 局部代码块，写在方法里的局部代码块。
- 构造代码块，写在成员位置（类里，方法外）的代码块。
- 静态代码块，写在成员位置（类里，方法外），且有 `static` 关键字修饰的代码块。

### 1.Java 局部代码块

Java 中的局部代码块，通常用于提前结束变量的生命周期：本质上是为了节约内存。

实际开发中用的很少。

demo-project/base-code/Day13-14/src/com/kkcf/a01codeblockdemo01/CodeBlockDemo1.java

```java
package com.kkcf.a01codeblockdemo01;

public class CodeBlockDemo1 {
    public static void main(String[] args) {
        {
            int a = 10;
        }

        // 报错 👇
        System.out.println(a);
    }
}
```

### 2.Java 构造代码块

Java 中的构造代码块，就是写在成员位置的代码块；

Java 中的构造代码块，它会优先于类的构造方法执行，每次使用类创建对象时，都会执行。

Java 中的构造代码块，一般把多个构造方法中重复的代码，抽取在构造代码块里。

实际开发中用的很少，因为不够灵活。

demo-project/base-code/Day13-14/src/com/kkcf/a01codeblockdemo01/Student.java

```java
package com.kkcf.a01codeblockdemo01;

public class Student {
    private String name;
    private int age;

    {
        System.out.println("开始创建学生对象");
    }

    public Student() {
    }

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // ...
}
```

上面的代码的逻辑，在实际开发中，一般有两种方式替代；因为这样写更加灵活。

方式一：使用 `this(...)` 重构上面的代码：

```java
package com.kkcf.a01codeblockdemo01;

public class Student {
    private String name;
    private int age;

    public Student() {
        this(null, 0);
    }

    public Student(String name, int age) {
        System.out.println("开始创建学生对象");
        this.name = name;
        this.age = age;
    }
}
```

方式二：使用方法抽取，重构上面的代码：

```java
package com.kkcf.a01codeblockdemo01;

public class Student {
    private String name;
    private int age;

    public Student() {
        foo();
    }

    public Student(String name, int age) {
        foo();
        this.name = name;
        this.age = age;
    }

    public void foo() {
        System.out.println("开始创建学生对象");
    }
}
```

### 3.Java 静态代码块

Java 中的静态代码块，书写格式为 `static {}`，它随着类的加载而加载，并且自动触发，只执行一次。

Java 中的静态代码块，在实际开发中，一般用于在类加载的时候，做一些数据初始化。

案例理解：重构学上管理系统中，登录模块，初始化一些用户对象，放入用户列表中。

demo-project/base-code/Day11-StudentSystem/src/com/kkcf/student/App.java

```java
package com.kkcf.student;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class App {
    private static final ArrayList<User> userList = new ArrayList<>();

    static {
        userList.add(new User("admin", "123456", "123456789012345678", "12345678901"));
        userList.add(new User("zetian", "654321", "123456789012345678", "12345678901"));
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        loop:
        do {
            System.out.println();
            System.out.println("-------------欢迎来到北宇治学生管理系统-------------");
            System.out.print("请选择操作：1登录；2注册；3忘记密码：4退出");
            String chose = sc.next();

            switch (chose) {
                case "1" -> login(userList);
                case "2" -> register(userList);
                case "3" -> forgetPassword(userList);
                case "4" -> {
                    System.out.println("退出成功！");
                    break loop;
                }
                default -> System.out.println("输入错误，请重新输入");
            }
        } while (true);
    }
}

// ...
```
