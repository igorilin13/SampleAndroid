package com.example.samplewords.util.function;

@FunctionalInterface
public interface BiConsumer<T, U> {
    void accept(T arg1, U arg2);
}
