package com.example.luozl.myapplication;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.net.FileNameMap;

/**
 * Created by luozl on 2017/12/11.
 */

public class SDFileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sdfile();
    }
    final    String FILE_NAME="/crazyit.txt";
    public   void sdfile()
    {
        setContentView(R.layout.sdfile);
        Button    read=(Button)findViewById(R.id.btnsdread);
        Button  write=(Button)findViewById(R.id.btnsdwrite);
        final EditText edit1=(EditText)findViewById(R.id.editsd1);
        final EditText edit2=(EditText)findViewById(R.id.editsd2);
        write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                write(edit1.getText().toString());
                edit1.setText("");
            }
        });
        read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit2.setText(read());
            }
        });

    }
    private   String   read()
    {
        try
        {
            if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED))
            {
                File sdCardDir=Environment.getExternalStorageDirectory();
                FileInputStream fileInputStream=new FileInputStream(sdCardDir.getCanonicalPath()+FILE_NAME);
                Toast.makeText(SDFileActivity.this,sdCardDir.getCanonicalPath()+FILE_NAME,Toast.LENGTH_SHORT).show();
                BufferedReader br=new BufferedReader(new InputStreamReader(fileInputStream));
                StringBuilder sb=new StringBuilder("");
                String  line=null;
                while ((line=br.readLine())!=null)
                {
                    sb.append(line);
                }
                br.close();
                return    sb.toString();
            }

        }catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return   null;
    }
    private   void write(String content)
    {
        try
        {
            if (Build.VERSION.SDK_INT >= 23) {
                int REQUEST_CODE_CONTACT = 101;
                String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
                //验证是否许可权限
                for (String str : permissions) {
                    if (this.checkSelfPermission(str) != PackageManager.PERMISSION_GRANTED) {
                        //申请权限
                        this.requestPermissions(permissions, REQUEST_CODE_CONTACT);
                        return;
                    }
                }
            }
            if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED))
            {
                File sdCardDir=Environment.getExternalStorageDirectory();
                File targetFile=new File(sdCardDir.getCanonicalPath()+FILE_NAME);
                RandomAccessFile randomAccessFile=new RandomAccessFile(targetFile,"rw");
                randomAccessFile.seek(targetFile.length());
                randomAccessFile.write(content.getBytes());
                randomAccessFile.close();
            }
        }catch (Exception  ex)
        {
            ex.printStackTrace();
        }
    }
}
