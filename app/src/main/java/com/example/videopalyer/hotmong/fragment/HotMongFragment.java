package com.example.videopalyer.hotmong.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.videopalyer.R;
import com.example.videopalyer.base.BaseFragment;
import com.example.videopalyer.hotmong.adapter.VpHotAdapter;
import com.example.videopalyer.hotmong.presenter.HotMongPre;
import com.example.videopalyer.hotmong.view.HotMongView;
import com.example.videopalyer.widget.CustomViewPager;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class HotMongFragment extends Fragment {
    @BindView(R.id.tab)
    TabLayout tab;
    @BindView(R.id.cvp_hot)
    CustomViewPager cvp;
    private ArrayList<Integer> titles;
    private ArrayList<Fragment> fragments;
    private Unbinder bind;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.fragment_hot, null);
        bind = ButterKnife.bind(this, inflate);
        initTabs();
        initFragment();
        initView();
        return inflate;
    }

    private void initView() {
        VpHotAdapter adapter = new VpHotAdapter(getChildFragmentManager(), titles, fragments, getContext());
        cvp.setAdapter(adapter);
        tab.setupWithViewPager(cvp);
    }

    private void initFragment() {
        fragments = new ArrayList<>();
        for (int i = 0; i < titles.size(); i++) {
            fragments.add(new HotChlidFragment());
        }
    }

    private void initTabs() {
        titles = new ArrayList<>();
        titles.add(R.string.week);
        titles.add(R.string.month);
        titles.add(R.string.total);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (bind==null){
            bind.unbind();
        }
    }
}
