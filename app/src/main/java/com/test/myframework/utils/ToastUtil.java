package com.test.myframework.utils;

import android.text.TextUtils;
import android.widget.Toast;

import com.test.myframework.MyApplication;


/**
 * 作者 chenli
 * 日期 2017/10/16
 * 描述 系统Toast工具类
 **/

public final class ToastUtil {

    private ToastUtil() {
        // static class
    }

    /**
     * 显示Toast信息
     */
    public static void show(String msg) {
        if (TextUtils.isEmpty(msg)) {
            return;
        }

        Toast toast = Toast.makeText(MyApplication.getInstance(), msg, Toast.LENGTH_SHORT);
        toast.show();
    }

    public static void show(int id) {
        if (id <= 0) {
            return;
        }
        String msg = MyApplication.getInstance().getResources().getString(id);
        if (TextUtils.isEmpty(msg)) {
            return;
        }

        Toast toast = Toast.makeText(MyApplication.getInstance(), msg, Toast.LENGTH_SHORT);
        toast.show();
    }
}
