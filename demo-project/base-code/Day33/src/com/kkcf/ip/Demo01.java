package com.kkcf.ip;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Demo01 {
    public static void main(String[] args) throws UnknownHostException {
        InetAddress address = InetAddress.getByName("10.33.157.90");
        System.out.println(address); // /10.33.157.90

        InetAddress address1 = InetAddress.getByName("ZeTiandeMacBook-Pro.local");
        System.out.println(address1); // ZeTiandeMacBook-Pro.local/127.0.0.1
    }
}
