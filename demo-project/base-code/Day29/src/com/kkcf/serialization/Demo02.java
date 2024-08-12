package com.kkcf.serialization;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class Demo02 {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Day29/src/com/kkcf/serialization/student.txt"));

        Object o = ois.readObject();
        Student student = (Student) o;
        System.out.println(student);

        ois.close();
    }
}
