package com.test.myframework.forty.four;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;
import androidx.lifecycle.OnLifecycleEvent;

import com.test.myframework.R;
import com.test.myframework.utils.ToastUtil;
import com.test.myframework.utils.UIHelper;

/**
 * @Auther baopengjian
 * @Date 2023.03.18 10:06 AM
 * Copyright (c) 2023  鱼包子 All rights reserved.
 */
public class UserDefinedLifecycleActivity  extends Activity implements LifecycleOwner,View.OnClickListener {

    private static final String TAG = "UserDefinedLifecycle";

    private LifecycleRegistry lifecycleRegistry;

    private  Risk risk;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_defined_lifecycle);


        lifecycleRegistry = new LifecycleRegistry(this);
        lifecycleRegistry.setCurrentState(Lifecycle.State.CREATED);

        // 第一种：直接监听
        getLifecycle().addObserver(new LifecycleEventObserver() {
            @Override
            public void onStateChanged(@NonNull LifecycleOwner source, @NonNull Lifecycle.Event event) {
                Log.i(TAG, "onStateChanged event ="+event.name());
            }
        });

        // 第二种：注解监听
        getLifecycle().addObserver(new MyObserver());




        otherRisk();
    }

    /**
     * 监听普通任务生命周期
     */
    private void otherRisk(){
        UIHelper.setOnClickListener(this,this,R.id.tv_start,R.id.tv_end);
        risk = new Risk();
        risk.getLifecycle().addObserver(new LifecycleEventObserver() {
            @Override
            public void onStateChanged(@NonNull LifecycleOwner source, @NonNull Lifecycle.Event event) {
               //注意：若是已经调用了ON_CREATE ，再次执行就不会再调用，除非调用了ON_DESTROY；这种特性可以用来设置必须创建和销毁对应的生命周期任务
                Log.i("RISK", "onStateChanged event ="+event.name());
            }
        });
    }




    @Override
    protected void onResume() {
        super.onResume();
        //  注意：设置了onResume ，ON_PAUSE、onDestroy会执行，注意注册和使用
        lifecycleRegistry.setCurrentState(Lifecycle.State.RESUMED);
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        lifecycleRegistry.setCurrentState(Lifecycle.State.DESTROYED);
    }

    @NonNull
    @Override
    public Lifecycle getLifecycle() {
        return lifecycleRegistry;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_start:
                risk.startRisk();
                break;
            case R.id.tv_end:
                risk.stopRisk();
                break;
            default:
                break;
        }
    }

    public class MyObserver implements LifecycleObserver {



        @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
        public void onCreate() {
            Log.i(TAG, "Lifecycle onCreate生命周期");
        }


        @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
        public void onResume() {
            Log.i(TAG, "Lifecycle onResume生命周期");
        }


        @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
        public void onPause() {
            Log.i(TAG, "Lifecycle onPause生命周期");
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
        protected void onStop() {
            Log.i(TAG,"Lifecycle onStop生命周期 LifecycleRegistry没有设置，但是会调用");
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
        public void onDestory() {
            Log.i(TAG, "Lifecycle onDestory生命周期");
        }

    }
}
