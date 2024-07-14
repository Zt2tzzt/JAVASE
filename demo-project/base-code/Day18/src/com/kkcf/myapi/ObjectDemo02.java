package com.kkcf.myapi;

import com.google.gson.Gson;

public class ObjectDemo02 {
    public static void main(String[] args) throws CloneNotSupportedException {
        //String s = new String("haha");
        //StringBuilder sb = new StringBuilder("hehe");
        //
        //System.out.println(s.equals(sb)); /// false
        //System.out.println(sb.equals(s)); /// false

        User u1 = new User("1", "zhangsan", "123456", "abc/cba/nba", new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15});

        //Object obj = u1.clone();
        //User u2 = (User) obj;
        //
        //u1.getData()[0] = 100;
        //
        //System.out.println(u1); // User{id='1', name='zhangsan', password='123456', path='abc/cba/nba', data=[1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15]}
        //System.out.println(u2); // User{id='1', name='zhangsan', password='123456', path='abc/cba/nba', data=[1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15]}

        Gson gson = new Gson();
        String u1JsontStr = gson.toJson(u1);
        System.out.println(u1JsontStr);

        User u3 = gson.fromJson(u1JsontStr, User.class);
        System.out.println(u3);
    }
}
