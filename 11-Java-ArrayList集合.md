# Java ArrayList 集合

ArrayList 集合，也是容器，也可用来存储多个数据。

## 一、ArrayList 与数组的区别

ArrayList 集合，与数组的区别有两个：

- ArrayList 集合的**长度是可变的**，当添加元素时，会自动扩容；数组的长度是固定的。
- ArrayList 集合**不能存储基本类型**，必须要把它们变成对应的包装类；数组可以存储基本数据类型。

## 二、ArrayList 的使用

`ArrayList` 类，定义在 `java.util` 包下，使用时，需要导包。

`ArrayList` 类，结合泛型使用，可以约束集合中存储元素的数据类型。

### 1.ArrayList 构造方法

`ArrayList` 类，常用的构造方法有：

- `public ArrayList()`，构造一个初始容量为 10 的空列表。

JDK 7 以前，创建 ArrayList 集合列表对象的方式：

```java
ArrayList<String> strings = new ArrayList<String>();
```

JDK 7 及以后，创建 ArrayList 集合列表对象的方式：

```java
ArrayList<String> strings = new ArrayList<>();
```

ArrayList 类内部做了处理，在打印 ArrayList 类创建的集合对象时，会展示对象中存储的数据，会用 `[]` 将内容包裹：

```java
ArrayList<String> strings = new ArrayList<>();

System.out.println(strings); // []
```

- 直接打印数组对象时，会输出它在内存中的地址值。

### 2.ArraylList 成员方法

ArrayList 类，常用的成员方法有：

- `public boolean add(E e)`，将指定的元素添加到此集合列表的尾部，返回是否添加成功。
- `public boolean remove(Object o)`，移除此集合列表中首次出现的指定元素，如果存在并移除成功，则返回 `true`，否则返回 `false`。
- `public E remove(int index)`，移除此集合列表中指定位置上的元素。向左移动后续所有元素（将其索引减 1）。
- `public E set(int index, E element)`，用指定的元素替代此集合列表中指定位置上的元素。
- `public E get(int index)`，返回此集合列表中指定位置上的元素。
- `public int size()`，返回此列表中的元素数。

## 三、ArrayList 存储字符串

demo-project/base-code/Day11/src/com/kkcf/ArrayList/Demo01.java

```java
package com.kkcf.ArrayList;

import java.util.ArrayList;

public class Demo01 {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();

        // 增
        list.add("aaa");
        list.add("bbb");
        list.add("ccc");
        list.add("ddd");

        System.out.println(list); // [aaa, bbb, ccc, ddd]

        // 删
        boolean isRemove1 = list.remove("aaa");
        System.out.println(isRemove1); // true
        System.out.println(list); // [bbb, ccc, ddd]

        String removeString = list.remove(0);
        System.out.println(removeString); // bbb
        System.out.println(list); // [ccc, ddd]

        // 改
        String settedString = list.set(1, "xxx");
        System.out.println(settedString); // ddd
        System.out.println(list); // [ccc, xxx]

        // 查
        String s = list.get(1);
        System.out.println(s); // xxx

        // 遍历
        for (int i = 0; i < list.size(); i++) {
            String s1 = list.get(i);
            System.out.println(s1);
        }
    }
}
```

案例理解：定义一个 ArrayList 集合列表，添加字符串，并进行遍历；遍历格式参照：[元素1, 元素2, 元素3]。

demo-project/base-code/Day11/src/com/kkcf/ArrayList/Demo02.java

```java
package com.kkcf.ArrayList;

import java.util.ArrayList;

public class Demo02 {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();

        list.add("kumiko");
        list.add("mayu");
        list.add("kaori");
        list.add("azusa");

        System.out.print("[");
        for (int i = 0; i < list.size(); i++) {
            String s = list.get(i);
            System.out.print(i == list.size() - 1 ? s : s + ", ");
        }
        System.out.println("]");

    }
}
```

## 四、ArrayList 存储数字

### 1.基本数据类型对应的包装类

基本数据类型对应的包装类，如下表所示：

| 基本数据类型 | 引用数据类型 |
| ------------ | ------------ |
| byte         | Byte         |
| short        | Short        |
| char         | Character    |
| int          | Integer      |
| long         | Long         |
| float        | Float        |
| double       | Double       |
| boolean      | Boolean      |

案例理解：定义一个集合，添加数字，并进行遍历。遍历格式参照：[元素1, 元素2, 元素3]

> JDK5 及以后，`int` 基本数据类型和 `Integer` 包装类型之间，可以自动**相互转换**。

demo-project/base-code/Day11/src/com/kkcf/ArrayList/Demo03.java

