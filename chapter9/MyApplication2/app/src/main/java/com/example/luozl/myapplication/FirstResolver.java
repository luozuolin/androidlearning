package com.example.luozl.myapplication;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

/**
 * Created by luozl on 2017/12/15.
 */

public class FirstResolver  extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.firstresolver);
        contentResolver=getContentResolver();
    }
    ContentResolver contentResolver;
    Uri uri=Uri.parse("content://com.example.luozl.myapplication.FirstProvider/");
    public   void query(View source)
    {
        Cursor cursor=contentResolver.query(uri,null,"query_where",null,null);
        Toast.makeText(this,"远程ContentProvider返回的Cursor为："+cursor,Toast.LENGTH_SHORT).show();
    }
}
