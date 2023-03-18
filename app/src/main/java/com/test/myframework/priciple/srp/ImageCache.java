package com.test.myframework.priciple.srp;

import android.graphics.Bitmap;
import android.util.LruCache;

/**
 * Single Responsibility Principle
 * 单一职责原则SRP:就一个类而言，应该仅有一个引起他变化的原因
 */
public class ImageCache {

    LruCache<String, Bitmap> mImageCache;

    public ImageCache(){
        initImageCache();
    }

    private void initImageCache() {
        final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
        final int cacheSize = maxMemory / 4;
        mImageCache = new LruCache<String, Bitmap>(cacheSize) {
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getRowBytes() * value.getHeight() / 1024;
            }
        };
    }

    public void put(String url,Bitmap bitmap){
        mImageCache.put(url,bitmap);
    }

    public Bitmap get(String url){
       return mImageCache.get(url);
    }
}
