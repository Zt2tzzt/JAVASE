package com.kkcf.test;

import java.io.*;
import java.net.Socket;
import java.util.UUID;

public class UploadRunnable implements Runnable {
    public Socket socket;

    public UploadRunnable(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            // 读取数据并保存到本地
            BufferedInputStream bis = new BufferedInputStream(socket.getInputStream());
            String filename = UUID.randomUUID().toString().replace("-", "");
            BufferedOutputStream bos  = new BufferedOutputStream(new FileOutputStream("Day33/src/com/kkcf/test/upload/" + filename + ".jpg"));
            byte[] buffer = new byte[1024];
            int len;
            while ((len = bis.read(buffer)) != -1)
                bos.write(buffer, 0, len);

            // 回血消息
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            bw.write("上传成功");
            bw.newLine();
            bw.flush();

            // 释放资源
            bos.close();
            bw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (socket != null){
                try {
                    socket.close();
                } catch (IOException e) {
                    System.err.println("关闭资源失败；err：" + e.getMessage());
                }
            }

        }
    }
}
