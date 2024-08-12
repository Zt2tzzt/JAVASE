package com.kkcf.serialization;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class Demo01 {
    public static void main(String[] args) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Day29/src/com/kkcf/serialization/student.txt"));

        Student stu = new Student("wee", 26, "CA");
        oos.writeObject(stu);

        oos.close();
    }
}
