package com.example.luozl.myapplication;

import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    final String  actions="com.example.luozl.myapplication.SecondActivity";
    final String  actions_cate="android.intent.category.DEFAULT";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("chapter5提交测试");
        setContentView(R.layout.activity_main);
        Button btn = (Button) findViewById(R.id.bn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             //   component();
               action();
            }
        });


    }

    private void component()
    {
        ComponentName comp = new ComponentName(MainActivity.this, SecondActivity.class);
        Intent intent = new Intent();
        intent.setComponent(comp);
        startActivity(intent);
    }
    public  void action()
    {
        Intent intent=new Intent();
        intent.setAction(actions);
        intent.addCategory(actions_cate);
        startActivity(intent);
    }

}
