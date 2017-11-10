package com.example.luozl.myapplication;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;

/**
 * Created by luozl on 2017/11/9.
 */

public class MyButton extends Button {
    public MyButton(Context context, AttributeSet set)
    {
        super(context,set);
    }
    @Override
    public   boolean onKeyDown(int keyCode,KeyEvent event)
    {
        super.onKeyDown(keyCode,event);
        Log.v("--MyButton--1","the onKeyDown in MyButton");
        return  false;
    }

}
