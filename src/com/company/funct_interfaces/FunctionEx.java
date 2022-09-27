package com.company.funct_interfaces;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

public class FunctionEx {

    public static void main(String[] args) {

        Function<String, Integer> f1 = Integer::parseInt;
        Function<Integer, List<Integer>> f2 = i -> List.of(i, i + 1, i + 2);
        Function<List<Integer>, Integer> f3 = list -> list.stream().reduce(0, Integer::sum);


        Function<String, Integer> f4 = f1.andThen(f2).andThen(f3);

        System.out.println(f4.apply("5"));

        Consumer<String> c = new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        };

        Consumer<String> s2 = s -> {
            System.out.println(s);
            System.out.println(s);
        };

        Callable<String> cal = new Callable<String>() {

            @Override
            public String call() throws Exception {
                return "str";
            }
        };


    }
}
