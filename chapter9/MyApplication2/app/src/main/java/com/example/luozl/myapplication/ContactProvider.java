package com.example.luozl.myapplication;

import android.Manifest;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.net.FileNameMap;
import java.util.ArrayList;

/**
 * Created by luozl on 2017/12/15.
 */

public class ContactProvider extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        contact();
    }
    private  void  contact()
    {
        setContentView(R.layout.contactprovider);
    }
    final ArrayList<String> names=new ArrayList<>();
    final ArrayList<ArrayList<String>> details=new ArrayList<>();
    public   void   search(View view)
    {
        if (Build.VERSION.SDK_INT >= 23) {
            int REQUEST_CODE_CONTACT = 101;
            String[] permissions = {Manifest.permission.READ_CONTACTS,Manifest.permission.WRITE_CONTACTS};
            //验证是否许可权限
            for (String str : permissions) {
                if (this.checkSelfPermission(str) != PackageManager.PERMISSION_GRANTED) {
                    //申请权限
                    this.requestPermissions(permissions, REQUEST_CODE_CONTACT);
                    return;
                }
            }
        }
        StringBuilder sb=new StringBuilder();
        Cursor cursor=getContentResolver().query(ContactsContract.Contacts.CONTENT_URI,null,null,null,null);
        while (cursor.moveToNext())
        {
            String contactId=cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
            String name=cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
            sb.append(name+"\n");
            names.add(name);
            Cursor phones=getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI
            ,null,ContactsContract.CommonDataKinds.Phone.CONTACT_ID+"="+contactId,null,null);
            ArrayList<String> detail=new ArrayList<>();
            while (phones.moveToNext())
            {
                String  phoneNumber=phones.getString(phones.getColumnIndex((ContactsContract
                .CommonDataKinds.Phone.NUMBER)));
                detail.add("电话号码："+phoneNumber);
                sb.append(phoneNumber+"\n");
            }
            phones.close();
            details.add(detail);
        }
        Toast.makeText(ContactProvider.this, sb.toString(),Toast.LENGTH_LONG).show();

    }
    public  void add(View view)
    {
        String name=((EditText)findViewById(R.id.contactname)).getText().toString();
        String phone=((EditText)findViewById(R.id.contactphone)).getText().toString();
        ContentValues values=new ContentValues();
        Uri rawContactUri=getContentResolver().insert(
                ContactsContract.RawContacts.CONTENT_URI,values);
        long rawContactId= ContentUris.parseId(rawContactUri);
        values.clear();
        values.put(ContactsContract.Data.RAW_CONTACT_ID,rawContactId);
        values.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE);
        values.put(ContactsContract.CommonDataKinds.StructuredName.GIVEN_NAME,name);
        getContentResolver().insert(ContactsContract.Data.CONTENT_URI,values);

        values.clear();
        values.put(ContactsContract.Data.RAW_CONTACT_ID,rawContactId);
        values.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE);
        values.put(ContactsContract.CommonDataKinds.StructuredName.GIVEN_NAME,name);
        values.put(ContactsContract.CommonDataKinds.Phone.NUMBER,phone);
        values.put(ContactsContract.CommonDataKinds.Phone.TYPE, ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE);
        getContentResolver().insert(ContactsContract.Data.CONTENT_URI,values);
        values.clear();
        Toast.makeText(ContactProvider.this,"联系人信息添加成功",Toast.LENGTH_SHORT).show();
    }

}
