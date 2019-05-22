package com.example.videopalyer.hotmong.fragment;


import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.videopalyer.R;
import com.example.videopalyer.base.BaseFragment;
import com.example.videopalyer.bean.HotBean;
import com.example.videopalyer.hotmong.presenter.HotMongPre;
import com.example.videopalyer.hotmong.view.HotMongView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class HotChlidFragment extends BaseFragment<HotMongView, HotMongPre> implements HotMongView{
    @BindView(R.id.recv_child)
    RecyclerView mRecv;
    private static final String TAG = "HotChlidFragment";

    @Override
    protected HotMongPre initPresenter() {
        return new HotMongPre();
    }

    @Override
    protected void initData() {
        super.initData();
        mPresenter.hotData();
    }

    @Override
    protected int createLayoutId() {
        return R.layout.fragment_hot_child;
    }

    @Override
    public void onSuccess(HotBean bean) {
        Log.e(TAG, "onSuccess: "+bean.getItemList().toString() );
    }

    @Override
    public void onFail(String error) {
        Toast.makeText(getContext(), "失败了", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgressBar() {

    }

    @Override
    public void hideProgressBar() {

    }
}
