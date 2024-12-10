package com.kkcf.io;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

public class CharsetDemo01 {
    public static void main(String[] args) throws UnsupportedEncodingException {
        String str = "wee可爱";

        // 默认编码方式：IDEA 使用 UTF-8 编码规则；英文字符 1 个字节，中文字符 3 个字节
        byte[] bytes1 = str.getBytes();

        System.out.println(bytes1.length); // 9（1 + 1 + 1 + 3 + 3）
        System.out.println(Arrays.toString(bytes1)); // [119, 101, 101, -27, -113, -81, -25, -120, -79]


        // 指定 GBK 的编码方式。英文字符 1 个字节，中文字符 2 个字节
        byte[] bytes2 = str.getBytes("GBK");

        System.out.println(bytes2.length); // 7（1 + 1 + 1 + 2 + 2）
        System.out.println(Arrays.toString(bytes2)); // [119, 101, 101, -65, -55, -80, -82]

        // 默认解码方式：IDEA 使用 UTF-8 解码规则
        String msg1 = new String(bytes1);
        System.out.println(msg1); // wee可爱

        String msg2 = new String(bytes2);
        System.out.println(msg2); // wee�ɰ�

        // 指定 GBK 的解码方式
        String msg3 = new String(bytes1, "GBK");
        System.out.println(msg3); // wee鍙埍

        String msg4 = new String(bytes2, "GBK");
        System.out.println(msg4); // wee可爱
    }
}
