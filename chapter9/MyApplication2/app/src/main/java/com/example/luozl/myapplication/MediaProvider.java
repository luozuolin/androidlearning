package com.example.luozl.myapplication;

import android.Manifest;
import android.content.ContentProvider;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

/**
 * Created by luozl on 2017/12/15.
 */

public class MediaProvider extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        media();
    }
    public   void media()
    {
        setContentView(R.layout.media);
        if (Build.VERSION.SDK_INT >= 23) {
            int REQUEST_CODE_CONTACT = 101;
            String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE};
            //验证是否许可权限
            for (String str : permissions) {
                if (this.checkSelfPermission(str) != PackageManager.PERMISSION_GRANTED) {
                    //申请权限
                    this.requestPermissions(permissions, REQUEST_CODE_CONTACT);
                    return;
                }
            }
        }
    }
    public void showimages(View view)
    {
        StringBuilder sb=new StringBuilder();
        Cursor cursor=getContentResolver().query(
                MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,null,null,null,null
        );
        while (cursor.moveToNext())
        {
            sb.append(cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DISPLAY_NAME))+"\n");
            sb.append(cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DESCRIPTION))+"\n");
        }
        Toast.makeText(MediaProvider.this,sb.toString(),Toast.LENGTH_SHORT).show();
    }
}
