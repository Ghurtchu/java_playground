package com.company.effect;

public interface UnsafeExecutable<T, R> {
    R executeWithArg(T arg);
}


