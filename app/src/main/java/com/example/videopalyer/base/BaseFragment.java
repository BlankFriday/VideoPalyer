package com.example.videopalyer.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment<V,P extends BasePresenter<V>> extends Fragment {

    public P mPresenter;
    private Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(createLayoutId(), null);
        unbinder = ButterKnife.bind(this, view);
        mPresenter = initPresenter();
        if (mPresenter!=null){
            mPresenter.attachView((V) this);
        }
        initView();
        initData();

        return view;
    }

    protected abstract P initPresenter();

    protected void initData(){};

    protected void initView(){};

    protected abstract int createLayoutId();


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (unbinder==null&&mPresenter==null){
            unbinder.unbind();
            mPresenter.deachView();
            mPresenter = null;
        }
    }
}
