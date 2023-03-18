package com.test.myframework.forty.four;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

import com.test.myframework.R;

/**
 * 移动架构44_Lifecycle与生命周期
 *
 * 因为ComponentActivity、FragmentActivity的的父类SupportActivity、android.support.v4.app.Fragment 均实现了 LifecycleOwner接口，getLifecycle是接口的方法，所以可以进行注册
 *
 */
public class LifecycleActivity extends AppCompatActivity {

    private static final String TAG = "LifecycleActivity";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lifecycle);

        getLifecycle().addObserver(new MyObserver());
    }

    public class MyObserver implements LifecycleObserver {

        @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
        public void onCreate() {
            Log.i(TAG, "Lifecycle onCreate生命周期");
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_START)
        public void onStart() {
            Log.i(TAG, "Lifecycle onStart生命周期");
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
            Log.i(TAG,"Lifecycle onStop生命周期");
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
        public void onDestory() {
            Log.i(TAG, "Lifecycle onDestory生命周期");
        }

    }

    public void defined(View v){
        startActivity(new Intent(LifecycleActivity.this,UserDefinedLifecycleActivity.class));
    }


}
