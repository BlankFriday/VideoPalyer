package com.example.videopalyer.base;

import java.lang.ref.WeakReference;

public class BasePresenter<V> {

    public V mView;
    public WeakReference<V> weakReference;

    public void attachView(V v){
        weakReference = new WeakReference<V>(v);
        mView = weakReference.get();
    }

    public void deachView(){
        if (weakReference!=null){
            weakReference.clear();
        }
    }

}
