package com.example.luozl.helloworld;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public  void clickHandle(View     source)
    {
        TextView tv=(TextView)findViewById(R.id.show);
        tv.setText("Hello   Android-"+new java.util.Date());

    }
}
