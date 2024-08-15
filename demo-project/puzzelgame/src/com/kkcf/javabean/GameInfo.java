package com.kkcf.javabean;

import java.io.Serializable;
import java.util.Arrays;

public class GameInfo implements Serializable {
    // 用于存放图片的随即索引
    private int[][] data = new int[4][4];

    // 记录空白方块，在二维数组中的位置
    private int x = 0;
    private int y = 0;

    // 记录图片路径
    private String path = "image/animal/animal3/";

    // 记录玩了多少步
    int stepCount = 0;

    public GameInfo() {
    }

    public GameInfo(int[][] data, int x, int y, String path, int stepCount) {
        this.data = data;
        this.x = x;
        this.y = y;
        this.path = path;
        this.stepCount = stepCount;
    }

    public int[][] getData() {
        return data;
    }

    public void setData(int[][] data) {
        this.data = data;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getStepCount() {
        return stepCount;
    }

    public void setStepCount(int stepCount) {
        this.stepCount = stepCount;
    }

    @Override
    public String toString() {
        return "GameInfo{" +
                "data=" + Arrays.toString(data) +
                ", x=" + x +
                ", y=" + y +
                ", path='" + path + '\'' +
                ", stepCount=" + stepCount +
                '}';
    }
}
