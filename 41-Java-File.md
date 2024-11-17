# Java File

在 Java 中用 `File` 类，表示计算机文件保存的位置（路径）。

- 绝对路径：
  - Windows 系统：带盘符；
  - Linux 系统：带 `/` 根目录。

- 相对路径：不带盘符，默认在当前项目（project）目录下查找。

`File` 对象，可以表示一个**文件**，或**文件夹**；它可以是**存在**，或**不存在**的；

把一个字符串表示的路径，转成 File 对象，就是为了使用其中的方法。

## 一、File 构造方法

`File` 类常见的构造方法有：

| 方法名                                     | 说明                                             |
| ------------------------------------------ | ------------------------------------------------ |
| `public File(String pathname)`             | 根据文件路径字符串，创建文件对象                 |
| `public File(String parentm String child)` | 根据父路径字符串，和子路径字符串，创建文件对象   |
| `public File(File parent, String child)`   | 根据父路径文件对象，和子路径字符串，创建文件对象 |

### 1.1.File(String pathname)

`public File(String pathname)` 构造方法使用：

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

### 1.2.File(String parentm String child)

`public File(String parentm String child)` 构造方法使用：

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
- 一般使用这种方式，进行路径拼接。因为不同操作系统，路径分隔符是不同的，比如：
  - Windows：`\`
  - Linux：`/`

### 1.3.File(File parent, String child)

`public File(File parent, String child)` 构造方法使用：

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

## 二、File 成员方法

### 2.1.判断、获取文件信息

`File` 类常见的成员方法，用于判断、获取文件信息：、

#### 2.1.1.boolean isDirectory() 方法

`public boolean isDirectory()`，判断此路径名表示的 file 是否为文件夹

#### 2.1.2.boolean isFile() 方法

`public boolean isFile()`，判断此路径名表示的 file 是否为文件

#### 2.1.3.boolean exists() 方法

`public boolean exists()`，判断此路径名表示的 file 是否存在

#### 2.1.4.long length() 方法

`public long length()`，返回 file 对象（文件）的大小（单位：字节），无法获取文件夹大小。

#### 2.1.5.String getAbsolutePath() 方法

`public String getAbsolutePath()`，返回 file 对象的绝对路径

#### 2.1.6.String getPath() 方法

`public String getPath()`，返回定义 file 对象时，使用的路径

#### 2.1.7.String getName() 方法

`public String getName()`，返回 file 对象的名称（带后缀）

#### 2.1.8.File getParentFile() 方法

`public File getParentFile()`，返回 file 对象的父级路径的绝对路径

#### 2.1.9.long lastModified() 方法

`public long lastModified()`，返回 file 对象最后修改时间（时间毫秒值）

demo-project/base-code/Day27/src/com/kkcf/file/Demo02.java

```java
package com.kkcf.file;

