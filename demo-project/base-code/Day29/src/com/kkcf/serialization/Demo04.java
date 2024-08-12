package com.kkcf.serialization;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class Demo04 {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Day29/src/com/kkcf/serialization/student.txt"));

        Object o = ois.readObject();
        ArrayList<Student> list = (ArrayList<Student>) o;
        list.forEach(System.out::println);

        ois.close();
    }
}
