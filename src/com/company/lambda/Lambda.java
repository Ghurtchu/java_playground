package com.company.lambda;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Lambda {

    // runnable, consumer, supplier, function, predicate

    Boolean field;

    public static void main(String[] args) {

        Function<Integer, String> intToString = Object::toString;
        Function<String, Boolean> strToBool = s -> s.length() > 5;
        Function<Boolean, Long> boolToLong = b -> {
            if (b)
                return 100L;
            else
                return 5L;
        };

        System.out.println(intToString.andThen(strToBool).andThen(boolToLong).apply(123468));

        System.out.println(boolToLong.compose(strToBool).compose(intToString).apply(123215));

        Supplier<Boolean> s = () -> true;

        Consumer<String> cons = str -> {};

        Predicate<Data> pr = p -> p.x == 5;

        f(pr, new Data(10, 20));
        f(d -> d.x < 10, new Data(100, 100));

    }

    private static boolean f(Predicate<Data> pd, Data argument) {
        return pd.test(argument);
    }

    private static class Data {
        int x;
        int y;

        public Data(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }


}
