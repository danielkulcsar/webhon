package com.example.customwidget;

import android.app.*;
import android.graphics.*;
import android.os.*;
import android.view.*;
import android.widget.*;

public class MainActivity extends Activity 
        implements CustomView.EventListener {
    CustomView customView;
    TextView mTextView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        customView = (CustomView)findViewById(R.id.customView);
        customView.setBackColor(Color.MAGENTA);
        customView.setListener(this);
        mTextView = (TextView)findViewById(R.id.textView1);
    }

    public void onTouch(int x, int y) {
        if( mTextView != null ) {
            mTextView.setText("Touch : " + x + " / " + y);
        }
    }
    
}
