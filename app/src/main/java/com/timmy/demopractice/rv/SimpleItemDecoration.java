package com.timmy.demopractice.rv;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class SimpleItemDecoration extends RecyclerView.ItemDecoration {

    private static final String LOG = SimpleItemDecoration.class.getSimpleName();
    private static final int COUNT = 20;
    private Context context;
    private Paint headPaint;
    private final Paint textPaint;

    public SimpleItemDecoration(Context context) {
        this.context = context;
        headPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        headPaint.setColor(Color.RED);

        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setColor(Color.BLACK);
        textPaint.setTextSize(50);
    }

    //在itemView的位置进行绘制
    //在Canvas中绘制背景和文本
    @Override
    public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDraw(c, parent, state);
        Log.d(LOG, "onDraw 11");
        RecyclerView.Adapter adapter = parent.getAdapter();
        int childCount = parent.getChildCount();
//        int itemCount = adapter.getItemCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = parent.getChildAt(i);
            int top = childAt.getTop();
            if (i % COUNT == 0) {
                int bottom = childAt.getBottom();
//                Log.d(LOG, "top:" + top + " ,bottom:" + bottom);
                c.drawRect(0, top - 100, parent.getWidth(), top, headPaint);
                //绘制文本
                c.drawText("DDDD", 30, 80, textPaint);
            } else {
                c.drawRect(0, top - 2, parent.getWidth(), top, headPaint);
            }
        }
    }

    /**
     * 该方法中复写的控件，覆盖在ItemView上面
     *
     * @param c
     * @param parent
     * @param state
     */
    @Override
    public void onDrawOver(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
        Log.d(LOG, "onDrawOver 22");

        RecyclerView.Adapter adapter = parent.getAdapter();
        LinearLayoutManager layoutManager = (LinearLayoutManager) parent.getLayoutManager();
        int position = layoutManager.findFirstVisibleItemPosition();
        Log.d(LOG, "onDrawOver 22 firstVisibleItemPosition:" + position);
        View itemView = parent.findViewHolderForAdapterPosition(position).itemView;

        if (position % COUNT == 0) {
            c.drawRect(0, 0, parent.getWidth(), 100, headPaint);
            //绘制文本
            c.drawText("DDDD", 30, 80, textPaint);

        } else {
            c.drawRect(0, 0, parent.getWidth(), 100, headPaint);
            //绘制文本
            c.drawText("DDDD", 30, 80, textPaint);
        }
    }

    //对itemView 增加间隔的高度
    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        RecyclerView.Adapter adapter = parent.getAdapter();
        int position = parent.getChildAdapterPosition(view);
        Log.d(LOG, "getItemOffsets position:" + position);
        if (position % COUNT == 0) {
            outRect.set(0, 100, 0, 0);
        } else {
            outRect.set(0, 2, 0, 0);
        }
    }
}
