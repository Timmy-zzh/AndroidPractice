package com.timmy.demopractice.scrollview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewParent;
import android.widget.Button;
import android.widget.TextView;

import com.timmy.demopractice.R;

public class ScrollViewActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mTextView;
    private Button mButton1;
    private Button mButton2;
    private Button mButton3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_view);


        mTextView = (TextView) this.findViewById(R.id.tv);

        mButton1 = (Button) this.findViewById(R.id.button_scroll1);
        mButton2 = (Button) this.findViewById(R.id.button_scroll2);
        mButton3 = (Button) this.findViewById(R.id.button_scroll3);
        mButton1.setOnClickListener(this);
        mButton2.setOnClickListener(this);
        mButton3.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_scroll1:
                mTextView.scrollTo(-10, -10);
//                View parent = (View) mTextView.getParent();
//                parent.scrollBy(-10, -10);
                break;
            case R.id.button_scroll2:
                mTextView.scrollBy(-2, -2);
                break;
            case R.id.button_scroll3:
                mTextView.scrollTo(0, 0);
                break;
            default:
                break;
        }
    }
}