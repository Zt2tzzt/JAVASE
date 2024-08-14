# Java IO 之工具包

## 一、Commons-io 工具包

Commons-io 是 apache 开源基金组织，提供的有关 Java IO 操作的开源工具包。用于提高 Java IO 操作效率。

> Apache 开源基金组织，维护了很多 Java 相关的开源项目：
>
> - Tomcat，Web 应用服务器；
> - Maven，项目管理工具；
> - Shiro，安全框架；
> - Dubbo，分布式框架
> - ActiveMQ，消息中间件；
> - RocketMQ，消息中间件；
> - Kafka，大数据消息中间件；
> - zookeeper，分布式框架配置中心；
> - Apache Pig，大数据分析平台
> - Hadoop，大数据开发框架
> - spark，大数据开发框架
> - Flink，大数据开发框架
> - Lucene，分布式搜索（已被 Elasticsearch 替代）

Commons 工具包中，有很多工具，比如：

- `StringUtils`，字符串工具类；
- `NumberUtils`，数字工具类；
- `ArrayUtils`，数组工具类；
- `RandomUtils`，随机数工具类；
- `DateUtils`，日期工具类；
- `StopWatch`，秒表工具类；
- `ClassUtils`，反射工具类；
- `SystemUtils`，系统工具类；
- `MapUtils`，集合工具类；
- `BeanUtils`，bean 工具类；
- ……

其中与 IO 相关的，就是 Commons-io 工具包；

> 第三方提供的 Java 包，一般以压缩包的形式提供，即 jar 包。

在项目中，导入 Commons-io 相关 jar 包；

### 1.FileUtils 工具类

其中 `FileUtils` 工具类中，常用的方法有：

| FileUtils（文件、文件夹相关）                                | 说明                         |
| ------------------------------------------------------------ | ---------------------------- |
| `static void copyFile(File srcFile, File destFile)`          | 复制文件                     |
| `static void copyDirectory(File srcDir, File destDir)`       | 复制文件夹                   |
| `static void copyDirectoryToDirectory(File srcDir, File destDir)` | 复制文件夹，到另一个文件夹中 |
| `static void deleteDirectory(File dir)`                      | 删除文件夹                   |
| `static void cleanDirectory(File dir)`                       | 清空文件夹                   |
| `static void readFileToString(File fil, Charset encoding)`   | 读取文件中的数据变为字符串   |
| `static void write(File file, CharSequence data, String encoding)` | 写出数据                     |

`copyFile` 静态方法的使用：

demo-project/base-code/Day29/src/com/kkcf/commonsio/Demo01.java

```java
package com.kkcf.commonsio;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class Demo01 {
    public static void main(String[] args) throws IOException {
        File src = new File("Day29/src/com/kkcf/commonsio/a.txt");
        File dest = new File("Day29/src/com/kkcf/commonsio/b.txt");

        FileUtils.copyFile(src, dest);
    }
}
```

### 2.IOUtils 工具类

其中 IOUtils 工具类中，常用的方法有：

| IOUtils（IO 流相关）                             | 说明       |
| ------------------------------------------------ | ---------- |
| `static int copy(InputStream i, OutputStream o)` | 复制文件   |
| `static int copuLarge(Reader r, writer w)`       | 复制大文件 |
| `static String readLine(Reader r)`               | 读取数据   |
| `static void write(String data, OutputStream o)` | 写出数据   |

Commons-io 更多用法，参考[commons-io整理的文档](doc\commons-io整理的文档.md)

## 二、Hutool 工具包

