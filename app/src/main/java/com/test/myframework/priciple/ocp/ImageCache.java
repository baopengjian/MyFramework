package com.test.myframework.priciple.ocp;

import android.graphics.Bitmap;

/**
 * OCP :开闭原则
 * 软件中的对象（类、模块、函数等）应该对于扩展是开放的，对于修改是关闭的
 */
public interface ImageCache {

      Bitmap get(String url);

      void put(String url,Bitmap bitmap);

}
