package com.company.visibility;

import java.util.HashMap;
import java.util.Map;

public class Showcase {

    public static void main(String[] args) {

        var runnable = new Runnable () {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println("printed");
                }
            }
        };

        var t = new Thread(runnable);

        Runnable r2 = () -> {
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("printed");
            }
        };

        var t2 = new Thread(r2);

        t.start();

    }


}
