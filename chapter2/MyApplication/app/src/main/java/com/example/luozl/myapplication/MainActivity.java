package com.example.luozl.myapplication;

import android.app.Activity;
import android.app.Notification;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.textservice.TextServicesManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.SimpleAdapter;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewFlipper;
import android.widget.ViewSwitcher;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.SimpleTimeZone;
import java.util.StringTokenizer;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by luozl on 2017/11/6.
 */

public class MainActivity extends AppCompatActivity {
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
       //setContentView(R.layout.activity_main);
        //activity_images()
        //drawCircle();
       // setContentView(R.layout.table);
      //  frames();
        //grid();
       // edittext();
     //   button();
       // checkbutton();
        //listview();
       // arrayadapter();
       //imageswitcher();
        //textswitcher();
       viewflipper();
    }
    private ViewFlipper viewFlipper;
    public void viewflipper()
    {
        setContentView(R.layout.viewflipper);
        viewFlipper=(ViewFlipper)findViewById(R.id.viewflipper);
    }
    public void   prev1(View source)
    {
        viewFlipper.setInAnimation(this,R.anim.slide_in_right);
        viewFlipper.setOutAnimation(this,R.anim.slide_out_left);
        viewFlipper.showPrevious();
        viewFlipper.stopFlipping();
    }
    public void   next1(View source)
    {
        viewFlipper.setInAnimation(this,R.anim.slide_in_left);
        viewFlipper.setOutAnimation(this,R.anim.slide_out_right);
        viewFlipper.showNext();
        viewFlipper.stopFlipping();
    }
    public void   auto(View source)
    {
        viewFlipper.setInAnimation(this,R.anim.slide_out_left);
        viewFlipper.setOutAnimation(this,R.anim.slide_in_right);
        viewFlipper.startFlipping();
    }
     TextSwitcher textSwitcher;
    String[] strs=new String[]
            {
                    "疯狂Java讲义",
                    "疯狂Android讲义",
                    "疯狂XML讲义",
                    "疯狂Spring讲义"
            };
    public   void textswitcher()
    {
        setContentView(R.layout.textswitcher);
        textSwitcher=(TextSwitcher)findViewById(R.id.textswitcher);
        textSwitcher.setFactory(new ViewSwitcher.ViewFactory(){
            @Override
            public View makeView() {
                TextView textView=new TextView(MainActivity.this);
                textView.setTextSize(40);
                textView.setTextColor(Color.MAGENTA);
                return textView;
            }
        });
      next(null);
    }
    int curStr=0;
    public   void   next(View source)
    {
        textSwitcher.setText(strs[curStr++ % strs.length]);
    }
    public   void imageswitcher()
    {
        setContentView(R.layout.imageswitcher);
        final int[] imageids=new int[]
                {
                        R.drawable.scrubber_disabled,R.drawable.scrubber_focussed,
                        R.drawable.scrubber_normal,R.drawable.scrubber_pressed
                };
        final ImageSwitcher switcher=(ImageSwitcher)findViewById(R.id.switcher);
        List<Map<String,Object>> list=new ArrayList<Map<String, Object>>();
        for (int i=0;i<imageids.length;i++)
        {
            Map<String,Object> listitem=new HashMap<String,Object>();
            listitem.put("image",imageids[i]);
            list.add(listitem);
        }
        switcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                ImageView imageView=new ImageView(MainActivity.this);
                imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                imageView.setLayoutParams(new ImageSwitcher.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                return  imageView;
            }
        });
        final SimpleAdapter simpleAdapter=new SimpleAdapter(this,list,R.layout.cell,new String[]{"image"},new int[]{R.id.image1});
        GridView gridView=(GridView)findViewById(R.id.grid01);
        gridView.setAdapter(simpleAdapter);
        gridView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switcher.setImageResource(imageids[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switcher.setImageResource(imageids[position]);
            }
        });
    }
    public   void arrayadapter()
    {
        setContentView(R.layout.arrayadapter);
        ListView list1=(ListView)findViewById(R.id.list1);
        ListView list2=(ListView)findViewById(R.id.list2);
        String[] arr1=new String[]{"孙悟空","猪八戒","沙悟净"};
        String[] arr2=new String[]{"Java","XML","Android","Spring"};
        ArrayAdapter<String> adapter1=new ArrayAdapter<String>(this,R.layout.array_item,arr1);
        list1.setAdapter(adapter1);
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
