# Java 泛型

## 一、Java 泛型

Java 泛型，是 JDK5 中引入的特性，它提供了编译时类型安全检测机制。

Java 泛型，定义格式：

- `<数据类型>`：指定一种类型的格式.，尖括号里面可以任意书写，一般只写一个字母；例如: `<E>`、`<T>`
- `<数据类型1, 数据类型2…>`：指定多种类型的格式,，多种类型之间用逗号隔开.例如: `<E,T>`、`<K,V>`

Java 泛型，仅支持**引用数据类型**。

Java 泛型，在集合中的应用：

在没有泛型的时候，集合中可以添加任意类型的数据，它们都会被当作 Object 类型。

demo-project/base-code/Day23/src/com/kkcf/generics/GenericsDemo01.java

```java
package com.kkcf.generics;

import java.util.ArrayList;

public class GenericsDemo01 {
    public static void main(String[] args) {
        ArrayList list = new ArrayList();

        list.add("aaa");
        list.add(123);
        list.add(new Student("zhangsan", 23));
    }
}
```

- 在获取数据时，无法使用它的特有行为。

Java 泛型，好处：

1. 限定集合中的数据类型，把运行时期的问题，提前到了编译期间。
2. 避免了强制类型转换。

Java 泛型，是伪泛型，只在编译时期起作用，比如为集合指定泛型后。

- 事实上，集合中存储的，仍然是 Object 类型的对象；
- 只是在数据添加到集合时，会检测数据类型是否符合泛型的限定。
- 在数据取出集合时，会将数据强转为泛型指定的类型。
- 当 Java 文件，编译为 .class 文件时，泛型就会消失，这个过程称为“**泛型的擦除**”。

Java 泛型的细节总结：

- 泛型中不能写基本数据类型；
- 指定泛型的具体类型后，传递数据时，可以传入该类型或者其子类型，这是多态的体现。
- 如果不写泛型，类型默认是 Object。

Java 泛型，可以写在方法、类、接口上，它们分别称为**泛型方法**、**泛型类**、**泛型接口**。

### 1.Java 泛型类

Java 泛型类，使用场景：当一个类中，某个变量的数据类型不确定时，就可以定义带有泛型的类。

#### 1.泛型类书写格式

Java 泛型类，书写格式为：

```java
修饰符 class 类名<泛型> {

}
```

#### 2.泛型类书写使用

案例理解：使用泛型类，实现一个自己的 ArrayList。

demo-project/base-code/Day23/src/com/kkcf/generics/MyArrayList.java

```java
package com.kkcf.generics;

import java.util.Arrays;

public class MyArrayList<E> {
    Object[] obj = new Object[10];

    int size;

    public boolean add(E e) {
        obj[size++] = e;
        return true;
    }

    public E get(int index) {
        return (E) obj[index];
    }

    @Override
    public String toString() {
        return Arrays.toString(obj);
    }
}
```

编写测试类：

demo-project/base-code/Day23/src/com/kkcf/test/Test02.java

```java
package com.kkcf.test;

import com.kkcf.generics.MyArrayList;

public class Test02 {
    public static void main(String[] args) {
        MyArrayList<String> list = new MyArrayList<>();

        boolean flag = list.add("haha");
        System.out.println(flag); // true

        String str = list.get(0);
        System.out.println(str); // haha

        System.out.println(list.toString()); // [haha, null, null, null, null, null, null, null, null, null]
    }
}
```

### 2.Java 泛型方法

在 Java 中，当方法形参类型不确定时，可以使用泛型，有两种方案：

- 方案一：为方法所在的类，定义泛型；该泛型在类中都可以使用。
- 方案二：在方法声明上，定义泛型。该泛型只有在本方法中使用。

#### 1.泛型方法书写格式

Java 泛型方法，书写格式为：

```java
修饰符 <泛型> 返回值类型 方法名(类型 变量名) {

}
```

在“返回值类型”前，即所有修饰符后，编写泛型。

