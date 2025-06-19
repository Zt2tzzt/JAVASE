# Java IO 之序列化流

序列化流，属于**字节流**，它只有两个实现类：

- 字节序列化输出流：`ObjectOutputStream`
- 字节反序列化输入流：`ObjectInputStream`

## 一、ObjectOutputStream 序列化流

`ObjectOutputStream` 序列化流，又称为“对象操作输出流”，它可以把 Java 程序中的实例对象，写入到文件中。

### 1.ObjectOutputStream 构造方法

| 方法名                                       | 说明                   |
| -------------------------------------------- | ---------------------- |
| `public ObjectOutputStream(OutputStream os)` | 把基本流，包装成高级流 |

### 2.ObjectOutputStream 成员方法

| 方法名                                      | 说明                         |
| ------------------------------------------- | ---------------------------- |
| `public final void writeObject(Object obj)` | 把对象序列化（写出）到文件中 |

### 3.Serializable 接口

案例理解，使用 `ObjectOutputStream` 字节序列化输出流，将 Student 对象，写出到文件中。

要使用字节序列化输出流，写出对象到文件，要先为对象的 JavaBean 类，实现 `Serializable` 接口。否则会抛出 `NotSerializableException` 异常。

- `Serializable` 接口，是一个**标记型接口**，里面没有抽象方法。

为 Student 类，实现 `Serializable` 接口。

demo-project/base-code/Day29/src/com/kkcf/serialization/Student.java

```java
package com.kkcf.serialization;

import java.io.Serializable;

public class Student implements Serializable {
    private String name;
    private int age;

    public Student() {
    }

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // getter、setter

    // toString
}

```

在测试类中，对 Student 对象进行序列化：

demo-project/base-code/Day29/src/com/kkcf/serialization/Demo01.java

```java
package com.kkcf.serialization;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class Demo01 {
    public static void main(String[] args) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Day29/src/com/kkcf/serialization/student.txt"));

        Student stu = new Student("wee", 26);
        oos.writeObject(stu);

        oos.close();
    }
}
```

## 二、ObjectInputStream 反序列化流

`ObjectInputStream` 字节输入反序列化流，又称为“对象操作输入流”，它可以把文件中保存的序列化的 Java 对象，读取到程序（内存）中。

### 1.ObjectInputStream  构造方法

| 方法名                                     | 说明                   |
| ------------------------------------------ | ---------------------- |
| `public ObjectInputStream(InputStream is)` | 把基本流，包装成高级流 |

### 2.ObjectInputStream 成员方法

| 方法名                       | 说明                                     |
| ---------------------------- | ---------------------------------------- |
| `public Object readObject()` | 把序列化到本地文件中的对象，读取到程序中 |

- `readObject` 方法，一次只能读取一个对象，读到文件末尾没有对象可读时，会抛出异常。

案例理解，使用 `ObjectInputStream` 反序列化流，将文件中保存的序列化 Student 对象，读取到程序中。

demo-project/base-code/Day29/src/com/kkcf/serialization/Demo02.java

```java
package com.kkcf.serialization;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class Demo02 {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Day29/src/com/kkcf/serialization/student.txt"));

        Object o = ois.readObject();
        Student student = (Student) o;
        System.out.println(student);

        ois.close();
    }
}
```

## 三、序列化流细节补充

### 1.Java 类的序列号（版本号）

如果一个 JavaBean 类，实现了 `Serializable` 接口，就表示这个类是可被序列化的。

Java 底层，会根据这个类的成员变量、静态变量，构造方法、成员方法，计算出一个 long 类型的**序列号**。

这个序列号，也称为这个类的**版本号**。

该类的对象，被序列化写出到文件中时，版本号也会被写入到文件中。

此时，如果修改 JavaBean 类中的成员，反序列化文件中保存的对象，读取到程序中时，就会抛出异常 `InvalidClassException`：如下方所示：

