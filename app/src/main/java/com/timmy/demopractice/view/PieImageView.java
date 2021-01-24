package com.timmy.demopractice.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * 绘制扇形进度控件：绘制一个圆，和其中代表进度的扇形
 * 1。接收自定义属性- 原的颜色，扇形的颜色等
 * 2。初始化Paint（2）
 * 3。onDraw方法中绘制圆形和扇形
 * 在onSizeChange方法中获取到绘制的区域
 */
public class PieImageView extends View {

    private Paint arcPaint;
    private Paint circlePaint;
    private RectF mBound;
    private int radius;

    public PieImageView(Context context) {
        this(context, null);
    }

    public PieImageView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PieImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint(context);
    }

    private void initPaint(Context context) {
        arcPaint = new Paint();
        arcPaint.setAntiAlias(true);
        arcPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        arcPaint.setStrokeWidth(dpTopx(0.1f, context));
        arcPaint.setColor(Color.BLUE);

        circlePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        circlePaint.setStyle(Paint.Style.STROKE);
        circlePaint.setStrokeWidth(dpTopx(2, context));
        circlePaint.setColor(Color.RED);

        mBound = new RectF();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //宽度测量模式
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        //宽度测量大小
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        //高度测量模式
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        //高度测量大小
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        if (MeasureSpec.AT_MOST == widthMode || MeasureSpec.AT_MOST == heightMode) {
            int size = Math.min(widthSize, heightSize);
            setMeasuredDimension(size, size);
        } else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }

    /**
     * 拿到控件的宽高
     * 设置圆形绘制的半径
     * 设置圆形绘制的区域 mBound
     */
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Log.e("Tim", "onSizeChanged w:" + w + " ,h:" + h);
        int min = Math.min(w, h);
        radius = min / 3;
        mBound.set(min / 2 - radius, min / 2 - radius, min / 2 + radius, min / 2 + radius);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.e("Tim", "onDraw");
        canvas.drawCircle(mBound.centerX(), mBound.centerY(), radius, circlePaint);
        canvas.drawArc(mBound, 0, 125, true, arcPaint);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    float density = 0;

    /**
     * dp转px
     */
    private int dpTopx(float dp, Context context) {
        //密度
        if (density == 0) {
            density = context.getResources().getDisplayMetrics().density;
        }
        Log.e("Tim", "density:" + density);
        return (int) (dp * density);
    }
}
