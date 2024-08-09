package com.kkcf.stream;

import java.util.stream.Stream;

public class Demo05 {
    public static void main(String[] args) {
        Stream<String> stream1 = Stream.of("a", "b", "c", "d", "e");

        stream1.forEach(s -> System.out.println(s));

        Stream<Integer> stream2 = Stream.of(new Integer[]{1, 2, 3, 4, 5});

        stream2.forEach(i -> System.out.println(i));
    }
}
