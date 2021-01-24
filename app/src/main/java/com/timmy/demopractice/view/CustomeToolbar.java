package com.timmy.demopractice.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.timmy.demopractice.R;

/**
 * 自定义ToolBar：
 * 左边返回图片，中间文本，右边图片
 * 1。获取自定义属性
 * 2。设置 文本与左右图片的位置
 */
public class CustomeToolbar extends RelativeLayout {

    private String myTitleText;
    private int myTitleTextColor;
    private float myTitleTextSize;
    private Drawable leftImgDrawable;
    private Drawable rightImgDrawable;

    public CustomeToolbar(Context context) {
        this(context, null);
    }

    public CustomeToolbar(Context context, AttributeSet attrs) {
        super(context, attrs);
//        LayoutInflater.from(context).inflate(R.layout.xxx, this);

        //1。获取自定义属性
        initAttr(context, attrs);
        //2.设置控制的位置
        initView(context);
    }

    private void initAttr(Context context, AttributeSet attrs) {
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.CustomeToolbar);
        myTitleText = ta.getString(R.styleable.CustomeToolbar_myTitleText);
        myTitleTextColor = ta.getColor(R.styleable.CustomeToolbar_myTitleTextColor, Color.BLACK);
        //已由sp转换为px
        myTitleTextSize = ta.getDimension(R.styleable.CustomeToolbar_myTitleTextSize, 14);
//        myTitleTextSize = ta.getDimensionPixelSize(R.styleable.CustomeToolbar_myTitleTextSize, 14);

        leftImgDrawable = ta.getDrawable(R.styleable.CustomeToolbar_lefeImgSrc);
        rightImgDrawable = ta.getDrawable(R.styleable.CustomeToolbar_rightImgSrc);
        ta.recycle();
    }

    private void initView(final Context context) {
        //准备控件
        ImageView leftImg = new ImageView(context);
        ImageView rightImg = new ImageView(context);
        leftImg.setImageDrawable(leftImgDrawable);
        rightImg.setImageDrawable(rightImgDrawable);
        TextView titleTextView = new TextView(context);
        titleTextView.setText(myTitleText);
        titleTextView.setTextColor(myTitleTextColor);
        titleTextView.setTextSize(myTitleTextSize);

        //添加控件，并设置排放规则
        RelativeLayout.LayoutParams leftImgParams = new RelativeLayout.LayoutParams(
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 28, context.getResources().getDisplayMetrics()),
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 28, context.getResources().getDisplayMetrics()));
        leftImgParams.addRule(ALIGN_PARENT_LEFT, TRUE);
        leftImgParams.addRule(CENTER_VERTICAL, TRUE);
        this.addView(leftImg, leftImgParams);

        RelativeLayout.LayoutParams rightImgParams = new RelativeLayout.LayoutParams(
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 48, context.getResources().getDisplayMetrics()),
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 48, context.getResources().getDisplayMetrics()));
        rightImgParams.addRule(ALIGN_PARENT_RIGHT, TRUE);
        rightImgParams.addRule(CENTER_VERTICAL, TRUE);
        this.addView(rightImg, rightImgParams);

        RelativeLayout.LayoutParams titleParams = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        titleParams.addRule(CENTER_IN_PARENT, TRUE);
        this.addView(titleTextView, titleParams);

        //添加点击事件
        leftImg.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "返回", Toast.LENGTH_SHORT).show();
            }
        });
        rightImg.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "右侧图片点击", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
