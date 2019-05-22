package com.example.videopalyer.main.presenter;

import com.example.videopalyer.base.BasePresenter;
import com.example.videopalyer.base.ResultCallBack;
import com.example.videopalyer.main.model.MainModel;
import com.example.videopalyer.main.view.MainView;

public class MainPresenter extends BasePresenter<MainView> implements ResultCallBack<String> {

    private MainModel mainModel = new MainModel();

    public void getData(){
        mainModel.getData(this);
    }

    @Override
    public void onSuccess(String bean) {
        mView.onSuccess(bean);
    }

    @Override
    public void onFail(String error) {
        mView.onFail(error);
    }
}
