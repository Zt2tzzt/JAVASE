package com.kkcf.object_oriented_exercises;

public class GirlFriendTest {
    public static void main(String[] args) {
        GirlFriend[] gfs = new GirlFriend[4];

        GirlFriend gf1 = new GirlFriend("wee", 18, "萌妹子", "吃");
        GirlFriend gf2 = new GirlFriend("yui", 19, "萌妹子", "吉他");
        GirlFriend gf3 = new GirlFriend("mio", 22, "萌妹子", "贝斯");
        GirlFriend gf4 = new GirlFriend("aoi", 21, "萌妹子", "登山");

        gfs[0] = gf1;
        gfs[1] = gf2;
        gfs[2] = gf3;
        gfs[3] = gf4;

        int ageSum = 0;
        for (int i = 0; i < gfs.length; i++)
            ageSum += gfs[i].getAge();

        int ageAvg = ageSum / gfs.length;
        System.out.println("年龄平均值：" + ageAvg);

        int count = 0;
        for (int i = 0; i < gfs.length; i++) {
            if (gfs[i].getAge() < ageAvg) {
                System.out.println(gfs[i].getName() + " 的年龄小于平均值 " + gfs[i].toString());
                count++;
            }
        }

        System.out.println("年龄小于平均值的人数：" + count);
    }
}
