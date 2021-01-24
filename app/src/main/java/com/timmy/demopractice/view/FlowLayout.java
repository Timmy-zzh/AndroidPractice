package com.timmy.demopractice.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class FlowLayout extends ViewGroup {

    //所有行，每一行包括的子view
    private List<List<View>> allViews;
    //所有行的高度集合
    private List<Integer> lineMaxHeights;

    public FlowLayout(Context context) {
        this(context, null);
    }

    public FlowLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        allViews = new ArrayList<>();
        lineMaxHeights = new ArrayList<>();
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }

    @Override
    protected LayoutParams generateLayoutParams(LayoutParams p) {
        return new MarginLayoutParams(p);
    }

    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return new MarginLayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
    }

    /**
     * 测量：先遍历计算子View的大小，然后最终计算得出ViewGroup的大小
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //获取到父类传递过来的宽高和测量模式
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        Log.e("Tim", "onMeasure widthMode:" + widthMode + " ,widthSize:" + widthSize);
        Log.e("Tim", "heightMode:" + heightMode + " ,heightSize:" + heightSize);

        //子View个数
        int childCount = getChildCount();
        //当前行计算的宽度
        int lineWidth = 0;
        //当前行最高的高度
        int lineMaxHeight = 0;
        //累加的高度
        int totalHeight = 0;

        for (int i = 0; i < childCount; i++) {
            View childView = getChildAt(i);
            //todo 先测量子View，再拿到子View的宽高
            measureChild(childView, widthMeasureSpec, heightMeasureSpec);

            MarginLayoutParams lp = (MarginLayoutParams) childView.getLayoutParams();
            int childWidth = childView.getMeasuredWidth() + lp.leftMargin + lp.rightMargin;
            int childHeight = childView.getMeasuredHeight() + lp.topMargin + lp.bottomMargin;

            //判断一行子view累加宽度 是否超过 父容器规定的宽度
            if (lineWidth + childWidth > widthSize) {
                //超过最大宽度，另外起一行
                lineWidth = childWidth;
                //累加后的totalHeight 等于上面所有行的总高度，不包括当前行的高度
                totalHeight += lineMaxHeight;
                lineMaxHeight = childHeight;
            } else {
                //未超过最大宽度，宽度累加，高度选最大值
                lineWidth += childWidth;
                lineMaxHeight = Math.max(lineMaxHeight, childHeight);
            }

            //最后一个子view特殊处理，需要加上当前行的高度
            if (i == childCount - 1) {
                totalHeight += lineMaxHeight;
            }
        }

        //设置最终的宽高
        heightSize = heightMode == MeasureSpec.EXACTLY ? heightSize : totalHeight;
        setMeasuredDimension(widthSize, heightSize);
    }

    /**
     * 摆放子view
     * 1。遍历拿到每一个childView的宽高，并计算每一行的宽高
     * 2。二次遍历，调用childView的layout方法进行子view的摆放
     */
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        allViews.clear();
        lineMaxHeights.clear();

        //保存每一行的子view
        List<View> lineViews = new ArrayList<>();
        //每一行的宽度
        int lineTotalWidth = 0;
        //每行最高的高度
        int lineMaxHeight = 0;
        int childCount = getChildCount();

        Log.e("Tim", "onLayout getMeasuredWidth():" + getMeasuredWidth());
        for (int i = 0; i < childCount; i++) {
            View childView = getChildAt(i);
            // 获取到ChildView的宽高
            MarginLayoutParams lp = (MarginLayoutParams) childView.getLayoutParams();
            int childWidth = childView.getMeasuredWidth() + lp.leftMargin + lp.rightMargin;
            int childHeight = childView.getMeasuredHeight() + lp.topMargin + lp.bottomMargin;

            if (lineTotalWidth + childWidth > getMeasuredWidth()) {
                //超过一行宽度，将前一行的所有view添加到allViews，高度添加到集合中
                allViews.add(lineViews);
                lineMaxHeights.add(lineMaxHeight);
                //开启新一行
                lineMaxHeight = 0;
                lineTotalWidth = 0;
                lineViews = new ArrayList<>();
            }
            lineViews.add(childView);
            lineTotalWidth += childWidth;
            lineMaxHeight = Math.max(lineMaxHeight, childHeight);
        }
        //最后一行处理
        allViews.add(lineViews);
        lineMaxHeights.add(lineMaxHeight);

        /**
         * 遍历allViews，lineMaxHeights 拿到每一行的views和高度，进行子view进行摆放
         */
        int mTop = 0;
        int mLeft = 0;
        for (int i = 0; i < allViews.size(); i++) {
            List<View> oneLineViews = allViews.get(i);
            Integer oneLineMaxHeight = lineMaxHeights.get(i);

            //遍历每一行的views
            for (int j = 0; j < oneLineViews.size(); j++) {
                View childView = oneLineViews.get(j);
                MarginLayoutParams lp = (MarginLayoutParams) childView.getLayoutParams();

                int left = mLeft + lp.leftMargin;
                int top = mTop + lp.topMargin;
                int right = left + childView.getMeasuredWidth();
                int bottom = top + childView.getMeasuredHeight();

                childView.layout(left, top, right, bottom);
                mLeft += childView.getMeasuredWidth() + lp.leftMargin + lp.rightMargin;
            }
            mLeft = 0;
            mTop += oneLineMaxHeight;
        }
    }
}