#### 2.泛型方法使用

案例理解：定义一个工具类：`ListUtil`。其中定义一个静态方法 `addAll`，用来添加多个元素。

demo-project/base-code/Day23/src/com/kkcf/generics/ListUtil.java

```java
package com.kkcf.generics;

import java.util.List;

public class ListUtil {
    // 私有化静态方法，不让外界创建工具类对象。
    private ListUtil() {}

    public static <E> void addAll(List<E> list, E e1, E e2, E e3) {
        list.add(e1);
        list.add(e2);
        list.add(e3);
    }

    public static <E> void addAll(List<E> list, E...e) {
        for (E ele : e) {
            list.add(ele);
        }
    }
}
```

> `E...e` 是 Java 中“可变参数”的写法，类似于 ES6 中的剩余参数，后续详细介绍

编写测试类：

demo-project/base-code/Day23/src/com/kkcf/test/Test01.java

```java
package com.kkcf.test;

import com.kkcf.generics.ListUtil;

import java.util.ArrayList;

public class Test01 {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        ListUtil.addAll(list, "hello", "world", "java");
        System.out.println(list); // [hello, world, java]

        ArrayList<Integer> list2 = new ArrayList<>();
        ListUtil.addAll(list2, 1, 2, 3);
        System.out.println(list2); // [1, 2, 3]
    }
}
```

结论：当泛型方法被调用时，泛型会被确认下来。

### 3.Java 泛型接口

在 Java 中，当接口里用到的类型不确定时，可以用泛型接口。

#### 1.泛型接口书写格式

Java 泛型接口，书写格式如下：

```java
修饰符 interface 接口名<泛型> {

}
```

#### 2.泛型接口使用

Java 泛型接口的两种使用方式：

- 方式一：实现类给出具体的泛型。
- 方式二：实现类也是泛型类，延续泛型接口的泛型，创建实现类对象时，再确定泛型。

方式一理解：定义一个 `MyArrayList02` 类，实现 `List` 接口，并指定 `List` 接口中的泛型。

demo-project/base-code/Day23/src/com/kkcf/generics/MyArrayList02.java

```java
package com.kkcf.generics;

import java.util.List;

public class MyArrayList02 implements List<String> {
    // 实现接口中的方法。方法

    // ...

    @Override
    public boolean add(String s) {
        return false;
    }
}
```

- `add` 方法，传参的类型，已被确定下来。

此时，在测试类中，创建 `MyArrayList02` 的实例，就不能再指定泛型了。

demo-project/base-code/Day23/src/com/kkcf/test/Test03.java

```java
package com.kkcf.test;

import com.kkcf.generics.MyArrayList02;

public class Test03 {
    public static void main(String[] args) {
        MyArrayList02 list = new MyArrayList02();
    }
}
```

方式二理解：定义一个 `MyArrayList03` 泛型类，实现 `List` 接口，并延续 `List` 泛型接口的泛型。

```java
package com.kkcf.generics;

import java.util.List;

public class MyArrayList03<E> implements List<E> {
    // 实现接口中的方法。方法

    // ...

    @Override
    public boolean add(E e) {
        return false;
    }
}
```

- `add` 方法，传参的类型，还未确定下来。

此时，创建 `MyArrayList03` 的实例，需要指定泛型了。

demo-project/base-code/Day23/src/com/kkcf/test/Test03.java

```java
package com.kkcf.test;

import com.kkcf.generics.MyArrayList03;

public class Test03 {
    public static void main(String[] args) {
        MyArrayList03<String> list = new MyArrayList03<>();
    }
}
```

## 二、Java 泛型没有继承

在 Java 中，泛型不具备继承性，理解下方代码：

demo-project/base-code/Day23/src/com/kkcf/generics/GenericsDemo02.java

