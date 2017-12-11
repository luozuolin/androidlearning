package com.example.luozl.myapplication;

import android.Manifest;
import android.content.ComponentName;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by luozl on 2017/12/11.
 */

public class TelePhoneActivity  extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tele();
    }
    final int PICK_CONRACT=0;
    private static final int TAG_PERMISSION = 1023;
    void tele()
    {
        setContentView(R.layout.telephone);
        Button bn=(Button)findViewById(R.id.telebn);
        Button bnhome=(Button)findViewById(R.id.telehome);
        bnhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setAction(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                startActivity(intent);
            }
        });
        bn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("vnd.android.cursor.item/phone");
                startActivityForResult(intent,PICK_CONRACT);
            }
        });
    }
    @Override
    public    void onActivityResult(int requestCode,int resultCode,Intent data)
    {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.READ_CONTACTS)) {
                Toast.makeText(TelePhoneActivity.this, "deny for what???", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(TelePhoneActivity.this, "show the request popupwindow", Toast.LENGTH_SHORT).show();
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_CONTACTS},
                        TAG_PERMISSION);
            }
        } else {
            Toast.makeText(TelePhoneActivity.this, "agreed", Toast.LENGTH_SHORT).show();
            //   getContact();
        }
        String phoneNumber="";
        super.onActivityResult(requestCode,requestCode,data);
       switch (requestCode)
        {
            case (PICK_CONRACT):
                if(resultCode== AppCompatActivity.RESULT_OK)
                {
                    Uri contactdata=data.getData();
                    CursorLoader cursorLoader=new CursorLoader(this,contactdata,null,null,null,null);
                    Cursor cursor=cursorLoader.loadInBackground();
                    if(cursor.moveToFirst())
                    {
                        String contactId=cursor.getString(cursor.getColumnIndex((ContactsContract.Contacts._ID)));
                        String name=cursor.getString(cursor.getColumnIndex((ContactsContract.Contacts.DISPLAY_NAME)));
                        Cursor phones=getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                                null,ContactsContract.CommonDataKinds.Phone.CONTACT_ID+" = "+contactId,null,null);
                        if(phones.moveToFirst())
                        {
                            phoneNumber=phones.getString(phones.getColumnIndex(ContactsContract
                            .CommonDataKinds.Phone.NUMBER));
                        }
                        phones.close();
                        EditText teleshow=(EditText)findViewById(R.id.teleshow);
                        teleshow.setText(name);
                        EditText telephone=(EditText)findViewById(R.id.telephone);
                        telephone.setText(phoneNumber);
                    }
                    cursor.close();
                }
                break;
        }
    }
}
