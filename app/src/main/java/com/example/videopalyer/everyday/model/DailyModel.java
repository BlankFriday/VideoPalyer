package com.example.videopalyer.everyday.model;

import android.util.Log;
import android.widget.Toast;

import com.example.mylibrary.utils.HttpUtils;
import com.example.videopalyer.api.ApiManager;
import com.example.videopalyer.api.ApiService;
import com.example.videopalyer.base.ResultCallBack;
import com.example.videopalyer.bean.DailyBean;
import com.example.mylibrary.callback.ObserverCallBack;
public class DailyModel {
    private static final String TAG = "DailyModel";
    public void getData(String url,final ResultCallBack<DailyBean> callBack){
        ApiManager.getApiManager().getService(ApiService.URL)
                .getData(url)
                .compose(HttpUtils.<DailyBean>rxShcdule())
                .subscribe(new ObserverCallBack<DailyBean>() {
                    @Override
                    public void onNext(DailyBean dailyBean) {
                        callBack.onSuccess(dailyBean);
                        Log.e(TAG, "onNext: "+dailyBean.getIssueList().get(0).getItemList().toString());
                    }

                    @Override
                    protected void onError(String error) {
                        callBack.onFail(error);
                    }
                });

    }
}
