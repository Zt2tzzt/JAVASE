package com.kkcf.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class ChatRoomRecive {
    public static void main(String[] args) throws IOException {
        // 创建 DatagramSocket 对象
        DatagramSocket ds = new DatagramSocket(10086);

        // 接收数据包
        byte[] bytes = new byte[1024];
        DatagramPacket dp = new DatagramPacket(bytes, bytes.length);


        while (true) {
            ds.receive(dp); // 阻塞方法

            // 解析数据包
            byte[] data = dp.getData();
            int len = dp.getLength();
            String ip = dp.getAddress().getHostAddress();
            String hostName = dp.getAddress().getHostName();

            System.out.println("接受到数据" + new String(data, 0, len));
            System.out.println("发送方ip" + ip);
            System.out.println("发送方主机名" + hostName);
        }

        // 释放资源
        //ds.close();
    }
}
