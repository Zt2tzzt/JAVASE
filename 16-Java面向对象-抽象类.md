# Java 面向对象之抽象类、抽象方法

## 一、Java 抽象类

Java 抽象类，可以有构造方法，但不能被实例化，

Java 抽象类的构造方法，不能用 `private` 修饰，因为要用于子类创建实例对象时，调用父类的构造方法，给子类对象进行构造函数初始化。

在 Java 中，抽象方法所在的类，必须是抽象类；抽象类中可以没有抽象方法。

Java 抽象类的子类，要么重写抽象类中的所有抽象方法；要么是抽象类。

所以，抽象类最主要是用于：强制子类必须按照父类方法的格式进行方法重写（方法名，参数都要相同）。

抽象类的定义格式：`public abstract class 类名 {}`

## 二、Java 抽象方法

在设计 Java 继承结构时：

1. 将子类共性的行为，抽取到父类以后；
2. 由于每一个子类执行的方法都是不同的逻辑。所以父类中不能确定具体的方法体；
3. 那么该方法在父类中，就可以定义为**抽象方法**。用于强制子类按照固定的格式，对方法进行重写。

抽象方法的定义格式：`public abstract 返回值类型 方法名(参数列表) {}`

定义一个抽象类 `Person`

demo-project/base-code/Day15/src/com/kkcf/abstractt/Person.java

```java
package com.kkcf.abstractt;

public abstract class Person {
    public abstract void work();
}
```

子类 `Teacher` 重写抽象类中的抽象方法：

demo-project/base-code/Day15/src/com/kkcf/abstractt/Teacher.java

```java
package com.kkcf.abstractt;

public class Teacher extends Person {
    @Override
    public void work() {
        System.out.println("老师正在工作");
    }
}
```

案例理解：现有如下动物

- 青蛙 Frog
  - 属性：名字，年龄。
  - 行为：吃虫子，喝水；
- 狗 Dog
  - 属性：名字，年龄。
  - 行为：吃骨头，喝水；
- 山羊 Sheep
  - 属性：名字，年龄。
  - 吃草，喝水；

定义一个抽象类 Animal

demo-project/base-code/Day15/src/com/kkcf/abstractt/Animal.java

```java
package com.kkcf.abstractt;

public abstract class Animal {
    private String name;
    private int age;

    public Animal() {
    }

    public Animal(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void drink() {
        System.out.println("动物在喝水");
    }

    public abstract void eat();

    // getter / setter
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

青蛙类：

demo-project/base-code/Day15/src/com/kkcf/abstractt/Frog.java

```java
package com.kkcf.abstractt;

public class Frog extends Animal {
    public Frog() {
    }

    public Frog(String name, int age) {
        super(name, age);
    }

    @Override
    public void eat() {
        System.out.println("青蛙在吃虫子");
    }
}
```

狗类：

demo-project/base-code/Day15/src/com/kkcf/abstractt/Dog.java

```java
package com.kkcf.abstractt;

public class Dog extends Animal {
    public Dog() {
    }

    public Dog(String name, int age) {
        super(name, age);
    }

    @Override
    public void eat() {
        System.out.println("狗在吃骨头");
    }
}
```

山羊类

demo-project/base-code/Day15/src/com/kkcf/abstractt/Sheep.java

```java
package com.kkcf.abstractt;

public class Sheep extends Animal{
    public Sheep() {
    }

    public Sheep(String name, int age) {
        super(name, age);
    }

    @Override
    public void eat() {
        System.out.println("羊在吃艹");
    }
}
```
