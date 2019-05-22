package com.example.videopalyer.main.activity;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.view.ViewGroup;

import com.example.videopalyer.R;
import com.example.videopalyer.base.BaseActivity;
import com.example.videopalyer.hotmong.presenter.HotMongPre;
import com.example.videopalyer.hotmong.view.HotMongView;
import com.example.videopalyer.utils.MyUtils;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MenuActivity extends BaseActivity<HotMongView, HotMongPre> {
    @BindView(R.id.menubg)
    ViewGroup viewGroup;

    @Override
    protected HotMongPre initPresenter() {
        return new HotMongPre();
    }

    @Override
    protected int createLayoutId() {
        return R.layout.activity_menu;
    }

    @Override
    protected void initView() {
        super.initView();
        MyUtils.setBackground(viewGroup,this);
    }
}
