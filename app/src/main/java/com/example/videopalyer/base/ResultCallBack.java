package com.example.videopalyer.base;

public interface ResultCallBack<T> {
    void onSuccess(T bean);
    void onFail(String error);
}
