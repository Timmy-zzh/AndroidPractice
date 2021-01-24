package com.timmy.demopractice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.timmy.demopractice.activity.FirstActivity;
import com.timmy.demopractice.handler.HandlerActivity;
import com.timmy.demopractice.inflate.LayoutInflateActivity;
import com.timmy.demopractice.rv.RecyclerViewDemoActivity;
import com.timmy.demopractice.scrollview.ScrollViewActivity;
import com.timmy.demopractice.touch.TouchDemoActivity;
import com.timmy.demopractice.view.CustomeViewActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_view1).setOnClickListener(this);
        findViewById(R.id.btn_touch).setOnClickListener(this);
        findViewById(R.id.btn_scroll).setOnClickListener(this);
        findViewById(R.id.recycler_view).setOnClickListener(this);
        findViewById(R.id.layout_inflate).setOnClickListener(this);
        findViewById(R.id.btn_handler).setOnClickListener(this);
        findViewById(R.id.btn_activity).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_view1:
                startActivity(new Intent(MainActivity.this, CustomeViewActivity.class));
                break;
            case R.id.btn_touch:
                startActivity(new Intent(MainActivity.this, TouchDemoActivity.class));
                break;
            case R.id.btn_scroll:
                startActivity(new Intent(MainActivity.this, ScrollViewActivity.class));
                break;
            case R.id.recycler_view:
                startActivity(new Intent(MainActivity.this, RecyclerViewDemoActivity.class));
                break;
            case R.id.layout_inflate:
                startActivity(new Intent(MainActivity.this, LayoutInflateActivity.class));
                break;
            case R.id.btn_handler:
                startActivity(new Intent(MainActivity.this, HandlerActivity.class));
                break;
            case R.id.btn_activity:
                startActivity(new Intent(MainActivity.this, FirstActivity.class));
                break;
        }
    }
}