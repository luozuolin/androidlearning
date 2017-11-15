package com.example.luozl.myapplication;

import android.Manifest;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.renderscript.Script;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.pm.ActivityInfoCompat;
import android.support.v4.content.res.ConfigurationHelper;
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
        //mybytton();
        configuration();
    }
    public   void   configuration()
    {
        setContentView(R.layout.configuration);
        final EditText ori=(EditText)findViewById(R.id.ori);
        final EditText navigation=(EditText)findViewById(R.id.navifation);
        final EditText touch=(EditText)findViewById(R.id.touch);
        final EditText mnc=(EditText)findViewById(R.id.mnc);
        Button btn=(Button)findViewById(R.id.configurationbtn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Configuration cfg=getResources().getConfiguration();
                String screen=cfg.orientation==Configuration.ORIENTATION_LANDSCAPE?"横向屏幕":"竖向屏幕";
                String mncCode=cfg.mnc+"";
                String naviName=cfg.orientation==Configuration.NAVIGATION_NONAV?
                        "没有方向控制":cfg.orientation==Configuration.NAVIGATION_WHEEL?
                        "滚轮方向控制":cfg.orientation==Configuration.NAVIGATION_DPAD?
                        "方向键控制方向":"轨迹球控制方向";
                navigation.setText(naviName);
                String touchName=cfg.touchscreen==Configuration.TOUCHSCREEN_NOTOUCH?
                        "无触摸屏":"支持触摸屏";
                ori.setText(screen);
                mnc.setText(mncCode);
                touch.setText(touchName);
            }
        });
        //修改屏幕方向
        Button  btnchage=(Button)findViewById(R.id.configurationbtnchange);
        btnchage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Configuration config=getResources().getConfiguration();
                if(config.orientation==Configuration.ORIENTATION_LANDSCAPE)
                {
                    MainActivity.this.setRequestedOrientation(
                            ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
                    );
                }
                if(config.orientation==Configuration.ORIENTATION_PORTRAIT)
                {
                    MainActivity.this.setRequestedOrientation(
                            ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
                    );
                }
            }
        });
    }
    @Override
    public   void onConfigurationChanged(Configuration newConfig)
    {
        super.onConfigurationChanged(newConfig);
        String  screen=newConfig.orientation==Configuration.ORIENTATION_LANDSCAPE?
                "横向屏幕":"纵向屏幕";
        Toast.makeText(this,"系统的屏幕方向发生改变"+"\n修改后的屏幕方向为："+screen,
                Toast.LENGTH_SHORT).show();
    }
    //修改测试
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
