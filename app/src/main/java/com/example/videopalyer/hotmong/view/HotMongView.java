package com.example.videopalyer.hotmong.view;

import com.example.videopalyer.base.BaseView;
import com.example.videopalyer.bean.HotBean;

public interface HotMongView extends BaseView {
    void onSuccess(HotBean bean);
    void onFail(String error);
}
