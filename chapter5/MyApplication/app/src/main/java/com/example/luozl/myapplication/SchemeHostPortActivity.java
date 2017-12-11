package com.example.luozl.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by luozl on 2017/12/11.
 */

public class SchemeHostPortActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        TextView tv = new TextView(this);
        tv.setText("指定Scheme、Host、Port匹配的Activity");
        tv.setTextSize(30);
        setContentView(tv);
    }
}
