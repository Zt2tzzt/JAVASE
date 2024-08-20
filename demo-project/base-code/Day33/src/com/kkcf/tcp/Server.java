package com.kkcf.tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        // 创建对象 ServerSocket
        ServerSocket serverSocket = new ServerSocket(10086); // 指定端口号

        // 监听客户端端 socket 连接
        Socket socket = serverSocket.accept(); // 阻塞

        // 从连接通道中，获取字节输入流，读取数据
        InputStream is = socket.getInputStream();

        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String line;
        while ((line = br.readLine()) != null)
            System.out.println(line);

        // 释放资源
        is.close();
        socket.close(); // socket 连接通道关闭
        serverSocket.close(); // 服务端 socket 关闭.
    }
}