import java.io.File;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class Demo02 {
    public static void main(String[] args) {
        File f1 = new File("D:\\Workshop\\tutorial\\JAVA\\demo-project\\base-code\\Day27\\src\\com\\kkcf\\file\\a.txt");

        System.out.println(f1.isDirectory()); // false
        System.out.println(f1.isFile()); // true
        System.out.println(f1.exists()); // true

        System.out.println(f1.length()); // 6

        System.out.println(f1.getAbsoluteFile()); // D:\Workshop\tutorial\JAVA\demo-project\base-code\Day27\src\com\kkcf\file\a.txt
        System.out.println(f1.getPath()); // D:\Workshop\tutorial\JAVA\demo-project\base-code\Day27\src\com\kkcf\file\a.txt
        System.out.println(f1.getName()); // a.txt

        long lastModifyTime = f1.lastModified();
        System.out.println(lastModifyTime); // 1722908867598

        ZonedDateTime zdt = Instant.ofEpochMilli(lastModifyTime).atZone(ZoneId.systemDefault());
        System.out.println(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss EE a").format(zdt)); // 2024-08-06 09:47:47 周二 上午
    }
}
```

### 2.2.创建、删除文件

`File` 类常见的成员方法，用于创建、删除文件、文件夹：

| 方法名                           | 说明                   |
| -------------------------------- | ---------------------- |
| `public boolean createNewFile()` | 创建一个新的空文件     |
| `public boolean mkdir()`         | 创建单极文件夹         |
| `public boolean mkdirs()`        | 创建多级文件夹         |
| `public boolean delete()`        | 删除文件，删除空文件夹 |

#### 2.2.1.boolean createNewFile 方法

`public boolean createNewFile()` 方法，用于创建一个新的文件：

- 细节 1：如果文件路径表示的文件已存在，则返回 `false`；否则返回 `true`；
- 细节 2：如果父级路径不存在，会出现异常 `IOException`。
- 细节 3：该方法创建的是文件，如果没有后缀名，会创建一个没有后缀名的文件。

demo-project/base-code/Day27/src/com/kkcf/file/Demo03.java

```java
package com.kkcf.file;

import java.io.File;
import java.io.IOException;

public class Demo03 {
    public static void main(String[] args) throws IOException {
        File f1 = new File("D:\\Workshop\\tutorial\\JAVA\\demo-project\\base-code\\Day27\\src\\com\\kkcf\\file\\b.txt");
        boolean flag = f1.createNewFile();
        System.out.println(flag); // true
    }
}
```

#### 2.2.2.boolean mkdir 方法

`public boolean mkdir()` 方法，用于创建单级文件夹。

- 细节 1：在 Windows 操作系统中，路径一定是唯一的，如果路径重复会返回 `false`，否则返回 `true`。
- 细节 2：只能创建单级文件夹，不能创建多级文件夹。

demo-project/base-code/Day27/src/com/kkcf/file/Demo03.java

```java
package com.kkcf.file;

import java.io.File;
import java.io.IOException;

public class Demo03 {
    public static void main(String[] args) throws IOException {
        File f1 = new File("D:\\Workshop\\tutorial\\JAVA\\demo-project\\base-code\\Day27\\src\\com\\kkcf\\file\\aaa");
        boolean flag = f1.mkdir();
        System.out.println(flag); // true
    }
}
```

#### 2.2.3.boolean mkdirs 方法

`public boolean mkdirs()` 方法，用于创建多级文件夹。

- 细节 1：既可以创建单级文件夹，又可以创建多级文件夹。
- 斜街 2：如果文件路径已存在，则会创建失败；否则就会创建成功。
- 该方法的底层，使用的是 `mkdir` 方法。

demo-project/base-code/Day27/src/com/kkcf/file/Demo03.java

```java
package com.kkcf.file;

import java.io.File;
import java.io.IOException;

public class Demo03 {
    public static void main(String[] args) throws IOException {
        File f1 = new File("D:\\Workshop\\tutorial\\JAVA\\demo-project\\base-code\\Day27\\src\\com\\kkcf\\file\\aaa\\bbb\\ccc");
        boolean flag = f1.mkdirs();
        System.out.println(flag); // true
    }
}
```

#### 2.2.4.boolean delete 方法

`public boolean delete()` 方法

- 细节 1：删除的是文件，则不放入回收站。
- 细节 2：删除的是空文件夹，则不放入回收站；
- 细节 3：删除的是有内容的文件夹，则则删除失败。

demo-project/base-code/Day27/src/com/kkcf/file/Demo03.java

```java
package com.kkcf.file;

import java.io.File;
import java.io.IOException;

public class Demo03 {
    public static void main(String[] args) throws IOException {
        File f1 = new File("D:\\Workshop\\tutorial\\JAVA\\demo-project\\base-code\\Day27\\src\\com\\kkcf\\file\\aaa\\bbb\\ccc");
        boolean flag = f1.delete();
        System.out.println(flag); // true
    }
}
```

### 2.3.获取并遍历

`File` 类，用于获取并遍历文件，常用的成员方法：

| 方法名                      | 说明                             |
| --------------------------- | -------------------------------- |
| `public File[] listFiles()` | 获取当前该 File 对象下所有内容。 |

#### 2.3.1.File[] listFiles() 方法

`public File[] listFiles()` 方法，用于获取 File 对象下所有内容的 File 对象。

demo-project/base-code/Day27/src/com/kkcf/file/Demo03.java

```java
package com.kkcf.file;

import java.io.File;
import java.io.IOException;

public class Demo03 {
    public static void main(String[] args) throws IOException {
        File f1 = new File("D:\\Workshop\\tutorial\\JAVA\\demo-project\\base-code\\Day27\\src\\com\\kkcf\\file");

        File[] files = f1.listFiles();

        for (File file : files)
            System.out.println(file.getName());
    }
}
```

- 当调用者 File 对象表示的路径：不存在，则返回 `null`；
- 当调用者 File 对象表示的路径：是文件，则返回 `null`；
- 当调用者 File 对象表示的路径：需要权限才能访问，则返回 `null`。
- 当调用者 File 对象表示的路径：是空文件夹，则返回一个长度为 `0` 的数组；
- 当调用者 File 对象表示的路径：是有内容的文件夹（包含隐藏文件、文件夹），则返回里面所有内容组成的 File 对象数组。

其它相似的方法，了解即可：

| 方法名                                           | 说明                                                   |
| ------------------------------------------------ | ------------------------------------------------------ |
| `public static File[] listRoots()`               | 列出可用的文件系统根                                   |
| `public String[] list()`                         | 获取 File 对象下所有内容的字符串路径                   |
| `public String[] list(FilenameFilter filter)`    | 利用文件名过滤器，获取 File 对象下所有内容的字符串路径 |
| `public File[] listFiles(FileFilter filter)`     | 利用文件过滤器，获取 File 对象下所有内容               |
| `public File[] listFiles(FilenameFilter filter)` | 利用文件名过滤器，获取 File 对象下所有内容             |

#### 2.3.2.File[] listRoots() 静态方法

`public static File[] listRoots()` 静态方法，用于获取系统中所有的盘符：

demo-project/base-code/Day27/src/com/kkcf/file/Demo04.java

```java
package com.kkcf.file;

import java.io.File;
import java.util.Arrays;

public class Demo04 {
    public static void main(String[] args) {
        File[] files = File.listRoots();

        System.out.println(Arrays.toString(files)); // [C:\, D:\, E:\, F:\]
    }
}
```

#### 2.3.3.String[] list() 方法

`public String[] list()` 方法，用于获取 File 对象下的所有内容的名字字符串数组：

demo-project/base-code/Day27/src/com/kkcf/file/Demo04.java

```java
package com.kkcf.file;

import java.io.File;
import java.util.Arrays;

public class Demo04 {
    public static void main(String[] args) {
        File f1 = new File("D:\\Workshop\\tutorial\\JAVA\\demo-project\\base-code\\Day27\\src\\com\\kkcf\\file");
        String[] list = f1.list();

        System.out.println(Arrays.toString(list)); // [a.txt, aaa, b.txt, Demo01.java, Demo02.java, Demo03.java, Demo04.java]
    }
}
```

#### 2.3.4.String[] list(FilenameFilter filter) 方法

`public String[] list(FilenameFilter filter)` 方法，使用文件名过略器，获取 File 对象下以 .txt 结尾的文件名字符串数组。

需求：获取目录下所有 ”.txt“ 结尾的文件。

```java
package com.kkcf.file;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;

public class Demo04 {
    public static void main(String[] args) {
        File f1 = new File("D:\\Workshop\\tutorial\\JAVA\\demo-project\\base-code\\Day27\\src\\com\\kkcf\\file");

        String[] list = f1.list(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return new File(dir, name).isFile() && name.endsWith(".txt");
            }
        });

        System.out.println(Arrays.toString(list)); // [a.txt, b.txt]
    }
}
```

`FilenameFilter` 是函数式接口，其中 `accept` 方法分析：

- 参数一：`File dir`，依次表示 File 对象下，所有内容的父级路径对象。
- 参数二：`String name`，依次表示 File 对象下，所有内容的名称字符串。
- 返回值： `true`，表示当前路径保留；`false`，表示当前路径不保留；

#### 2.3.5.File[] listFiles(FileFilter filter) 方法

`public File[] listFiles(FileFilter filter)` 方法，使用文件过滤器，获取 File 对象下，所有的内容的 File 对象数组：

需求：获取目录下所有 ”.txt“ 结尾的文件。

demo-project/base-code/Day27/src/com/kkcf/file/Demo04.java

```java
package com.kkcf.file;

