package com.company;

import java.util.Arrays;
import java.util.concurrent.Callable;

public class Parallel {

    public static void main(String[] args) {

        var emptyTup = new Tuple<Integer, Integer>();

        var c1Task = new Callable<Integer> () {
            @Override
            public Integer call() throws Exception {
                return new java.util.Random().nextInt(10000);
            }
        };

        var c2Task = new Callable<Integer>() {

            @Override
            public Integer call() throws Exception {
                return 500_000_000;
            }
        };

        var updatedTup = emptyTup.compute(c1Task, c2Task);

        System.out.println(updatedTup);
    }

    private static class Tuple<A, B> implements ComputableInParallel<A, B> {

        A a = null;
        B b = null;

        public Tuple() {

        }

        public Tuple(A a, B b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public Tuple<A, B> compute(Callable<A> cA, Callable<B> cB) {
            var t1 = new Thread(() -> a = CallableOps.get(cA));
            var t2 = new Thread(() -> b = CallableOps.get(cB));
            t1.start();
            t2.start();
            ThreadOps.join(t1, t2);
            return new Tuple<>(a, b);
        }

        @Override
        public String toString() {
            return "Tuple{" +
                    "a=" + a +
                    ", b=" + b +
                    '}';
        }
    }

    interface ComputableInParallel<A, B> {
        Tuple<A, B> compute(Callable<A> cA, Callable<B> cB);
    }

    private static class CallableOps<A> {

        static <A> A get(Callable<A> cA) {
            try {
                return cA.call();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static class ThreadOps {
        static void join(Thread... threads) {
            Arrays.stream(threads).forEach(t -> {
                try {
                    t.join();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
        }
    }

}
