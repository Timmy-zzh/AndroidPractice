package com.timmy.demopractice.touch;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * 查看ViewGroup 事件拦截方法
 */
public class DownInterceptGroup extends FrameLayout {

    private static final String TAG = "DownInterceptGroup";

    public DownInterceptGroup(@NonNull Context context) {
        super(context);
    }

    public DownInterceptGroup(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public DownInterceptGroup(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    //事件分发
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.e(TAG, "dispatchTouchEvent :" + ev.getAction());
        return super.dispatchTouchEvent(ev);
    }

    //事件拦截
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
//        Log.e(TAG, "onInterceptTouchEvent :" + ev.getAction());
        boolean interceptor = super.onInterceptTouchEvent(ev);
        Log.e(TAG, "onInterceptTouchEvent :" + ev.getAction() + " ,interceptor:" + interceptor);
        return interceptor;
    }


}
