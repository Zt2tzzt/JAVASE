package com.kkcf.dynamicproxy;

import java.lang.reflect.Proxy;

public class ProxyUtil {
    public static Star createProxy(Object bigStar) {
        return (Star) Proxy.newProxyInstance(
                ProxyUtil.class.getClassLoader(),
                new Class[]{Star.class},
                (proxy, method, args) -> {
                    if ("sing".equals(method.getName()))
                        System.out.println("准备话筒，收钱");
                    else if ("dance".equals(method.getName()))
                        System.out.println("准备场地，收钱");

                    return method.invoke(bigStar, args);
                }
        );
    }
}
