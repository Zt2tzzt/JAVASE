# Java 面向对象之内部类

Java 中的内部类，是 Java 类的五大成员之一，这五大成员分别是：

- 属性（成员变量）
- 方法（成员方法）
- 构造方法
- 代码块
- 内部类

“内部类”，是相对于“外部类”来说的，它们不相干的类统称为“外部其它类”

内部类，就是在一个（外部）类的里面，再定义一个（内部）类。

## 一、Java 内部类的设计原则

Java 内部类的设计原则：

- 内部类表示的事物，应是外部类的一部分。
- 内部类单独出现，应该是没有任何意义的。

> `java.util.ArrayList` 类，内部的 `Itr` 就是一个成员内部类，用于集合列表的迭代器类。

## 二、Java 内部类的特点

内部类的访问特点：

- 内部类中，可以直接访问外部类的成员，包括私有成员。
- 外部类中，要访问内部类的成员，必须创建内部类的实例对象，再通过该对象访问。

案例理解：写一个 JavaBean 类 car，描述汽车。

属性有：汽车品牌，车龄，颜色，发动机的品牌，发动机使用年限。

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

成员内部类的地位，与成员方法，成员变量一样；

成员内部类编写的注意点：

- 成员内部类，可以被权限修饰符所修饰，比如： `private`，`空着不写`，`protected`，`public`，……
- 在成员内部类里面，JDK16 之前不能定义静态变量，JDK16 开始才可以定义静态变量。

> 被 `static` 修饰的成员内部类，是静态内部类。

#### 2.成员内部类创建对象

成员内部类创建对象的两种方式：

- 方式一：在外部类中编写方法，对外提供内部类对象。
  - 一般在成员内部类被私有化（`private` 修饰）时使用。
- 方式二：直接创建对象，格式为：`外部类名.内部类名 对项名 = new 外部类名().new 内部类名()`
  - 一般在成员内部类公开（`public`、`空着不写`、`protected` 修饰）时使用。

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

- 因为现在 `Inner` 成员内部类是 `private` 修饰，外界无法直接使用；
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

> Java 内存中的地址值，会用 `$` 号分隔外部类和内部类。

方式二：

外部类如下，内部类没有被 `private` 修饰。

demo-project/base-code/Day15/src/com/kkcf/innerclass/Outer1.java

```java
package com.kkcf.innerclass;

public class Outer1 {

    class Inner1 {

    }
}
```

测试类如下：

- 因为成员内部类 `Inner1` 是公开的，可以直接在外界创建其对象。

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

成员内部类，获取外部类的成员，有如下特点：

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

- 内部类，和外部类会编译为两个独立的字节码（.class）文件。

### 2.静态内部类

#### 1.静态内部类的书写

使用 `static` 关键字修饰的成员内部类，就是静态内部类。

#### 2.静态内部类创建对象

创建静态内部类对象的格式为：`外部类名.内部类名 对象名 = new 外部类名.内部类名();`

#### 3.静态内部类访问外部类成员

在静态内部类中：

- 只能访问外部类中的静态变量，和静态方法；
- 如果要访问外部类的非静态成员，那么要创建外部类的对象。

#### 4.外界访问静态内部类成员

外界调用静态内部类里的非静态成员（方法）的步骤：1.先创建静态内部类的实例对象；2.再用对象调用。

外界调用静态内部类里的静态成员（方法）的格式：`外部类名.内部类名.方法名`

案例理解：

demo-project/base-code/Day15/src/com/kkcf/innerclass/Outer3.java

```java
package com.kkcf.innerclass;

public class Outer3 {
    int a = 10;
    static int b = 20;

    static class Inner3 {
        public void show1() {
            System.out.println("非静态方法调用了");

            // 静态内部类访问外部非静态类成员
            Outer3 o = new Outer3();
            System.out.println(o.a); // 10

            // 静态内部类访问外部静态类成员
            System.out.println(b); // 20
        }

        public static void show2() {
            System.out.println("静态方法调用了");

            // 静态内部类访问外部非静态类成员
            Outer3 o = new Outer3();
            System.out.println(o.a); // 10

            // 静态内部类访问外部静态类成员
            System.out.println(b); // 20
        }
    }
}
```

demo-project/base-code/Day15/src/com/kkcf/innerclass/Test3.java

```java
package com.kkcf.innerclass;

public class Test3 {
    public static void main(String[] args) {
        // 创建静态内部类对象
        Outer3.Inner3 oi = new Outer3.Inner3();

        // 外部访问静态内部类非静态成员
        oi.show1();

        // 外部访问静态内部类静态成员
        Outer3.Inner3.show2();
    }
}
```

