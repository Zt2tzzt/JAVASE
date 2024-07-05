import com.kkcf.student.Student;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        ArrayList<Student> stus = new ArrayList<>();

        // 给 while 循环体标号为 loop，方便 break 关键字推出循环体。
        loop:
        while (true) {
            System.out.println("”-------------欢迎来到北宇治学生管理系统----------------“");
            System.out.println("”1：添加学生“");
            System.out.println("”2：删除学生“");
            System.out.println("”3：修改学生“");
            System.out.println("”4：查询学生“");
            System.out.println("”5：退出“");
            System.out.print("”请输入您的选择:“");
            String input = sc.next();

            switch (input) {
                case "1" -> addStu(stus);
                case "2" -> removeStu(stus);
                case "3" -> modifyStu(stus);
                case "4" -> showStu(stus);
                case "5" -> {
                    System.out.println("”退出“");
                    // System.exit(0);
                    break loop;
                }
                default -> System.out.println("”输入错误“");
            }
        }
    }

    /**
     * 此方法用于：添加学生
     * @param stus 学生集合列表
     */
    public static void addStu(ArrayList<Student> stus) {
        Scanner sc = new Scanner(System.in);

        // 输入学生 id
        String id = "";
        do {
            System.out.print("请输入学生id:");
            id = sc.next();
            if (findStuIndexById(stus, id) >= 0)
                System.out.print("该id已存在，请重新输入！");
            else
                break;
        } while (true);

        // 输入学生姓名
        System.out.print("请输入学生姓名:");
        String name = sc.next();

        // 输入学生年龄
        String ageStr = "";
        do {
            System.out.print("请输入学生年龄:");
            ageStr = sc.next();

            if (checkNum(ageStr) && str2Num(ageStr) < 100)
                System.out.print("输入值不合法，请重新输入！");
            else
                break;
        } while (true);
        int age = str2Num(ageStr);

        // 输入学生家庭住址
        System.out.print("请输入学生家庭住址:");
        String address = sc.next();

        // 添加学生
        stus.add(new Student(id, name, age, address));
        System.out.println("添加学生成功");
    }

    /**
     * 此方法用于：删除学生
     * @param stus 学生集合列表
     */
    public static void removeStu(ArrayList<Student> stus) {
        Scanner sc = new Scanner(System.in);

        System.out.print("请输入要删除的学生id:");
        String id = sc.next();

        int index = findStuIndexById(stus, id);
        if (index >= 0) {
            Student stu = stus.remove(index);
            if (stu != null) System.out.println("删除学生成功");
        } else
            System.out.println("该学生不存在，回到主菜单");
    }

    /**
     * 此函数用于：修改学生信息
     *
     * @param stus 学生集合列表
     */
    public static void modifyStu(ArrayList<Student> stus) {
        Scanner sc = new Scanner(System.in);

        // 输入要修改的学生id
        System.out.print("请输入要修改的学生id:");
        String id = sc.next();

        int index = findStuIndexById(stus, id);
        if (index < 0) {
            System.out.println("该学生不存在，回到主菜单");
            return;
        }

        // 输入要修改的学生姓
        System.out.print("请输入要修改的学生姓名:");
        String newName = sc.next();

        // 输入要修改的学生年龄
        String ageStr = "";
        do {
            System.out.print("请输入学生年龄:");
            ageStr = sc.next();

            if (checkNum(ageStr) && str2Num(ageStr) < 100)
                System.out.print("输入值不合法，请重新输入！");
            else
                break;
        } while (true);
        int newAge = str2Num(ageStr);

        // 输入要修改的学生家庭住址
        System.out.print("请输入学生家庭住址:");
        String newAddress = sc.next();

        // 修改学生信息
        Student stu = stus.get(index);
        stu.setName(newName);
        stu.setAge(newAge);
        stu.setAddress(newAddress);

        System.out.println("修改学生信息成功");
    }

    /**
     * 此函数用于，显示学生信息
     *
     * @param stus 学生集合列表
     */
    public static void showStu(ArrayList<Student> stus) {
        if (stus.isEmpty()) {
            System.out.println("当前无学生信息，请添加后再查询！");
            return;
        }

        System.out.println("id\t\t姓名\t\t年龄\t\t家庭住址");
        for (Student stu : stus)
            System.out.println(stu.getId() + "\t\t" + stu.getName() + "\t\t" + stu.getAge() + "\t\t" + stu.getAddress());
    }

    /**
     * 此函数用于，查找学生id
     *
     * @param stus 学生集合列表
     * @param id   学生id
     * @return -1：未找到；>=0：找到
     */
    public static int findStuIndexById(ArrayList<Student> stus, String id) {
        for (int i = 0; i < stus.size(); i++)
            if (stus.get(i).getId().equals(id))
                return i;

        return -1;
    }

    /**
     * 此方法用于：判断字符串是否由数字组成
     *
     * @param str 字符串
     * @return true：是数字；false：不是数字
     */
    public static boolean checkNum(String str) {
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);

            if (c < '0' || c > '9')
                return true;
        }

        return false;
    }

    /**
     * 此函数用于：将字符串转换为数字
     *
     * @param str 字符串
     * @return -1：转换失败；其他：转换后的数字
     */
    public static int str2Num(String str) {
        int num = 0;

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);

            if (c >= '0' && c <= '9')
                num = num * 10 + (c - '0');
            else
                return -1;
        }

        return num;
    }
}