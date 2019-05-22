package com.example.videopalyer.main.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.videopalyer.base.BaseFragment;

import java.util.ArrayList;

public class MainVpAdapter extends FragmentPagerAdapter {
    private ArrayList<Integer> tabs;
    private ArrayList<Fragment> fragments;
    private Context context;

    public MainVpAdapter(FragmentManager fm, ArrayList<Integer> tabs, ArrayList<Fragment> fragments, Context context) {
        super(fm);
        this.tabs = tabs;
        this.fragments = fragments;
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return context.getResources().getString(tabs.get(position));
    }
}
