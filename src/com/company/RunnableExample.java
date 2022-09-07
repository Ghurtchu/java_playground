package com.company;

import java.util.concurrent.*;

public class RunnableExample {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

//
//        Thread t2 = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("anonymous class");
//            }
//        });
//
//        Thread t3 = new Thread(() -> System.out.println("lambda expression"));

        ExecutorService executorService = Executors.newFixedThreadPool(8);
        Future<Integer> submit = executorService.submit(new MyCallable(5));

        Integer result = null;

        // busy wait
        while(!submit.isDone()){
            result = submit.get();
        }

        System.out.println(result);



    }

    private static class MyRunnable implements Runnable {
        @Override
        public void run() {
            System.out.println("My runnable is running");
        }
    }

    private static class MyCallable implements Callable<Integer> {

        private final Integer value;

        public MyCallable(Integer value) {
            this.value = value;
        }

        @Override
        public Integer call() throws Exception {
            System.out.println("It's running");
            System.out.println("sleeping...");
            Thread.sleep(2000);
            System.out.println("returning");
            return new java.util.Random().nextInt(value);
        }
    }
}
