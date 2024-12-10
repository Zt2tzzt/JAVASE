package com.kkcf.test;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Sever3 {
    public static void main(String[] args) throws IOException {
        // 线程池子
        ThreadPoolExecutor pool = new ThreadPoolExecutor(
                3, // 核心线程数
                22, // 最大线程数
                60, // 存活时间
                TimeUnit.SECONDS, // 时间单位
                new ArrayBlockingQueue<>(2), // 阻塞队列
                Executors.defaultThreadFactory(), // 线程工厂
                new ThreadPoolExecutor.AbortPolicy() // 拒绝策略
        );

        // 创建 socket 对象，并绑定端口
        try (ServerSocket serverSocket = new ServerSocket(10086)) {
            while (true) {
                // 等待用户端到连接
                Socket socket = serverSocket.accept();

                //new Thread(new UploadRunnable(socket)).start();
                pool.submit(new UploadRunnable(socket));
            }
        }

    }
}
