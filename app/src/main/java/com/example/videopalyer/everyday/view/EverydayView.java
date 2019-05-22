package com.example.videopalyer.everyday.view;

import com.example.videopalyer.base.BaseView;
import com.example.videopalyer.bean.DailyBean;

public interface EverydayView extends BaseView {
    void onSuccess(DailyBean bean);
    void onFail(String error);
}
