package com.kkcf.string;

public class Demo08 {
    public static void main(String[] args) {
        String s1 = "abcd";
        System.out.println(s1); // abcd

        String s2 = new String();
        System.out.println("@" + s2 + "!"); // @!

        String s3 = new String(s1);
        System.out.println(s3); // abcd

        // 使用场景：修改字符串的内容时，先把字符串转为字符数组，再转为字符串。
        char[] chs = {'a', 'b', 'c', 'd'};
        String s4 = new String(chs);
        System.out.println(s4); // abcd

        // 使用场景：网络传输中都是字节信息，将这些字节信息放入数组，再转成字符串
        byte[] bytes = {97, 98, 99, 100};
        String s5 = new String(bytes);
        System.out.println(s5); // abcd
    }
}
