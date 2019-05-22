package com.example.mylibrary.callback;

import com.example.mylibrary.bean.ApiException;

import io.reactivex.Observer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import retrofit2.HttpException;

public abstract class ObserverCallBack<T> implements Observer<T> {
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    public void onSubscribe(Disposable d) {
        compositeDisposable.add(d);
    }


    @Override
    public void onError(Throwable e) {
        String error = "";
        if (e instanceof ApiException){
            ApiException apiException = (ApiException) e;
            error = e.getMessage();
        }else if (e instanceof HttpException){
            HttpException httpException = (HttpException) e;
            error = e.getMessage();
        }
        onError(error);
        if (compositeDisposable!=null){
            compositeDisposable.clear();
        }
    }

    protected abstract void onError(String error);

    @Override
    public void onComplete() {
        if (compositeDisposable!=null){
            compositeDisposable.clear();
        }
    }
}
