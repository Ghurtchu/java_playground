package com.company.effect;

import java.util.function.Consumer;
import java.util.function.Function;

public class Effect<T, R> implements InfallibleConsumer<T, R>, FallibleConsumer<T, R>,
        UnsafeExecutable<T, R>,Composable<T, R> {

    private final Function<T, R> function;

    private Consumer<R> onSuccessConsumer;
    private Consumer<Exception> onFailureConsumer;

    private Effect(Function<T, R> function) {
        this.function = function;
    }

    public static <T, R> Effect<T, R> fromFunction(Function<T, R> function) {
        return new Effect<>(function);
    }

    @Override
    public R executeWithArg(T arg) {
        R result = null;
        try {
            result = function.apply(arg);
            onSuccessConsumer.accept(result);
        } catch (Exception e) {
            onFailureConsumer.accept(e);
        }
        return result;
    }

    @Override
    public Effect<T, R> onFailure(Consumer<Exception> consumer) {
        this.onFailureConsumer = consumer;
        return this;
    }

    @Override
    public Effect<T, R> onSuccess(Consumer<R> consumer) {
        this.onSuccessConsumer = consumer;
        return this;
    }

    @Override
    public <V> Effect<T, V> compose(Effect<R, V> that) {
        return new Effect<>(function.andThen(that.function));
    }

}




