package com.example.videopalyer.base;

import android.view.View;

public abstract class BaseActivity<V,P extends BasePresenter<V>> extends SimpleActivity {


    public P mPresnter;


    @Override
    public void createView(View view) {
        mPresnter = initPresenter();
        if (mPresnter!=null){
            mPresnter.attachView((V)this);
        }
    }

    protected abstract P initPresenter();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresnter==null){
            mPresnter.deachView();
            mPresnter = null;
        }
    }
}
