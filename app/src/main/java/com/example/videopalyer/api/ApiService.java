package com.example.videopalyer.api;

import com.example.videopalyer.bean.DailyBean;
import com.example.videopalyer.bean.FindBean;
import com.example.videopalyer.bean.FindDetailBean;
import com.example.videopalyer.bean.HotBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface ApiService {
    //http://baobab.kaiyanapp.com/api/v2/feed?num=2&udid=26868b32e808498db32fd51fb422d00175e179df&vc=83
    //http://baobab.kaiyanapp.com/api/v2/categories?udid=26868b32e808498db32fd51fb422d00175e179df&vc=83
    //http://baobab.kaiyanapp.com/api/v2/feed?date=1558227600000&num=1
    //http://baobab.kaiyanapp.com/api/v3/ranklist?num=30&strategy=%s&udid=26868b32e808498db32fd51fb422d00175e179df&vc=83
    public String URL = "http://baobab.wandoujia.com/api/";


    @GET
    Observable<DailyBean> getData(@Url String url);

    @GET("v2/categories?udid=26868b32e808498db32fd51fb422d00175e179df&vc=83")
    Observable<List<FindBean>> findData();

    @GET("v4/discovery/category")
    Observable<FindDetailBean> detailData();

        //v3/ranklist?num=30&strategy=%s&udid=26868b32e808498db32fd51fb422d00175e179df&vc=83
    @GET("v3/ranklist?num=30&strategy=%s&udid=26868b32e808498db32fd51fb422d00175e179df&vc=83")
    Observable<HotBean> hotData();
}
