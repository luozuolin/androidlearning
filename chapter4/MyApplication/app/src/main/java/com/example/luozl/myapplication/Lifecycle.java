package com.example.luozl.myapplication;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by luozl on 2017/11/15.
 */

public class Lifecycle extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityforresult();
    }
}
