package com.example.videopalyer.findmore.view;

import com.example.videopalyer.base.BaseView;
import com.example.videopalyer.bean.FindDetailBean;

public interface FindDetailView extends BaseView {
    void onSuccess(FindDetailBean bean);
    void onFail(String error);
}
