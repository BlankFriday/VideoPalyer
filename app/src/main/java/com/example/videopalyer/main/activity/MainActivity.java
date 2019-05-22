package com.example.videopalyer.main.activity;


import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.videopalyer.R;
import com.example.videopalyer.base.BaseActivity;
import com.example.videopalyer.base.BaseFragment;
import com.example.videopalyer.everyday.fragment.EverydayFragment;
import com.example.videopalyer.findmore.fragment.FindMoreFragment;
import com.example.videopalyer.hotmong.fragment.HotMongFragment;
import com.example.videopalyer.main.adapter.MainVpAdapter;
import com.example.videopalyer.main.presenter.MainPresenter;
import com.example.videopalyer.main.view.MainView;
import com.example.videopalyer.utils.MyUtils;
import com.example.videopalyer.utils.StatusBarManager;
import com.example.videopalyer.widget.CustomViewPager;

import java.util.ArrayList;

import butterknife.BindView;

public class MainActivity extends BaseActivity<MainView, MainPresenter> implements MainView {

    @BindView(R.id.tv_left)
    TextView tvLeft;
    @BindView(R.id.ibt_right)
    ImageButton ibtRight;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.cvp)
    CustomViewPager cvp;
    @BindView(R.id.tab)
    TabLayout tab;
    private ArrayList<Integer> tabs;
    private ArrayList<Fragment> fragments;
    public static ViewGroup viewGroup;

    @Override
    protected MainPresenter initPresenter() {
        return new MainPresenter();
    }


    @Override
    protected void initView() {
        super.initView();

        toolbar.setTitle("");
        setSupportActionBar(toolbar);
      /*  int color = ContextCompat.getColor(this, R.color.transport);
        StatusBarManager.setStatusBarColor(this, color);*/

        initTabs();
        initFragment();
        MainVpAdapter vpAdapter = new MainVpAdapter(getSupportFragmentManager(), tabs,fragments,this);
        cvp.setAdapter(vpAdapter);

        tab.setupWithViewPager(cvp);

    }

    private void initFragment() {
        fragments = new ArrayList<>();
        fragments.add(new EverydayFragment());
        fragments.add(new FindMoreFragment());
        fragments.add(new HotMongFragment());
    }

    private void initTabs() {
        tabs = new ArrayList<>();
        tabs.add(R.string.everyday);
        tabs.add(R.string.findmore);
        tabs.add(R.string.hot);
    }

    @Override
    protected void initData() {
        mPresnter.getData();
    }

    @Override
    protected int createLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void onSuccess(String s) {

    }

    @Override
    public void onFail(String s) {

    }

    @Override
    public void showProgressBar() {

    }

    @Override
    public void hideProgressBar() {

    }
}