import java.io.File;
import java.io.FileFilter;
import java.util.Arrays;

public class Demo04 {
    public static void main(String[] args) {
        File f1 = new File("D:\\Workshop\\tutorial\\JAVA\\demo-project\\base-code\\Day27\\src\\com\\kkcf\\file");

        File[] files = f1.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.isFile() && pathname.getName().endsWith(".txt");
            }
        });

        System.out.println(Arrays.toString(files)); // [D:\Workshop\tutorial\JAVA\demo-project\base-code\Day27\src\com\kkcf\file\a.txt, D:\Workshop\tutorial\JAVA\demo-project\base-code\Day27\src\com\kkcf\file\b.txt]
    }
}
```

#### 2.3.6.File[] listFiles(FilenameFilter filter) 方法

`public File[] listFiles(FilenameFilter filter)` 方法，使用文件名过滤器，获取 File 对象下所有的内容的 File 对象数组：

需求：获取目录下所有 ”.txt“ 结尾的文件。

demo-project/base-code/Day27/src/com/kkcf/file/Demo04.java

```java
package com.kkcf.file;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;

public class Demo04 {
    public static void main(String[] args) {
        File f1 = new File("D:\\Workshop\\tutorial\\JAVA\\demo-project\\base-code\\Day27\\src\\com\\kkcf\\file");

        File[] files = f1.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                File file = new File(dir, name);
                return file.isFile() && file.getName().endsWith(".txt");
            }
        });

        System.out.println(Arrays.toString(files)); // [D:\Workshop\tutorial\JAVA\demo-project\base-code\Day27\src\com\kkcf\file\a.txt, D:\Workshop\tutorial\JAVA\demo-project\base-code\Day27\src\com\kkcf\file\b.txt]
    }
}
```

## 三、File 综合练习

### 1.练习一：在文件夹下创建文件

需求：在当前文件夹下的 aaa 文件夹中，创建一个 a.txt 文件。

demo-project/base-code/Day27/src/com/kkcf/test/Test2.java

```java
package com.kkcf.test;

