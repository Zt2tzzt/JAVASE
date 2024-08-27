# Java 反射

反射，允许对类中的成员方法，成员变量，构造方法的信息，进行编程访问。可以获取到它们的所有信息。比如：

- 类中的成员变量，可以获取它本身实例对象，它的修饰符，它的名字，它的类型。取值或赋值……。
- 类中的构造方法：可以获取它本身实例对象，它的修饰符，它的名字，它的形参列表，用它创建对象……。
- 类中的成员方法，可以获取它的本身实例对象，它的修饰符，它的名字，它的形参列表，它的返回值，抛出的异常，获取注解，运行的方法……。

IDEA 中的语法提示，实际上就是利用反射实现的。

上面的操作，分为“获取”和“解刨”两部分：

1. 先从 .class 文件中“获取”到成员变量（`Field`）、成员方法（`Method`）、构造方法（`Constructor`）的实例对象；
2. 再对它们进行解刨。

## 一、获取 Class 对象

获取 class 字节码文件对象的三种方式：

- 方式一：`Class.forName("全类名")`，最常使用。
- 方式二：`类名.class`，通常作为参数传递。
- 方式三：`对象.getClass()`

这三种方式，要根据编写代码的时机，来选择。

- 源代码阶段：.java 文件，还没有编译成 .class 文件。用第一种方式。
- 加载阶段：.class 文件，加载到了内存当中。用第二种方式。
- 运行阶段；类的对象被创建了。用第三种方式。

`Class` 是 Java 中用于描述 class 字节码文件的类。

创建一个学生类 `Student`

- 其中有 `private`、`protected` 修饰的构造方法。

demo-project/base-code/Day35/src/com/kkcf/reflect/Student.java

```java
package com.kkcf.reflect;

public class Student {
    private String name;
    private int age;

    public Student() {
    }

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    protected Student(String name) {
        this.name = name;
    }

    private Student(int age) {
        this.age = age;
    }
  
    // getter、setter……

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
```

在测试类中，获取这个学生类的字节码文件对象。

demo-project/base-code/Day35/src/com/kkcf/reflect/Demo01.java

```java
package com.kkcf.reflect;

public class Demo01 {
    public static void main(String[] args) throws ClassNotFoundException {
        // 第一种方式
        Class clazz1 = Class.forName("com.kkcf.reflect.Student");
        System.out.println(clazz1); // class com.kkcf.reflect.Student

        // 第二种方式
        Class<Student> clazz2 = Student.class;

        // 第三种方式
        Student stu = new Student("zzt", 18);
        Class<? extends Student> clazz3 = stu.getClass();

        System.out.println(clazz1 == clazz2); // true
        System.out.println(clazz2 == clazz3); // true
    }
}
```

在 Java 中，万物皆对象：

- `Constructors`、`Filed`、`Method` 三个类，分别表示构造方法，成员变量，成员方法。

## 二、获取构造方法（Constructor）

`Class` 类中，用于获取构造方法实例对象的方法有：

| 方法名                                                       | 说明                           |
| ------------------------------------------------------------ | ------------------------------ |
| `Constructor<?>[] getConstructors()`                         | 返回所有公共构造方法对象的数组 |
| `Constructor<?>[] getDeclaredConstructors()`                 | 返回所有构造方法对象的数组。   |
| `Constructor<T> getConstructor(Class<?>... parameterTypes)`  | 返回单个公共构造方法对象       |
| `Constructor<T> getDeclaredConstructor(Class<?>... parameterTypes)` | 返回单个构造方法对象           |

demo-project/base-code/Day35/src/com/kkcf/reflect/Demo02.java

