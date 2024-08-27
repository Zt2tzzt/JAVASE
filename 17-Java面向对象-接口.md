# Java 面向对象之接口

Java 接口，就是一种规则。它是对**行为**的抽象。

Java 抽象类，表示一类**事物**；Java 接口，表示一系列**行为**。

Java 接口，相比抽象类，是更加彻底的抽象；

Java 接口，在 JDK7 及之前全部是**抽象方法**。

Java 接口，不能实例化，不能用来创建对象。

Java 接口，用关键字 `interface` 来定义，格式为：`public interface 接口名 {}`

Java 接口和 Java 类之间，有实现关系，使用 `implements` 关键字表示。格式为：`public class 类名 implements 接口名 {}`

- 一个类，可以实现多个接口，格式为：`public class 类名 implements 接口名1, 接口名2 {}`
- 一个类，可以在继承一个类的同时，实现多个接口，格式为：`public class 子类 extends 父类 implements 接口名1, 接口名2 {}`

在 Java 中，实现了接口的类，称为子类（或实现类），这个类：

- 要么重写接口中所有抽象方法（实际开发中更多的是这种）；
- 要么是抽象类。

案例理解：编写带有接口和抽象类的 JavaBean 类。

- 青蛙 Frog
  - 属性：名字，年龄。
  - 行为：吃虫子，蛙泳；
- 狗 Dog
  - 属性：名字，年龄。
  - 行为：吃骨头，狗刨；
- 兔子 Rabbit
  - 属性：名字，年龄。
  - 吃胡萝卜；

动物类：

demo-project/base-code/Day15/src/com/kkcf/interfacee/Animal.java

```java
package com.kkcf.interfacee;

public abstract class Animal {
    private String name;
    private int age;

    public Animal() {
    }

    public Animal(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // 抽象方法
    public abstract void eat();

    // getter / setter……
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
```

游泳接口：

demo-project/base-code/Day15/src/com/kkcf/interfacee/Swim.java

```java
package com.kkcf.interfacee;

public interface Swim {
    public abstract void swim();
}
```

青蛙 Frog 类，继承动物类，并实现游泳接口：

demo-project/base-code/Day15/src/com/kkcf/interfacee/Frog.java

```java
package com.kkcf.interfacee;

public class Frog extends Animal implements Swim {
    public Frog() {
    }

    public Frog(String name, int age) {
        super(name, age);
    }

    // 重写抽象类中的抽象方法
    @Override
    public void eat() {
        System.out.println("青蛙在吃虫子");
    }

    // 实现接口中的抽象方法
    @Override
    public void swim() {
        System.out.println("青蛙在蛙泳");
    }
}
```

狗 Dog 类，继承动物类，并实现游泳接口：

demo-project/base-code/Day15/src/com/kkcf/interfacee/Dog.java

```java
package com.kkcf.interfacee;

public class Dog extends Animal implements Swim {
    public Dog() {
    }

    public Dog(String name, int age) {
        super(name, age);
    }

    // 重写抽象类中的抽象方法
    @Override
    public void eat() {
        System.out.println("狗在吃骨头");
    }

    // 实现接口中的抽象方法
    @Override
    public void swim() {
       System.out.println("狗在狗刨");
    }
}
```

兔子类，继承动物类。

demo-project/base-code/Day15/src/com/kkcf/interfacee/Rabbit.java

```java
package com.kkcf.interfacee;

public class Rabbit extends Animal {
    public Rabbit() {
    }

    public Rabbit(String name, int age) {
        super(name, age);
    }

    // 重写抽象类中的抽象方法
    @Override
    public void eat() {
        System.out.println("兔子在吃胡萝卜");
    }
}
```

## 一、Java 接口中的成员

接口中的成员，有成员变量，成员方法，没有构造方法。

### 1.接口中的成员变量

接口代表一种规则，规则是不允许改变的，所以：

接口中的成员变量，只能是常量；默认修饰符是 `public static final`

接口中的常量，一般使用 `接口名.常量` 访问。

### 2.接口中的成员方法

接口中的成员方法，默认修饰符是 `public abstract`

- JDK7 及之前，接口中只能是**非私有的抽象方法**；
- JDK8 及以后，接口中可以定义**有方法体的方法**。
- JDK9 及以后，接口中可以定义**私有方法**。

### 3.接口中没有构造方法

接口不能创建对象。也不需要给子类的成员变量赋值，所以：Java 接口中，没有构造方法。

## 二、Java 接口与类之间的关系

