package com.company;

import java.util.ArrayDeque;
import java.util.Optional;
import java.util.Queue;

public class AnonymousThread {

    private static final Queue<Runnable> queue = new ArrayDeque<>();

    public static void main(String[] args) {

        var t = new Thread() {

            Optional<Runnable> poll() {
                synchronized (queue) {
                    return queue.isEmpty()
                            ? Optional.empty()
                            : Optional.of(queue.poll());
                }
            }

            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    poll().ifPresent(Runnable::run);
                }
            }
        };

        queue.add(() -> System.out.println("Hello there"));
        queue.add(() -> System.out.println("Hey baby"));
        queue.add(() -> System.out.println("Bye baby"));

        t.start();

    }

}
