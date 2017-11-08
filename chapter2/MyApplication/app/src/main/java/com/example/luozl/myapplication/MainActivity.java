package com.example.luozl.myapplication;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.hardware.camera2.CameraManager;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.IdRes;
import android.support.annotation.IntegerRes;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.menu.SubMenuBuilder;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.ViewGroup;
import android.view.textservice.TextServicesManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RadioGroup;
import android.widget.SimpleAdapter;
import android.widget.TabHost;
import android.widget.TableLayout;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ViewFlipper;
import android.widget.ViewSwitcher;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Calendar;
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
    @Override
    public void onCreate(Bundle savedInstanceState) {
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
        // viewflipper();
        //toast();
        //  calendar();
       //    searchview();
      //  notification();
       // dialog();
        //processdialog();
        showmenu();
    }
    public void showmenu()
    {
        setContentView(R.layout.menu);
        editText=(EditText)findViewById(R.id.menuedit);
    }
    final int FONT_10=0x111;
    final int FONT_12=0x112;
    final int FONT_14=0x113;
    final int FONT_16=0x114;
    final int FONT_18=0x115;

    final int PLAIN_ITEM=0x11b;
    final  int FONT_RED=0x116;
    final  int FONT_BLUR=0x117;
    final  int FONT_GREEN=0x118;
    private EditText editText;
    @Override
     public boolean onCreateOptionsMenu(Menu menu)
    {
        SubMenu fontMenu=menu.addSubMenu("字体大小");
        fontMenu.setIcon(R.drawable.error);
        fontMenu.setHeaderTitle("选择字体大小");
        fontMenu.add(0,FONT_10,0,"10号字体");
        fontMenu.add(0,FONT_12,0,"12号字体");
        fontMenu.add(0,FONT_14,0,"14号字体");
        fontMenu.add(0,FONT_16,0,"16号字体");
        fontMenu.add(0,FONT_18,0,"18号字体");
        menu.add(0,PLAIN_ITEM,0,"普通菜单项");
        SubMenu colorMenu=menu.addSubMenu("字体颜色");
        colorMenu.setHeaderTitle("选择字体颜色");
        colorMenu.add(0,FONT_RED,0,"红色");
        colorMenu.add(0,FONT_GREEN,0,"绿色");
        colorMenu.add(0,FONT_BLUR,0,"蓝色");
        SubMenu prog=menu.addSubMenu("启动程序");
        prog.setHeaderTitle("选择您要启动的程序");
        MenuItem menuItem=prog.add("查看Swift");
        menuItem.setIntent(new Intent(this,OtherActivity.class));
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem mi)
    {
        switch (mi.getItemId())
        {
            case FONT_10:
                editText.setTextSize(10*2);
                break;
            case FONT_12:
                editText.setTextSize(12*2);
                break;
            case FONT_14:
                editText.setTextSize(14*2);
                break;
            case FONT_16:
                editText.setTextSize(16*2);
                break;
            case FONT_18:
                editText.setTextSize(18*2);
                break;
            case FONT_RED:
                editText.setTextColor(Color.RED);
                break;
            case FONT_GREEN:
                editText.setTextColor(Color.GREEN);
                break;
            case FONT_BLUR:
                editText.setTextColor(Color.BLUE);
                break;
            case PLAIN_ITEM:
               Toast toast=Toast.makeText(MainActivity.this,
                       "您单击了普通的菜单项",Toast.LENGTH_SHORT);
                toast.show();
                break;
        }
        return  true;
    }
    int[] images = new int[]{
            R.drawable.bg_top,
            R.drawable.error,
            R.drawable.info_up,
            R.drawable.info
    };
    int currentImg = 0;
    public void processdialog()
    {
        setContentView(R.layout.progress);
    }
    final static int MAX_PROGRESS=100;
    private int[] data=new int[50];
    int processStatus=0;
    int hasData=0;
    ProgressDialog pd1,pd2;
    Handler handlerProcess=new Handler()
    {
        @Override
        public  void handleMessage(Message msg)
        {
            if(msg.what==0x123)
            {
                pd2.setProgress(processStatus);
            }
        }
    };
    public void    showSpinner(View v)
    {
        ProgressDialog.show(this,"任务执行中","任务执行中，请等待",false,true);
    }
    public void showIndeterminate(View v)
    {
        pd1=new ProgressDialog(MainActivity.this);
        pd1.setTitle("任务正在执行中");
        pd1.setMessage("任务正在执行中，请稍等");
        pd1.setCancelable(true);
        pd1.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        pd1.setIndeterminate(true);
        pd1.show();
    }
    public void showProgress(View e)
    {
        processStatus=0;
        hasData=0;
        pd2=new ProgressDialog(MainActivity.this);
        pd2.setMax(MAX_PROGRESS);
        pd2.setTitle("任务完成百分比");
        pd2.setMessage("好事任务的完成百分比");
        pd2.setCancelable(false);
        pd2.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        pd2.setIndeterminate(false);
        pd2.show();
        new Thread()
        {
            public  void run()
            {
                while (processStatus<MAX_PROGRESS)
                {
                    processStatus=MAX_PROGRESS*doWork()/data.length;
                    handlerProcess.sendEmptyMessage(0x123);
                }
                if(processStatus>=MAX_PROGRESS)
                {
                    pd2.dismiss();
                }
            }
        }.start();
    }
    public  int doWork()
    {
        data[hasData++]=(int)(Math.random()*100);
        try
        {
            Thread.sleep(100);
        }catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        return  hasData;
    }
    public   void dateset(View v)
    {
        Calendar  c=Calendar.getInstance();
        new DatePickerDialog(MainActivity.this,
                new DatePickerDialog.OnDateSetListener(){

                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        dialogshow.setText("您选择了："+year+"-"+month+"-"+dayOfMonth);
                    }
                },c.get(Calendar.YEAR),c.get(Calendar.MONTH),c.get(Calendar.DAY_OF_MONTH)).show();
    }
    public   void timeset(View v)
    {
        Calendar  c=Calendar.getInstance();
        new TimePickerDialog(MainActivity.this,
                new TimePickerDialog.OnTimeSetListener(){

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        dialogshow.setText("您选择了："+hourOfDay+"-"+minute);
                    }

                },c.get(Calendar.HOUR_OF_DAY),c.get(Calendar.MINUTE),true).show();
    }
    public   void popupwindow(View v)
    {
        popupWindow.showAtLocation(findViewById(R.id.dialogpopupbtn),Gravity.CENTER,20,20);

    }
    public  void customView(View v)
    {
        TableLayout loginForm=(TableLayout)getLayoutInflater().inflate(R.layout.logform,null);
        new AlertDialog.Builder(this)
                .setIcon(R.drawable.error)
                .setTitle("自定义View对话框")
                .setView(loginForm)
                .setPositiveButton("登录", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialogshow.setText("你选中了《登录》");
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialogshow.setText("你选中了《取消》");
                    }
                }).create()
                .show();
    }
   TextView  dialogshow;
    View root;
     PopupWindow popupWindow;
    public void dialog()
    {
        setContentView(R.layout.dialog);
        dialogshow=(TextView)findViewById(R.id.dialogshow);


         root=this.getLayoutInflater().inflate(R.layout.popup,null);
         popupWindow=new PopupWindow(root,560,720);
        root.findViewById(R.id.close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });

    }
    public   void multiChoice(View v)
    {
        final String[] items={
                "招商银行",
                "建设银行",
                "民生银行"
        };
        AlertDialog.Builder builder=new AlertDialog.Builder(this)
                .setTitle("单选列表项对话框")
                .setIcon(R.drawable.error)
                .setMultiChoiceItems(items,new boolean[]{false,true,false,false}, null);
        setPositiveButton(builder);
        setNegativeButton(builder).create().show();
    }
    public void singleChoice(View v)
    {
        final String[] items={
                "招商银行",
                "建设银行",
                "民生银行"
        };
        AlertDialog.Builder builder=new AlertDialog.Builder(this)
                .setTitle("单选列表项对话框")
                .setIcon(R.drawable.error)
                .setSingleChoiceItems(items,1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialogshow.setText("你选中了《"+items[which]+"》");
                    }
                });
        setPositiveButton(builder);
        setNegativeButton(builder).create().show();
    }
    public void simpleList(View v)
    {
        final String[] items={
                "招商银行",
                "建设银行",
                "民生银行"
        };
        AlertDialog.Builder builder=new AlertDialog.Builder(this)
                .setTitle("简单列表项对话框")
                .setIcon(R.drawable.error)
                .setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialogshow.setText("你选中了《"+items[which]+"》");
                    }
                });
        setPositiveButton(builder);
        setNegativeButton(builder).create().show();
    }
      public void simple(View v)
     {
         AlertDialog.Builder builder=new AlertDialog.Builder(this)
                 .setTitle("简单对话框")
                 .setIcon(R.drawable.error)
                 .setMessage("对话框测试内容\n第二行");
         setPositiveButton(builder);
         setNegativeButton(builder).create().show();

     }
     public AlertDialog.Builder setPositiveButton(AlertDialog.Builder builder)
     {
         return builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
             @Override
             public void onClick(DialogInterface dialog, int which) {
                 dialogshow.setText("点击了【确定】按钮！");
             }
         });
     }
     public AlertDialog.Builder setNegativeButton(AlertDialog.Builder builder)
     {
         return builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
             @Override
             public void onClick(DialogInterface dialog, int which) {
                 dialogshow.setText("点击了【取消】按钮！");
             }
         });
     }
    static final int NOTIFICATION_ID=0x123;
    //消息提醒
    NotificationManager notificationManager;
    public   void  notification()
    {
        setContentView(R.layout.notification);
         notificationManager=(NotificationManager)getSystemService(NOTIFICATION_SERVICE);
    }
    public   void send(View source)
    {
        Intent intent=new Intent(MainActivity.this,OtherActivity.class);
        PendingIntent pi=PendingIntent.getActivity(MainActivity.this,0,intent,0);
        Notification notification=new Notification.Builder(this)
                .setAutoCancel(true)
                .setTicker("有新消息")
                .setSmallIcon(R.drawable.error)
                .setContentTitle("一条新消息")
                .setContentText("恭喜，来了条新消息")
                .setWhen(System.currentTimeMillis())
                .setContentIntent(pi)
                .build();
        notificationManager.notify(NOTIFICATION_ID,notification);

    }
    public void del(View v)
    {
        notificationManager.cancel(NOTIFICATION_ID);
    }

    public void searchview()
    {
        String[] mstrings={"aaaa","bbbasas","cdcscs"};
        setContentView(R.layout.searchview);
         SearchView searchView=(SearchView)findViewById(R.id.searchView);
        final  ListView lv=(ListView)findViewById(R.id.lv);
        lv.setAdapter(new ArrayAdapter<String>(this,R.layout.array_item,mstrings));
        lv.setTextFilterEnabled(true);
        searchView.setIconifiedByDefault(false);
        searchView.setSubmitButtonEnabled(true);
        searchView.setQueryHint("查找");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener()
        {

            @Override
            public boolean onQueryTextSubmit(String query) {
                Toast.makeText(MainActivity.this,"您选择的是："+query,Toast.LENGTH_SHORT).show();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if(TextUtils.isEmpty(newText))
                {
                    lv.clearTextFilter();
                }
                else
                {
                    lv.setFilterText(newText);
                }
                return true;
            }
        });




    }
    public void calendar()
    {
        setContentView(R.layout.calendar);
        CalendarView calendarView=(CalendarView)findViewById(R.id.calendarView);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                Toast.makeText(MainActivity.this,
                        "你的生日是："+year+"年" +month+"月" +dayOfMonth+"日",Toast.LENGTH_SHORT).show();
            }
        });
    }
    public  void  toast()
    {
        setContentView(R.layout.toast);
        Button  btn1=(Button)findViewById(R.id.btn1);
        Button btn2=(Button)findViewById(R.id.btn2);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast=Toast.makeText(MainActivity.this,"简单的提示信息",Toast.LENGTH_SHORT);
                toast.show();
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast=new Toast(MainActivity.this);
                toast.setGravity(Gravity.CENTER,0,0);
                ImageView image=new ImageView(MainActivity.this);
                image.setImageResource(R.drawable.error);
                LinearLayout linearLayout=new LinearLayout(MainActivity.this);
                linearLayout.addView(image);
                TextView textView=new TextView(MainActivity.this);
                textView.setText("带图片的提示信息!");
                textView.setTextSize(24);
                textView.setTextColor(Color.MAGENTA);
                linearLayout.addView(textView);
                toast.setView(linearLayout);
                toast.setDuration(Toast.LENGTH_SHORT);
                toast.show();
            }
        });
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
