package com.example.mylibrary.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class HttpUtils {
    public static boolean isNetworkAvailable(Context context) {
        if (context!=null){
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo info = cm.getActiveNetworkInfo();
            if (info!=null){
                return info.isAvailable();
            }
        }
        return false;
    }
    //线程切换
    public static<T>ObservableTransformer<T,T>rxShcdule(){
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> upstream) {
                return upstream.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
            }
        };
    }
}
