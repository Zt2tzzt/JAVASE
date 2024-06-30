# Java 面向对象

## 一、类和对象

在 Java 中，必须先设计类，才能获取对象。

- 类是共同特征的描述（设计图）；
- 对象是真实存在的具体实例。

一个类里面，可以有

- 成员变量（代表属性，一般是名词形式）
- 成员方法（代表行为，一般是动词）
- 构造器
- 代码块
- 内部类

创建一个手机类。

demo-project/base-code/Day08/src/com/kkcf/object_riented/Phone.java

```java
package com.kkcf.object_riented;

public class Phone {
    double price;
    String brand;

    public void call() {
        System.out.println("手机在打电话");
    }

    public void playGame() {
        System.out.println("手机在玩游戏");
    }
}
```

创建一个手机测试类，在其中

- 通过手机类，创建手机对象；
- 访问对象中的属性；格式：`对象名.成员变量`
- 访问对象中的方法。格式：`对项名.方法名(...)`

demo-project/base-code/Day08/src/com/kkcf/object_riented/PhoneTest.java

```java
package com.kkcf.object_riented;

public class PhoneTest {
    public static void main(String[] args) {
        Phone phone = new Phone();

        phone.price = 1999.98;
        phone.brand = "小米";

        System.out.println(phone.price);
        System.out.println(phone.brand);

        phone.call();
        phone.playGame();
    }
}
```

### 1.Java 类的种类

类名的首字母，建议使用大写的驼峰命名规范，需要做到见名知意，

一个 .java 文件中：

- 可以定义多个 class 类；
- 但是，只能有一个类是 `public` 修饰；
- 而且 `public` 修饰的类名，必须成为 .java 的文件名。

> 建议：一个 .java 文件中，通常只写一个类。

Java 类中：成员变量，完整定义格式为 `修饰符 数据类型 变量名称 = 初始化值;`，一般无需指定初始化值，存在默认值，规则如下：

| 数据类型     | 明细                   | 默认值 |
| ------------ | ---------------------- | ------ |
| 基本数据类型 | byte、short、int、long | 0      |
|              | float、double          | 0.0    |
|              | boolean                | false  |
| 引用类型     | 类、接口、数组、String | null   |

demo-project/base-code/Day08/src/com/kkcf/object_riented/GirlFriend.java

```java
package com.kkcf.object_riented;

public class GirlFriend {
    private String name = "aoi";
    private int age = 18;
}
```

#### 1.JavaBean 类

用来描述一类事物的类，专业叫做 JavaBean 类。在 JavaBean 类中，是不写 `main` 方法的。

#### 2.测试类

编写 `main` 方法的类，叫做测试类。

我们通常会在测试类中，创建 JavaBean 类的实例对象。

### 2.Java 类的设计

名词提炼法：将需求中的名词，提炼为类的属性。

动词提炼法：将需求中的动词，提炼为类的方法。
