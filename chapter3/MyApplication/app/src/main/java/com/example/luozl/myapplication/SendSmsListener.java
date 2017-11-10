package com.example.luozl.myapplication;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by luozl on 2017/11/9.
 */

public class SendSmsListener  implements View.OnLongClickListener{
    private Activity  act;
    private EditText  address;
    private   EditText context;
    public SendSmsListener(Activity act, EditText address,EditText  context)
    {
        this.act=act;
        this.address=address;
        this.context=context;
    }

    @Override
    public boolean onLongClick(View v) {
        String addressStr=address.getText().toString();
        String  contextStr=context.getText().toString();
        SmsManager smsManager=SmsManager.getDefault();
        PendingIntent  sendIntent=PendingIntent.getBroadcast(act,0,new Intent(),0);
        smsManager.sendTextMessage(addressStr,null,contextStr,sendIntent,null);
        Toast.makeText(act,"短信发送完成",Toast.LENGTH_SHORT);
        return false;
    }
}
