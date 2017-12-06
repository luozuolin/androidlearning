package com.example.luozl.myapplication;


import android.content.ComponentName;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

/**
 * Created by luozl on 2017/12/6.
 */

public class SecondActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second);
        EditText show=(EditText)findViewById(R.id.secondshow);
        ComponentName componentName=getIntent().getComponent();
        show.setText("组件包名称："+componentName.getPackageName()
        +"\n组件类名称："+componentName.getClassName());

    }
}
