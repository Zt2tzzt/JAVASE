package com.kkcf.innerclass;

public class Car {
    private String carName;
    int carAge;
    String carColor;

    public void show() {
        Engin e = new Engin();
        System.out.println(carName + " power by " + e.engineName);
    }

    class Engin {
        String engineName;
        String engineAge;

        public void show() {
            System.out.println(carName + " power by  " + engineName);
        }
    }
}
