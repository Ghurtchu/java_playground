package com.company.race_conditions;

import java.util.concurrent.atomic.AtomicInteger;

public class SafeCounterAtomicInteger {

    private AtomicInteger count = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {

        var s = new SafeCounterAtomicInteger();

        new Thread(() -> {
            for (int i = 0; i < 100_000; i++) {
                s.inc();
            }
        }).start();

        new Thread(() -> {
            for (int i = 0; i < 100_000; i++) {
                s.inc();
            }
        }).start();

        Thread.sleep(250);

        System.out.println(s.get());
    }

    public void inc() {
        count.getAndIncrement();
    }

    public int get() {
        return count.get();
    }

}
