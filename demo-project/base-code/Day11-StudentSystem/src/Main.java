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
            System.out.println("”请输入您的选择:“");
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

        sc.close();
    }

    /**
     * 此方法用于：添加学生
     *
     * @param stus
     */
    public static void addStu(ArrayList<Student> stus) {
        Scanner sc = new Scanner(System.in);

        // 输入学生 id
        String id = "";
        do {
            System.out.print("请输入学生id:");
            id = sc.next();
            if (findStuId(stus, id) >= 0)
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

            if (!checkNum(ageStr) && str2Num(ageStr) < 100)
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
        System.out.println("”添加学生成功“");
    }

    public static void removeStu(ArrayList<Student> stus) {
        System.out.println("”删除学生“");
    }

    public static void modifyStu(ArrayList<Student> stus) {
        System.out.println("”修改学生“");
    }

    /**
     * 此函数用于，显示学生信息
     *
     * @param stus
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
     * @param stus
     * @param id
     * @return
     */
    public static int findStuId(ArrayList<Student> stus, String id) {
        for (int i = 0; i < stus.size(); i++)
            if (stus.get(i).getId().equals(id))
                return i;

        return -1;
    }

    /**
     * 此方法用于：判断字符串是否由数字组成
     *
     * @param str
     * @return
     */
    public static boolean checkNum(String str) {
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);

            if (c < '0' || c > '9')
                return false;
        }

        return true;
    }

    /**
     * 此函数用于：将字符串转换为数字
     *
     * @param str
     * @return
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