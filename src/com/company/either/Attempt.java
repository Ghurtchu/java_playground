package com.company.either;

import java.nio.charset.StandardCharsets;
import java.util.function.Consumer;
import java.util.function.Function;

interface Executable {
    void execute();
}

public abstract class Attempt<S> implements Executable {

    public static void main(String[] args) {

        Function<byte[], String> byteArrayToString = chars -> new String(chars, StandardCharsets.UTF_8);
        byte[] byteArray = new byte[] {100, 101, 102, 103, 104, 105, 106};

        Attempt<String> stringAttempt = Attempt.create(byteArrayToString, byteArray);
        stringAttempt
                .onSuccess(string -> System.out.println("constructed string = " + string))
                .onFailure(error -> System.out.println(error.getMessage()))
                .execute();

    }

    protected Consumer<Exception> failureConsumer;
    protected Consumer<S>         successConsumer;

    public static <T, S> Attempt<S> create(Function<T, S> function, T argument) {
        try {
            return new Success<>(function.apply(argument));
        } catch (Exception e) {
            return new Failure(e);
        }
    }

    public Attempt<S> onFailure(Consumer<java.lang.Exception> failureConsumer) {
        this.failureConsumer = failureConsumer;
        return this;
    }

    public Attempt<S> onSuccess(Consumer<S> successConsumer) {
        this.successConsumer = successConsumer;
        return this;
    }

    protected abstract void executeSuccess();

    protected abstract void executeFailure();

    @Override
    public void execute() {
        if (this instanceof Success) executeSuccess();
        else executeFailure();
    }

    private static class Success<S> extends Attempt<S> {

        S result;

        private Success(S result) {
            this.result = result;
        }

        @Override
        protected void executeSuccess() {
            if (successConsumer == null) {
                this.successConsumer = res -> System.out.println("Exit code: [SUCCESS]");
            }
            successConsumer.accept(result);
        }

        @Override
        protected void executeFailure() {}
    }

    private static class Failure<S> extends Attempt<Exception> {

        Exception error;

        private Failure(Exception error) {
            this.error = error;
        }

        @Override
        protected void executeSuccess() {}

        @Override
        protected void executeFailure() {
            if (failureConsumer == null) {
                this.failureConsumer = err -> System.out.println("Exit code: [FAILURE]");
            }
            failureConsumer.accept(error);
        }
    }
}
