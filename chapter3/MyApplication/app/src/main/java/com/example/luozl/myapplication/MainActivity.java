package com.example.luozl.myapplication;

import android.Manifest;
import android.content.Context;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //  setContentView(R.layout.main);
        //eventQs();
      //  plane();
        //  sendMsg();
        mybytton();
    }

    public void mybuttonclick(View v)
    {
        Toast toast=Toast.makeText(this,"mybutton",Toast.LENGTH_SHORT);
        toast.show();
    }
    public  void mybytton()
    {
        setContentView(R.layout.mybutton);
        MyButton myButton=(MyButton)findViewById(R.id.mybutton);
        myButton.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                Log.v("--MyButton--1","the onKey in mybuttonclick");
                return true;
            }
        });
    }
    public void sendMsg()
    {
        setContentView(R.layout.sendmsg);
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.SEND_SMS} , 1);
        EditText  address=(EditText)findViewById(R.id.address);
        EditText content=(EditText)findViewById(R.id.content);
        Button button=(Button)findViewById(R.id.sendmsgbtn);
        button.setOnLongClickListener(new SendSmsListener(this,address,content));
    }
    public    void plane()
    {
        //弹出软键盘结束
        final int speed=10;
        // 去掉窗口标题
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // 全屏显示
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        // 创建PlaneView组件
        final PlaneView planeView = new PlaneView(this);
        setContentView(planeView);
       planeView.setBackgroundResource(R.drawable.back);
        // 获取窗口管理器
        WindowManager windowManager = getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        // 获得屏幕宽和高
        display.getMetrics(metrics);
        // 设置飞机的初始位置
      //  planeView.currentX = metrics.widthPixels / 2;
       // planeView.currentY = metrics.heightPixels - 100;
        // 为planeView组件的键盘事件绑定监听器
        planeView.setOnKeyListener(new PlaneView.OnKeyListener()
        {
            @Override
            public boolean onKey(View source, int keyCode, KeyEvent event)
            {
                // 获取由哪个键触发的事件
                switch (event.getKeyCode())
                {
                    // 控制飞机下移
                    case KeyEvent.KEYCODE_S:
                        planeView.currentY += speed;
                        break;
                    // 控制飞机上移
                    case KeyEvent.KEYCODE_W:
                        planeView.currentY -= speed;
                        break;
                    // 控制飞机左移
                    case KeyEvent.KEYCODE_A:
                        planeView.currentX -= speed;
                        break;
                    // 控制飞机右移
                    case KeyEvent.KEYCODE_D:
                        planeView.currentX += speed;
                        break;
                }
                // 通知planeView组件重绘
                planeView.invalidate();
                return true;
            }
        });
        ((InputMethodManager)getSystemService(INPUT_METHOD_SERVICE)).showSoftInput(planeView, 0);
    }

    public void eventQs()
    {
        setContentView(R.layout.main);
        Button bn=(Button)findViewById(R.id.bn);
        bn.setOnClickListener(new MyClickListener());
        Button bn1=(Button)findViewById(R.id.bn1);
        bn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText txt=(EditText)findViewById(R.id.txt);
                txt.setText("触发匿名内部类!");
            }
        });

    }
    class   MyClickListener   implements View.OnClickListener
    {

        @Override
        public void onClick(View v) {
            EditText txt=(EditText)findViewById(R.id.txt);
            txt.setText("bn按钮被点击了!");
        }
    }
}
