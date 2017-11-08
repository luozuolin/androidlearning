package com.example.luozl.myapplication;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


/**
 * Created by luozl on 2017/11/8.
 */

public class MenuActivity extends AppCompatActivity {
    final int MENU1=0x111;
    final int MENU2=0x112;
    final int MENU3=0x113;
    private TextView txt;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu1);
        txt=(TextView)findViewById(R.id.txt);
        registerForContextMenu(txt);
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View source,ContextMenu.ContextMenuInfo menuInfo)
    {
        menu.add(0,MENU1,0,"红色");
        menu.add(0,MENU2,0,"绿色");
        menu.add(0,MENU3,0,"蓝色");
        menu.setGroupCheckable(0,true,true);
        menu.setHeaderTitle("选择背景色");
    }
    @Override
    public  boolean onContextItemSelected(MenuItem mi)
    {
        switch (mi.getItemId())
        {
            case MENU1:
                mi.setChecked(true);
                txt.setBackgroundColor(Color.RED);
                break;
            case MENU2:
                mi.setChecked(true);
                txt.setBackgroundColor(Color.GREEN);
                break;
            case MENU3:
                mi.setChecked(true);
                txt.setBackgroundColor(Color.BLUE);
                break;
        }
        return   true;
    }
}
