package com.kkcf.myapi;

import java.io.IOException;

public class RuntimeDemo01 {
    public static void main(String[] args) throws IOException {
        //Runtime r1 = Runtime.getRuntime();
        //Runtime r2 = Runtime.getRuntime();
        //System.out.println(r1 == r2); // true

        //Runtime.getRuntime().exit(0);
        //System.out.println("看看我执行了码"); // 未执行

        //System.out.println(Runtime.getRuntime().availableProcessors()); // 12

        //System.out.println(Runtime.getRuntime().maxMemory() / 1024 / 1024); // 4084 即 4G 内存

        //System.out.println(Runtime.getRuntime().totalMemory() / 1024 / 1024); // 256 即 256MB 内存

        //System.out.println(Runtime.getRuntime().freeMemory() / 1024 / 1024); // 253 即 253MB 内存

        Runtime.getRuntime().exec("java -version");
    }
}
