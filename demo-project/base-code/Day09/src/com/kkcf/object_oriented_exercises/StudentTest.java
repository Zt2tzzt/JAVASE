package com.kkcf.object_oriented_exercises;

public class StudentTest {
    public static void main(String[] args) {
        // 1.创建学生数组
        Student[] stus = new Student[3];

        // 2.创建学生对象，并添加进数组
        stus[0] = new Student(1, "张三", 18);
        stus[1] = new Student(2, "李四", 19);
        stus[2] = new Student(3, "王五", 20);

        // 3.再次添加一个学生对象，并在添加的时候进行学号的唯一性判断。
        Student stu4 = new Student(4, "赵六", 21);

        // 唯一性判断，学号重复，不用添加进数组
        boolean flag1 = isOnly(stus, stu4.getNo());
        if (!flag1) {
            System.out.println("学号重复，添加失败");
            return;
        }

        // 判断数组是否已满，不满，则添加
        int count = getCount(stus);
        boolean flag2 = count == stus.length;
        if (!flag2) {
            stus[count] = stu4;
            printStudents(stus);
            return;
        }

        // 判断数组是否已满，已满，则创建一个新数组，并将原数组中的元素复制到新数组中
        stus =  createStudentArr(stus);
        stus[count] = stu4;
        printStudents(stus);

        // 通过 id 删除学生信息；如果存在，则删除，如果不存在，则提示删除失败。
        int index1 = getIndex(stus, 4);
        if (index1 == -1) {
            System.out.println("删除失败");
            return;
        }
        stus[index1] = null;
        printStudents(stus);
        
        // 查询数组 id 为“2”的学生，如果存在，则将他的年龄 +1 岁
        int index2 = getIndex(stus, 2);
        if (index2 == -1) {
            System.out.println("查询失败");
            return;
        }
        stus[index2].setAge(stus[index2].getAge() + 1);
        printStudents(stus);
    }

    /**
     * 此函数用于，判断学号是否唯一
     * @param stus
     * @param id
     * @return
     */
    public static boolean isOnly(Student[] stus, int id) {
        for (int i = 0; i < stus.length; i++) {
            if (stus[i] != null && stus[i].getNo() == id) {
                return false;
            }
        }
        return true;
    }

    /**
     * 此函数用于，获取学生人数
     * @param stus
     * @return
     */
    public static int getCount(Student[] stus) {
        int count = 0;

        for (int i = 0; i < stus.length; i++) {
            if (stus[i] != null) count++;
        }

        return count;
    }

    /**
     * 此函数用于，创建一个新数组，并将原数组中的元素复制到新数组中
     * @param oldArr
     * @return
     */
    public static Student[] createStudentArr(Student[] oldArr) {
        Student[] newArr = new Student[oldArr.length + 1];

        for (int i = 0; i < oldArr.length; i++) {
            newArr[i] = oldArr[i];
        }

        System.out.println("创建新数组成功");
        return newArr;
    }

    /**
     * 此函数用于，打印学生信息
     * @param stus
     */
    public static void printStudents(Student[] stus) {
        System.out.println("=====================遍历开始=====================");
        for (int i = 0; i < stus.length; i++) {
            if (stus[i] != null) System.out.println(stus[i].toString());
        }
        System.out.println("=====================遍历结束=====================");
    }

    /**
     * 此函数用于，获取学生索引
     * @param stus
     * @param no
     * @return
     */
    public static int getIndex(Student[] stus, int no) {
        for (int i = 0; i < stus.length; i++) {
            if (stus[i] != null && stus[i].getNo() == no) {
                return i;
            }
        }
        return -1;
    }
}
