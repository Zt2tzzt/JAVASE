package com.kkcf.object_riented;

public class Door {
    boolean isOpen = false;

    public void close() {
        isOpen = false;
        System.out.println("门被关上了");
    }
}
