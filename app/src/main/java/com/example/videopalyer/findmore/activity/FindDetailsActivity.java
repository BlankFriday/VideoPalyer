package com.example.videopalyer.findmore.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.example.videopalyer.R;
import com.example.videopalyer.findmore.adapter.VpFindDetailAdapter;
import com.example.videopalyer.findmore.fragment.ShareFragment;
import com.example.videopalyer.findmore.fragment.TimeFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class FindDetailsActivity extends AppCompatActivity {

    @BindView(R.id.tab)
    TabLayout tab;
    @BindView(R.id.vp)
    ViewPager vp;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.ctv)
    TextView ctv;
    private ArrayList<Integer> tabs;
    private ArrayList<Fragment> fragments;
    private Unbinder bind;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_detail);
        bind = ButterKnife.bind(this);

        initToolbar();
        initTabs();
        initFragment();
        initView();

    }

    private void initToolbar() {
        String name = getIntent().getStringExtra("name");
        toolbar.setTitle("");
        ctv.setText(name);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.mipmap.ic_action_back);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initFragment() {
        fragments = new ArrayList<>();
        fragments.add(new TimeFragment());
        fragments.add(new ShareFragment());
    }

    private void initTabs() {
        tabs = new ArrayList<>();
        tabs.add(R.string.time);
        tabs.add(R.string.shareph);
    }

    private void initView() {
        VpFindDetailAdapter adapter = new VpFindDetailAdapter(getSupportFragmentManager(), fragments, tabs, this);
        vp.setAdapter(adapter);
        tab.setupWithViewPager(vp);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (bind==null){
            bind.unbind();
        }
    }
}
