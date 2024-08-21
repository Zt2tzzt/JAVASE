# Java 网络编程之综合练习

## 一、练习一：多发多收

需求，客户端多次发送数据；服务端多次接收数据。

客户端 Client1

demo-project/base-code/Day33/src/com/kkcf/test/Client1.java

```java
package com.kkcf.test;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client1 {
    public static void main(String[] args) throws IOException {
        // 创建 socket 对象，并连接服务器
        Socket socket = new Socket("127.0.0.1", 10086);

        // 获取输出流
        OutputStream os = socket.getOutputStream();

        Scanner sc = new Scanner(System.in);
        String data = null;

        // 向服务端发送数据
        do {
            System.out.println("请输入要发送的数据：");
            data = sc.nextLine();
            os.write(data.getBytes());
        } while (!"886".equals(data));

        // 释放资源，会关闭输出流和 socket
        socket.close();
    }
}
```

服务端 Server1

demo-project/base-code/Day33/src/com/kkcf/test/Server1.java

```java
package com.kkcf.test;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server1 {
    public static void main(String[] args) throws IOException {
        // 创建 ServerSocket 对象，绑定 10096 端口
        ServerSocket serverSocket = new ServerSocket(10086);

        // 等待客户端连接
        Socket socket = serverSocket.accept(); // 阻塞

        InputStream is = socket.getInputStream();

        // 接收数据
        InputStreamReader isr = new InputStreamReader(is);
        char[] chs = new char[1024];
        int len;
        while ((len = isr.read(chs)) != -1)
            System.out.print(new String(chs, 0, len));

        socket.close();
        serverSocket.close();
    }
}
```

- 如果客户端不发送结束标记，上方的 while 循环会一直进行下去。

在浏览器地址栏，输入 `127.0.0.1:10086` 查看控制台输出：

```sh
GET / HTTP/1.1
Host: 127.0.0.1:10086
Sec-Fetch-Site: none
Connection: keep-alive
Upgrade-Insecure-Requests: 1
Sec-Fetch-Mode: navigate
Accept: text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8
User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/17.6 Safari/605.1.15
Accept-Language: zh-CN,zh-Hans;q=0.9
Sec-Fetch-Dest: document
Accept-Encoding: gzip, deflate
```

## 二、练习二：接收并反馈

需求：客户端发送一条数据，接收服务端反馈的消息，并打印；服务端：接收数据并打印，再给客户端反馈消息。

客户端 Client2

demo-project/base-code/Day33/src/com/kkcf/test/Client2.java

```java
package com.kkcf.test;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client2 {
    public static void main(String[] args) throws IOException {
        // 创建 socket 对象，并连接服务器
        Socket socket = new Socket("127.0.0.1", 10086);

        // 获取输出流
        OutputStream os = socket.getOutputStream();

        // 向服务端发送数据
        String data = "见到你很高兴！";
        os.write(data.getBytes());
        // 写出一个结束标记
        socket.shutdownOutput();

        // 接收服务器端回写的数据
        InputStream ls = socket.getInputStream();
        InputStreamReader lsr = new InputStreamReader(ls);
        char[] chs = new char[1024];
        int len;
        while ((len = lsr.read(chs)) != -1)
            System.out.print(new String(chs, 0, len));

        // 释放资源，会关闭输出流和 socket
        socket.close();
    }
}
```

- `socket.shutdownOutput();` 用于给服务端，写出一个结束标记。

服务端 Server2

demo-project/base-code/Day33/src/com/kkcf/test/Server2.java

```java
package com.kkcf.test;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server2 {
    public static void main(String[] args) throws IOException {
        // 创建 ServerSocket 对象，绑定 10096 端口
        ServerSocket serverSocket = new ServerSocket(10086);

        // 等待客户端连接
        Socket socket = serverSocket.accept(); // 阻塞

        // 接收数据
        InputStream is = socket.getInputStream();
        InputStreamReader isr = new InputStreamReader(is); 
        char[] chs = new char[1024];
        int len;
        // 细节：read 方法，从连接通道中读取数据，发送的数据需要有一个结束标记，否则会一直停留在 read 方法这里，等待读取下面的数据。
        while ((len = isr.read(chs)) != -1)
            System.out.print(new String(chs, 0, len));

        // 返回数据
        String str = "见到你我也很高心";
        socket.getOutputStream().write(str.getBytes());

        socket.close();
        serverSocket.close();
    }
}
```

## 三、练习三：上传文件

需求：客户端：将本地文件上传到服务器，接收服务器的反馈；服务端：接收客户端上传的文件保存到本地，上传完毕之后给出反馈。

思路：客户端，使用 `FileInputSream` 字节输入流，读取本地文件到内容到内存中，再将文件数据发送给服务器端；服务器端接收到文件数据后，使用 `FileOutputStream` 字节输出流，保存文件到本地，并向客户端返回消息。

客户端：Client3

demo-project/base-code/Day33/src/com/kkcf/test/Client3.java

```java
package com.kkcf.test;

import java.io.*;
import java.net.Socket;

public class Client3 {
    public static void main(String[] args) throws IOException {
        // 创建 socket 对象，并连接服务器
        Socket socket = new Socket("127.0.0.1", 10086);

        // 读取本地文件中到数据，并发送给服务端
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream("Day33/src/com/kkcf/test/upload/baja(1).jpg"));
        BufferedOutputStream bos = new BufferedOutputStream(socket.getOutputStream());
        byte[] buffer = new byte[1024];
        int len;
        while ((len = bis.read(buffer)) != -1)
            bos.write(buffer, 0, len);

        // 发送结束标记
        socket.shutdownOutput();

        // 接收服务器端返回的数据
        BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String line = br.readLine();
        System.out.println(line);

        // 释放资源
        //bos.close();
        bis.close();
        socket.close();
    }
}
```

