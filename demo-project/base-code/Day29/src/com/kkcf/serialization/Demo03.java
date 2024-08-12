package com.kkcf.serialization;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class Demo03 {
    public static void main(String[] args) throws IOException {
        ArrayList<Student> list = new ArrayList<>(List.of(
                new Student("zhagnsan", 23, "beijing"),
                new Student("lisi", 24, "shanghai"),
                new Student("wangwu", 25, "guangzhou")
        ));

        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Day29/src/com/kkcf/serialization/student.txt"));

        oos.writeObject(list);

        oos.close();
    }
}
