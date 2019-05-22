package com.example.videopalyer.api;

import com.example.mylibrary.HttpManager;
import com.example.videopalyer.app.BaseApp;

public class ApiManager {
    private static volatile ApiManager apiManager;

    private ApiManager() {
    }

    public static ApiManager getApiManager() {
        if (apiManager == null){
            synchronized (ApiManager.class){
                if (apiManager == null){
                    apiManager = new ApiManager();
                }
            }
        }
        return apiManager;
    }

    public ApiService getService(String baseUrl) {
        return HttpManager.getInstance().getRetrofitClient(baseUrl,BaseApp.getmApp()).create(ApiService.class);
    }

}
