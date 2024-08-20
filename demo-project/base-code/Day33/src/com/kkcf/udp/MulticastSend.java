package com.kkcf.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class MulticastSend {
    public static void main(String[] args) throws IOException {
        // 创建 MulticastSocket 对象
        MulticastSocket ms = new MulticastSocket();

        // 创建 DatagramPacket 对象
        String data = "你好，你好！";
        byte[] bytes = data.getBytes();
        InetAddress address = InetAddress.getByName("224.0.0.1"); // 指定组播地址
        int port = 10086;

        DatagramPacket dp = new DatagramPacket(bytes, bytes.length, address, port);

        // 发送数据
        ms.send(dp);

        // 释放资源
        ms.close();
    }
}
