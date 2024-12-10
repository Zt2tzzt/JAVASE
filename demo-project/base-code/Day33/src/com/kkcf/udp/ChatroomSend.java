package com.kkcf.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class ChatroomSend {
    public static void main(String[] args) throws IOException {
        // 创建 DatagramSocket 对象
        DatagramSocket ds = new DatagramSocket();

        // 打包数据
        Scanner sc = new Scanner(System.in);

        String data;
        do {
            System.out.println("请输入要发送到数据：");
            data = sc.nextLine();
            byte[] bytes = data.getBytes();

            // 打包数据
            InetAddress address = InetAddress.getByName("127.0.0.1");
            int port = 10086;
            DatagramPacket dp = new DatagramPacket(bytes, bytes.length, address, port);

            // 发送数据
            ds.send(dp);
        } while (!("886".equals(data)));

        // 释放资源
        ds.close();
    }
}
