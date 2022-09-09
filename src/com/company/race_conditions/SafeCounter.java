package com.company.race_conditions;

public class SafeCounter {

    public static void main(String[] args) throws InterruptedException {

        var safeCounter = new SafeCounter();

        new Thread(() -> {
            for (int i = 0; i < 100_000; i++) {
                safeCounter.inc();
            }
        }).start();

        new Thread(() -> {
            for (int i = 0; i < 100_000; i++) {
                safeCounter.inc();
            }
        }).start();

        Thread.sleep(250);

        System.out.println(safeCounter.get());

    }

    private volatile int count = 0;

    // atomic operation
    public synchronized void inc() {
        count++;
    }

    public int get() {
        return count;
    }

}
