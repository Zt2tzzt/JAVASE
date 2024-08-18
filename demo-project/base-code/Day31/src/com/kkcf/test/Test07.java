package com.kkcf.test;

public class Test07 {
    public static void main(String[] args) {
        RedEnvelopeThread t1 = new RedEnvelopeThread();
        RedEnvelopeThread t2 = new RedEnvelopeThread();
        RedEnvelopeThread t3 = new RedEnvelopeThread();
        RedEnvelopeThread t4 = new RedEnvelopeThread();
        RedEnvelopeThread t5 = new RedEnvelopeThread();

        t1.setName("小A");
        t2.setName("小B");
        t3.setName("小C");
        t4.setName("小D");
        t5.setName("小E");

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
    }
}
