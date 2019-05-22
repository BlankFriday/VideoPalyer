package com.example.videopalyer.hotmong.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class VpHotAdapter extends FragmentPagerAdapter {

    private ArrayList<Integer> titles;
    private ArrayList<Fragment> fragments;
    private Context context;

    public VpHotAdapter(FragmentManager fm, ArrayList<Integer> titles, ArrayList<Fragment> fragments, Context context) {
        super(fm);
        this.titles = titles;
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
        return context.getResources().getString(titles.get(position));
    }
}