```java
package com.kkcf.generics;

import java.util.ArrayList;

public class GenericsDemo02 {
    public static void main(String[] args) {
        ArrayList<Ye> list1 = new ArrayList<>();
        ArrayList<Fu> list2 = new ArrayList<>();
        ArrayList<Zi> list3 = new ArrayList<>();

        method(list1);

        method(list2); // 报错
        method(list3); // 报错
    }

    public static void method(ArrayList<Ye> list) {
    }
}

class Ye {}

class Fu extends Ye {}

class Zi extends Fu {}
```

在 Java 中，数据具备继承性，理解下方代码：

demo-project/base-code/Day23/src/com/kkcf/generics/GenericsDemo02.java

```java
package com.kkcf.generics;

import java.util.ArrayList;

public class GenericsDemo02 {
    public static void main(String[] args) {
        ArrayList<Ye> list1 = new ArrayList<>();

        list1.add(new Ye());
        list1.add(new Fu());
        list1.add(new Zi());

        // 数据具有继承性
        method(list1);
    }

    public static void method(ArrayList<Ye> list) {
    }
}


class Ye {}

class Fu extends Ye {}

class Zi extends Fu {}
```

## 三、Java 泛型通配符

为了解决 Java 泛型，不能继承的问题，要使用泛型的通配符。

Java 泛型的通配符是 `?`，表示所有的类型，

在通配符 `?` 上，**限定类型的范围**，常用的有两种方式：

- `? extends E`，表示限定为 E 和它的所有子类类型。
- `? super E`，表示限定为 E 和它的所有父类类型。

使用泛型的通配符，重构上方代码：

demo-project/base-code/Day23/src/com/kkcf/generics/GenericsDemo02.java

```java
package com.kkcf.generics;

import java.util.ArrayList;

public class GenericsDemo02 {
    public static void main(String[] args) {
        ArrayList<Ye> list1 = new ArrayList<>();
        ArrayList<Fu> list2 = new ArrayList<>();
        ArrayList<Zi> list3 = new ArrayList<>();

        method(list1);
        method(list2);
        method(list3);
    }

    public static void method(ArrayList<? extends Ye> list) {
    }
}


class Ye {}

class Fu extends Ye {}

class Zi extends Fu {}
```

泛型，和泛型通配符的使用总结：

- 在定义类、方法、接口的时候，不确定类型，就可以使用泛型类、泛型方法、泛型接口；
- 类型不确定，但是该类型在某个继承体系中，就可以使用泛型的通配符。

案例理解：定义一个继承结构：用于表示波斯猫、狸花猫、泰迪、哈士奇；

它们共有的属性：名字、年龄；

它们行为，吃东西：

- 波斯猫方法体行为：一只叫做 xxx 的 x 岁的波斯猫，正在吃小饼干。
- 狸花猫方法体行为：一只叫做 xxx 的 x 岁的狸花猫，正在吃鱼。
- 泰迪方法体行为：一只叫做 xxx 的 x 岁的泰迪，正在吃骨头，边吃边蹭。
- 哈士奇方法体行为，一只叫做 xxx 的 x 岁的哈士奇，正在吃骨头，边吃边拆家。

需求 1：定义一个方法，该方法能养所有品种的猫，但是不能养狗；

需求 2：定义一个方法，该方法能养所有品种的狗，但是不能养猫；

需求 2：定义一个方法，该方法能养所有动物，但是不能传递其它类型；

Animal 类：

demo-project/base-code/Day23/src/com/kkcf/javabean/Animal.java

```java
package com.kkcf.javabean;

public abstract class Animal {
    private String name;
    private int age;

    public Animal() {
    }

    public Animal(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public abstract void eat();

    // getter、getter
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

    public String toString() {
        return "Animal{name = " + name + ", age = " + age + "}";
    }
}
```

Cat 类

demo-project/base-code/Day23/src/com/kkcf/javabean/Cat.java

```java
package com.kkcf.javabean;

public abstract class Cat extends Animal {
    public Cat(String name, int age) {
        super(name, age);
    }
}
```

Dog 类

demo-project/base-code/Day23/src/com/kkcf/javabean/Dog.java

