# Java 面向对象之内部类

Java 中的内部类，是 Java 类的五大成员之一，这五大成员分别是：

- 属性（成员变量）
- 方法（成员方法）
- 构造方法
- 代码块
- 内部类

内部类，就是在一个 Java 类的里面，再定义一个类。

“内部类”，是“外部类”中的类，与它们不相干的类统称为“外部其它类”

## 一、Java 内部类的设计原则

Java 内部类的设计原则：

- 内部类表示的事物，是外部类的一部分。
- 内部类单独出现，没有任何意义。

> `ArrayList` 内部的 `Itr` 就是一个成员内部类，用于集合列表的迭代器类。

## 二、Java 内部类的特点

内部类的访问特点：

- 内部类可以直接访问外部类的成员，包括私有成员。
- 外部类要访问内部类的成员，必须创建对象。

案例理解：写一个 JavaBean 类，描述汽车。

属性有：汽车品牌，车龄，颜色，发动机的品牌，发送机使用年限。

demo-project/base-code/Day15/src/com/kkcf/innerclass/Car.java

```java
package com.kkcf.innerclass;

public class Car {
    private String carName;
    int carAge;
    String carColor;

    public void show() {
        Engin e = new Engin();
        System.out.println(carName + " power by " + e.engineName);
    }

    class Engin {
        String engineName;
        String engineAge;

        public void show() {
            System.out.println(carName + " power by  " + engineName);
        }
    }
}
```

## 三、Java 内部类的种类

### 1.成员内部类

成员内部类写在成员位置，即类中，方法外；

#### 1.成员内部类的书写

成员内部类的地位，与成员方法，成员变量一样；成员内部类编写的注意点：

- 成员内部类可以被权限修饰符所修饰，比如： `private`，`默认`，`protected`，`public`，……
- 在成员内部类里面，JDK16 之前不能定义静态变量，JDK16 开始才可以定义静态变量。

> 被 `static` 修斯的成员内部类，是静态内部类。

#### 2.成员内部类创建对象

成员内部类创建对象的两种方式：

- 方式一：在外部类中编写方法，对外提供内部类对象。
  - 一般在内部类被私有化时使用。
- 方式二：直接创建对象，格式为：`外部类名.内部类名 对项名 = new 外部类名().new 内部类名()`
  - 一般在内部类公开时使用。

方式一：

外部类如下，其中内部类使用了 `private` 修饰：

demo-project/base-code/Day15/src/com/kkcf/innerclass/Outer1.java

```java
package com.kkcf.innerclass;

public class Outer1 {

    public Inner1 getInstance() {
        return new Inner1();
    }

    private class Inner1 {

    }
}
```

测试类如下：

- 因为现在 `Inner` 内部类是 `private` 修饰，外界无法直接使用；
- 所以，利用多态的特性，使用 `Object` 类型，接收 `Inner` 类型的变量。

demo-project/base-code/Day15/src/com/kkcf/innerclass/Test1.java

```java
package com.kkcf.innerclass;

public class Test1 {
    public static void main(String[] args) {
        Outer1 o = new Outer1();

        Object i = o.getInstance();
        System.out.println(i); // com.kkcf.innerclass.Outer1$Inner1@41629346
    }
}
```

> Java 内存中的地址值，用 `$` 号分隔外部类和内部类。

方式二：

外部类如下：

demo-project/base-code/Day15/src/com/kkcf/innerclass/Outer1.java

```java
package com.kkcf.innerclass;

public class Outer1 {

    class Inner1 {

    }
}
```

测试类如下：

demo-project/base-code/Day15/src/com/kkcf/innerclass/Test1.java

```java
package com.kkcf.innerclass;

public class Test1 {
    public static void main(String[] args) {
        Outer1.Inner1 oi = new Outer1().new Inner1();
    }
}
```

#### 3.成员内部类获取外部类的成员

成员内部类获取外部类的成员

- 同样也遵循就近原则。
- JDK8 以前，成员内部类要访问外部类的成员变量，该变量必须手动用 `final` 修饰；JDK8 之后，不需要手动写，JVM 会默认加上。
- 创建内部类对象时，对象中有一个隐含的 `外部类名.this` 记录外部类对象的地址值，可用于访问外部类的成员。

现有如下代码：

外部类：

demo-project/base-code/Day15/src/com/kkcf/innerclass/Outer2.java

```java
package com.kkcf.innerclass;

public class Outer2 {
    private int a = 10;

    class Inner2 {
        private int a = 20;

        public void show() {
            int a = 30;

            System.out.println(a); // 30
            System.out.println(this.a); // 29
            System.out.println(Outer2.this.a); // 10
        }

    }
}
```

测试类：

demo-project/base-code/Day15/src/com/kkcf/innerclass/Test2.java

```java
package com.kkcf.innerclass;

public class Test2 {
    public static void main(String[] args) {
        Outer2.Inner2 oi = new Outer2().new Inner2();

        oi.show();
    }
}
```

上面代码的内存表现，如下图所示

![内部类内存图](NodeAssets/内部类内存图.png)

- 内部类，和外部类的会编译为两个独立的字节码文件。

### 2.静态内部类

使用 `static` 关键字修饰的成员内部类，就是静态内部类。

在静态内部类中，只能访问外部类中的静态变量，和静态方法；如果要访问外部类的非静态成员，那么要创建外部类的对象。

创建静态内部类对象的格式为：`外部类名.内部类名 对象名 = new 外部类名.内部类名();`

调用非静态方法的：1.先创建对象；2.再用对象调用。

调用静态方法的格式：`外部类名.内部类名.方法名`

### 3.局部内部类

写在方法里面。

### 4.匿名内部类

没有手动命名的内部类。