服务端：Sever3

demo-project/base-code/Day33/src/com/kkcf/test/Sever3.java

```java
package com.kkcf.test;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Sever3 {
    public static void main(String[] args) throws IOException {
        // 创建 socket 对象，并绑定端口
        ServerSocket serverSocket = new ServerSocket(10086);

        // 等待用户端到连接
        Socket socket = serverSocket.accept();

        // 读取数据并保存到本地
        BufferedInputStream bis = new BufferedInputStream(socket.getInputStream());
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("Day33/src/com/kkcf/test/upload/a.jpg"));
        byte[] buffer = new byte[1024];
        int len;
        while ((len = bis.read(buffer)) != -1)
            bos.write(buffer, 0, len);

        // 返回消息
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        bw.write("上传成功");
        bw.newLine();
        bw.flush();

        // 释放资源
        //bis.close();
        bos.close();
        bw.close();
        socket.close();
        serverSocket.close();
    }
}
```

### 1.UUID 类

为了使服务端保存本地文件的时候，文件不重名，需要使用到 `UUID` 类

`UUID` 可以生成一个随机的字符串，字符串的内容是唯一的。

使用静态方法 `randomUUID`，可以返回一个 uuid 对象。

demo-project/base-code/Day33/src/com/kkcf/test/UUIDTest.java

```java
package com.kkcf.test;

import java.util.UUID;

public class UUIDTest {
    public static void main(String[] args) {
        UUID uuid = UUID.randomUUID();
        System.out.println(uuid); // a9568ed2-81f1-4665-acef-128f7143fcc7

        String result = uuid.toString().replace("-", "");
        System.out.println(result); // a9568ed281f14665acef128f7143fcc7
    }
}
```

优化上方服务端代码：

demo-project/base-code/Day33/src/com/kkcf/test/Sever3.java

```java
package com.kkcf.test;

import ……

public class Sever3 {
    public static void main(String[] args) throws IOException {
        // ……

        String filename = UUID.randomUUID().toString().replace("-", "");
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("Day33/src/com/kkcf/test/upload/" + filename + ".jpg"));

        // ……
    }
}
```

## 四、练习四：上传文件（多线程）

需求：服务器不停止，接收很多用户上传的图片。

思路：可以使用循环，或者多线程；最优解：循环 + 多线程。

重构上方服务端代码 `Sever3`，在其中开启多线程。

demo-project/base-code/Day33/src/com/kkcf/test/Sever3.java

```java
package com.kkcf.test;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Sever3 {
    public static void main(String[] args) throws IOException {
        // 创建 socket 对象，并绑定端口
        ServerSocket serverSocket = new ServerSocket(10086);

        while (true) {
            // 等待用户端到连接
            Socket socket = serverSocket.accept();

            new Thread(new UploadRunnable(socket)).start();
        }
    }
}
```

自定义 `UploadRunnable` 类，实现 `Runnable` 接口

- 这种方式，对于要共享的 socket 对象，更加灵活。

demo-project/base-code/Day33/src/com/kkcf/test/UploadRunnable.java

```java
package com.kkcf.test;

import java.io.*;
import java.net.Socket;
import java.util.UUID;

public class UploadRunnable implements Runnable {
    public Socket socket;

    public UploadRunnable(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            // 读取数据并保存到本地
            BufferedInputStream bis = new BufferedInputStream(socket.getInputStream());
            String filename = UUID.randomUUID().toString().replace("-", "");
            BufferedOutputStream bos  = new BufferedOutputStream(new FileOutputStream("Day33/src/com/kkcf/test/upload/" + filename + ".jpg"));
            byte[] buffer = new byte[1024];
            int len;
            while ((len = bis.read(buffer)) != -1)
                bos.write(buffer, 0, len);

            // 回血消息
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            bw.write("上传成功");
            bw.newLine();
            bw.flush();

            // 释放资源
            bos.close();
            bw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (socket != null){
                try {
                    socket.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
```

## 五、练习五：上传文件（线程池）

使用线程池，管理线程；

重构上方服务端代码 `Server3`

demo-project/base-code/Day33/src/com/kkcf/test/Sever3.java

```java
package com.kkcf.test;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Sever3 {
    public static void main(String[] args) throws IOException {
        // 线程池子
        ThreadPoolExecutor pool = new ThreadPoolExecutor(
                3, // 核心线程数
                22, // 最大线程数
                60, // 存活时间
                TimeUnit.SECONDS, // 时间单位
                new ArrayBlockingQueue<>(2), // 阻塞队列
                Executors.defaultThreadFactory(), // 线程工厂
                new ThreadPoolExecutor.AbortPolicy() // 拒绝策略
        );

        // 创建 socket 对象，并绑定端口
        ServerSocket serverSocket = new ServerSocket(10086);

        while (true) {
            // 等待用户端到连接
            Socket socket = serverSocket.accept();

            //new Thread(new UploadRunnable(socket)).start();
            pool.submit(new UploadRunnable(socket));
        }
    }
}
```
