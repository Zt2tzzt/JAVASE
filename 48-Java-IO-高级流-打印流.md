# Java IO 之打印流

打印流，只有**输出流**，没有输入流。它有两个实现类：

- 字节打印输出流：`PrintStream`
- 字符打印输出流：`PrintWriter`

打印流，有如下特点：

- 特点 1：打印流只操作文件目的地，不操作数据源；
- 特点 2：特有的写出方法，可以实现数据**原样输出**，比如：
  - 打印 97，文件中写入 97；
  - 打印 true，文件中写入 true
- 特点 3：特有的写出方法，可以实现自动刷新、自动换行。
  - 打印一次数据，进行了**写出、换行、刷新**操作。

## 一、PrintStream 字节打印输出流

### 1.PrintStream 的构造方法

`PrintStream` 字节打印输出流，常用的构造方法如下：

| 方法名                                                       | 说明                                       |
| ------------------------------------------------------------ | ------------------------------------------ |
| `public PrintStream(OutputStream os)`                        | 关联字节输出流                             |
| `public PrintStream(File file)`                              | 关联 File 对象                             |
| `public PrintStream(String filename)`                        | 关联文件路径字符串                         |
| `public PrintStream(String filename, Charset charset)`       | 关联文件路径字符串，并指定字符集编码       |
| `public PrintStream(OutputStream os, boolean autoFlush)`     | 关联字节输出流，并指定自动刷新             |
| `public PrintStream(OutputStream os, boolean autoFlush, String encoding)` | 关联字节输出流，并指定字符集编码和自动刷新 |

- 无论关联的是 File 对象，还是文件路径字符串，底层都会创建一个**字节输出流**。
- 字节打印输出流底层没有缓冲区，开不开自动刷新，都会直接写出到文件中。

### 2.PrintStream 的成员方法

`PrintStream` 字节打印输出流，常用的成员方法如下：

| 方法名                                             | 说明                                         |
| -------------------------------------------------- | -------------------------------------------- |
| `public void write(int b)`                         | 常规方法，将指定的字节写出。                 |
| `public void println(Xxx xxx)`                     | **特有方法**，打印任意数据，自动刷新、换行   |
| `public void print(Xxx xxx)`                       | **特有方法**，打印任意数据，自动刷新         |
| `public void printf(String format, Object...args)` | **特有方法**，打印带有占位符的语句，自动刷新 |

- 字节打印输出流，底层没有缓冲区，所以指定或不指定自动刷新，数据都会直接写出到文件中。

案例理解：使用 `PrintStream` 字节打印输出流，往文件中写出数据。

demo-project/base-code/Day29/src/com/kkcf/print/Demo01.java

```java
package com.kkcf.print;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

public class Demo01 {
    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
        PrintStream ps = new PrintStream(new FileOutputStream("Day29/src/com/kkcf/print/a.txt"), true, "UTF-8");

        ps.println(97); // 写入 97
        ps.print(true); // 写入 true
        ps.println();
        ps.printf("%s爱上了%s", "阿珍", "阿强");

        ps.close();
    }
}
```

> 补充：`printf` 方法中，可用的占位符有：
>
> - `%s`，表示字符串的占位符；
> - `%n`，表示换行；
> - `%c`，表示将字符转为大写。
> - `%b`，表示布尔类型数据的占位符；
> - `%d`，表示小数的占位符。
> - ……
>
> 更多占位符，请看：
>
> demo-project/base-code/Day29/src/com/kkcf/print/PrintStreamDemo2.java

## 二、PrintWriter 字符打印输出流

`PrintWriter` 字符打印输出流，与 `PrintStream` 字节打印输出流类似，它们不同的地方在于：

- `PrintWriter` 字符打印输出流，底层有**缓冲区**，需要明确指定自动刷新。因此它的效率更高。

### 1.PrintWriter 构造方法

`PrintWriter` 字符打印输出流，常用的构造方法有：

| 方法名                                                       | 说明                           |
| ------------------------------------------------------------ | ------------------------------ |
| `public PrintWriter(Writer w)`                               | 关联字符输出流                 |
| `public PrintWriter(File file)`                              | 关联文件                       |
| `public PrintWriter(String filename)`                        | 关联文件路径                   |
| `public PrintWriter(String filename, Charset charset)`       | 关联文件路径，并指定字符集编码 |
| `public PrintWriter(Writer w, boolean autoFlush)`            | 关联字符输出流，并指定自动刷新 |
| `public PrintWriter(Writer w, boolean autoFlush, String encoding)` | 关联字符输出流，               |

### 2.PrintWriter 成员方法

`PrintWriter` 字符打印输出流，常用的成员方法有：

| 方法名                                             | 说明                                         |
| -------------------------------------------------- | -------------------------------------------- |
| `public void write(int b)`                         | 常规方法，将指定的字节写出。                 |
| `public void println(Xxx xxx)`                     | **特有方法**，打印任意数据，自动刷新、换行   |
| `public void print(Xxx xxx)`                       | **特有方法**，打印任意数据，自动刷新         |
| `public void printf(String format, Object...args)` | **特有方法**，打印带有占位符的语句，自动刷新 |

案例理解：使用 `PrintWriter` 字符打印输出流，往文件中写出数据。

demo-project/base-code/Day29/src/com/kkcf/print/Demo02.java

```java
package com.kkcf.print;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Demo02 {
    public static void main(String[] args) throws IOException {
        PrintWriter pw = new PrintWriter(new FileWriter("Day29/src/com/kkcf/print/a.txt"), true);

        pw.println("今天你终于叫我名字了，虽然叫错了，但是没关系，我马上改");
        pw.print("侬好侬好");
        pw.printf("%s爱上了%s", "阿珍", "阿强");

        pw.close();
    }
}
```

## 三、打印流与输出语句的关系

`System` 类是 Java 中的一个类：

java/lang/System.java

```java
public final class System {}
```

`System` 类中，有一个静态常量 `out`

```java
public final class System {
    // ……

    public static final PrintStream out = null;

    // ……
}
```

- 可以看到，`out` 常量的类型，就是 `PrintStream` 打印流。
- 所以，使用 `System.out`，获取的就是一个字节打印输出流对象。虚拟机在启动时，自动创建了这个对象。
  - 这个打印流对象，默认指向控制台。
  - 这个打印流对象，也是系统中的**标准输出流**。它不能关闭，因为在系统中它是唯一的。
