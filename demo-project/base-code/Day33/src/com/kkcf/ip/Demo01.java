package com.kkcf.ip;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Demo01 {
    public static void main(String[] args) throws UnknownHostException {
        // 传入 IP 地址
        InetAddress address = InetAddress.getByName("10.33.157.90");
        System.out.println(address); // /10.33.157.90

        // 传入主机名
        InetAddress address1 = InetAddress.getByName("ZeTiandeMacBook-Pro.local");
        System.out.println(address1); // ZeTiandeMacBook-Pro.local/127.0.0.1

        // 获取主机名，如果获取不到，会返回一个 iP
        String hostName = address1.getHostName();
        System.out.println(hostName); // ZeTiandeMacBook-Pro.local

        // 获取主机地址
        String hostAddress = address1.getHostAddress();
        System.out.println(hostAddress); // 127.0.0.1
    }
}
