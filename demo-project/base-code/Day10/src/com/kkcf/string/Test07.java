package com.kkcf.string;

public class Test07 {
    public static void main(String[] args) {
        String talk = "你玩的真好，SB，以后别玩了，CNM，NMSL";

        String[] arr = {"SB", "CNM", "NMSL", "MLGB", "TMD"};

        for (int i = 0; i < arr.length; i++)
            talk = talk.replace(arr[i], "***");

        System.out.println(talk);
    }

}