import java.io.File;
import java.io.IOException;

public class Test2 {
    public static void main(String[] args) throws IOException {
        File f1 = new File("Day27\\src\\com\\kkcf\\test\\aaa");

        // 创建 aaa 文件夹
        boolean flag = f1.mkdirs();

        // 创建 a.txt 文件
        if (flag) {
            File f2 = new File(f1, "a.txt");
            boolean flag2 = f2.createNewFile();
            System.out.println(flag2 ? "创建成功" : "创建失败");
        }
    }
}
```

### 2.练习二：找文件夹下的文件

需求：定义一个方法，找某一个文件下，是否有以 avi 结尾的文件（不考虑子文件夹）

demo-project/base-code/Day27/src/com/kkcf/test/Test3.java

```java
package com.kkcf.test;

import java.io.File;

public class Test3 {
    /**
     * 此方法用于：判断目录下是否有avi文件
     * @param file 目录
     * @return 是否有 avi 文件
     */
    public static boolean hasAvi(File file) {
        File[] files = file.listFiles();

        for (File f : files)
            if (f.getName().endsWith(".avi")) return true;

        return false;
    }

    public static void main(String[] args) {
        File file = new File("Day27/src/com/kkcf/test/aaa");
        boolean flag = hasAvi(file);
        System.out.println(flag ? "有 avi 文件" : "没有 avi 文件");
    }
}
```

### 3.练习三：找电脑中的文件

需求：找到电脑中所有以 .avi 结尾的文件。

- 使用递归的方式。

demo-project/base-code/Day27/src/com/kkcf/test/Test4.java

```java
package com.kkcf.test;

