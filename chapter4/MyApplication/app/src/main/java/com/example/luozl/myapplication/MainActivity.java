package com.example.luozl.myapplication;

import android.app.LauncherActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

public class MainActivity extends LauncherActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this
        ,android.R.layout.simple_list_item_1,names);
        setListAdapter(adapter);
        //setContentView(R.layout.activity_main);
    }
    @Override
    public Intent intentForPosition(int position)
    {
        return  new Intent(MainActivity.this,clazzs[position]);
    }
    String[] names={"设置程序参数","查看星际兵种"};
    Class<?>[] clazzs={PreferenceActivityTest.class,ExpandableListActivityTest.class};
}
