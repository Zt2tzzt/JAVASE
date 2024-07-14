package com.kkcf.myapi;

import java.util.Arrays;

public class User implements Cloneable {
    private String id; // 游戏角色
    private String name; // 用户名
    private String password; // 密码
    private String path; // 图片
    private int[] data; // 游戏进度

    public User() {
    }

    public User(String id, String name, String password, String path, int[] data) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.path = path;
        this.data = data;
    }

    // getter、setter
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int[] getData() {
        return data;
    }

    public void setData(int[] data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", path='" + path + '\'' +
                ", data=" + Arrays.toString(data) +
                '}';
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        int[] newData = new int[data.length];

        System.arraycopy(data, 0, newData, 0, newData.length);

        // 用于调用 Object 类中的 clone 方法，克隆一个对象，并把它返回出去
        User u = (User) super.clone();
        u.data = newData;

        return u;
    }
}
