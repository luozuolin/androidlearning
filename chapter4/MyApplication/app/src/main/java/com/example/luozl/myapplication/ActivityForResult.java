package com.example.luozl.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by luozl on 2017/11/15.
 */

public class ActivityForResult extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityforresult();
    }
    EditText  city;
    public void activityforresult()
    {
        setContentView(R.layout.activityforresult);
        Button  btn=(Button)findViewById(R.id.activityforresultbtn1);
        city=(EditText)findViewById(R.id.activityforresultcity);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ActivityForResult.this,
                        SelectCityActivity.class);
                startActivityForResult(intent,0);
            }
        });
    }
    @Override
    public void onActivityResult(int requestCode,int resultCode,Intent intent)
    {
        if(requestCode==0 && resultCode==0)
        {
            Bundle data=intent.getExtras();
            String  resultCity=data.getString("city");
            city.setText(resultCity);
        }

    }
}
