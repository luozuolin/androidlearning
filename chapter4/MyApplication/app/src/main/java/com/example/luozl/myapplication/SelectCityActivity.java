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

public class SelectCityActivity extends Activity {
     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         setContentView(R.layout.selectcityactivity);
         Button  btn=(Button)findViewById(R.id.selectcityactivitybtn);
         btn.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent intent=getIntent();
                 intent.putExtra("city",((EditText)findViewById(R.id.selectcityactivitytxt)).getText().toString());
                 SelectCityActivity.this.setResult(0,intent);
                 SelectCityActivity.this.finish();
             }
         });
         //activityforresult();
    }
}