在 Java 中，要分清楚接口与类之间的关系，要先分清以下三种关系：

### 1.类与类之间的关系

在 Java 中，类与类之间，只能是继承关系，且只能是单继承，不能多继承，可以多层继承。

### 2.接口与类的关系

在 Java 中，接口与类之间，是实现关系，类实现接口，可以单实现，多实现，还可以在继承一个类的同时多实现接口。

如果一个类，多实现的接口中：

- 有同名的方法，那么只需要实现一次即可：
- 有同名的方法，但是参数不同，那么需要使用方法重载来实现：

定义一个接口 `Inter1`

demo-project/base-code/Day15/src/com/kkcf/interfacee/Inter1.java

```java
package com.kkcf.interfacee;

public interface Inter1 {
    // 接口中的方法，默认用 public abstract 修饰
    void method1();
    void method2();
    void method3();
}
```

再定义一个接口 `Inter2`，里面有和 `Inter1` 同名的方法。

demo-project/base-code/Day15/src/com/kkcf/interfacee/Inter2.java

```java
package com.kkcf.interfacee;

public interface Inter2 {
    // 接口中的方法，默认用 public abstract 修饰
    void method1();
    void method2();
    void method3();
    void method4();
}
```

定义一个类 `InterImpl`，实现 `Inter1` 和 `Inter2` 接口；同方法签名的方法，只需要实现一次即可。

demo-project/base-code/Day15/src/com/kkcf/interfacee/InterImpl.java

```java
package com.kkcf.interfacee;

public class InterImpl implements Inter1, Inter2 {
    @Override
    public void method1() {

    }

    @Override
    public void method2() {

    }

    @Override
    public void method3() {

    }

    @Override
    public void method4() {

    }
}
```

### 3.接口和接口的关系

在 Java 中，接口和接口之间，是继承关系，可以单继承，也可以多继承。

如果实现类，实现了继承关系中最下层的子接口，那么就要实现其中所有的抽象方法。

案例理解：

定义一个接口 `Inter1`

demo-project/base-code/Day15/src/com/kkcf/interfacee2/Inter1.java

```java
package com.kkcf.interfacee2;

public interface Inter1 {
    // 接口中的方法，默认用 public abstract 修饰
    void method1();
}
```

定义一个接口 `Inter2`

demo-project/base-code/Day15/src/com/kkcf/interfacee2/Inter2.java

```java
package com.kkcf.interfacee2;

public interface Inter2 {
    // 接口中的方法，默认用 public abstract 修饰
    void method2();
}
```

定义一个接口 `Inter3`，继承 `Inter1`, `Inter2`

demo-project/base-code/Day15/src/com/kkcf/interfacee2/Inter3.java

```java
package com.kkcf.interfacee2;

public interface Inter3 extends Inter1, Inter2{
    // 接口中的方法，默认用 public abstract 修饰
    void method3();
}
```

定义一个类 `InterImpl`，实现 `Inter3` 接口。

demo-project/base-code/Day15/src/com/kkcf/interfacee2/InterImpl.java

```java
package com.kkcf.interfacee2;

public class InterImpl implements Inter3{
    @Override
    public void method3() {

    }

    @Override
    public void method1() {

    }

    @Override
    public void method2() {

    }
}
```

案例理解：编写实现接口，和继承抽象类的标准 JavaBean 类。

我们现在有乒乓球运动员篮球运动员，乒乓球教练和篮球教练。

- 乒乓球运动员：姓名，年龄，学打乒乓球，说英语；
- 乒乓球教练：姓名，年龄，教打乒乓球，说英语；
- 篮球运动员：姓名，年龄，学打篮球；
- 篮球教练：姓名，年龄，教打篮球；

定义一个抽象类，人类 `Person`；

- `Person` 类设置为抽象类，但其中没有抽象方法，原因只是不想让外界直接创建该类的实例对象。

demo-project/base-code/Day15/src/com/kkcf/interfacee2/Person.java

```java
package com.kkcf.interfacee2;

public abstract class Person {
    private String name;
    private int age;

    public Person() {
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
```

定义一个抽象类，运动员类：`Athlete`

demo-project/base-code/Day15/src/com/kkcf/interfacee2/Athlete.java

```java
package com.kkcf.interfacee2;

public abstract class Athlete extends Person {
    public Athlete() {
    }

    public Athlete(String name, int age) {
        super(name, age);
    }

    public abstract void learn();
}
```

定义一个抽象类，教练类：`Coach`

