package com.kkcf.ArrayList;

import java.util.ArrayList;

public class Demo05 {
    public static void main(String[] args) {
        ArrayList<User> list = new ArrayList<>();

        list.add(new User("1", "zhangsan", "123"));
        list.add(new User("2", "lisi", "123"));
        list.add(new User("3", "wangwu", "123"));

        int index = findUserIndex(list, "2");
        System.out.println(index);
    }

    public static int findUserIndex(ArrayList<User> list, String id) {
        for (int i = 0; i < list.size(); i++) {
            User user = list.get(i);

            if (id.equals(user.getId()))
                return i;
        }
        return -1;
    }
}
