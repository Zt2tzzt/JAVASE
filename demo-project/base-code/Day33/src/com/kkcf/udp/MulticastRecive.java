package com.kkcf.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class MulticastRecive {
    public static void main(String[] args) throws IOException {
        // 创建 MulticastSocket 对象
        MulticastSocket ms = new MulticastSocket(10086); // 指定接收数据的端口

        // 将当前本机，添加到 224.0.0.1 的这一组当中
        InetAddress address = InetAddress.getByName("224.0.0.1");
        ms.joinGroup(address);

        // 创建 Datagram 数据包对象
        byte[] bytes = new byte[1024];
        DatagramPacket dp = new DatagramPacket(bytes, bytes.length);

        // 接收数据
        ms.receive(dp); // 阻塞

        // 接续数据
        byte[] data = dp.getData();
        int len = dp.getLength();
        String hostAddress = dp.getAddress().getHostAddress();
        String hostName = dp.getAddress().getHostName();

        System.out.println("接受到数据" + new String(data, 0, len));
        System.out.println("发送方ip" + hostAddress);
        System.out.println("发送方主机名" + hostName);

        // 释放资源
        ms.close();
    }
}
