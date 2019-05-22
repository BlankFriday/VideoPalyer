package com.example.videopalyer.findmore.view;

import com.example.videopalyer.base.BaseView;
import com.example.videopalyer.bean.FindBean;

import java.util.List;

public interface FindMoreView extends BaseView {
    void onSuccess(List<FindBean> bean);
    void onFail(String error);
}
