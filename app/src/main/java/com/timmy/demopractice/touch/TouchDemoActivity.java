package com.timmy.demopractice.touch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.timmy.demopractice.R;

public class TouchDemoActivity extends AppCompatActivity {

    private InterceptScrollView scrollView;
    private TextView tvScroll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_touch_demo);
        setContentView(R.layout.activity_touch_demo2);
        initView();
    }

    private void initView() {
        scrollView = findViewById(R.id.scroll_view);
        tvScroll = findViewById(R.id.tv_scroll);

        tvScroll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float scrollY = scrollView.getScrollY();
                tvScroll.setText("scrollY:" + scrollY);
            }
        });
    }
}