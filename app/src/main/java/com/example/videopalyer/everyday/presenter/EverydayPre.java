package com.example.videopalyer.everyday.presenter;

import com.example.videopalyer.base.BasePresenter;
import com.example.videopalyer.base.ResultCallBack;
import com.example.videopalyer.bean.DailyBean;
import com.example.videopalyer.everyday.model.DailyModel;
import com.example.videopalyer.everyday.view.EverydayView;

public class EverydayPre extends BasePresenter<EverydayView> implements ResultCallBack<DailyBean> {
    private DailyModel model = new DailyModel();

    public void getData(String url){
        model.getData(url,this);
    }

    @Override
    public void onSuccess(DailyBean bean) {
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
