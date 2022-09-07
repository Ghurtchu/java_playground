package com.company.effect;

import java.util.function.Consumer;

public interface FallibleConsumer<T, R> {
    Effect<T, R> onFailure(Consumer<Exception> consumer);
}


