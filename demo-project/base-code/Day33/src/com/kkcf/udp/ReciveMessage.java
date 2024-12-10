package com.kkcf.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ReciveMessage {
    public static void main(String[] args) throws IOException {
        // 创建 DatagramSocket 对象
        //     - 在接受数据时，一定要绑定端口，要与发送数据到目标端口一致。
        DatagramSocket ds = new DatagramSocket(10086);

        // 接受数据包
        byte[] bytes = new byte[1024];
        DatagramPacket dp = new DatagramPacket(bytes, bytes.length);
        ds.receive(dp); // 该方法是阻塞的，如果没有接受到数据，会在此处死等

        // 解析数据包
        byte[] data = dp.getData();
        int len = dp.getLength();
        InetAddress ip = dp.getAddress();
        int port = dp.getPort();

        System.out.println("接受到数据" + new String(data, 0, len));
        System.out.println("发送方ip" + ip);
        System.out.println("发送方端口" + port);

        // 释放资源
        ds.close();
    }
}
