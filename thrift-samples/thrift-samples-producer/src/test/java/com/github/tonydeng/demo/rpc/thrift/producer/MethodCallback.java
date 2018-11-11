package com.github.tonydeng.demo.rpc.thrift.producer;

import lombok.Getter;
import org.apache.thrift.async.AsyncMethodCallback;

@Getter
public class MethodCallback<T> implements AsyncMethodCallback<T> {
    private T response;
    private Exception exception;

    @Override
    public void onComplete(T t) {
        this.response = t;
    }

    @Override
    public void onError(Exception e) {
        this.exception = e;
    }
}
