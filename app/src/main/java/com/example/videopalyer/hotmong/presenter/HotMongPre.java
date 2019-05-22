package com.example.videopalyer.hotmong.presenter;

import com.example.videopalyer.base.BasePresenter;
import com.example.videopalyer.base.ResultCallBack;
import com.example.videopalyer.bean.HotBean;
import com.example.videopalyer.everyday.view.EverydayView;
import com.example.videopalyer.findmore.view.FindMoreView;
import com.example.videopalyer.hotmong.model.HotMongModel;
import com.example.videopalyer.hotmong.view.HotMongView;

public class HotMongPre extends BasePresenter<HotMongView> implements ResultCallBack<HotBean> {

    private HotMongModel mongModel = new HotMongModel();

    public void hotData(){
        mongModel.hotData(this);
    }

    @Override
    public void onSuccess(HotBean bean) {
        mView.onSuccess(bean);
    }

    @Override
    public void onFail(String error) {
        mView.onFail(error);
    }
}
