package com.example.luozl.myapplication;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

/**
 * Created by luozl on 2017/12/13.
 */

public class DBTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //file();
        db();
    }
    SQLiteDatabase db;
    Button  bn=null;
    ListView listView;
    private     void db()
    {
        setContentView(R.layout.db);
        db=SQLiteDatabase.openOrCreateDatabase(
                this.getFilesDir().toString()+"/my.db3",null
        );
        listView=(ListView)findViewById(R.id.dblistview1);
        bn=(Button)findViewById(R.id.dbinsert);
        bn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title=((EditText)findViewById(R.id.dbtitle)).getText().toString();
                String content=((EditText)findViewById(R.id.dbcontent)).getText().toString();
                try
                {
                    insertData(db,title,content);
                    Cursor cursor=db.rawQuery("select * from news_inf",null);
                    inflateList(cursor);
                }catch (SQLiteException ex)
                {
                   db.execSQL("create  table news_inf(_id integer" +
                           " primary key autoincrement," +
                           " news_title varchar(50)," +
                           " news_content varchar(255))");
                    insertData(db,title,content);
                    Cursor cursor=db.rawQuery("select * from  news_inf",null);
                    inflateList(cursor);
                }
            }
        });
    }
    private  void insertData(SQLiteDatabase db,String title,String content)
    {
        db.execSQL("insert into news_inf values(null,?,?)",new String[]{title,content});
    }
    private void inflateList(Cursor cursor)
    {
        SimpleCursorAdapter simpleCursorAdapter=new SimpleCursorAdapter(
              DBTestActivity.this,R.layout.dbline1,cursor,
                new String[]{"news_title","news_content"}
                ,new int[]{R.id.my_title,R.id.my_content},
                CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        listView.setAdapter(simpleCursorAdapter);
    }
    @Override
    public   void onDestroy()
    {
        super.onDestroy();
        if(db!=null && db.isOpen())
        {
            db.close();
        }
    }
}
