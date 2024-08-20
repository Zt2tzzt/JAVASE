package com.kkcf.tcp;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws IOException {
        // 创建 Socket 对象，连接服务器端，如果连接不上，会抛出异常
        Socket socket = new Socket("127.0.0.1", 10086);

        // 从连接通道中，获取字节输出流
        OutputStream os = socket.getOutputStream();

        // 写数据
        os.write("你好，你好".getBytes());

        // 释放资源
        os.close();
        socket.close();
    }
}
