package com.example.luozl.myapplication;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by luozl on 2017/11/8.
 */

public class XmlMenu extends AppCompatActivity {
    private TextView txt;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu1);
        txt=(TextView)findViewById(R.id.txt);
        registerForContextMenu(txt);
    }
    @Override
    public   boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater=new MenuInflater(this);
        inflater.inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public    void onCreateContextMenu(ContextMenu menu, View source , ContextMenu.ContextMenuInfo menuInfo)
    {
        MenuInflater inflater=new MenuInflater(this);
        inflater.inflate(R.menu.context,menu);
        menu.setHeaderTitle("请选择背景色");
    }
    @Override
    public boolean onContextItemSelected(MenuItem mi)
    {
        mi.setChecked(true);
        switch (mi.getItemId())
        {
            case R.id.red:
                mi.setChecked(true);
                txt.setBackgroundColor(Color.RED);
                break;
            case R.id.blue:
                mi.setChecked(true);
                txt.setBackgroundColor(Color.BLUE);
                break;
            case R.id.green:
                mi.setChecked(true);
                txt.setBackgroundColor(Color.GREEN);
                break;
        }
        return   true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem mi)
    {
        switch (mi.getItemId())
        {
            case R.id.font_10:
                txt.setTextSize(10*2);
                break;
            case R.id.font_12:
                txt.setTextSize(12*2);
                break;
            case R.id.font_14:
                txt.setTextSize(14*2);
                break;
            case R.id.font_16:
                txt.setTextSize(16*2);
                break;
            case R.id.font_18:
                txt.setTextSize(18*2);
                break;
            case R.id.red_font:
                txt.setTextColor(Color.RED);
                break;
            case R.id.green_font:
                txt.setTextColor(Color.GREEN);
                break;
            case R.id.blue_font:
                txt.setTextColor(Color.BLUE);
                break;
            case R.id.plain_item:
                Toast toast=Toast.makeText(XmlMenu.this,
                        "您单击了普通的菜单项",Toast.LENGTH_SHORT);
                toast.show();
                break;
        }
        return  true;
    }
    public   void popupMenu(View v)
    {
        final PopupMenu popupMenu=new PopupMenu(this,v);
        getMenuInflater().inflate(R.menu.popup_menu,popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener()
        {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.exit:
                        popupMenu.dismiss();
                        break;
                    default:
                        Toast toast=Toast.makeText(XmlMenu.this,
                                "您单击了普通的菜单项"+item.getTitle(),Toast.LENGTH_SHORT);
                        toast.show();
                        break;
                }
                return true;
            }
        });
        popupMenu.show();
    }
}
