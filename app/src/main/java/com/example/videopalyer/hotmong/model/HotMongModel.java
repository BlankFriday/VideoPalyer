package com.example.videopalyer.hotmong.model;

import com.example.mylibrary.callback.ObserverCallBack;
import com.example.mylibrary.utils.HttpUtils;
import com.example.videopalyer.api.ApiManager;
import com.example.videopalyer.api.ApiService;
import com.example.videopalyer.base.ResultCallBack;
import com.example.videopalyer.bean.HotBean;

public class HotMongModel{
    public void hotData(final ResultCallBack<HotBean> callBack){
        ApiManager.getApiManager().getService(ApiService.URL)
                .hotData()
                .compose(HttpUtils.<HotBean>rxShcdule())
                .subscribe(new ObserverCallBack<HotBean>() {
                    @Override
                    public void onNext(HotBean hotBean) {
                        callBack.onSuccess(hotBean);
                    }

                    @Override
                    protected void onError(String error) {
                        callBack.onFail(error);
                    }
                });
    }
}
