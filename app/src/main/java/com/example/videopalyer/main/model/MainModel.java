package com.example.videopalyer.main.model;

import com.example.videopalyer.base.ResultCallBack;

public class MainModel {
    public void getData(ResultCallBack<String> callBack){
        callBack.onSuccess("你成功了");
    }
}