```java
package com.kkcf.reflect;

import java.lang.reflect.Constructor;

public class Demo02 {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException {
        Class<?> clazz = Class.forName("com.kkcf.reflect.Student");

        // 获取 public 修饰的构造方法数组
        Constructor<?>[] cons = clazz.getConstructors();
        for (Constructor<?> con : cons)
            System.out.println(con);

        /*public com.kkcf.reflect.Student()
        public com.kkcf.reflect.Student(java.lang.String,int)*/

        // 获取所有的构造方法
        Constructor<?>[] allCons = clazz.getDeclaredConstructors();
        for (Constructor<?> con : allCons)
            System.out.println(con);

        /*public com.kkcf.reflect.Student()
        private com.kkcf.reflect.Student(int)
        protected com.kkcf.reflect.Student(java.lang.String)
        public com.kkcf.reflect.Student(java.lang.String,int)*/

        // 获取 public 修饰的空参构造方法
        Constructor<?> con1 = clazz.getConstructor();
        System.out.println(con1); // public com.kkcf.reflect.Student()

        // 获取指定的带参构造方法，传入的参数，与带参构造的参数类型一致
        Constructor<?> con2 = clazz.getDeclaredConstructor(String.class);
        System.out.println(con2); // protected com.kkcf.reflect.Student(java.lang.String)

        Constructor<?> con3 = clazz.getDeclaredConstructor(int.class);
        System.out.println(con3); // private com.kkcf.reflect.Student(int)

        Constructor<?> con4 = clazz.getDeclaredConstructor(String.class, int.class);
        System.out.println(con4); // public com.kkcf.reflect.Student(java.lang.String,int)
    }
}
```

### 1.getModifiers 方法获取构造方法权限修饰符

使用 `Constructor` 类的 `int getModifiers()` 方法。

