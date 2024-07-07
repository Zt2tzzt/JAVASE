package com.kkcf.extendss;

public class Zi extends Fu {
    String name = "zi";

    public void ziShow() {
        String name = "ziShow";
        System.out.println(name); // ziShow
        System.out.println(this.name); // zi
        System.out.println(super.name); // Fu
    }
}
