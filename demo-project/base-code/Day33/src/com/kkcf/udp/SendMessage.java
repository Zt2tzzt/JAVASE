package com.kkcf.udp;

import java.io.IOException;
import java.net.*;

public class SendMessage {
    public static void main(String[] args) throws IOException {
        // 创建 DatagramSocket 对象
        //   - 不传参数，表示随机绑定计算机上的一个端口，发送数据
        //   - 传惨，表示指定一个端口，发送数据
        DatagramSocket ds = new DatagramSocket();

        // 打包数据
        String str = "你很勇哦";
        byte[] bytes = str.getBytes();
        InetAddress ip = InetAddress.getByName("127.0.0.1");
        int port = 10086;

        DatagramPacket dp = new DatagramPacket(bytes, bytes.length, ip, port);

        // 发送数据
        ds.send(dp);

        // 释放资源
        ds.close();
    }
}
