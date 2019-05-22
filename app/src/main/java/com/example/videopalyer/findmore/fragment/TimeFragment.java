package com.example.videopalyer.findmore.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.videopalyer.R;
import com.example.videopalyer.base.BaseFragment;
import com.example.videopalyer.bean.FindDetailBean;
import com.example.videopalyer.findmore.adapter.RlvFindDteailAdapter;
import com.example.videopalyer.findmore.presenter.FindDetailPre;
import com.example.videopalyer.findmore.view.FindDetailView;
import com.example.videopalyer.video.VideoDetailActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class TimeFragment extends BaseFragment<FindDetailView, FindDetailPre>implements FindDetailView {
    @BindView(R.id.recv_time)
    RecyclerView mRecv;
    private ArrayList<FindDetailBean.ItemListBeanX.DataBeanX.ItemListBean> list;
    private RlvFindDteailAdapter adapter;

    @Override
    protected FindDetailPre initPresenter() {
        return new FindDetailPre();
    }

    @Override
    protected int createLayoutId() {
        return R.layout.fragment_time;
    }

    @Override
    protected void initView() {
        super.initView();
        list = new ArrayList<>();
        adapter = new RlvFindDteailAdapter(list, getContext());
        mRecv.setAdapter(adapter);
        mRecv.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter.setItemOnClick(new RlvFindDteailAdapter.ItemOnClick() {
            @Override
            public void onClick(int position, FindDetailBean.ItemListBeanX.DataBeanX.ItemListBean bean,String str) {
                Intent intent = new Intent(getContext(), VideoDetailActivity.class);

                Bundle bundle = new Bundle();

                FindDetailBean.ItemListBeanX.DataBeanX.ItemListBean.DataBean data = bean.getData();

                bundle.putString("video", data.getPlayUrl());
                bundle.putString("title", data.getTitle());
                bundle.putString("category", str);
                bundle.putString("desc", data.getDescription());
                bundle.putString("datemail", data.getCover().getFeed());
                bundle.putString("blurred", data.getCover().getBlurred());//模糊图片地址
                bundle.putInt("collect", data.getConsumption().getCollectionCount());//收藏量
                bundle.putInt("share", data.getConsumption().getShareCount());//分享量
                bundle.putInt("reply", data.getConsumption().getReplyCount());//回复数量

                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void initData() {
        super.initData();
        mPresenter.detailData();
    }


    @Override
    public void onSuccess(FindDetailBean bean) {
        List<FindDetailBean.ItemListBeanX> itemList = bean.getItemList();
        for (int i = 0; i < itemList.size(); i++) {
            list.addAll(itemList.get(i).getData().getItemList());
        }
        adapter.setNewList(list);
        adapter.notifyDataSetChanged();
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
}
