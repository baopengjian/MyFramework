package com.test.myframework;

import android.app.Application;

/**
 * @Auther baopengjian
 * @Date 2023.1.18 11:28 AM
 * Copyright (c) 2023  鱼包子 All rights reserved.
 */
public class MyApplication extends Application {


    private static MyApplication sInstance;

    public static MyApplication getInstance() {
        return sInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
    }
}
