package com.kkcf.object_riented;

public class Test02 {
    public static void main(String[] args) {
        GirlFriend gf = new GirlFriend();
        //gf.age = 18; // 报错

        gf.setName("kumiko");
        gf.setAge(19);
        gf.setGender("萌妹子");

        System.out.println(gf.getName());
        System.out.println(gf.getAge());
        System.out.println(gf.getGender());
    }
}