demo-project/base-code/Day15/src/com/kkcf/interfacee2/Coach.java

```java
package com.kkcf.interfacee2;

public abstract class Coach extends Person {
    public Coach() {
    }

    public Coach(String name, int age) {
        super(name, age);
    }

    public abstract void teach();
}
```

定义一个接口 `SpeakEnglish`

demo-project/base-code/Day15/src/com/kkcf/interfacee2/SpeakEnglish.java

```java
package com.kkcf.interfacee2;

public interface SpeakEnglish {
    // 接口中的方法，默认用 public abstract 修饰
    void speakEnglish();
}
```

定义一个乒乓球运动员类 `PingPangPlayer`，继承 `Athlete` 类，实现 `SpeakEnglish` 接口。

demo-project/base-code/Day15/src/com/kkcf/interfacee2/PingPangPlayer.java

```java
package com.kkcf.interfacee2;

public class PingPangPlayer extends Athlete implements SpeakEnglish {
    public PingPangPlayer() {
    }

    public PingPangPlayer(String name, int age) {
        super(name, age);
    }

    @Override
    public void learn() {
        System.out.println("学打乒乓球");
    }

    @Override
    public void speakEnglish() {
        System.out.println("乒乓球运动员说英语");
    }
}
```

定义一个篮球运动员类 `BasketballPlayer`，继承 `Athlete` 类。

demo-project/base-code/Day15/src/com/kkcf/interfacee2/BasketballPlayer.java

```java
package com.kkcf.interfacee2;

public class BasketballPlayer extends Athlete {
    public BasketballPlayer() {
    }

    public BasketballPlayer(String name, int age) {
        super(name, age);
    }

    @Override
    public void learn() {
        System.out.println("学打篮球");
    }
}
```

定义一个乒乓球教练类 `PingPangCoach`，继承 `Coach` 类，实现 `SpeakEnglish` 接口。

demo-project/base-code/Day15/src/com/kkcf/interfacee2/PingPangCoach.java

```java
package com.kkcf.interfacee2;

public class PingPangCoach extends Coach implements SpeakEnglish {
    public PingPangCoach() {
    }

    public PingPangCoach(String name, int age) {
        super(name, age);
    }

    @Override
    public void teach() {
        System.out.println("教打乒乓球");
    }

    @Override
    public void speakEnglish() {
        System.out.println("乒乓球教练说英语");
    }
}
```

定义一个篮球教练类 `BasketballCoach`，继承 `Coach` 类。

demo-project/base-code/Day15/src/com/kkcf/interfacee2/BasketballCoach.java

```java
package com.kkcf.interfacee2;

public class BasketballCoach extends Coach {
    public BasketballCoach() {
    }

    public BasketballCoach(String name, int age) {
        super(name, age);
    }

    @Override
    public void teach() {
        System.out.println("教打篮球");
    }
}
```

定义一个测试类：

demo-project/base-code/Day15/src/com/kkcf/interfacee2/Test.java

```java
package com.kkcf.interfacee2;

public class Test {
    public static void main(String[] args) {
        PingPangPlayer ppp = new PingPangPlayer("刘诗雯", 23);
        System.out.println(ppp.getName() + " " + ppp.getAge());
        ppp.learn();
        ppp.speakEnglish();
    }
}
```

## 三、Java 接口在 JDK8 及以后的使用

JDK8 及以后接口中允许**有方法体的方法**存在，这是为了在接口升级（增加了很多新的抽象方法）时，提升代码兼容性。

- 接口升级后，实现类不需要立马修改，等以后用到某个规则，再重写接口中的方法即可。

JDK8 及以后，接口中可以定义有方法体的方法，可以是：

- 默认（`default` 修饰的）方法。
- 静态（`static` 修饰的）方法。

### 1.接口中的默认方法

JDK8 及以后接口中的默认方法，要用 `default` 关键字修饰：

- 格式为：`public default 返回值类型 方法名(参数列表) {}`；比如：`public default void show() {}`
- `public` 是默认修饰符，可省略。

子类实现接口中的默认方法时，有如下特点：

- 特点一：JDK8 及以后接口中的默认方法，不是抽象方法，所以不强制子类实现（重写）。如果被实现（重写），子类实现（重写）的方法上要去掉 `default` 关键字。
- 特点二：如果子类实现了多个接口，且多个接口中存在相同名字的默认方法，子类就必须对该默认方法进行实现（重写）了。

特点一，理解：

定义一个接口 `Inter1`，其中有抽象方法 `method` 和默认方法 `defaultMethod`。

