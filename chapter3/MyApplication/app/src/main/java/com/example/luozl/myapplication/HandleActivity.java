package com.example.luozl.myapplication;

import android.app.ProgressDialog;
import android.content.Context;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.IntegerRes;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by luozl on 2017/11/13.
 */

public class HandleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //handle();
      downtasks();
    }
    private TextView show;
    public void   downtasks()
    {
        setContentView(R.layout.downtask);
        show=(TextView)findViewById(R.id.downtasktext);
    }
    public   void download(View source) throws MalformedURLException
    {
        DownTask task=new DownTask(this);
        task.execute(new URL("http://172.28.34.72:8081/nexus/content/groups/public/com/qb/platform/com-qb-platform-system-mysql/maven-metadata.xml"));
    }
    class  DownTask extends AsyncTask<URL,Integer,String>
    {
        ProgressDialog  pdialog;
        int hasRead=0;
        Context mContext;
        public DownTask(Context context)
        {
            mContext=context;
        }
        @Override
        protected String doInBackground(URL... params) {
            StringBuilder sb=new StringBuilder();
            try
            {
                URLConnection  conn=params[0].openConnection();
                BufferedReader br=new BufferedReader(new InputStreamReader(conn.getInputStream(),"utf-8"));
                String line=null;
                while((line=br.readLine())!=null)
                {
                    sb.append(line+"\n");
                    Thread.sleep(100);
                    hasRead++;
                    publishProgress(hasRead);
                }
                return sb.toString();
            }catch (Exception  ex)
            {
                ex.printStackTrace();
            }
            return null;
        }
        @Override
        protected   void onPostExecute(String  result)
        {
            show.setText(result);
            pdialog.dismiss();
        }
        @Override
        protected void onPreExecute()
        {
            pdialog=new ProgressDialog(mContext);
            pdialog.setTitle("任务正在执行中");
            pdialog.setMessage("任务正在执行中，请等待。。。");
            pdialog.setCancelable(false);
            pdialog.setMax(202);
            pdialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            pdialog.setIndeterminate(false);
            pdialog.show();
        }
        @Override
        protected  void onProgressUpdate(Integer... values)
        {
            show.setText("已经读取了【"+values[0]+"】行！");
            pdialog.setProgress(values[0]);
        }
    }
    int currentImageId=0;
    public   void handle()
    {

        setContentView(R.layout.handle);
        final int[] imageids=new int[]
                {
                        R.drawable.back,
                        R.drawable.ic_launcher,
                        R.drawable.plane
                };

        final ImageView show=(ImageView)findViewById(R.id.handleImageView);
        final Handler myhandle=new Handler()
        {
            @Override
            public void handleMessage(Message msg)
            {
                if(msg.what==0x1233)
                {
                    show.setImageResource(imageids[currentImageId++ % imageids.length]);
                }
            }
        };
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                myhandle.sendEmptyMessage(0x1233);
            }
        },0,1200);
    }
}
