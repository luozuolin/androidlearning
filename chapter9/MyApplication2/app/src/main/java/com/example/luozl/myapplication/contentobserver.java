package com.example.luozl.myapplication;

import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by luozl on 2017/12/15.
 */

public class contentobserver extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contentobservice);
        getContentResolver().registerContentObserver(Uri.parse("content://sms"),true,
                new SmsObserver(new Handler()));
    }
    private  final  class SmsObserver extends ContentObserver
    {

        /**
         * Creates a content observer.
         *
         * @param handler The handler to run {@link #onChange} on, or null if none.
         */
        public SmsObserver(Handler handler) {
            super(handler);
        }
        public   void onChange(boolean selfChange)
        {
            Cursor cursor=getContentResolver().query(Uri.parse("content://sms/outbox")
            ,null,null,null,null);
            while (cursor.moveToNext())
            {
                StringBuilder  sb=new StringBuilder();
                sb.append("address=").append(cursor.getString(cursor.getColumnIndex("address")));
                sb.append(";subject=").append(cursor.getString(cursor.getColumnIndex("subject")));
                sb.append(";body=").append(cursor.getString(cursor.getColumnIndex("body")));
                sb.append(";time=").append(cursor.getLong(cursor.getColumnIndex("time")));
                System.out.println(sb.toString());
            }
        }
    }
}

