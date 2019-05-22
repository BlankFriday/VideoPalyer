package com.example.videopalyer.everyday.fragment;


import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.videopalyer.R;
import com.example.videopalyer.base.BaseFragment;
import com.example.videopalyer.bean.DailyBean;
import com.example.videopalyer.everyday.adapter.RlvDailyAdapter;
import com.example.videopalyer.everyday.presenter.EverydayPre;
import com.example.videopalyer.everyday.view.EverydayView;
import com.example.videopalyer.video.VideoDetailActivity;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class EverydayFragment extends BaseFragment<EverydayView, EverydayPre> implements EverydayView {
    @BindView(R.id.recv_day)
    RecyclerView mRecv;
    @BindView(R.id.srl)
    SmartRefreshLayout mSrl;
    private ArrayList<DailyBean.IssueListBean.ItemListBean> list;
    private RlvDailyAdapter adapter;
    private ArrayList<String> strings;
    private static final String TAG = "EverydayFragment";
    private String url = "v2/feed?num=2&udid=26868b32e808498db32fd51fb422d00175e179df&vc=83";

    @Override
    protected EverydayPre initPresenter() {
        return new EverydayPre();
    }

    @Override
    protected int createLayoutId() {
        return R.layout.fragment_day;
    }

    @Override
    protected void initView() {
        super.initView();
        list = new ArrayList<>();
        strings = new ArrayList<>();
        mRecv.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new RlvDailyAdapter(list, strings, getContext());
        mRecv.setAdapter(adapter);

        adapter.setItemOnClick(new RlvDailyAdapter.ItemOnClick() {
            @Override
            public void onClick(DailyBean.IssueListBean.ItemListBean.DataBean bean, String str) {
                Intent intent = new Intent(getContext(), VideoDetailActivity.class);

                Bundle bundle = new Bundle();

                bundle.putString("video", bean.getPlayUrl());
                bundle.putString("title", bean.getTitle());
                bundle.putString("category", str);
                bundle.putString("desc", bean.getDescription());
                bundle.putString("datemail", bean.getCover().getFeed());
                bundle.putString("blurred", bean.getCover().getBlurred());//模糊图片地址
                bundle.putInt("collect", bean.getConsumption().getCollectionCount());//收藏量
                bundle.putInt("share", bean.getConsumption().getShareCount());//分享量
                bundle.putInt("reply", bean.getConsumption().getReplyCount());//回复数量

                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        //上拉加载下拉刷新
        mSrl.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                strings.add("Weekends special");
                strings.add("Weekends special");
                initData();

            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                strings.clear();
                list.clear();
                initData();
            }
        });
    }

    @Override
    protected void initData() {
        super.initData();
        mPresenter.getData(url);
    }

    @Override
    public void onSuccess(DailyBean bean) {
        strings.add("Weekend special");
        strings.add("Weekend special");
        String nextPageUrl = bean.getNextPageUrl();
        String newUrl = nextPageUrl.substring(32);
        url = newUrl;

        adapter.setTitles(strings);
        list.addAll(bean.getIssueList().get(0).getItemList());
        adapter.setNewList(list);
        adapter.notifyDataSetChanged();
        mSrl.finishRefresh();
        mSrl.finishLoadMore();
    }

    @Override
    public void onFail(String error) {
        Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgressBar() {

    }

    @Override
    public void hideProgressBar() {

    }


    /**
     * 清除滤镜
     */
    private void removeFilter(ImageView imageView) {
        //先获取设置的src图片
        Drawable drawable = imageView.getDrawable();
        //当src图片为Null，获取背景图片
        if (drawable == null) {
            drawable = imageView.getBackground();
        }
        if (drawable != null) {
            //清除滤镜
            drawable.clearColorFilter();
        }
    }

}
