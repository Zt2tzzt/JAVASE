## Java 面向对象综合训练

## 一、文字版格斗游戏

需求：格斗游戏，每个游戏角色的姓名，血量，都不相同，在选定人物的时候（new 对象的时候），这些信息就应该被确定下来。 

程序运行之后创建了两个角色：

- 姓名：:乔峰；血量：100
- 姓名：:鸠摩智；血量：100

程序运行的过程如下：

- 乔峰举起拳头打了鸠摩智一下，造成了 XX 点伤害，鸠摩智还剩下 XXX 点血。
- 鸠摩智举起拳头打了鸠摩智一下，造成了 XX 点伤害，乔峰还剩下 XXX 点血。
- 乔峰举起拳头打了鸠摩智一下，造成了 XX 点伤害，鸠摩智还剩下 XXX 点血。
- 鸠摩智举起拳头打了鸠摩智一下，造成了 XX 点伤害，乔峰还剩下 XXX 点血。
- 乔峰 K.O 了鸠摩智 

> 输出语句
>
> ```java
> System.out.printf();
> ```
>
> 有两部分参数：
>
> - 第一部分：要输出的内容，使用 `%s` 占位；
> - 第二部分：填充的数据。
>
> ```java
> System.out.printf("%s，你好啊", "张三"); // 张三，你好啊
> System.out.println();
> System.out.printf("%s，你好啊，我是%s", "张三", "李四"); // 张三，你好啊，我是李四
> ```

demo-project/base-code/Day09/src/com/kkcf/object_oriented_exercises/Role.java

```java
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
```

demo-project/base-code/Day09/src/com/kkcf/object_oriented_exercises/GameTest.java

```java
package com.kkcf.object_oriented_exercises;

public class GameTest {
    public static void main(String[] args) {
        Role r1 = new Role("乔峰", 100, '男');
        Role r2 = new Role("鸠摩智", 100, '女');

        System.out.println(r1.toString());
        System.out.println(r2.toString());

        while (true) {
            r1.attack(r2);

            if (r2.getBlood() == 0) {
                System.out.println(r1.getName() + " K.0 了" + r2.getName());
                break;
            }

            r2.attack(r1);

            if (r1.getBlood() == 0) {
                System.out.println(r2.getName() + " K.0 了" + r1.getName());
                break;
            }
        }
    }
}
```

## 二、对象数组

### 1.对象数组-商品

需求1：定义数组存储 3 个商品对象。商品的属性：商品的 id，名字，价格，库存。创建三个商品对象，并把商品对象存入到数组当中。

demo-project/base-code/Day09/src/com/kkcf/object_oriented_exercises/Goods.java

```java
package com.kkcf.object_oriented_exercises;

public class Goods {
    private String id;
    private String name;
    private double price;
    private int count;

    public Goods() {
    }

    public Goods(String id, String name, double price, int count) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.count = count;
    }

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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", count=" + count +
                '}';
    }
}
```

demo-project/base-code/Day09/src/com/kkcf/object_oriented_exercises/GameTest.java

```java
package com.kkcf.object_oriented_exercises;

public class GoodsTest {
    public static void main(String[] args) {
        Goods[] goods = new Goods[3];

        Goods iphone = new Goods("001", "IPhone", 9999.99, 100);
        Goods macBook = new Goods("002", "MacBook", 19999.99, 150);
        Goods ipad = new Goods("003", "iPad", 5999.99, 200);

        goods[0] = iphone;
        goods[1] = macBook;
        goods[2] = ipad;

        for (int i = 0; i < goods.length; i++) {
            Goods good = goods[i];

            System.out.println(good.toString());
        }
    }
}
```

### 2.对象数组-汽车

#### 1.键盘录入的补充

第一套体系：

- 遇到空格，制表符，回车就停止接收（后面的数据就不会接收了）。
- 这些符号后面的键盘录入，会交给下一个键盘录入语句接收。

```java
Scanner sc = new Scanner();

sc.nextInt(); // 键盘录入一个整数
sc.nextDouble(); // 键盘录入一个浮点数
sc.next(); // 键盘录入一个字符串
```

第二套体系：

- 可以接收空格，制表符，遇到回车才停止接收。

```java
Scanner sc = new Scanner();

sc.nextLine(); // 键盘录入一个字符串
```

第一套体系，和第二套体系不能混用，

- 比如：先用 `nextInt`，再用 `nextLine` 会导致 `nextLine` 接收不到数据。
- 因为：`nextInt` 键盘录入完成后，输入的 Enter，被 `nextLine` 接收了。

