package com.kkcf.test;

import java.util.Arrays;

public class Test06 {
    public static void main(String[] args) {
        GirlFriend kumiko = new GirlFriend("wee", 18, 1.66);
        GirlFriend kaori = new GirlFriend("yui", 19, 1.58);
        GirlFriend mayu = new GirlFriend("mio", 18, 1.67);

        GirlFriend[] gfs = {kumiko, mayu, kaori};

        Arrays.sort(gfs, (o1, o2) -> {
            int ageDiff = o1.getAge() - o2.getAge();
            int heightDiff = o1.getHeight() - o2.getHeight() == 0 ? 0
                    : o1.getHeight() - o2.getHeight() > 0 ? 1
                    : -1;

            int nameDiff = o1.getName().compareTo(o2.getName());

            return ageDiff != 0 ? ageDiff
                    : heightDiff != 0 ? heightDiff
                    : nameDiff;
        });

        System.out.println(Arrays.toString(gfs));
    }
}