```java
package com.kkcf.ArrayList;

import java.util.ArrayList;

public class Demo03 {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();

        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);

        System.out.print("[");
        for (int i = 0; i < list.size(); i++) {
            int s = list.get(i);
            System.out.print(i == list.size() - 1 ? s : s + ", ");
        }
        System.out.println("]");
    }
}
```

## 五、ArrayList 存储学生对象

案例理解：定义一个集合列表，添加一些学生对象，并进行遍历。学生类的属性为：姓名，年龄。

创建 JavaBean 类 `Student`。

demo-project/base-code/Day11/src/com/kkcf/ArrayList/Student.java

```java
package com.kkcf.ArrayList;

public class Student {
    private String name;
    private int age;

    public Student() {
    }

    public Student(String name, int age) {
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
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
```

测试类：

demo-project/base-code/Day11/src/com/kkcf/ArrayList/Demo04.java

```java
package com.kkcf.ArrayList;

import java.util.ArrayList;

public class Demo04 {
    public static void main(String[] args) {
        ArrayList<Student> stus = new ArrayList<>();

        stus.add(new Student("张三", 18));
        stus.add(new Student("李四", 19));
        stus.add(new Student("王五", 20));

        for (int i = 0; i < stus.size(); i++) {
            Student stu = stus.get(i);
            System.out.println(stu.getName() + " " + stu.getAge());
        }
    }
}
```

案例理解：键盘录入学生信息，定义一个集合列表，添加录入的学生对象，并进行遍历。学生类的属性为：姓名，年龄。

demo-project/base-code/Day11/src/com/kkcf/ArrayList/Demo04.java

```java
package com.kkcf.ArrayList;

import java.util.ArrayList;
import java.util.Scanner;

public class Demo04 {
    public static void main(String[] args) {
        ArrayList<Student> stus = new ArrayList<>();

        Scanner sc = new Scanner(System.in);

        for (int i = 0; i < 3; i++) {
            System.out.println("请输入第" + (i + 1) + "个学生的姓名：");
            String name = sc.next();

            System.out.println("请输入第" + (i + 1) + "个学生的年龄：");
            int age = sc.nextInt();

            stus.add(new Student(name, age));
        }

        sc.close();

        for (int i = 0; i < stus.size(); i++) {
            Student stu = stus.get(i);
            System.out.println(stu.getName() + " " + stu.getAge());
        }
    }
}
```

## 六、ArrayList 查找用户索引

案例理解：测试类 `main` 方法中定义一个集合，存入三个用户对象。用户属性为：id，username，password。

要求：定义一个方法，根据 id 查找对应的用户信息。

- 如果存在，返回索引
- 如果不存在，返回 `-1`

demo-project/base-code/Day11/src/com/kkcf/ArrayList/Demo05.java

```java
package com.kkcf.ArrayList;

import java.util.ArrayList;

public class Demo05 {
    public static void main(String[] args) {
        ArrayList<User> list = new ArrayList<>();

        list.add(new User("1", "zhangsan", "123"));
        list.add(new User("2", "lisi", "123"));
        list.add(new User("3", "wangwu", "123"));

        int index = findUserIndex(list, "2");
        System.out.println(index);
    }

    public static int findUserIndex(ArrayList<User> list, String id) {
        for (int i = 0; i < list.size(); i++) {
            User user = list.get(i);

            if (id.equals(user.getId()))
                return i;
        }
        return -1;
    }
}
```

## 七、ArrayList 返回一个集合列表

需求：定义 JavaBean 类 `Phone`，它有属性：品牌、价格。

main 方法中，定义一个集合列表，存入三个手机对象。分别为：小米，1000；苹果：8000；锤子：2999；

定义一个方法，将价格低于 3000 的手机返回。

demo-project/base-code/Day11/src/com/kkcf/ArrayList/Phone.java

```java
package com.kkcf.ArrayList;

public class Phone {
    private String brand;
    private int price;

    public Phone() {
    }

    public Phone(String brand, int price) {
        this.brand = brand;
        this.price = price;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Phone{" +
                "brand='" + brand + '\'' +
                ", price=" + price +
                '}';
    }
}
```

测试类：

demo-project/base-code/Day11/src/com/kkcf/ArrayList/Demo06.java

```java
package com.kkcf.ArrayList;

import java.util.ArrayList;

public class Demo06 {
    public static void main(String[] args) {
        ArrayList<Phone> list = new ArrayList<>();

        list.add(new Phone("小米", 1999));
        list.add(new Phone("苹果", 8999));
        list.add(new Phone("锤子", 2999));

        ArrayList<Phone> list2 = findPhone(list);
        System.out.println(list2);
    }

    public static ArrayList<Phone> findPhone(ArrayList<Phone> list) {
        ArrayList<Phone> result = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            Phone p = list.get(i);

            if (p.getPrice() < 3000)
                result.add(p);
        }

        return result;
    }
}
```
