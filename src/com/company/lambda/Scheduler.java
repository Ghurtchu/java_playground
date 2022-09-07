package com.company.lambda;

public class Scheduler {

    public static void main(String[] args) {

        var s = new Scheduler();
        s.periodically(1000, () -> System.out.println("This gets printed every 1 second"));

    }

    public void periodically(long duration, Runnable task) {
        new Thread(() -> {
            while (true) {
                ThreadOps.sleep(duration);
                task.run();
            }
        }).start();
    }

    private static class ThreadOps {
        private static void sleep(long duration) {
            try {
                Thread.sleep(duration);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
