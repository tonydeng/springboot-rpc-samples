package com.github.tonydeng.demo.rpc.thrift.producer;

import org.apache.thrift.async.AsyncMethodCallback;

public class MethodCallback<T> implements AsyncMethodCallback<T> {
    private T response;

    @Override
    public void onComplete(T t) {
        this.response = t;
    }

    @Override
    public void onError(Exception e) {

    }

    public T getResponse() {
        return response;
    }
}
