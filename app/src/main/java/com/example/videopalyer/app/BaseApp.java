package com.example.videopalyer.app;

import android.app.Application;

public class BaseApp extends Application {
    private static BaseApp mApp;

    @Override
    public void onCreate() {
        super.onCreate();
        mApp = this;
    }

    public static BaseApp getmApp() {
        return mApp;
    }
}