[Hutool 工具包](https://github.com/dromara/hutool)，由国人开发者开发；Hutool = Hu + tool，是原公司项目底层代码剥离后的开源库，“Hu”是公司名称的表示，tool 表示工具。Hutool 谐音“糊涂”，一方面简洁易懂，一方面寓意“难得糊涂”。

Hutool 工具包中，有很多工具类：

- `DateUtil`，日期时间工具类；
- `TimeInterval`，计时器工具类
- `StrUtil`，字符串工具类；
- `HexUtil`，十六进制工具类；
- `HashUtil`，哈希工具类；
- `ObjectUtil`，对象工具类
- `ReflectUtil`，反射工具类；
- `TypeUtil`，泛型工具类；
- `PageUtil`，分页工具类；
- `NumberUtil`，数字工具类
- ……

其中与 [IO 相关](https://doc.hutool.cn/pages/io/)的，有 7 个工具类；它们在 hutool.core.io 包中。

| 类名              | 说明                          |
| ----------------- | ----------------------------- |
| IoUtil            | 操作了工具类                  |
| FileUtil          | 文件读写和操作工具类          |
| FileTypeUtil      | 为念类型判断工具类            |
| WatchMonitor      | 目录、文件监听工具类          |
| ClassPathResource | 针对 ClassPath 资源的访问封装 |
| FileReader        | 封装为念读取                  |
| FileWriter        | 封装文件写入                  |

### 1.FileUtil 工具类

其中 FileUtil 工具类中，常用的方法有：

| f方法名                                                      | 说明                     |
| ------------------------------------------------------------ | ------------------------ |
| `static File file(String... names)`                          | 通过多层目录创建文件     |
| `static File touch(File file)`                               | 创建文件及其父目录       |
| `static <T> File writeLines(Collection<T> list, File file, Charset charset)` | 将列表写入文件，覆盖模式 |
| `static <T> File appendLines(Collection<T> list, File file, String charset)` | 将列表写入文件，追加模式 |
| `static List<String> readLines(File file, Charset charset)`  | 从文件中读取每一行数据   |

`file` 静态方法的使用：

- 通过多层目录创建文件

demo-project/base-code/Day29/src/com/kkcf/hutool/Demo01.java

```java
package com.kkcf.hutool;

import cn.hutool.core.io.FileUtil;

import java.io.File;

public class Demo01 {
    public static void main(String[] args) {
        File file = FileUtil.file("D:\\Workshop\\tutorial\\JAVASE\\demo-project\\base-code\\Day29\\src\\com\\kkcf\\hutool", "haha", "aaa"); // 必须是绝对路径

        boolean flag = file.mkdirs();

        System.out.println(file);
    }
}
```

`touch` 静态方法的使用：

- 创建文件及其父目录

demo-project/base-code/Day29/src/com/kkcf/hutool/Demo01.java

```java
package com.kkcf.hutool;

import cn.hutool.core.io.FileUtil;

import java.io.File;

public class Demo01 {
    public static void main(String[] args) {
        File file = FileUtil.file("D:\\Workshop\\tutorial\\JAVASE\\demo-project\\base-code\\Day29\\src\\com\\kkcf\\hutool", "haha", "aaa", "a.txt"); // 必须是绝对路径

        FileUtil.touch(file);
    }
}
```

`writeLines` 静态方法的使用：

- 将列表写入文件，覆盖模式

demo-project/base-code/Day29/src/com/kkcf/hutool/Demo01.java

```java
package com.kkcf.hutool;

import cn.hutool.core.io.FileUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Demo01 {
    public static void main(String[] args) {
        File file = FileUtil.file("D:\\Workshop\\tutorial\\JAVASE\\demo-project\\base-code\\Day29\\src\\com\\kkcf\\hutool", "haha", "aaa", "a.txt"); // 必须是绝对路径

        ArrayList<String> list = new ArrayList<>(List.of("abc", "cba", "nba"));

        FileUtil.writeLines(list, file, "UTF-8");
    }
}
```

`appendLines` 静态方法的使用：

- 将列表写入文件，追加模式

demo-project/base-code/Day29/src/com/kkcf/hutool/Demo01.java

```java
package com.kkcf.hutool;

import cn.hutool.core.io.FileUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Demo01 {
    public static void main(String[] args) {
        File file = FileUtil.file("D:\\Workshop\\tutorial\\JAVASE\\demo-project\\base-code\\Day29\\src\\com\\kkcf\\hutool", "haha", "aaa", "a.txt"); // 必须是绝对路径

        ArrayList<String> list1 = new ArrayList<>(List.of("wee", "yui", "mio"));

        FileUtil.appendLines(list1, file, "UTF-8");
    }
}
```

`readLines` 静态方法的使用

- 从文件中读取每一行数据

demo-project/base-code/Day29/src/com/kkcf/hutool/Demo01.java

```java
package com.kkcf.hutool;

import cn.hutool.core.io.FileUtil;

import java.io.File;
import java.util.List;

public class Demo01 {
    public static void main(String[] args) {
        File file = FileUtil.file("D:\\Workshop\\tutorial\\JAVASE\\demo-project\\base-code\\Day29\\src\\com\\kkcf\\hutool", "haha", "aaa", "a.txt"); // 必须是绝对路径

        List<String> list = FileUtil.readLines(file, "UTF-8");

        System.out.println(list); // [abc, cba, nba, wee, yui, mio]
    }
}
```
