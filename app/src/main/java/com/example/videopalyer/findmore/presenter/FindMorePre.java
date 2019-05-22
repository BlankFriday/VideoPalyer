package com.example.videopalyer.findmore.presenter;

import com.example.videopalyer.base.BasePresenter;
import com.example.videopalyer.base.ResultCallBack;
import com.example.videopalyer.bean.FindBean;
import com.example.videopalyer.everyday.view.EverydayView;
import com.example.videopalyer.findmore.model.FindMoreModel;
import com.example.videopalyer.findmore.view.FindMoreView;

import java.util.List;

public class FindMorePre extends BasePresenter<FindMoreView> implements ResultCallBack<List<FindBean>> {
    private FindMoreModel moreModel = new FindMoreModel();

    public void findData(){
        moreModel.findData(this);
    }

    @Override
    public void onSuccess(List<FindBean> bean) {
        if (mView!=null){
            mView.onSuccess(bean);
        }
    }

    @Override
    public void onFail(String error) {
        if (mView!=null){
            mView.onFail(error);
        }
    }
}
