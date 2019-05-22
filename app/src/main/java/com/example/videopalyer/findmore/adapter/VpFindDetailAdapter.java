package com.example.videopalyer.findmore.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class VpFindDetailAdapter extends FragmentPagerAdapter {

    private ArrayList<Fragment> fragments;
    private ArrayList<Integer> tabs;
    private Context context;

    public VpFindDetailAdapter(FragmentManager fm, ArrayList<Fragment> fragments, ArrayList<Integer> tabs, Context context) {
        super(fm);
        this.fragments = fragments;
        this.tabs = tabs;
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
