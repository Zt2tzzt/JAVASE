# Java File

计算机中，文件保存的位置（路径），在 Java 中用 `File` 类表示。

- 绝对路径：Windows 系统：带盘符；Linux 系统：从 `/` 根目录下查找。
- 相对路径：不带盘符，默认在当前目录下查找。

File 对象，就表示一个路径，可以是文件，或文件夹的路径；可以是存在，或不存在的路径；

把字符串路径，变成 File 对象，就是为了使用其中的方法。

## 一、File 构造方法

File 类常见的构造方法有：

| 方法名                                     | 说明                                           |
| ------------------------------------------ | ---------------------------------------------- |
| `public File(String pathname)`             | 根据文件路径，创建文件对象                     |
| `public File(String parentm String child)` | 根据父路径字符串，和子路径字符串创建文件对象   |
| `public File(File parent, String child)`   | 根据父路径文件对象，和子路径字符串创建文件对象 |

构造方法一：

demo-project/base-code/Day27/src/com/kkcf/file/Demo01.java

```java
package com.kkcf.file;

import java.io.File;

public class Demo01 {
    public static void main(String[] args) {
        File f1 = new File("D:\\workshop\\tutorial\\JAVA\\demo-project\\base-code\\Day27\\src\\com\\kkcf\\file\\a.txt");
        System.out.println(f1); // D:\workshop\tutorial\JAVA\demo-project\base-code\Day27\src\com\kkcf\file\a.txt
    }
}
```

构造方法二：

demo-project/base-code/Day27/src/com/kkcf/file/Demo01.java

```java
package com.kkcf.file;

import java.io.File;

public class Demo01 {
    public static void main(String[] args) {
        File f2 = new File("D:\\workshop\\tutorial\\JAVA\\demo-project\\base-code\\Day27\\src\\com\\kkcf\\file", "a.txt");
        System.out.println(f2); // D:\workshop\tutorial\JAVA\demo-project\base-code\Day27\src\com\kkcf\file\a.txt
    }
}
```

- 父级路径：`D:\workshop\tutorial\JAVA\demo-project\base-code\Day27\src\com\kkcf\file`
- 子级路径：`a.txt`
- 一般使用这种方式进行路径拼接。因为不同操作系统，路径分隔符是不同的：
  - Windows：`\`
  - Linux：`/`

构造方法三：

demo-project/base-code/Day27/src/com/kkcf/file/Demo01.java

```java
package com.kkcf.file;

import java.io.File;

public class Demo01 {
    public static void main(String[] args) {
        File f3 = new File("D:\\workshop\\tutorial\\JAVA\\demo-project\\base-code\\Day27\\src\\com\\kkcf\\file");

        File f4 = new File(f3, "a.txt"); // D:\workshop\tutorial\JAVA\demo-project\base-code\Day27\src\com\kkcf\file\a.txt

        System.out.println(f4);
    }
}
```
