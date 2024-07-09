package com.kkcf.interfacee2;

public class Test {
    public static void main(String[] args) {
        PingPangPlayer ppp = new PingPangPlayer("刘诗雯", 23);
        System.out.println(ppp.getName() + " " + ppp.getAge());
        ppp.learn();
        ppp.speakEnglish();
    }
}
