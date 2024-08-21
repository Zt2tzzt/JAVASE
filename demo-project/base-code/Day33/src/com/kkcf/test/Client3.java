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

        // 接收服务器到返回到数据
        BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String line = br.readLine();
        System.out.println(line);

        // 释放资源
        //bos.close();
        bis.close();
        socket.close();
    }
}
