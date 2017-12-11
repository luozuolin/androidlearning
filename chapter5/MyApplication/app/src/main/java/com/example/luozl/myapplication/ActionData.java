package com.example.luozl.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by luozl on 2017/12/11.
 */

public class ActionData extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actiondata);
        Button btnactiondata=(Button)findViewById(R.id.mainactiondata);
        btnactiondata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actiondata();
            }
        });
        Button btnactionedit=(Button)findViewById(R.id.mainedit);
        btnactionedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                  actionedit();
            }
        });
        Button btnactioncall=(Button)findViewById(R.id.maincall);
        btnactioncall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                   actioncall();
            }
        });
    }
    public    void   actiondata()
    {
        Intent intent=new Intent();
      //  String  data="http://www.crazyit.org";
        String  data="http://www.baidu.com";
        Uri uri=Uri.parse(data);
        intent.setAction(Intent.ACTION_VIEW);
        intent.setData(uri);
        startActivity(intent);
    }
    public void actionedit()
    {
        Intent intent=new Intent();
        intent.setAction(Intent.ACTION_EDIT);
        String data="content://com.android.contacts/contacts/1";
        Uri uri=Uri.parse(data);
        intent.setData(uri);
        startActivity(intent);
    }

    public void actioncall()
    {
        Intent intent=new Intent();
        intent.setAction(Intent.ACTION_DIAL);
        String  data="tel:18618127407";
        Uri uri=Uri.parse(data);
        intent.setData(uri);
        startActivity(intent);

    }
}
