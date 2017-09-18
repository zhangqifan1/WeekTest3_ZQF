package com.example.anadministrator.catchunknowexception;

import android.app.Application;

public class CrashApplication extends Application {
    @Override  
    public void onCreate() {  
        super.onCreate();
        //初始化
        CrashHandler crashHandler = CrashHandler.getInstance();
        crashHandler.init(getApplicationContext());

    }  
}  