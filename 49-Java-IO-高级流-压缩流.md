# Java IO 之压缩流、解压缩流

压缩流、解压缩流的应用场景：

- 场景一：当传输的数据比较大，就可以使用压缩流先压缩，再传输；
- 场景二：接收到一个压缩包，要先进行解压，才能访问其中的文件。

压缩流、解压缩流都属于**字节流**，它的实现类主要有两个：

- 字节解压缩输入流 `ZipInputStream`；
- 字节压缩输出流`ZipOutputStream`。

## 一、ZipInputStream 字节解压缩输入流的使用

字节解压缩输入流：

- 解压的压缩包，必须是 zip 格式的。
- 解压的压缩包，其中的每一个文件、文件夹，在解压缩流中，都是一个 `ZipEntry` 对象。
- 解压的本质，就是把每一个 `ZipEntry` 对象，按照层级，拷贝到本地另一个文件夹中。

### 1.解压 zip 压缩包

案例理解：将 aaa.zip 压缩包，解压到同目录下同名的文件夹中：

demo-project/base-code/Day29/src/com/kkcf/compression/Demo01.java

```java
package com.kkcf.compression;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.StringJoiner;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class Demo01 {
    /**
     * 此方法用于：解压压缩包
     * @param src 解压的压缩包
     * @param dest 解压到的目录
     * @throws IOException 抛出异常
     */
    public static void unzip(File src, File dest) throws IOException {
        // 创建解压目录
        String[] split = src.getName().split("\\.");
        StringJoiner sj = new StringJoiner(".", "", "");
        for (int i = 0; i < split.length - 1; i++)
            sj.add(split[i]);
        
        String dirName = sj.toString();
        File destDir = new File(dest, dirName);
        destDir.mkdirs();

        // 解压文件
        ZipInputStream zis = new ZipInputStream(new FileInputStream(src));

        ZipEntry entry;
        while ((entry = zis.getNextEntry()) != null) {
            System.out.println(entry); // 得到一个以压缩包为参照的相对路径
            File file = new File(destDir, entry.toString());

            if (entry.isDirectory()) {
                // 在目的地 distdir 处，创建一个同名的文件夹
                file.mkdirs();
            } else {
                // 读取压缩包中的文件，并把它存放到目的地 destdir 文件夹中
                FileOutputStream fos = new FileOutputStream(file);

                int b;
                while ((b = zis.read()) != -1)
                    fos.write(b);

                fos.close();
                zis.closeEntry(); // 表示压缩包中的一个文件处理完毕
            }
        }

        zis.close();
    }

    public static void main(String[] args) throws IOException {
        // 解压的压缩包
        File src = new File("Day29/src/com/kkcf/compression/aaa.zip");
        // 解压到的目录
        File destDir = new File("Day29/src/com/kkcf/compression/");

        unzip(src, destDir);
    }
}
```

## 二、ZipOutputStream 字节压缩输出流的使用

字节压缩输出流：

- 把多个文件，或文件夹压缩到一个压缩包中。
- 压缩包中的每一个文件、文件夹，在压缩流中都是一个 `ZipEntry` 对象。
- 压缩的本质，就是把每一个文件、文件夹，看作一个 `ZipEntry` 对象，放入到压缩包中。

### 1.压缩单个文件

把指定目录下的 a.txt 文件，压缩为 a.zip 文件。

demo-project/base-code/Day29/src/com/kkcf/compression/Demo02.java

```java
package com.kkcf.compression;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.StringJoiner;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Demo02 {
    public static void toZip(File src, File dest) throws IOException {
        // 压缩包名称
        StringJoiner sj = new StringJoiner(".", "", "");
        String[] split = src.getName().split("\\.");
        for (int i = 0; i < split.length - 1; i++)
            sj.add(split[i]);

        sj.add("zip");
        String zipName = sj.toString();
        File destFile = new File(dest, zipName);

        // 创建压缩流，关联压缩包
        ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(destFile));

        // 创建 ZipEntry 对象
        ZipEntry zipEntry = new ZipEntry(src.getName());

        // 把 ZipEntry 对象放入到压缩包中
        zos.putNextEntry(zipEntry);

        // 把 src 文件中的数据，写入到压缩包文件中。
        FileInputStream fis = new FileInputStream(src);

        int b;
        while ((b = fis.read()) != -1)
            zos.write(b);

        fis.close();

        zos.closeEntry();
        zos.close();
    }

    public static void main(String[] args) throws IOException {
        File src = new File("Day29/src/com/kkcf/compression/a.txt");
        File dest = new File("Day29/src/com/kkcf/compression/");

        toZip(src, dest);
    }
}
```

### 1.压缩目录下所有内容

压缩一个文件夹下的所有内容：

- `ZipEntry` 构造函数中的字符串路径，表示以压缩包为根目录的相对路径

demo-project/base-code/Day29/src/com/kkcf/compression/Demo03.java

```java
package com.kkcf.compression;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Demo03 {
    /**
     * 此方法用于：压缩一个文件夹下的所有内容
     * @param src 压缩的文件夹
     * @param zos 压缩流
     * @param name 要压缩文件、文件夹的名称
     */
    public static void toZip(File src, ZipOutputStream zos, String name) throws IOException {
        File[] files = src.listFiles();

        for (File file : files) {
            if (file.isFile()) {
                ZipEntry zipEntry = new ZipEntry((name == null ? file.getName() : name + "/") + file.getName()); // 难点
                zos.putNextEntry(zipEntry);

                FileInputStream fis = new FileInputStream(file);

                int b;
                while ((b = fis.read()) != -1)
                    zos.write(b);

                fis.close();
                zos.closeEntry();
            } else {
                toZip(file, zos, (name == null ? file.getName() : name + "/") + file.getName());
            }
        }
    }

    public static void main(String[] args) throws IOException {
        // 要压缩的文件夹
        File src = new File("Day29/src/com/kkcf/compression/bbb");

        // 压缩后的文件
        File destParent = src.getParentFile();
        File dest = new File(destParent, src.getName() + ".zip");

        // 压缩
        ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(dest));

        toZip(src, zos, null);

        zos.close();
    }
}
```