- `java.lang.reflect.Modifier` 类，用于表示权限修饰符。
- 权限修饰符常量，见[此处](https://docs.oracle.com/en/java/javase/17/docs/api/constant-values.html#java.lang.reflect.Modifier.PUBLIC)

demo-project/base-code/Day35/src/com/kkcf/reflect/Demo03.java

```java
package com.kkcf.reflect;

import java.lang.reflect.Constructor;

public class Demo03 {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException {
        Class<?> clazz = Class.forName("com.kkcf.reflect.Student");

        Constructor<?> con = clazz.getConstructor(String.class, int.class);

        int modifiers = con.getModifiers();
        System.out.println(modifiers); // 1
    }
}
```

### 2.getParameters 方法获取构造方法参数列表

使用 `Constructor` 类的 `public Parameter[] getParameters()` 方法。

- `java.lang.reflect.Parameter` 类，用于表示形参。

demo-project/base-code/Day35/src/com/kkcf/reflect/Demo03.java

```java
package com.kkcf.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Parameter;

public class Demo03 {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException {
        Class<?> clazz = Class.forName("com.kkcf.reflect.Student");

        Constructor<?> con = clazz.getConstructor(String.class, int.class);

        Parameter[] params = con.getParameters();
        for (Parameter param : params)
            System.out.println(param);

        /*java.lang.String arg0
        int arg1*/
    }
}
```

### 3.newInstance 方法调用构造方法创建对象

通过反射获取到的 `private` 修饰的构造方法，不能直接用于创建对象，需要进行**暴力反射**。

- `Constructor` 类的 `void setAccessible(boolean flag)` 方法，用于设置暴力反射。
- `Constructor` 类的 `public [T] newInstance(Object... initargs)` 方法，用于创建实例对象。

demo-project/base-code/Day35/src/com/kkcf/reflect/Demo04.java

```java
package com.kkcf.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class Demo04 {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<?> clazz = Class.forName("com.kkcf.reflect.Student");

        // 获取 public 修饰的构造方法，并创建对象
        Constructor<?> con1 = clazz.getConstructor(String.class, int.class);
        Student stu = (Student) con1.newInstance("张三", 18);
        System.out.println(stu);

        // 获取 private 修饰的构造方法，并创建对象
        Constructor<?> con2 = clazz.getDeclaredConstructor(int.class);
        con2.setAccessible(true); // 暴力反射：表示临时取消权限校验
        Student stu2 = (Student) con2.newInstance(18);
        System.out.println(stu2);
    }
}
```

- `con2.setAccessible(true);` 用于设置暴力反射，表示临时取消权限校验。

## 三、获取成员变量（Field）

在 Student 类中，添加使用 `public` 修饰的成员变量 `gender`。

demo-project/base-code/Day35/src/com/kkcf/reflect/Student.java

```java
package com.kkcf.reflect;

public class Student {
    public String gender;
    private String name;
    private int age;

    // constructors……

    // getter、setter……
}
```

`Class` 类中，用于获取成员变量的方法有：

| 方法名                                | 说明                           |
| ------------------------------------- | ------------------------------ |
| `Field[] getFields()`                 | 返回所有公共成员变量对象的熟组 |
| `Field[] getDeclaredFields()`         | 返回所有成员变量对象的熟组     |
| `Field getField(String name)`         | 返回单个公共成员变量           |
| `Field getDeclaredField(String name)` | 返回单个成员变量               |

demo-project/base-code/Day35/src/com/kkcf/reflect/Demo05.java

```java
package com.kkcf.reflect;

import java.lang.reflect.Field;

public class Demo05 {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchFieldException {
        Class<?> clazz = Class.forName("com.kkcf.reflect.Student");

        // 获取所有成员变量
        Field[] fields1 = clazz.getDeclaredFields();
        for (Field field : fields1)
            System.out.println(field);

        /*public java.lang.String com.kkcf.reflect.Student.gender
        private java.lang.String com.kkcf.reflect.Student.name
        private int com.kkcf.reflect.Student.age*/

        // 获取所有公共成员变量
        Field[] fields2 = clazz.getFields();
        for (Field field : fields2)
            System.out.println(field);

        //public java.lang.String com.kkcf.reflect.Student.gender

        // 获取单个成员变量
        Field name = clazz.getDeclaredField("name");
        System.out.println(name); // private java.lang.String com.kkcf.reflect.Student.name

        // 获取单个公共的成员变量
        Field gender = clazz.getField("gender");
        System.out.println(gender); // public java.lang.String com.kkcf.reflect.Student.gender
    }
}
```

### 1.getModifiers 方法获取成员变量权限修饰符

使用 `Field` 类的 `int getModifiers()` 方法，获取成员变量的修饰符。

demo-project/base-code/Day35/src/com/kkcf/reflect/Demo06.java

```java
package com.kkcf.reflect;

import java.lang.reflect.Field;

public class Demo06 {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchFieldException {
        Class<?> clazz = Class.forName("com.kkcf.reflect.Student");

        Field nameField = clazz.getDeclaredField("name");

        // 获取成员变量权限修饰符
        int modifiers = nameField.getModifiers();
        System.out.println(modifiers); // 2
    }
}
```

### 2.getName 方法获取成员变量名

使用 `Field` 类的 `String getName()` 方法，获取成员变量的修饰符。

demo-project/base-code/Day35/src/com/kkcf/reflect/Demo06.java

```java
package com.kkcf.reflect;

import java.lang.reflect.Field;

public class Demo06 {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchFieldException {
        Class<?> clazz = Class.forName("com.kkcf.reflect.Student");

        Field nameField = clazz.getDeclaredField("name");

        // 获取成员变量名
        String name = nameField.getName();
        System.out.println(name); // name
    }
}
```

### 3.getType 方法获取成员变量类型

使用 `Field` 类的 `Class<?> getType()` 方法，获取成员变量的类型。

demo-project/base-code/Day35/src/com/kkcf/reflect/Demo06.java

```java
package com.kkcf.reflect;

import java.lang.reflect.Field;

public class Demo06 {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchFieldException {
        Class<?> clazz = Class.forName("com.kkcf.reflect.Student");

        Field nameField = clazz.getDeclaredField("name");

        // 获取成员变量类型
        Class<?> type = nameField.getType();
        System.out.println(type); // class java.lang.String
    }
}
```

### 4.get、set 方法取值和设值成员变量

使用 `Field` 类的 `Object get(Object obj)`、`void set(Object obj, Object value)` 方法，用于获取、设置成员变量的值。

通过反射获取到的 private 修饰的成员变量，不能直接获取它记录的值，需要设置**暴力反射**。

demo-project/base-code/Day35/src/com/kkcf/reflect/Demo07.java

```java
package com.kkcf.reflect;

import java.lang.reflect.Field;

public class Demo07 {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException {
        Class<?> clazz = Class.forName("com.kkcf.reflect.Student");

        Field nameField = clazz.getDeclaredField("name");
        nameField.setAccessible(true); // 暴力反射

        Student stu = new Student("张三", 23, "男");

        // 获取 private 修饰的属性记录的值
        String nameVal = (String) nameField.get(stu);
        System.out.println(nameVal); // 张三

        // 修改 private 修饰的属性记录的值
        nameField.set(stu, "李四");
        System.out.println(stu); // Student{name='李四', age=23, gender='男'}
    }
}
```

- `nameField.setAccessible(true);` 用于设置暴力反射。

## 四、获取成员方法（Method）

在 Student 类中，加入 `sleep`，`eat` 方法

demo-project/base-code/Day35/src/com/kkcf/reflect/Student.java

```java
package com.kkcf.reflect;

public class Student {
    public String gender;
    private String name;
    private int age;

    // constructors……

    // getter、setter……

    public void sleep() {
        System.out.println("睡觉");
    }

    private String eat(String somthing) throws IOException, NullPointerException {
        System.out.println("吃饭" + somthing);
        return "奥利给!";
    }

    // toString……
}
```

`Class` 类中，用于获取成员方法实例对象的方法有：

| 方法名                                                       | 说明                                       |
| ------------------------------------------------------------ | ------------------------------------------ |
| `Method[] getMethods()`                                      | 返回所有公共成员方法对象的数组，包括继承的 |
| `Method[] getDeclaredMethods()`                              | 返回所有成员方法对象的数组，不包括继承的   |
| `Method getMethod(String name, Class<?>... parameterTypes)`  | 返回单个公共成员方法对象                   |
| `Method getDeclaredMethod(String name, Class<?>... parameterTypes)` | 返回单个成员方法对象                       |

demo-project/base-code/Day35/src/com/kkcf/reflect/Demo08.java

```java
package com.kkcf.reflect;

import java.lang.reflect.Method;

public class Demo08 {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException {
        Class<?> clazz = Class.forName("com.kkcf.reflect.Student");

        // 获取所有的 public 修饰的方法对象，包含父类中的方法
        Method[] methods1 = clazz.getMethods();
        for (Method method : methods1)
            System.out.println(method);

        System.out.println("-----------------------------------------------------");

        // 获取所有的方法对象
        Method[] methods2 = clazz.getDeclaredMethods();
        for (Method method : methods2)
            System.out.println(method);

        System.out.println("-----------------------------------------------------");

        // 获取单个指定的公共方法对象
        Method sleepMethod = clazz.getMethod("sleep");
        System.out.println(sleepMethod); // public void com.kkcf.reflect.Student.sleep()

        // 获取单个指定的方法对象
        Method eatMethod = clazz.getDeclaredMethod("eat", String.class);
        System.out.println(eatMethod); // private java.lang.String com.kkcf.reflect.Student.eat(java.lang.String) throws java.io.IOException,java.lang.NullPointerException
    }
}
```

### 1.getModifiers 方法获取成员方法权限修饰符

使用 `Method` 类的 `int getModifiers()` 方法，获取成员方法的修饰符。

demo-project/base-code/Day35/src/com/kkcf/reflect/Demo09.java

```java
package com.kkcf.reflect;

import java.lang.reflect.Method;

public class Demo09 {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException {
        Class<?> clazz = Class.forName("com.kkcf.reflect.Student");

        Method eatMethod = clazz.getDeclaredMethod("eat", String.class);
        int modifiers = eatMethod.getModifiers();
        System.out.println(modifiers); // 2
    }
}
```

### 2.getName 方法获取成员方法名

使用 `Method` 类的 `String getName()` 方法，获取成员方法的名称。

demo-project/base-code/Day35/src/com/kkcf/reflect/Demo09.java

```java
package com.kkcf.reflect;

import java.lang.reflect.Method;

public class Demo09 {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException {
        Class<?> clazz = Class.forName("com.kkcf.reflect.Student");

        Method eatMethod = clazz.getDeclaredMethod("eat", String.class);

        String name = eatMethod.getName();
        System.out.println(name); // eat
    }
}
```

### 3.getParameters 获取成员方法形参列表

使用 `Method` 类的 `public Parameter[] getParameters()` 方法，获取成员方法的形参列表。

demo-project/base-code/Day35/src/com/kkcf/reflect/Demo09.java

```java
package com.kkcf.reflect;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

public class Demo09 {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException {
        Class<?> clazz = Class.forName("com.kkcf.reflect.Student");

        Method eatMethod = clazz.getDeclaredMethod("eat", String.class);

        Parameter[] params = eatMethod.getParameters();
        for (Parameter param : params)
            System.out.println(param);

        // java.lang.String arg0
    }
}
```

### 4.getExceptionTypes 方法获取成员方法抛出的异常

使用 `Method` 类的 `Class<?>[] getExceptionTypes()` 方法，获取成员方法抛出的异常。

demo-project/base-code/Day35/src/com/kkcf/reflect/Demo09.java

```java
package com.kkcf.reflect;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

public class Demo09 {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException {
        Class<?> clazz = Class.forName("com.kkcf.reflect.Student");

        Method eatMethod = clazz.getDeclaredMethod("eat", String.class);

        Class<?>[] execpts = eatMethod.getExceptionTypes();
        for (Class<?> execpt : execpts)
            System.out.println(execpt);

        /*class java.io.IOException
        class java.lang.NullPointerException*/
    }
}
```

### 4.invoke 方法执行成员方法并获取返回值

使用 `Method` 类的 `Object invoke(Object obj, Object... args)` 方法，执行成员方法，获取返回值。

- 参数一：实例对象；
- 参数二：成员方法的参数列表

demo-project/base-code/Day35/src/com/kkcf/reflect/Demo09.java

```java
package com.kkcf.reflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

public class Demo09 {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class<?> clazz = Class.forName("com.kkcf.reflect.Student");

        Method eatMethod = clazz.getDeclaredMethod("eat", String.class);

        // 执行成员方法并获取返回值
        eatMethod.setAccessible(true); // 暴力反射
        Student stu = new Student("zzt", 18, "男");
        String result = (String) eatMethod.invoke(stu, "牛腩饭");
        System.out.println(result); // 奥利给
    }
}
```

- `eatMethod.setAccessible(true);` 设置暴力反射。

## 五、反射的主要作用

在开发中，反射主要有两个作用：

- 作用一：获取一个类里面的所有信息，再执行其它业务逻辑；
- 作用二：结合配置文件，动态的创建对象和调用方法。

### 1.作用一：获取类里的信息

案例理解：对于任意一个对象，把其中所有的字段名和值，保存到文件中去。

- 使用字符缓冲流输出流 BufferWriter 写出数据到本地文件 a,txt。

demo-project/base-code/Day35/src/com/kkcf/test/Test1.java

```java
package com.kkcf.test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;

public class Test1 {
    /**
     * 此方法用于：保存对象中所有字段的值到本地文件
     *
     * @param obj 实例对象
     */
    private static void saveObj(Object obj) throws IllegalAccessException, IOException {
        // 获取字节码文件对象
        Class<?> clazz = obj.getClass();

        BufferedWriter bw = new BufferedWriter(new FileWriter("Day35/src/com/kkcf/test/a.txt"));

        // 获取所有成员变量
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true); // 暴力反射

            // 获取成员变量名称和保存的值
            String name = field.getName();
            Object val = field.get(obj);

            // 写出数据
            bw.write(name + "=" + val);
            bw.newLine();
        }

        bw.close();
    }

    public static void main(String[] args) throws IllegalAccessException, IOException {
        Student stu = new Student("zzt", 18, "男", 1.88, "咖啡");

        saveObj(stu);
    }
}
```

### 2.作用二：结合配置文件动态创建对象调用方法

案例理解：读取配置文件 prop.properties 文件中的信息。根据 `classname` 字段创建对象，根据 `method` 字段调用方法

配置文件 prop.properties

```properties
classname=com.kkcf.test.Student
method=study
```

测试类

- 使用 Properties 类，加载本地 .properties 配置文件。

demo-project/base-code/Day35/src/com/kkcf/test/Test2.java

```java
package com.kkcf.test;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Properties;

public class Test2 {
    public static void main(String[] args) throws IOException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        // 加载配置文件
        Properties p = new Properties();
        FileInputStream fis = new FileInputStream("Day35/src/com/kkcf/test/prop.properties");
        p.load(fis);
        fis.close();

        // 获取全类名，方法名
        String classname = (String) p.get("classname");
        String method = (String) p.get("method");

        // 利用反射，创建类的实例对象
        Class<?> clazz = Class.forName(classname);
        Constructor<?> con = clazz.getDeclaredConstructor();
        Object obj = con.newInstance();

        // 利用反射，并调用方法
        Method meth = clazz.getDeclaredMethod(method);
        meth.setAccessible(true);
        meth.invoke(obj);
    }
}
```
