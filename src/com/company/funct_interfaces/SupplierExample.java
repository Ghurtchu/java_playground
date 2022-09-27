package com.company.funct_interfaces;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Supplier;

public class SupplierExample {

    public static void main(String[] args) {

        Supplier<Integer> s = () -> 5;

        Integer integer = s.get();

        AtomicBoolean bool = new AtomicBoolean(false);

        Supplier<Integer> s2 = () -> {
            bool.set(true);
            return 5;
        };


         CompletableFuture<Integer> cf = new CompletableFuture<>();

    }


}
