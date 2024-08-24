package com.kkcf.dynamicproxy;

public class Test {
    public static void main(String[] args) {
        Star proxy = ProxyUtil.createProxy(new BigStar("坤坤"));

        String result = proxy.sing("只因你太美");

        /*准备话筒，收钱
        坤坤正在唱只因你太美*/

        System.out.println(result); // 谢谢

        proxy.dance();

        /*准备场地，收钱
        坤坤正在跳舞*/
    }
}
