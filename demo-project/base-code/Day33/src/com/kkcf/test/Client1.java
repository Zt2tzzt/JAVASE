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
        String data;
        do {
            System.out.println("请输入要发送的数据：");
            data = sc.nextLine();
            // 向服务端发送数据
            os.write(data.getBytes());
        } while (!"886".equals(data));

        // 释放资源，会关闭输出流和 socket
        socket.close();
    }
}
