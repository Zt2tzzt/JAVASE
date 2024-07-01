package com.kkcf.object_oriented_exercises;

import java.util.Random;

public class Role {
    private String name;
    private int blood;
    private char gender;
    private String face;

    String[] boyfaces = {"风流俊雅", "气宇轩昂", "相貌英俊", "五官端正", "相貌平平", "一塌糊涂", "面目狰狞"};
    String[] girlfaces = {"美奂绝伦", "沉鱼落雁", "婷婷玉立", "身材娇好", "相貌平平", "相貌简陋", "惨不忍睹"};

    //attack 攻击描述：
    String[] attacks_desc = {
            "%s使出了一招【背心钉】，转到对方的身后，一掌向%s背心的灵台穴拍去。",
            "%s使出了一招【游空探爪】，飞起身形自半空中变掌为抓锁向%s。",
            "%s大喝一声，身形下伏，一招【劈雷坠地】，捶向%s双腿。",
            "%s运气于掌，一瞬间掌心变得血红，一式【掌心雷】，推向%s。",
            "%s阴手翻起阳手跟进，一招【没遮拦】，结结实实的捶向%s。",
            "%s上步抢身，招中套招，一招【劈挂连环】，连环攻向%s。"
    };

    //injured 受伤描述：
    String[] injureds_desc = {
            "结果%s退了半步，毫发无损",
            "结果给%s造成一处瘀伤",
            "结果一击命中，%s痛得弯下腰",
            "结果%s痛苦地闷哼了一声，显然受了点内伤",
            "结果%s摇摇晃晃，一跤摔倒在地",
            "结果%s脸色一下变得惨白，连退了好几步",
            "结果『轰』的一声，%s口中鲜血狂喷而出",
            "结果%s一声惨叫，像滩软泥般塌了下去"
    };

    public Role() {
    }

    public Role(String name, int blood, char gender) {
        this.name = name;
        this.blood = blood;
        this.gender = gender;
        this.setFace(this.gender);
    }

    public String getFace() {
        return face;
    }

    public void setFace(char gender) {
        Random r = new Random();

        switch (gender) {
            case '男' -> {
                int index = r.nextInt(boyfaces.length);
                this.face = boyfaces[index];
            }
            case '女' -> {
                int index = r.nextInt(girlfaces.length);
                this.face = girlfaces[index];
            }
            default -> {
                this.face = "面目狰狞";
            }
        }
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBlood() {
        return blood;
    }

    public void setBlood(int blood) {
        this.blood = blood;
    }

    @Override
    public String toString() {
        return "Role{" +
                "name='" + name + '\'' +
                ", blood=" + blood +
                ", gender=" + gender +
                ", face='" + face + '\'' +
                '}';
    }

    // 攻击方法，方法的调用者，去攻击参数
    public void attack(Role role) {
        // 攻击的伤害从 1-20 随机取数
        Random r = new Random();

        int hurt = r.nextInt(19) + 1;

        int remainBlood = role.blood - hurt;

        if (remainBlood < 0) remainBlood = 0;

        role.setBlood(remainBlood);

        int index1 = r.nextInt(attacks_desc.length);
        String desc1 = attacks_desc[index1];
        System.out.printf(desc1, this.getName(), role.getName());

        String desc2;
        if (remainBlood > 90) {
            desc2 = injureds_desc[0];
        } else if (remainBlood > 80) {
            desc2 = injureds_desc[1];
        } else if (remainBlood > 70) {
            desc2 = injureds_desc[2];
        } else if (remainBlood > 60) {
            desc2 = injureds_desc[3];
        } else if (remainBlood > 50) {
            desc2 = injureds_desc[4];
        } else if (remainBlood == 0) {
            desc2 = injureds_desc[6];
        } else {
            desc2 = injureds_desc[5];
        }

        System.out.printf(desc2, role.getName());
        System.out.println();

        //System.out.println(this.getName() + "举起拳头打了" + role.getName() + "一下，造成了 " + hurt + " 点伤害，" + role.getName() + "还剩下 " + remainBlood + " 点血。");
    }
}
