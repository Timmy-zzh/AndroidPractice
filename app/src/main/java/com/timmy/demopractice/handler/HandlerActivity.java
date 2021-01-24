package com.timmy.demopractice.handler;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;

import com.timmy.demopractice.R;

public class HandlerActivity extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler);
        textView = findViewById(R.id.tv);
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 100) {
                textView.setText("123456");
            }
        }
    };

    public void onClickTest(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                handler.sendEmptyMessageAtTime(100, 0);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                    }
                });

                handler.post(new Runnable() {
                    @Override
                    public void run() {

                    }
                });
            }
        }).start();
    }
}