demo-project/base-code/Day15/src/com/kkcf/interfacee3/Inter1.java

```java
package com.kkcf.interfacee3;

public interface Inter1 {
    // 接口中的方法，默认会加上 public abstract 修饰
    void method();

    // 接口中的默认方法，默认会加上 public 修饰
    default void defaultMethod() {
        System.out.println("我是默认方法");
    }
}
```

定义一个 `InterImpl1` 类，用于实现 `Inter1` 接口。

- 必须实现接口中的抽象方法 `method`。
- 不强制要求实现默认方法 `defaultMethod`。

demo-project/base-code/Day15/src/com/kkcf/interfacee3/InterImpl1.java

```java
package com.kkcf.interfacee3;

public class InterImpl1 implements Inter1 {
    @Override
    public void method() {
        System.out.println("我实现了method 方法");
    }
}
```

定义一个测试类 `Test1`，用于测试：

demo-project/base-code/Day15/src/com/kkcf/interfacee3/Test1.java

```java
package com.kkcf.interfacee3;

public class Test1 {
    public static void main(String[] args) {
        InterImpl1 ii = new InterImpl1();

        ii.method(); // 我实现了 method 方法
        ii.defaultMethod(); // 我是默认方法
    }
}
```

特点二，理解：

再定义一个接口 `Inter2`，其中有一个与 `Inter1` 接口中同名的默认方法 `defaultMethod`，

demo-project/base-code/Day15/src/com/kkcf/interfacee3/Inter2.java

```java
package com.kkcf.interfacee3;

public interface Inter2 {
    // 接口中的默认方法，默认会加上 public 修饰
    default void defaultMethod() {
        System.out.println("我也是默认方法");
    }
}
```

在 `InterImpl1` 类中，实现 `Inter1` 和 `Inter2` 接口。

此时，就必须在子类 `InterImpl1` 中实现两接口中，同名的 `defaultMethod` 方法。

demo-project/base-code/Day15/src/com/kkcf/interfacee3/InterImpl1.java

```java
package com.kkcf.interfacee3;

public class InterImpl1 implements Inter1, Inter2 {
    @Override
    public void method() {
        System.out.println("我实现了method 方法");
    }

    @Override
    public void defaultMethod() {
        System.out.println("我重写了 defaultMethod 默认方法");
    }
}
```

在测试类 `Test1` 中，测试：

demo-project/base-code/Day15/src/com/kkcf/interfacee3/Test1.java

```java
package com.kkcf.interfacee3;

public class Test1 {
    public static void main(String[] args) {
        InterImpl1 ii = new InterImpl1();

        ii.method(); // 我实现了 method 方法
        ii.defaultMethod(); // 我重写了 defaultMethod 默认方法
    }
}
```

### 2.JDK8 及以后接口中的静态方法

JDK8 及以后接口中的静态方法，要用 `static` 关键字修饰：

- 格式为：`public static 返回值类型 方法名(参数列表) {}`，比如：`public static void show() {}`
- `public` 是默认修饰符，可省略。

JDK8 及以后接口中的静态方法：

- 只能通过接口名调用；
- 不能通过实现类名，或实例对象调用。

JDK8 及以后接口中的静态方法，不能被实现类重写（静态方法，不会添加到虚方法表中）。

案例理解：

定义一个接口 `Inter`，在其中声明一个静态方法 `staticMethod`。

demo-project/base-code/Day15/src/com/kkcf/interfacee4/Inter.java

```java
package com.kkcf.interfacee4;

public interface Inter {
    // 接口中的静态方法，会省略修饰符 public
    static void staticMethod() {
        System.out.println("静态方法调用");
    }
}
```

定义一个测试类 `Test`，用于测试该静态方法。

demo-project/base-code/Day15/src/com/kkcf/interfacee4/Test.java

```java
package com.kkcf.interfacee4;

public class Test {
    public static void main(String[] args) {
        Inter.staticMethod(); // 静态方法调用
    }
}
```

## 四、Java 接口在 JDK9 及以后的使用

JDK9 及以后接口里可以存在 `private` 修饰的私有方法，是为了

- 在接口的默认方法中，抽取共性代码；
- 并禁止外界调用该段代码逻辑。

JDK9 及以后，接口中可以定义 `private` 修饰的私有方法，可以是：

- 普通私有方法（`private` 修饰，没有 `default` 修饰）。
- 静态私有方法（`private static` 修饰）。

### 1.JDK9 及以后接口中的私有方法

