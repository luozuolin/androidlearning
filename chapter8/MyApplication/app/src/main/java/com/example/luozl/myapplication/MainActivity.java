package com.example.luozl.myapplication;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharepreference();
    }
    SharedPreferences preferences;
    SharedPreferences preferencescount;
    SharedPreferences.Editor editor;
    public   void sharepreference()
    {
        setContentView(R.layout.sharepreference);
        preferences=getSharedPreferences("crazyit",MODE_PRIVATE);
        editor=preferences.edit();
        Button read=(Button)findViewById(R.id.shareread);
        Button write=(Button)findViewById(R.id.sharewrite);
        Button count=(Button)findViewById(R.id.sharecount);
        read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String time=preferences.getString("time",null);
                int randNum=preferences.getInt("random",0);
                String result=time==null?"您暂时还未写入数据":"写入时间为："+time+"\n上次生成的随机数为："+randNum;
                Toast.makeText(MainActivity.this, result, Toast.LENGTH_SHORT).show();
            }
        });
        write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SimpleDateFormat sfd=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                editor.putString("time",sfd.format(new Date()));
                editor.putInt("random",(int)(Math.random()*100));
                editor.commit();
            }
        });
        count.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                preferencescount=getSharedPreferences("count",MODE_PRIVATE);
                int count=preferencescount.getInt("count",0);
                Toast.makeText(MainActivity.this,"程序以前被读取了"+count+"次。",Toast.LENGTH_SHORT).show();
                SharedPreferences.Editor editor=preferencescount.edit();
                editor.putInt("count",++count);
                editor.commit();
            }
        });

    }
}