需求 2：定义数组存储3部汽车对象。汽车的属性：品牌，价格，颜色。创建三个汽车对象，数据通过键盘录入而来，并把数据存入到数组当中。

demo-project/base-code/Day09/src/com/kkcf/object_oriented_exercises/Car.java

```java
package com.kkcf.object_oriented_exercises;

public class Car {
    private String brand;
    private int price;
    private String color;

    public Car() {
    }

    public Car(String brand, int price, String color) {
        this.brand = brand;
        this.price = price;
        this.color = color;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Car{" +
                "brand='" + brand + '\'' +
                ", price=" + price +
                ", color='" + color + '\'' +
                '}';
    }
}
```

demo-project/base-code/Day09/src/com/kkcf/object_oriented_exercises/CarTest.java

```java
package com.kkcf.object_oriented_exercises;

import java.util.Scanner;

public class CarTest {
    public static void main(String[] args) {
        Car[] cars = new Car[3];
        Scanner sc = new Scanner(System.in);

        for (int i = 0; i < cars.length; i++) {
            System.out.print("请输入第 " + (i + 1) + " 辆汽车的品牌：");
            String brand = sc.next();

            System.out.print("请输入第 " + (i + 1) + " 辆汽车的价格：");
            int price = sc.nextInt();

            System.out.print("请输入第 " + (i + 1) + " 辆汽车的颜色：");
            String color = sc.next();

            Car car = new Car(brand, price, color);
            cars[i] = car;
        }
        
        sc.close();

        for (int i = 0; i < cars.length; i++) {
            System.out.println(cars[i].toString());
        }
    }
}
```

### 3.对象数组-手机

需求 :  定义数组存储 3 部手机对象。手机的属性：品牌，价格，颜色。要求，计算出三部手机的平均价格。

demo-project/base-code/Day09/src/com/kkcf/object_oriented_exercises/Phone.java

```java
package com.kkcf.object_oriented_exercises;

public class Phone {
    private String brand;
    private int price;
    private String color;

    public Phone() {
    }

    public Phone(String brand, int price, String color) {
        this.brand = brand;
        this.price = price;
        this.color = color;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Phone{" +
                "brand='" + brand + '\'' +
                ", price=" + price +
                ", color='" + color + '\'' +
                '}';
    }
}
```

demo-project/base-code/Day09/src/com/kkcf/object_oriented_exercises/PhoneTest.java

```java
package com.kkcf.object_oriented_exercises;

public class PhoneTest {
    public static void main(String[] args) {
        Phone[] phones = new Phone[3];

        phones[0] = new Phone("小米", 2999, "黑色");
        phones[1] = new Phone("华为", 5999, "白色");
        phones[2] = new Phone("苹果", 9999, "金色");

        int sum = 0;
        for (int i = 0; i < phones.length; i++) {
            sum += phones[i].getPrice();
        }

        double avg = sum * 1.0 / phones.length;
        System.out.println("平均价格：" + avg); // 6332.333333333333
    }
}
```

需求：定义数组存储 4 个女朋友的对象；女朋友的属性：姓名、年龄、性别、爱好；

要求 1：计算出四女朋友的平均年龄；要求 2：统计年龄比平均值低的女朋友有几个？并把她们的所有信息打印出来。

demo-project/base-code/Day09/src/com/kkcf/object_oriented_exercises/GirlFriend.java

```java
package com.kkcf.object_oriented_exercises;

public class GirlFriend {
    private String name;
    private int age;
    private String gender;
    private String hobby;

    public GirlFriend() {
    }

    public GirlFriend(String name, int age, String gender, String hobby) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.hobby = hobby;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    @Override
    public String toString() {
        return "GirlFriend{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", hobby='" + hobby + '\'' +
                '}';
    }
}
```

demo-project/base-code/Day09/src/com/kkcf/object_oriented_exercises/GirlFriendTest.java

```java
package com.kkcf.object_oriented_exercises;

public class GirlFriendTest {
    public static void main(String[] args) {
        GirlFriend[] gfs = new GirlFriend[4];

        GirlFriend gf1 = new GirlFriend("aoi", 18, "萌妹子", "向山进发");
        GirlFriend gf2 = new GirlFriend("rin", 19, "萌妹子", "摇曳露营");
        GirlFriend gf3 = new GirlFriend("mayu", 22, "萌妹子", "照片摄影");
        GirlFriend gf4 = new GirlFriend("kumiko", 21, "萌妹子", "上低音号");

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
```