JDK9 及以后接口中的私有方法，要用 `private` 关键字修饰；

它服务于接口中其它的默认（`default`）方法，用于抽取它们中共性的逻辑：

理解下方代码：

demo-project/base-code/Day15/src/com/kkcf/interfacee3/Inter1.java

```java
package com.kkcf.interfacee4;

public interface Inter1 {
    // 接口中的默认方法，默认会加上 public 修饰
    default void show1() {
        System.out.println("show1 方法开始执行了");
        record();
    }

    // 接口中的默认方法，默认会加上 public 修饰
    default void show2() {
        System.out.println("show2 方法开始执行了");
        record();
    }

    private void record() {
        System.out.println("记录程序在运行中的各种细节，此处有 100 行代码");
    }
}
```

### 2.JDK9 及以后接口中的静态私有方法

JDK9 及以后接口中的私有静态方法，要用 `private static` 关键字修饰，

服务于接口中其它的静态（`static`）方法，用于抽取它们中共性的逻辑：

demo-project/base-code/Day15/src/com/kkcf/interfacee3/Inter1.java

```java
package com.kkcf.interfacee4;

public interface Inter1 {
    // 接口中的静态方法，默认会加上 public 修饰符
    static void show1() {
        System.out.println("show1 方法开始执行了");
        record();
    }

    // 接口中的静态方法，默认会加上 public 修饰符
    static void show2() {
        System.out.println("show2 方法开始执行了");
        record();
    }

    private static void record() {
        System.out.println("记录程序在运行中的各种细节，此处有 100 行代码");
    }
}
```

## 五、Java 接口的多态

接口常用于多态，子类实现接口，也满足多态的三个前提条件：

- 有继承，或者实现的关系。
- 父类类型的引用，指向子类对象。
- 有方法的重写。

接口的多态表现形式为：`接口类型 j = new 实现类();`

实例对象，在调用接口中的方法时，也会遵循“编译看左边，运行看右边”的规则。

### 1.适配器设计模式

> **设计模式（Design Pattern）**，指的是一套被反复使用，多数人知晓的，经过分类编目的，代码设计经验的总结。
>
> 使用设计模式，是为了可重用代码，让代码更容易被他人理解，保证代码的可靠性，程序的可复用性。

适配器设计模式，用于解决接口，与接口实现类之间的矛盾问题。

- 当一个接口，抽象方法过多，但是实现类只需要使用其中一部分时，就可以使用适配器模式。

使用步骤：

1. 编写中间类 `XxxAdapter`，实现的接口。在其中**空实现**接口中的所有抽象方法；
2. 让真正的实现类，继承中间类 `XxxAdapter`，并重写需要用到的方法。
3. 将中间类 `XxxAdapter` 设为抽象类，避免外界创建它的实例对象。
4. 如果实现类有父类，先让中间类 `XxxAdapter` 继承父类，并添加构造方法即可。

案例理解，接口中有很多抽象方法：

demo-project/base-code/Day15/src/com/kkcf/interfacee4/Inter2.java

```java
package com.kkcf.interfacee4;

public interface Inter2 {
    // 接口中的方法，默认用 public abstract 修饰
    void method1();
    void method2();
    void method3();
    void method4();
    void method5();
    void method6();
    void method7();
    void method8();
    void method9();
    void method10();
}
```

假设，现有一个实现类 `InterImpl`，只需要用到其中的 `method5` 方法 ；

那么可以先定义一个中间类 `InterAdapter`（抽象类），空实现 `Inter2` 接口里的所有抽象方法。

demo-project/base-code/Day15/src/com/kkcf/interfacee4/InterAdapter.java

```java
package com.kkcf.interfacee4;

public abstract class InterAdapter implements Inter2 {
    @Override
    public void method1() {

    }

    @Override
    public void method2() {

    }

    @Override
    public void method3() {

    }

    @Override
    public void method4() {

    }

    @Override
    public void method5() {

    }

    @Override
    public void method6() {

    }

    @Override
    public void method7() {

    }

    @Override
    public void method8() {

    }

    @Override
    public void method9() {

    }

    @Override
    public void method10() {

    }
}
```

再将实现类 `InterImpl`，继承自 `InterAdapter`，重写 `method5` 方法即可。

demo-project/base-code/Day15/src/com/kkcf/interfacee4/InterImpl.java

```java
package com.kkcf.interfacee4;

public class InterImpl extends InterAdapter {
    @Override
    public void method5() {
        System.out.println("我是 method 5");
    }
}
```