案例理解：修改 Student 类，在其中加入成员变量 `address`：

demo-project/base-code/Day29/src/com/kkcf/serialization/Student.java

```java
package com.kkcf.serialization;

import java.io.Serializable;

public class Student implements Serializable {
    private String name;
    private int age;
    private String address;

    public Student() {
    }

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // getter、setter

    // toString
}
```

在测试类中，进行反序列化：

demo-project/base-code/Day29/src/com/kkcf/serialization/Demo02.java

```java
package com.kkcf.serialization;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class Demo02 {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Day29/src/com/kkcf/serialization/student.txt"));

        Object o = ois.readObject();
        Student student = (Student) o;
        System.out.println(student);

        ois.close();
    }
}
```

报错信息如下：

```shell
Exception in thread "main" java.io.InvalidClassException: com.kkcf.serialization.Student; local class incompatible: stream classdesc serialVersionUID = -4392380043550236176, local class serialVersionUID = -527847733620412698
```

为了避免以上错误，通常会在 JavaBean 类中，固定版本号，主要有两种方式：

方式一：在 JavaBean 类中，手动指定版本号。

```java
public class Student implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    // ……
}
```

方式二（推荐）：在 IDEA 中，进行设置，并自动生成：

1. File -> Settings -> 搜索“Serializable”关键字
2. 勾选“Serializable class without 'serialVersionUID'”;
3. 勾选“Transient field is not initialized on deserialization”

根据提示，IDEA 会自动计算并生成版本号。

```java
public class Student implements Serializable {
    @Serial
    private static final long serialVersionUID = -527847733620412698L;

    // ……
}
```

> 因为版本号根据类中的成员进行计算，所以一般写完 JavaBean 类后，再生成版本号。

### 2.transient 关键字

序列化一个对象时，如果它的某一个属性，不允许被序列化，应在该属性前，加上 `transient` 关键字，表示瞬态的。

demo-project/base-code/Day29/src/com/kkcf/serialization/Student.java

```java
public class Student implements Serializable {
    @Serial
    private static final long serialVersionUID = -527847733620412698L;
    private String name;
    private int age;
    private transient String address;

    // ……
}
```

### 3.序列化后的数据不能修改

序列化对象，写出到文件中的数据，不能被修改；否则反序列化时将抛出异常。

## 四、序列化列表集合对象

需求：将多个自定义对象，序列化到文件中，但是由于对象的个数不确定，反序列化流该如何读取呢？

思路：将要序列化的多个对象，放入一个 ArrayList 集合中，然后再对该集合进行序列化。

ArrayList 类，也实现了 Serializable 接口：并有一个固定的版本号（序列号）。

java/util/ArrayList.java

```java
public class ArrayList<E> extends AbstractList<E>
        implements List<E>, RandomAccess, Cloneable, java.io.Serializable
{
    @java.io.Serial
    private static final long serialVersionUID = 8683452581122892189L;

    // ……
}
```

在测试类中，进行序列化：

demo-project/base-code/Day29/src/com/kkcf/serialization/Demo03.java

```java
package com.kkcf.serialization;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class Demo03 {
    public static void main(String[] args) throws IOException {
        ArrayList<Student> list = new ArrayList<>(List.of(
                new Student("zhagnsan", 23, "beijing"),
                new Student("lisi", 24, "shanghai"),
                new Student("wangwu", 25, "guangzhou")
        ));

        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Day29/src/com/kkcf/serialization/student.txt"));
        oos.writeObject(list);
        oos.close();
    }
}
```

在测试类中，进行反序列化：

demo-project/base-code/Day29/src/com/kkcf/serialization/Demo04.java

```java
package com.kkcf.serialization;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class Demo04 {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Day29/src/com/kkcf/serialization/student.txt"));

        Object o = ois.readObject();
        ArrayList<Student> list = (ArrayList<Student>) o;
        list.forEach(System.out::println);

        ois.close();
    }
}
```
