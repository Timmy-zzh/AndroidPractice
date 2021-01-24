package com.timmy.demopractice.touch;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * 自定义View，对事件进行捕获
 */
public class CaptureTouchView extends View {

    private static final String TAG = CaptureTouchView.class.getSimpleName();

    public CaptureTouchView(Context context) {
        super(context);
    }

    public CaptureTouchView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CaptureTouchView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    //事件分发
    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        Log.e(TAG, "dispatchTouchEvent :" + event.getAction());
        boolean result = super.dispatchTouchEvent(event);
        Log.e(TAG, "dispatchTouchEvent result is :" + result);
        return result;
    }

    //事件捕获 -返回true，表示事件消耗
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        requestLayout();
        Log.e(TAG, "onTouchEvent :" + event.getAction());
        return true;
    }

}