import java.io.File;

public class Test4 {
    public static void findAvi(File src) {
        // 1.进入文件夹；
        File[] files = src.listFiles();

        if (files == null) return;

        // 2.遍历文件夹下的内容（File 数组）；
        for (File file : files) {
            if (file.isDirectory()) {
                // 3.判断文件夹；
                findAvi(file);
            } else {
                // 4.判断文件。
                if (file.getName().endsWith(".avi")) {
                    System.out.println(file.getAbsolutePath());
                }
            }
        }
    }

    public static void main(String[] args) {
        File[] pans = File.listRoots();

        for (File pan : pans)
            findAvi(pan);
    }
}
```

文件夹操作的固定步骤：

1. 判断文件；
2. 判断文件夹。
3. 进入文件夹；
4. 遍历文件夹下的内容（File 数组）；

### 4.练习四：删除一个多级文件夹

需求：删除一个多级文件夹：要分为两步：

1. 先删除文件夹里面所有的内容；
2. 再删除文件夹本身。

demo-project/base-code/Day27/src/com/kkcf/test/Test5.java

```java
package com.kkcf.test;

import java.io.File;

public class Test5 {
    public static void deleteFile(File src) {
        if (src.isFile()) {
            boolean flag = src.delete();
            System.out.println("删除文件" + (flag ? "成功" : "失败") + src.getAbsoluteFile());
        } else {
            File[] files = src.listFiles();

            if (files == null) return;

            for (File file : files)
                deleteFile(file);

            boolean flag = src.delete();
            System.out.println("删除文件夹" + (flag ? "成功" : "失败") + src.getAbsoluteFile());
        }
    }

    public static void main(String[] args) {
        File src = new File("Day27/src/com/kkcf/test/aaa");
        deleteFile(src);
    }
}
```

### 5.练习五：统计文件夹总大小

需求：统计一个文件夹的总大小：

demo-project/base-code/Day27/src/com/kkcf/test/Test6.java

```java
package com.kkcf.test;

import java.io.File;

public class Test6 {
    public static long getFileSize(File src) {
        //File[] files = src.listFiles();
        if(src.isFile()) {
            return src.length();
        } else {
            File[] files = src.listFiles();

            if (files == null) return 0;

            long size = 0;
            for(File file : files)
                size += getFileSize(file);

            return size;
        }
    }
    public static void main(String[] args) {
        File src = new File("Day27/src/com/kkcf/test/aaa");

        long fileSize = getFileSize(src);
        System.out.println("文件大小为：" + fileSize);
    }
}
```

### 6.练习六：统计文件夹中各种文件的个数

需求：统计一个文件夹中，每种文件的个数，并打印（考虑子文件夹）

打印格式如下：

- txt:3个
- doc4个
- jpg:5个

统计的思路：1.计数器统计；2.Map 统计；这里用 Map。

demo-project/base-code/Day27/src/com/kkcf/test/Test7.java

```java
package com.kkcf.test;

import java.io.File;
import java.util.HashMap;

public class Test7 {
    public static HashMap<String, Integer> getCountMap(File src, HashMap<String, Integer> map) {

        if (src.isDirectory()) {
            File[] files = src.listFiles();

            if (files == null) return null;

            for (File file : files)
                getCountMap(file, map);
        } else {
            String name = src.getName();
            String[] split = name.split("\\.");
            String suffix = split[split.length - 1];

            map.put(suffix, map.containsKey(suffix) ? map.get(suffix) + 1 : 1);
        }

        return map;
    }

    public static void main(String[] args) {
        File src = new File("Day27/src/com/kkcf/test/aaa");
        HashMap<String, Integer> map = new HashMap<>();

        HashMap<String, Integer> result = getCountMap(src, map);
        if (result == null) return;

        result.forEach((key, val) -> System.out.println(key + ":" + val + "个"));
    }
}
```
