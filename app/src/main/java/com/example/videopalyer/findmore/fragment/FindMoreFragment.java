package com.example.videopalyer.findmore.fragment;


import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.videopalyer.R;
import com.example.videopalyer.base.BaseFragment;
import com.example.videopalyer.bean.FindBean;
import com.example.videopalyer.findmore.activity.FindDetailsActivity;
import com.example.videopalyer.findmore.adapter.GvFindMoreAdapter;
import com.example.videopalyer.findmore.presenter.FindMorePre;
import com.example.videopalyer.findmore.view.FindMoreView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.Unbinder;

public class FindMoreFragment extends BaseFragment<FindMoreView, FindMorePre> implements FindMoreView{
    @BindView(R.id.gv)
    RecyclerView mGv;
    Unbinder unbinder;
    private ArrayList<FindBean> list;

    private static final String TAG = "FindMoreFragment";
    private GvFindMoreAdapter adapter;

    @Override
    protected FindMorePre initPresenter() {
        return new FindMorePre();
    }

    @Override
    protected int createLayoutId() {
        return R.layout.fragment_find;
    }

    @Override
    protected void initView() {
        super.initView();
        list = new ArrayList<>();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        mGv.setLayoutManager(gridLayoutManager);
        adapter = new GvFindMoreAdapter(list, getContext());
        mGv.setAdapter(adapter);

        adapter.setItemOnClick(new GvFindMoreAdapter.ItemOnClick() {
            @Override
            public void onClick(int position, FindBean bean) {
                Intent intent = new Intent(getContext(), FindDetailsActivity.class);
                intent.putExtra("name",bean.getName());
                startActivity(intent);
            }
        });
    }

    @Override
    protected void initData() {
        super.initData();
        mPresenter.findData();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (unbinder!=null){
            unbinder.unbind();
        }
    }

    @Override
    public void onSuccess(List<FindBean> bean) {
        list.addAll(bean);
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
