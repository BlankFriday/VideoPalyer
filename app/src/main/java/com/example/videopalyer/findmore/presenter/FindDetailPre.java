package com.example.videopalyer.findmore.presenter;

import com.example.videopalyer.base.BasePresenter;
import com.example.videopalyer.base.ResultCallBack;
import com.example.videopalyer.bean.FindDetailBean;
import com.example.videopalyer.findmore.model.FindDetailsModel;
import com.example.videopalyer.findmore.model.FindMoreModel;
import com.example.videopalyer.findmore.view.FindDetailView;

public class FindDetailPre extends BasePresenter<FindDetailView> implements ResultCallBack<FindDetailBean> {
    private FindDetailsModel model = new FindDetailsModel();

    public void detailData(){
        model.getDetail(this);
    }

    @Override
    public void onSuccess(FindDetailBean bean) {
        mView.onSuccess(bean);
    }

    @Override
    public void onFail(String error) {
        mView.onFail(error);
    }
}
