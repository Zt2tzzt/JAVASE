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
