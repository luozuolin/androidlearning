package com.example.luozl.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by luozl on 2017/12/13.
 */

public class SDFileExplorerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.sdfileexplorer);
        explorer();
    }
    File  currentParent;
    File[] currentFiles;
    ListView listView;
    TextView textView;
    private   void explorer()
    {
        setContentView(R.layout.sdfileexplorer);
         listView=(ListView)findViewById(R.id.sdfileexplorerlist);
         textView=(TextView)findViewById(R.id.sdfileexplorerpath);
        File root=new File("/mnt/sdcard");
        if(root.exists())
        {
            currentFiles=root.listFiles();
            currentParent=root;
            inflateListView(currentFiles);
        }
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(currentFiles[position].isFile())
                     return;
                File[] tmp=currentFiles[position].listFiles();
                if(tmp==null || tmp.length==0)
                {
                    Toast.makeText(SDFileExplorerActivity.this, "当前路径不可访问或当前路径下没有文件", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    currentParent=currentFiles[position];
                    currentFiles=tmp;
                    inflateListView(currentFiles);
                }
            }
        });

        Button  parent=(Button)findViewById(R.id.sdfileexplorerparent);
        parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try
                {
                    if(!currentParent.getCanonicalPath().equals("/mnt/sdcard"))
                    {
                        currentParent=currentParent.getParentFile();
                        currentFiles=currentParent.listFiles();
                        inflateListView(currentFiles);
                    }
                }catch (Exception ex)
                {
                    ex.printStackTrace();
                }
            }
        });
    }
    private   void    inflateListView(File[] files)
    {
        List<Map<String,Object>> listItems=new ArrayList<Map<String,Object>>();
        for(int i=0;i<files.length;i++)
        {
            Map<String ,Object> listItem=new HashMap<String,Object>();
            if(files[i].isDirectory())
            {
                listItem.put("icon",R.drawable.folder);
            }else
            {
                listItem.put("icon",R.drawable.file);
            }
            listItem.put("fileName",files[i].getName());
            listItems.add(listItem);
        }
        SimpleAdapter   simpleAdapter=new SimpleAdapter(this,listItems,R.layout.line,
                new String[]{"icon","fileName"}
               ,new int[]{R.id.icon,R.id.file_name});
        listView.setAdapter(simpleAdapter);
        try
        {
            textView.setText("当前路径为："+currentParent.getCanonicalPath());
        }catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
