package com.test.myframework.utils;

import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.inputmethodservice.KeyboardView;
import android.os.Build;
import android.text.InputType;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import java.lang.reflect.Method;
import java.util.Date;


public class UIHelper {
	


    public static void show(Dialog loadingDialog){
        if(loadingDialog != null&& !loadingDialog.isShowing()){
            loadingDialog.show();
        }
    }






    public static void showOrHideNativeKeyboard(Context context, boolean isShow, EditText editText){
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        if(isShow){
            imm.showSoftInput(editText, InputMethodManager.SHOW_FORCED);//弹出软键盘时，焦点定位在输入框中
        }else {
            imm.hideSoftInputFromWindow(editText.getWindowToken(), 0); //强制隐藏键盘
        }
    }

    public static void hideNativeKeyboard(Window window, EditText editText){
        if (android.os.Build.VERSION.SDK_INT <= 10) {  //解决sdk4.0以上光标不显示问题
            editText.setInputType(InputType.TYPE_NULL);
        } else {
            if(window != null) {
                window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
            }
            try {
                Class<EditText> cls = EditText.class;
                Method setShowSoftInputOnFocus = cls.getMethod("setShowSoftInputOnFocus", boolean.class);
                setShowSoftInputOnFocus.setAccessible(true);
                setShowSoftInputOnFocus.invoke(editText, false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void showOrHideKeyborad(boolean isShow, KeyboardView keyboardView){
        if (isShow && keyboardView.getVisibility() != View.VISIBLE) {
            keyboardView.setVisibility(View.VISIBLE);
        }else if(!isShow && keyboardView.getVisibility() != View.GONE){
            keyboardView.setVisibility(View.GONE);
        }
    }

    public static int measureText(int defaultSize, int defaultWidth, CharSequence sequence){
        int textSize = defaultSize;
        if(sequence != null) {
            Paint paint = new Paint();
            paint.setTextSize(textSize);
            float width = paint.measureText(sequence.toString());

            while(width >= defaultWidth - 5) {
                --textSize;
                if(textSize <= 0.0F) {
                    break;
                }
                paint.setTextSize(textSize);
                width = paint.measureText(sequence.toString());
            }
        }
        return textSize;
    }

    public static float getTextScaleX(int defaultSize, float dpDefaultWidth, CharSequence sequence){
        float scaleX = 1.0F;
        if(sequence != null) {
            Paint paint = new Paint();
            paint.setTextSize(defaultSize);
            float txtWidth = paint.measureText(sequence.toString());
            if(txtWidth >= dpDefaultWidth){
                scaleX = (dpDefaultWidth - 5) * 1.0F / txtWidth;
            }
        }
        return scaleX;
    }

    public static void setAlpha(float alpha, View... views){
        if(views == null || views.length == 0){
            return;
        }
        for(int i = 0; i < views.length; i++){
            if(views[i] != null) {
                views[i].setAlpha(alpha);
            }
        }
    }

    public static void setBackgroundResource(int colorId, View... views){
        if(views == null || views.length == 0){
            return;
        }
        for(int i = 0; i < views.length; i++){
            if(views[i] != null) {
                views[i].setBackgroundResource(colorId);
            }
        }
    }

    public static void setBackground(Drawable drawable, View... views){
        if(views == null || views.length == 0){
            return;
        }
        for(int i = 0; i < views.length; i++){
            if(views[i] != null) {
                views[i].setBackground(drawable);
            }
        }
    }

    public static void setTextSize(float size, View... views){
        if(views == null || views.length == 0){
            return;
        }
        for(int i = 0; i < views.length; i++){
            if(views[i] != null && views[i] instanceof TextView) {
                ((TextView)views[i]).setTextSize(size);
            }
        }
    }


    public static void setTextColor(int colorId, View... views){
        if(views == null || views.length == 0){
            return;
        }
        for(int i = 0; i < views.length; i++){
            if(views[i] != null && views[i] instanceof TextView) {
                ((TextView)views[i]).setTextColor(colorId);
            }
        }
    }

    public static void setTextColor(ColorStateList colorId, View... views){
        if(views == null || views.length == 0){
            return;
        }
        for(int i = 0; i < views.length; i++){
            if(views[i] != null && views[i] instanceof TextView) {
                ((TextView)views[i]).setTextColor(colorId);
            }
        }
    }

    public static void setText(View view, CharSequence cs){
        if(view != null && view instanceof TextView){
            ((TextView)view).setText(cs);
        }
    }

    public static void setVisible(View... views){
        if(views == null || views.length == 0){
            return;
        }
        for(int i = 0; i < views.length; i++){
            if(views[i] != null) {
                views[i].setVisibility(View.VISIBLE);
            }
        }
    }

    public static void setInvisible(View... views){
        if(views == null || views.length == 0){
            return;
        }
        for(int i = 0; i < views.length; i++){
            if(views[i] != null) {
                views[i].setVisibility(View.INVISIBLE);
            }
        }
    }

    public static void setGone(View... views){
        if(views == null || views.length == 0){
            return;
        }
        for(int i = 0; i < views.length; i++){
            if(views[i] != null) {
                views[i].setVisibility(View.GONE);
            }
        }
    }

    public static void setOnCheckedChangeListener(CheckBox.OnCheckedChangeListener listener, View... views){
        if(views == null || views.length == 0){
            return;
        }
        for(int i = 0; i < views.length; i++){
            if(views[i] != null && views[i] instanceof CheckBox) {
                ((CheckBox)views[i]).setOnCheckedChangeListener(listener);
            }
        }
    }

    public static void setOnClickListener(View.OnClickListener listener, View... views){
        if(views == null || views.length == 0){
            return;
        }
        for(int i = 0; i < views.length; i++){
            if(views[i] != null) {
                views[i].setOnClickListener(listener);
            }
        }
    }

    public static void setOnClickListener(View view, View.OnClickListener listener, int... ids) {
        if (view == null || ids == null || ids.length == 0) {
            return;
        }
        for (int i = 0; i < ids.length; i++) {
            view.findViewById(ids[i]).setOnClickListener(listener);
        }
    }

    public static void setOnClickListener(View.OnClickListener listener, Activity activity, int... ids){
        if(activity == null ||ids == null || ids.length == 0){
            return;
        }
        for(int i = 0; i < ids.length; i++){
            activity.findViewById(ids[i]).setOnClickListener(listener);
        }
    }

    public static void removeOnGlobalLayoutListener(View view, ViewTreeObserver.OnGlobalLayoutListener victim) {
        if(Build.VERSION.SDK_INT < 16) { // Build.VERSION_CODES.JELLY_BEAN
            view.getViewTreeObserver().removeGlobalOnLayoutListener(victim);
        } else {
            view.getViewTreeObserver().removeOnGlobalLayoutListener(victim);
        }
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static void rotationExpandIcon(final ImageView imageView, float from, float to) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            ValueAnimator valueAnimator = ValueAnimator.ofFloat(from, to);//属性动画
            valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

                @Override
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    imageView.setRotation((Float) valueAnimator.getAnimatedValue());
                }
            });
            valueAnimator.start();
        }
    }

