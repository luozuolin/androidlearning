package com.example.luozl.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.Toast;

/**
 * Created by luozl on 2017/12/15.
 */

public class GestureActivity extends AppCompatActivity implements GestureDetector.OnGestureListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //file();
        gesturetest();
    }
    GestureDetector  detector;
    private  void  gesturetest()
    {
        setContentView(R.layout.gesturetest);
        detector=new GestureDetector(this,this);
    }


    @Override
    public boolean onTouchEvent(MotionEvent me)
    {
        return detector.onTouchEvent(me);
    }
    @Override
    public boolean onDown(MotionEvent e) {
        Toast.makeText(this,"onDown",Toast.LENGTH_SHORT).show();
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {
        Toast.makeText(this,"onShowPress",Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        Toast.makeText(this,"onScroll",Toast.LENGTH_SHORT).show();
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {
        Toast.makeText(this,"onLongPress",Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        Toast.makeText(this,"onFling",Toast.LENGTH_SHORT).show();
        return false;
    }
}
