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
