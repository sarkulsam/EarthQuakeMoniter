package com.project.earthquake.util;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.function.Consumer;

public final class Resource<T> {

    public enum Status {
        SUCCESS,
        LOADING,
        ERROR
    }

    public final Status status;
    public final T data;
    public final Throwable throwable;

    private boolean isConsumed;


    private Resource(Status status, T data, Throwable throwable) {
        this.status = status;
        this.data = data;
        this.throwable = throwable;
        this.isConsumed = false;
    }

    public static <T> Resource<T> success(@NonNull T data) {
        return new Resource<>(Status.SUCCESS, data, null);
    }

    public static <T> Resource<T> loading() {
        return Resource.loading(null);
    }

    public static <T> Resource<T> loading(T data) {
        return new Resource<>(Status.LOADING, data, null);
    }

    public static <T> Resource<T> error(@NonNull Throwable throwable) {
        return Resource.error(null, throwable);
    }

    public static <T> Resource<T> error(T data, @NonNull Throwable throwable) {
        return new Resource<>(Status.ERROR, data, throwable);
    }

    public boolean isSuccess() {
        return status == Status.SUCCESS;
    }

    public boolean isLoading() {
        return status == Status.LOADING;
    }

    public boolean isError() {
        return status == Status.ERROR;
    }

    @MainThread
    @Nullable
    public T consumeData() {
        if (isConsumed || data == null) {
            return null;
        } else {
            isConsumed = true;
            return data;
        }
    }

    @FunctionalInterface
    public interface NonNullConsumer<T> {
        void accept(@NonNull T var1);
    }
}