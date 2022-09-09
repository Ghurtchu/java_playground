package com.company.race_conditions;

public class UnsafeCounter {

    public static void main(String[] args) throws InterruptedException {

        var unsafeCounter = new UnsafeCounter();

        new Thread(() -> {
            for (int i = 0; i < 100_000; i++) {
                unsafeCounter.inc();
            }
        }).start();

        new Thread(() -> {
            for (int i = 0; i < 100_000; i++) {
                unsafeCounter.inc();
            }
        }).start();

        Thread.sleep(500);

        System.out.println(unsafeCounter.get());

    }

    private int count = 0;

    public void inc() {
        // non-atomic operation
        count++;
    }

    public int get() {
        return count;
    }

}
