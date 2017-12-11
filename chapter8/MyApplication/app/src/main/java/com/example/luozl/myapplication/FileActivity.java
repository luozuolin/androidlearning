package com.example.luozl.myapplication;

import android.os.Bundle;
import android.os.DropBoxManager;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ButtonBarLayout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintStream;

/**
 * Created by luozl on 2017/12/11.
 */

public class FileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      file();
    }
    final String FILE_NAME="crazyit.bin";
    public   void file()
    {
        setContentView(R.layout.file);
        System.out.println(new StringBuilder("a").append("b").append("c").toString());
        Button read=(Button)findViewById(R.id.fileread);
        final Button write=(Button)findViewById(R.id.filewrite);

        final EditText edit1=(EditText)findViewById(R.id.fileedit1);
        final EditText edit2=(EditText)findViewById(R.id.fileedit2);

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
    private  String   read()
    {
        try{
            FileInputStream fileInputStream=openFileInput(FILE_NAME);
            byte[]  buff=new byte[1024];
            int hasRead=0;
            StringBuilder sb=new StringBuilder();
            while ((hasRead=fileInputStream.read(buff))>0)
            {
                sb.append(new String(buff,0,hasRead));
            }
            fileInputStream.close();
            return sb.toString();
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return   null;
    }
    private void write(String  content)
    {
        try{
            FileOutputStream fileOutputStream=openFileOutput(FILE_NAME,MODE_APPEND);
            PrintStream printStream=new PrintStream(fileOutputStream);
            printStream.println(content);
            printStream.close();

        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
