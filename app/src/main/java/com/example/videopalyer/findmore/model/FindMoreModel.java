package com.example.videopalyer.findmore.model;

import com.example.mylibrary.callback.ObserverCallBack;
import com.example.mylibrary.utils.HttpUtils;
import com.example.videopalyer.api.ApiManager;
import com.example.videopalyer.api.ApiService;
import com.example.videopalyer.base.ResultCallBack;
import com.example.videopalyer.bean.FindBean;

import java.util.List;

public class FindMoreModel {
    public void findData(final ResultCallBack<List<FindBean>> callBack){
        ApiManager.getApiManager().getService(ApiService.URL)
                .findData()
                .compose(HttpUtils.<List<FindBean>>rxShcdule())
                .subscribe(new ObserverCallBack<List<FindBean>>() {
                @Override
                public void onNext(List<FindBean> list) {
                    callBack.onSuccess(list);
                }

                @Override
                protected void onError(String error) {
                    callBack.onFail(error);
                }
        });
    }
}
