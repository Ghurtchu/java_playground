package com.company.effect;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {

        Effect<Integer, List<Data>> pipeline = Effect.fromFunction((Integer num) -> num + 1)
                .compose(Effect.fromFunction(Object::toString))
                .compose(Effect.fromFunction(txt -> txt.concat(txt)))
                .compose(Effect.fromFunction(Data::new))
                .compose(Effect.fromFunction(instance -> Stream
                        .iterate(0, i -> i + 1)
                        .limit(10)
                        .map(num -> new Data(instance.inner + num))
                        .collect(Collectors.toList())))
                .onSuccess(num -> System.out.println("mapped num to Data successfully: " + num))
                .onFailure(err -> System.out.println("Failed due to: " + err));

        pipeline.executeWithArg(new java.util.Random().nextInt(10));

    }

    private static class Data {
        private final String inner;
        private Data(String s) {
            this.inner = s;
        }

        @Override
        public String toString() {
            return "Data{" +
                    "s='" + inner + '\'' +
                    '}';
        }
    }
}