```java
package com.kkcf.javabean;

public abstract class Dog extends Animal {
    public Dog(String name, int age) {
        super(name, age);
    }
}
```

PersianCat 类，表示波斯猫

demo-project/base-code/Day23/src/com/kkcf/javabean/PersianCat.java

```java
package com.kkcf.javabean;

public class PersianCat extends Cat {
    public PersianCat(String name, int age) {
        super(name, age);
    }

    @Override
    public void eat() {
        System.out.println("一只叫做 " + getName() + " 的 " + getAge() + " 岁的波斯猫，正在吃小饼干");
    }
}
```

LihuaCat 类，表示狸花猫

demo-project/base-code/Day23/src/com/kkcf/javabean/LihuaCat.java

```java
package com.kkcf.javabean;

public class LihuaCat extends Cat {
    public LihuaCat(String name, int age) {
        super(name, age);
    }

    @Override
    public void eat() {
        System.out.println("一只叫做 " + getName() + " 的 " + getAge() + " 岁的狸花猫，正在吃鱼");
    }
}
```

TeditDog 类，表示泰迪狗

demo-project/base-code/Day23/src/com/kkcf/javabean/TeditDog.java

```java
package com.kkcf.javabean;

public class TeditDog extends Dog {
    public TeditDog(String name, int age) {
        super(name, age);
    }

    @Override
    public void eat() {
        System.out.println("一只叫做 " + getName() + " 的 " + getAge() + " 岁的泰迪，正在吃骨头，边吃边蹭。");
    }
}
```

HuskyDog 类，表示哈士奇狗

demo-project/base-code/Day23/src/com/kkcf/javabean/HuskyDog.java

```java
package com.kkcf.javabean;

public class HuskyDog extends Dog {
    public HuskyDog(String name, int age) {
        super(name, age);
    }

    @Override
    public void eat() {
        System.out.println("一只叫做 " + getName() + " 的 " + getAge() + " 岁的泰迪，正在吃骨头，边吃边拆家。");
    }
}
```

测试类：

demo-project/base-code/Day23/src/com/kkcf/test/Test04.java

```java
package com.kkcf.test;

import com.kkcf.javabean.*;

import java.util.ArrayList;

public class Test04 {
    /**
     * 此方法用于，养狗
     * @param list 狗列表
     */
    public static void keepCat(ArrayList<? extends Cat> list) {
        for (Cat cat : list)
            cat.eat();
    }

    /**
     * 此方法用于，养猫
     * @param list 猫列表
     */
    public static void keepDog(ArrayList<? extends Dog> list) {
        for (Dog dog : list)
            dog.eat();
    }

    /**
     * 此方法用于，养动物
     * @param list 猫列表
     */
    public static void keepPet(ArrayList<? extends Animal> list) {
        for (Animal animal : list)
            animal.eat();
    }

    public static void main(String[] args) {
        ArrayList<PersianCat> pcs = new ArrayList<>();
        pcs.add(new PersianCat("小a", 1));
        pcs.add(new PersianCat("小b", 1));
        pcs.add(new PersianCat("小c", 2));

        ArrayList<LihuaCat> lhs = new ArrayList<>();
        lhs.add(new LihuaCat("小d", 1));
        lhs.add(new LihuaCat("小e", 2));
        lhs.add(new LihuaCat("小f", 1));

        keepCat(pcs);
        keepCat(lhs);

        ArrayList<TeditDog> teditList = new ArrayList<>();
        teditList.add(new TeditDog("小g", 2));
        teditList.add(new TeditDog("小h", 2));
        teditList.add(new TeditDog("小i", 1));

        ArrayList<HuskyDog> huskyList = new ArrayList<>();
        huskyList.add(new HuskyDog("小j", 1));
        huskyList.add(new HuskyDog("小k", 2));
        huskyList.add(new HuskyDog("小l", 1));

        keepDog(teditList);
        keepDog(huskyList);
    }
}
```