    public static void expandLayout(final LinearLayout linearLayout, int from, final int to) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            ValueAnimator animator = ValueAnimator.ofInt(from, to);
            animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

                @Override
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    int intRes = (Integer) valueAnimator.getAnimatedValue();
                    if(intRes > 0 && linearLayout.getVisibility() == View.GONE){
                        linearLayout.setVisibility(View.VISIBLE);
                    }
                    if(intRes == 0 && linearLayout.getVisibility() == View.VISIBLE){
                        linearLayout.setVisibility(View.GONE);
                    }
                    linearLayout.getLayoutParams().width = intRes;
                    linearLayout.requestLayout();
                }
            });
            animator.start();
        }
    }

    public static void expandLayoutFromRight(final View view, final int from, final int to) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            final int max = Math.max(from, to);
            ValueAnimator animator = ValueAnimator.ofInt(from, to);
            animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

                @Override
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    int intRes = (Integer) valueAnimator.getAnimatedValue();
                    if(intRes > 0 && view.getVisibility() == View.GONE){
                        view.setVisibility(View.VISIBLE);
                    }
                    if(intRes == 0 && view.getVisibility() == View.VISIBLE){
                        view.setVisibility(View.GONE);
                    }
                    view.setTranslationX(max - intRes);
                    view.getLayoutParams().width = intRes;
                    view.requestLayout();
                }
            });
            animator.start();
        }
    }

    public static void doBlur(Context context, Bitmap bmp, View view) {
        doBlur(context, 8, 10, bmp, view, view.getMeasuredWidth(), view.getMeasuredHeight());
    }

    public static void doBlur(Context context, Bitmap bmp, View view, int width, int height) {
        doBlur(context, 8, 10, bmp, view, width, height);
    }

    public static void doBlur(Context context, float scale, float radius, Bitmap bmp, View view, int width, int height) {
        if (scale < 1) {
            scale = 8;
        }

        Bitmap overlay = Bitmap.createBitmap((int) (width/scale),
                (int) (height/scale), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(overlay);
        canvas.translate(-view.getLeft()/scale, -view.getTop()/scale);
        canvas.scale(1 / scale, 1 / scale);
        Paint paint = new Paint();
        paint.setFlags(Paint.FILTER_BITMAP_FLAG);
        canvas.drawBitmap(bmp, 0, 0, paint);

        overlay = doBlur(overlay, (int)radius, true);
        view.setBackgroundDrawable(new BitmapDrawable(context.getResources(), overlay));
    }

    public static Bitmap doBlur(Bitmap sentBitmap, int radius, boolean canReuseInBitmap) {
        Bitmap bitmap;
        if (canReuseInBitmap) {
            bitmap = sentBitmap;
        } else {
            bitmap = sentBitmap.copy(sentBitmap.getConfig(), true);
        }

        if (radius < 1) {
            return (null);
        }
        int w = bitmap.getWidth();
        int h = bitmap.getHeight();

        int[] pix = new int[w * h];
        bitmap.getPixels(pix, 0, w, 0, 0, w, h);

        int wm = w - 1;
        int hm = h - 1;
        int wh = w * h;
        int div = radius + radius + 1;

        int r[] = new int[wh];
        int g[] = new int[wh];
        int b[] = new int[wh];
        int rsum, gsum, bsum, x, y, i, p, yp, yi, yw;
        int vmin[] = new int[Math.max(w, h)];

        int divsum = (div + 1) >> 1;
        divsum *= divsum;
        int dv[] = new int[256 * divsum];
        for (i = 0; i < 256 * divsum; i++) {
            dv[i] = (i / divsum);
        }
        yw = yi = 0;

        int[][] stack = new int[div][3];
        int stackpointer;
        int stackstart;
        int[] sir;
        int rbs;
        int r1 = radius + 1;
        int routsum, goutsum, boutsum;
        int rinsum, ginsum, binsum;

        for (y = 0; y < h; y++) {
            rinsum = ginsum = binsum = routsum = goutsum = boutsum = rsum = gsum = bsum = 0;
            for (i = -radius; i <= radius; i++) {
                p = pix[yi + Math.min(wm, Math.max(i, 0))];
                sir = stack[i + radius];
                sir[0] = (p & 0xff0000) >> 16;
                sir[1] = (p & 0x00ff00) >> 8;
                sir[2] = (p & 0x0000ff);
                rbs = r1 - Math.abs(i);
                rsum += sir[0] * rbs;
                gsum += sir[1] * rbs;
                bsum += sir[2] * rbs;
                if (i > 0) {
                    rinsum += sir[0];
                    ginsum += sir[1];
                    binsum += sir[2];
                } else {
                    routsum += sir[0];
                    goutsum += sir[1];
                    boutsum += sir[2];
                }
            }
            stackpointer = radius;

            for (x = 0; x < w; x++) {
                r[yi] = dv[rsum];
                g[yi] = dv[gsum];
                b[yi] = dv[bsum];

                rsum -= routsum;
                gsum -= goutsum;
                bsum -= boutsum;

                stackstart = stackpointer - radius + div;
                sir = stack[stackstart % div];

                routsum -= sir[0];
                goutsum -= sir[1];
                boutsum -= sir[2];

                if (y == 0) {
                    vmin[x] = Math.min(x + radius + 1, wm);
                }
                p = pix[yw + vmin[x]];

                sir[0] = (p & 0xff0000) >> 16;
                sir[1] = (p & 0x00ff00) >> 8;
                sir[2] = (p & 0x0000ff);

                rinsum += sir[0];
                ginsum += sir[1];
                binsum += sir[2];

                rsum += rinsum;
                gsum += ginsum;
                bsum += binsum;

                stackpointer = (stackpointer + 1) % div;
                sir = stack[(stackpointer) % div];

                routsum += sir[0];
                goutsum += sir[1];
                boutsum += sir[2];

                rinsum -= sir[0];
                ginsum -= sir[1];
                binsum -= sir[2];

                yi++;
            }
            yw += w;
        }
        for (x = 0; x < w; x++) {
            rinsum = ginsum = binsum = routsum = goutsum = boutsum = rsum = gsum = bsum = 0;
            yp = -radius * w;
            for (i = -radius; i <= radius; i++) {
                yi = Math.max(0, yp) + x;

                sir = stack[i + radius];

                sir[0] = r[yi];
                sir[1] = g[yi];
                sir[2] = b[yi];

                rbs = r1 - Math.abs(i);

                rsum += r[yi] * rbs;
                gsum += g[yi] * rbs;
                bsum += b[yi] * rbs;

                if (i > 0) {
                    rinsum += sir[0];
                    ginsum += sir[1];
                    binsum += sir[2];
                } else {
                    routsum += sir[0];
                    goutsum += sir[1];
                    boutsum += sir[2];
                }

                if (i < hm) {
                    yp += w;
                }
            }
            yi = x;
            stackpointer = radius;
            for (y = 0; y < h; y++) {
                // Preserve alpha channel: ( 0xff000000 & pix[yi] )
                pix[yi] = (0xff000000 & pix[yi]) | (dv[rsum] << 16) | (dv[gsum] << 8) | dv[bsum];

                rsum -= routsum;
                gsum -= goutsum;
                bsum -= boutsum;

                stackstart = stackpointer - radius + div;
                sir = stack[stackstart % div];

                routsum -= sir[0];
                goutsum -= sir[1];
                boutsum -= sir[2];

                if (x == 0) {
                    vmin[y] = Math.min(y + r1, hm) * w;
                }
                p = x + vmin[y];

                sir[0] = r[p];
                sir[1] = g[p];
                sir[2] = b[p];

                rinsum += sir[0];
                ginsum += sir[1];
                binsum += sir[2];

                rsum += rinsum;
                gsum += ginsum;
                bsum += binsum;

                stackpointer = (stackpointer + 1) % div;
                sir = stack[stackpointer];

                routsum += sir[0];
                goutsum += sir[1];
                boutsum += sir[2];

                rinsum -= sir[0];
                ginsum -= sir[1];
                binsum -= sir[2];

                yi += w;
            }
        }
        bitmap.setPixels(pix, 0, w, 0, 0, w, h);

        return (bitmap);
    }
}
