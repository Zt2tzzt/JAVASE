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

        // 回写数据
        String str = "见到你我也很高心";
        socket.getOutputStream().write(str.getBytes());

        socket.close();
        serverSocket.close();
    }
}
