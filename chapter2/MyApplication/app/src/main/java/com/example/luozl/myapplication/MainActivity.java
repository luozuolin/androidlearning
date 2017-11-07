package com.example.luozl.myapplication;

import android.app.Activity;
import android.app.Notification;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.IdRes;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Date;
import java.util.StringTokenizer;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by luozl on 2017/11/6.
 */

public class MainActivity extends Activity {
    int[] images=new int[]{
            R.drawable.bg_top,
            R.drawable.error,
            R.drawable.info_up,
            R.drawable.info
    };
    int currentImg=0;
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_main);
        //activity_images()
        //drawCircle();
       // setContentView(R.layout.table);
      //  frames();
        //grid();
       // edittext();
     //   button();
       // checkbutton();
        listview();
    }
    public   void listview()
    {
        setContentView(R.layout.listview);
    }
    public     void checkbutton()
    {
        setContentView(R.layout.checkbuttons);
        RadioGroup rg=(RadioGroup)findViewById(R.id.rg);
        final TextView show=(TextView)findViewById(R.id.show);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                String tip=checkedId==R.id.male?"您的性别是男人":"您的性别是女人";
                show.setText(tip);
            }
        });
    }
    public   void button()
    {
        setContentView(R.layout.button);
    }
    public   void edittext()
    {
        setContentView(R.layout.edittext);
    }
    public   void textview()
    {
        setContentView(R.layout.textview);
    }
    //android srudio  git  测试
    public void grid()
    {
        GridLayout gridLayout;
        String[] chars=new String[]
                {
                        "7","8","9","÷",
                        "4","5","6","×",
                        "1","2","3","－",
                        ".","0","=","＋"
                };
                setContentView(R.layout.grid);
        gridLayout=(GridLayout)findViewById(R.id.root);
        for(int i=0;i<chars.length;i++)
        {
            Button button=new Button(this);
            button.setText(chars[i]);
            button.setTextSize(40);
            button.setPadding(5,35,5,35);
            GridLayout.Spec rowspec=GridLayout.spec(i/4+2);
            GridLayout.Spec columnspec=GridLayout.spec(i%4);
            GridLayout.LayoutParams params=new GridLayout.LayoutParams(rowspec,columnspec);

            params.setGravity(Gravity.FILL);
            gridLayout.addView(button,params);
        }

    }
    private int currentColor=0;
    final int[] colors=new int[]{
            R.color.color1,
            R.color.color2,
            R.color.color3,
            R.color.color4,
            R.color.color5,
            R.color.color6
    };
    final int[] names=new int[]{
            R.id.view1,
            R.id.view2,
            R.id.view3,
            R.id.view4,
            R.id.view5,
            R.id.view6
    };
    TextView[] views=new TextView[names.length];
    Handler handler=new Handler()
    {
        @Override
        public void handleMessage(Message message)
        {
            if(message.what==0x123)
            {
                for(int i=0;i<names.length;i++)
                {
                    views[i].setBackground(null);
                   // currentColor++;
                }
                views[(currentColor) % names.length].setBackgroundResource(colors[(currentColor) % names.length]);
                currentColor++;
               super.handleMessage(message);
            }
        }
    };
    public void frames()
    {
        setContentView(R.layout.frames);
        for(int i=0;i<names.length;i++)
        {
            views[i]=(TextView)findViewById(names[i]);
        }
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                handler.sendEmptyMessage(0x123);
                System.out.println("currentColor"+currentColor+":"+new Date());
            }
        },0,2000);
    }
    public void drawCircle()
    {
        setContentView(R.layout.activity_draw);
    }
    public void  activity_images()
    {
        setContentView(R.layout.activity_images);
        LinearLayout main=(LinearLayout)findViewById(R.id.root);
        final ImageView image=new ImageView(this);
        main.addView(image);
        image.setImageResource(images[0]);
        image.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View v)
            {
                image.setImageResource(images[++currentImg % images.length]);
            }
        });
    }
}
