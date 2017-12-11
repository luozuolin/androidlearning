package com.example.luozl.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

/**
 * Created by luozl on 2017/12/11.
 */

public class DataTypeOverride extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.datatypeoverride);
    }
    public    void  overrideType(View v)
    {
        Intent intent=new Intent();
        intent.setType("abc/xyz");
        intent.setData(Uri.parse("lee://www.fkjava.org:8888/test"));
        Toast.makeText(this, intent.toString(), Toast.LENGTH_SHORT).show();
    }
    public    void  overrideData(View v)
    {
        Intent intent=new Intent();
        intent.setData(Uri.parse("lee://www.fkjava.org:8888/mypath"));
        intent.setType("abc/xyz");
        Toast.makeText(this, intent.toString(), Toast.LENGTH_SHORT).show();
    }
    public    void  overrideDataAndType(View v)
    {
        Intent intent=new Intent();
        intent.setData(Uri.parse("lee://www.fkjava.org:8888/mypath"));
        intent.setDataAndType(Uri.parse("lee://www.fkjava.org:8888/mypath"),"abc/xyz");
        Toast.makeText(this, intent.toString(), Toast.LENGTH_SHORT).show();

    }
    public void schema(View source)
    {
        Intent intent=new Intent();
        intent.setData(Uri.parse("lee://www.crazyit.org:1234/test"));
        startActivity(intent);
    }
    public void schemeHostPort(View source)
    {
        Intent intent = new Intent();
        // 只设置Intent的Data属性
        intent.setData(Uri.parse("lee://www.fkjava.org:8888/test"));
        startActivity(intent);
    }
}
