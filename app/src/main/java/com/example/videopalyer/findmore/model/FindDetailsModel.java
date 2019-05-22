package com.example.videopalyer.findmore.model;

import com.example.mylibrary.callback.ObserverCallBack;
import com.example.mylibrary.utils.HttpUtils;
import com.example.videopalyer.api.ApiManager;
import com.example.videopalyer.api.ApiService;
import com.example.videopalyer.base.ResultCallBack;
import com.example.videopalyer.bean.FindDetailBean;

public class FindDetailsModel {
    public void getDetail(final ResultCallBack<FindDetailBean> callBack){
        ApiManager.getApiManager().getService(ApiService.URL)
                .detailData()
                .compose(HttpUtils.<FindDetailBean>rxShcdule())
                .subscribe(new ObserverCallBack<FindDetailBean>() {
                    @Override
                    public void onNext(FindDetailBean bean) {
                        callBack.onSuccess(bean);
                    }

                    @Override
                    protected void onError(String error) {
                        callBack.onFail(error);
                    }
                });

    }
}
