    package com.kkcf.methodref;

    import java.util.ArrayList;
    import java.util.List;
    import java.util.function.Function;
    import java.util.stream.Collectors;

    public class Demo03 {
        public static void main(String[] args) {
            ArrayList<String> list = new ArrayList<>(List.of("aaa", "bbb", "ccc", "ddd"));

            List<String> newList = list.stream()
                    .map(String::toUpperCase)
                    .collect(Collectors.toList());

            System.out.println(newList);
        }
    }
