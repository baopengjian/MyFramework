package com.test.myframework.forty.four;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;

/**
 * @Auther baopengjian
 * @Date 2023.03.18 11:04 AM
 * Copyright (c) 2023  鱼包子 All rights reserved.
 */
public class Risk implements LifecycleOwner {

    private static final String TAG = "RISK";

    private LifecycleRegistry lifecycleRegistry;

    public Risk(){
        lifecycleRegistry = new LifecycleRegistry(this);
    }

    public void startRisk(){
        Log.i(TAG, "Lifecycle startRick");
        lifecycleRegistry.setCurrentState(Lifecycle.State.CREATED);
    }

    public void stopRisk(){
        Log.i(TAG, "Lifecycle stopRisk");
        lifecycleRegistry.setCurrentState(Lifecycle.State.DESTROYED);
    }

    @NonNull
    @Override
    public Lifecycle getLifecycle() {
        return lifecycleRegistry;
    }
}