### 3.局部内部类

#### 1.局部内部类的书写

局部内部类，就是将内部类定义在方法里面，类似于方法里面的局部变量。

#### 2.局部内部类创建对象

局部内部类，只能在方法里创建对象。

#### 2.局部内部类访问外部成员

局部内部类，可以

- 直接访问外部类的成员；
- 也可以直接访问方法内的局部变量

#### 3.外界访问局部内部类中的成员

外界无法直接使用局部内部类，需要在方法里面创建对象才能使用。

案例理解：

```java
package com.kkcf.innerclass;

public class Outer4 {
    int b = 20;

    public void show() {
        int a = 10;

        // 局部内部类的书写
        class Inner4 {
            String name;
            int age;

            public void method1() {
                // 局部内部类访问外部成员
                System.out.println(b); // 20
                System.out.println(a); // 10

                System.out.println("局部内部类中的 method1 方法");
            }

            public static void method2() {
                System.out.println("局部内部类中的 method2 静态方法");
            }
        }

        // 局部内部类创建对象
        Inner4 i = new Inner4();

        // 外界访问局部内部类中的成员
        System.out.println(i.name);
        System.out.println(i.age);
        i.method1();
        Inner4.method2();
    }
}
```

### 4.匿名内部类（重点）

匿名内部类，本质上就是隐藏了名字的内部类。

匿名内部类，可以写在成员位置，也可以写在局部位置。

- 写在成员位置，那么它就是一个隐藏名字的成员内部类。
- 写在局部位置，那么它就是一个隐藏名字的局部内部类。

匿名内部类书写格式如下：

```java
new 类名或接口名() {
  重写方法;
}
```

上面代码块整体，包含了三部分内容：

- 实现 / 继承：`{}` 中的类才是字面意义上的匿名内部类，它实现或继承了 `类名或接口名` 所指定的类或接口。
- 方法重写：继承了抽象类，就要重写里面的所有抽象方法；实现了接口，就要实现里面的所有抽象方法。
- 创建对象：`new` 操作符，创建了这个匿名类的对象。`()` 表示用空参构造创建的对象。

> 匿名内部类，无需开发者手动命名；
>
> JDK 在编译时，会给匿名内部类起名，比如 `Xxx$1`，`Xxx$2`。其中 `Xxx` 一般是外部类名。

案例理解：

定义一个接口 `Swim`

demo-project/base-code/Day15/src/com/kkcf/innerclass/Swim.java

```java
package com.kkcf.innerclass;

public interface Swim {
    void swim();
}
```

定义一个抽象类 `Animal`

demo-project/base-code/Day15/src/com/kkcf/innerclass/Animal.java

```java
package com.kkcf.innerclass;

public abstract class Animal {
    public abstract void eat();
}
```

在测试类 `Test` 中，创建两个匿名内部类的实例对象，分别实现 `Swim` 接口，继承 `Animal` 抽象类：

demo-project/base-code/Day15/src/com/kkcf/innerclass/Test5.java

```java
package com.kkcf.innerclass;

public class Test5 {
    public static void main(String[] args) {
        new Swim() {
            @Override
            public void swim() {
                System.out.println("重写的 method1 方法");
            }
        };

        new Animal() {
            @Override
            public void eat() {
                System.out.println("重写的 eat 方法");
            }
        };
    }
}
```

匿名内部类的应用场景：

- 场景一：当一个类，只需要使用一次，可以省略定义类的步骤，直接使用匿名内部类，定义类并创建它的对象；
- 场景二：结合多态使用：

下方代码，在 `method` 方法中，传入一个匿名内部类创建的实例对象。

demo-project/base-code/Day15/src/com/kkcf/innerclass/Test5.java

```java
package com.kkcf.innerclass;

public class Test5 {
    public static void main(String[] args) {
        method(new Animal() {
            @Override
            public void eat() {
                System.out.println("狗在吃骨头");
            }
        });
    }

    public static void method(Animal a) {
        a.eat(); // 遵循“编译看左边，运行看右边”的规则
    }
}
```

匿名内部类的其它使用方式：

demo-project/base-code/Day15/src/com/kkcf/innerclass/Test5.java

```java
package com.kkcf.innerclass;

public class Test5 {
    public static void main(String[] args) {
        // 多态
        Swim s = new Swim() {
            @Override
            public void swim() {
                System.out.println("重写游泳的方法");
            }
        };
        s.swim();

        // 创建对象，并直接调用方法
        new Swim() {
            @Override
            public void swim() {
                System.out.println("重写游泳的方法");
            }
        }.swim();
    }
}
```
